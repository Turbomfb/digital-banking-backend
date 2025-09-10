/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.customer.service.impl;

import com.techservices.digitalbanking.common.domain.enums.UserType;
import com.techservices.digitalbanking.core.domain.BaseAppResponse;
import com.techservices.digitalbanking.core.domain.data.model.CustomerStatusAudit;
import com.techservices.digitalbanking.core.domain.data.repository.CustomerStatusAuditRepository;
import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.core.domain.dto.request.NotificationRequestDto;
import com.techservices.digitalbanking.core.domain.dto.request.OtpDto;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.enums.AccountType;
import com.techservices.digitalbanking.core.domain.enums.AlertType;
import com.techservices.digitalbanking.core.domain.enums.CustomerStatusAuditType;
import com.techservices.digitalbanking.core.domain.enums.OtpType;
import com.techservices.digitalbanking.core.exception.AbstractPlatformResourceNotFoundException;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.core.fineract.service.AccountService;
import com.techservices.digitalbanking.core.redis.service.RedisService;
import com.techservices.digitalbanking.core.service.AlertPreferenceService;
import com.techservices.digitalbanking.core.util.AppUtil;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import com.techservices.digitalbanking.customer.domian.data.repository.CustomerRepository;
import com.techservices.digitalbanking.customer.domian.dto.request.CustomerAccountClosureRequest;
import com.techservices.digitalbanking.customer.domian.dto.request.CustomerTransactionPinRequest;
import com.techservices.digitalbanking.customer.domian.dto.response.CustomerDashboardResponse;
import com.techservices.digitalbanking.customer.domian.dto.response.CustomerDtoResponse;
import com.techservices.digitalbanking.core.fineract.model.response.*;
import com.techservices.digitalbanking.core.fineract.service.ClientService;
import com.techservices.digitalbanking.customer.domian.dto.request.CreateCustomerRequest;
import com.techservices.digitalbanking.customer.domian.dto.request.CustomerUpdateRequest;
import com.techservices.digitalbanking.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.techservices.digitalbanking.core.util.AppUtil.normalizePhoneNumber;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

	private final ClientService clientService;
	private final AccountService accountService;
	private final CustomerRepository customerRepository;
	private final RedisService redisService;
	private final PasswordEncoder passwordEncoder;
	private final CustomerStatusAuditRepository customerStatusAuditRepository;
	private final AlertPreferenceService alertPreferenceService;

	@Override
	public BaseAppResponse createCustomer(CreateCustomerRequest createCustomerRequest, String command, UserType customerType) {
		if ("generate-otp".equalsIgnoreCase(command)) {
			createCustomerRequest.setCustomerType(customerType != null ? customerType : UserType.RETAIL);
			validateCreateCustomer(createCustomerRequest, customerType);
			log.info("Generating otp");
			if (StringUtils.isNotBlank(createCustomerRequest.getTransactionPin()) && createCustomerRequest.getTransactionPin().length() < 4) {
				throw new ValidationException("transaction.pin.length", "Transaction pin must be at least 4 characters long.");
			}
			validateDuplicateCustomer(createCustomerRequest, createCustomerRequest.getCustomerType());
			NotificationRequestDto notificationRequestDto = new NotificationRequestDto(createCustomerRequest.getPhoneNumber(), createCustomerRequest.getEmailAddress());
			OtpDto otpDto = this.redisService.generateOtpRequest(createCustomerRequest, OtpType.ONBOARDING, notificationRequestDto, null);
			return new GenericApiResponse(otpDto.getUniqueId(), "We sent an OTP to "+ AppUtil.maskPhoneNumber(createCustomerRequest.getPhoneNumber())+" and "+AppUtil.maskEmailAddress(createCustomerRequest.getEmailAddress()), "success", null);
		} else if ("verify-otp".equalsIgnoreCase(command)) {
			String uniqueId = createCustomerRequest.getUniqueId();
			OtpDto otpDto = this.redisService.validateOtpWithoutDeletingRecord(uniqueId, createCustomerRequest.getOtp(), OtpType.ONBOARDING);
			createCustomerRequest = (CreateCustomerRequest) otpDto.getData();
			BaseAppResponse response = this.completeCustomerRegistration(createCustomerRequest);
			this.redisService.delete(uniqueId);
			return response;
		}
		else {
			throw new ValidationException("invalid.command", "Invalid command: " + command);
		}
	}

	private static void validateCreateCustomer(CreateCustomerRequest createCustomerRequest, UserType customerType) {
		if (StringUtils.isNotBlank(createCustomerRequest.getPhoneNumber())) {
			createCustomerRequest.setPhoneNumber(normalizePhoneNumber(createCustomerRequest.getPhoneNumber()));
		}
		if (customerType == UserType.RETAIL) {
			if (StringUtils.isBlank(createCustomerRequest.getEmailAddress()) && StringUtils.isBlank(createCustomerRequest.getPhoneNumber())) {
				throw new ValidationException("email.or.phone.required", "Either email address or phone number is required for retail customers.");
			}
			if (StringUtils.isNotBlank(createCustomerRequest.getBusinessName())) {
				throw new ValidationException("business.name.not.allowed", "Business name is not allowed for retail customers.");
			}
			if (StringUtils.isNotBlank(createCustomerRequest.getRcNumber())) {
				throw new ValidationException("rc.number.not.allowed", "RC number is not allowed for retail customers.");
			}
		} else if (customerType == UserType.CORPORATE) {
			if (StringUtils.isBlank(createCustomerRequest.getBusinessName())) {
				throw new ValidationException("business.name.required", "Business name is required for corporate customers.");
			}
			if (StringUtils.isBlank(createCustomerRequest.getRcNumber())) {
				throw new ValidationException("rc.number.required", "RC number is required for corporate customers.");
			}
		} else {
			throw new ValidationException("invalid.customer.type", "Invalid customer type provided.");
		}
	}

	private CustomerDtoResponse completeCustomerRegistration(CreateCustomerRequest createCustomerRequest) {
		boolean isInitialRegistration = isInitialRegistration(createCustomerRequest);
		PostClientsResponse postClientsResponse = isInitialRegistration ? new PostClientsResponse() : clientService.createCustomer(createCustomerRequest);

		Customer customer = buildCustomer(createCustomerRequest, postClientsResponse, isInitialRegistration);
		Customer savedCustomer = customerRepository.save(customer);
		alertPreferenceService.updatePreference(savedCustomer.getId(), AlertType.LOGIN, true, false, false);
		alertPreferenceService.updatePreference(savedCustomer.getId(), AlertType.TRANSACTION, true, false, false);
		return CustomerDtoResponse.parse(savedCustomer, clientService);
	}

	@Override
	public Customer updateCustomer(CustomerUpdateRequest customerUpdateRequest, Long customerId, Customer customer) {
		customer = customer == null ? getCustomerById(customerId) : customer;
		updateCustomerDetails(customerUpdateRequest, customer);
		return getCustomerById(customerId);
	}

	@Override
	public Customer getCustomerById(Long customerId) {
		return customerRepository.findById(customerId).orElseThrow(() ->
				new AbstractPlatformResourceNotFoundException("customer.not.found.with.id", "Customer not found with id: " + customerId, customerId)
		);
	}

	@Override
	public Optional<Customer> getCustomerByEmailAddressAndUserType(String emailAddress, UserType customerType) {
		return customerRepository.findByEmailAddressAndUserType(emailAddress, customerType);
	}

	@Override
	public Optional<Customer> getCustomerByPhoneNumberAndUserType(String phoneNumber, UserType customerType) {
		String normalizedPhone = normalizePhoneNumber(phoneNumber);
		return customerRepository.findByPhoneNumberAndUserType(normalizedPhone, customerType);
	}

	public Optional<Customer> getCustomerByBusinessName(String businessName) {
		return customerRepository.findByBusinessName(businessName);
	}

	public Optional<Customer> getCustomerByRcNumber(String rcNumber) {
		return customerRepository.findByRcNumber(rcNumber);
	}

	@Override
	public Optional<Customer> getCustomerByEmailAddress(String emailAddress) {
		return customerRepository.findByEmailAddress(emailAddress);
	}

	@Override
	public Optional<Customer> getCustomerByPhoneNumber(String phoneNumber) {
		String normalizedPhone = normalizePhoneNumber(phoneNumber);
		return customerRepository.findByPhoneNumber(normalizedPhone);
	}

	@Override
	public BasePageResponse<CustomerDtoResponse> getAllCustomers(Pageable pageable) {
		return BasePageResponse.instance(customerRepository.findAll(pageable).map(CustomerDtoResponse::parse));
	}

	@Override
	public GetClientsClientIdAccountsResponse getClientAccountsByClientId(Long customerId, String accountType) {
		String externalId = this.retrieveCustomerExternalId(customerId);
		return clientService.getClientAccountsByClientId(externalId, accountType);
	}

	private String retrieveCustomerExternalId(Long customerId) {
		return getCustomerById(customerId).getExternalId();
	}

	@Override
	public CustomerDashboardResponse retrieveCustomerDashboard(Long customerId) {
		String externalId = this.retrieveCustomerExternalId(customerId);
		GetClientsClientIdAccountsResponse customerAccounts = clientService.getClientAccountsByClientId(externalId, null);
		CustomerDashboardResponse customerDashboardResponse = CustomerDashboardResponse.builder()
				.walletAccount(
						new CustomerDashboardResponse.Account(
								customerAccounts.getTotalAccountBalanceFor(AccountType.SAVINGS),
								customerAccounts.getTotalAccountInterestsFor(AccountType.SAVINGS, accountService),
								customerAccounts.getTotalAccountDepositsFor(AccountType.SAVINGS, accountService),
								customerAccounts.getTotalAccountWithdrawalsFor(AccountType.SAVINGS, accountService),
								customerAccounts.getTotalActivePlanFor(AccountType.SAVINGS, accountService)
						)
				)
				.flexAccount(
						new CustomerDashboardResponse.Account(
								customerAccounts.getTotalAccountBalanceFor(AccountType.RECURRING_DEPOSIT),
								customerAccounts.getTotalAccountInterestsFor(AccountType.RECURRING_DEPOSIT, accountService),
								customerAccounts.getTotalAccountDepositsFor(AccountType.RECURRING_DEPOSIT, accountService),
								customerAccounts.getTotalAccountWithdrawalsFor(AccountType.RECURRING_DEPOSIT, accountService),
								customerAccounts.getTotalActivePlanFor(AccountType.RECURRING_DEPOSIT, accountService)
						)
				)
				.lockAccount(
						new CustomerDashboardResponse.Account(
								customerAccounts.getTotalAccountBalanceFor(AccountType.FIXED_DEPOSIT),
								customerAccounts.getTotalAccountInterestsFor(AccountType.FIXED_DEPOSIT, accountService),
								customerAccounts.getTotalAccountDepositsFor(AccountType.FIXED_DEPOSIT, accountService),
								customerAccounts.getTotalAccountWithdrawalsFor(AccountType.FIXED_DEPOSIT, accountService),
								customerAccounts.getTotalActivePlanFor(AccountType.FIXED_DEPOSIT, accountService)
						)
				)
				.build();
		customerDashboardResponse.setInvestmentMetrics(
				new CustomerDashboardResponse.Account(
						null,
						customerDashboardResponse.getFlexAccount().getTotalInterestEarned().add(customerDashboardResponse.getLockAccount().getTotalInterestEarned()),
						customerDashboardResponse.getFlexAccount().getTotalDeposit().add(customerDashboardResponse.getLockAccount().getTotalDeposit()),
						customerDashboardResponse.getFlexAccount().getTotalWithdrawal().add(customerDashboardResponse.getLockAccount().getTotalWithdrawal()),
						customerDashboardResponse.getFlexAccount().getTotalActivePlan() + customerDashboardResponse.getLockAccount().getTotalActivePlan()
				)
		);
		return customerDashboardResponse;
	}

	@Override
	public GenericApiResponse createTransactionPin(Long customerId, CustomerTransactionPinRequest customerTransactionPinRequest) {
		Customer foundCustomer = getCustomerById(customerId);
		if (StringUtils.isBlank(customerTransactionPinRequest.getPin())) {
			throw new ValidationException("transaction.pin.required", "pin field is required.");
		}
		if (StringUtils.isNotBlank(foundCustomer.getTransactionPin())){
			throw new ValidationException("transaction.pin.already.set", "Transaction pin has already been set for this customer. Kindly reset it.");
		}
		if (customerTransactionPinRequest.getPin().length() < 4) {
			throw new ValidationException("transaction.pin.length", "Transaction pin must be at least 4 characters long.");
		}
		foundCustomer.setTransactionPin(passwordEncoder.encode(customerTransactionPinRequest.getPin()));
		foundCustomer.setTransactionPinSet(true);
		customerRepository.save(foundCustomer);
		return new GenericApiResponse(null, "Transaction pin created successfully", "success", CustomerDtoResponse.parse(foundCustomer));
	}

	@Override
	public String getCustomerSavingsId(Long customerId) {
		Customer customer = this.getCustomerById(customerId);
		if (StringUtils.isBlank(customer.getAccountId())) {
			log.error("No savings account found for customer ID: {}", customerId);
			throw new ValidationException("No savings account found for customer ID: " + customerId);
		}
		return customer.getAccountId();
	}

	@Override
	public GenericApiResponse closeAccount(Long customerId, CustomerAccountClosureRequest request) {
		Customer customer = this.getCustomerById(customerId);

		Optional<CustomerStatusAudit> customerStatusAudit = customerStatusAuditRepository.findByCustomerIdAndTypeAndIsActive(customer.getId(), CustomerStatusAuditType.ACCOUNT_CLOSURE, true);
		if (customerStatusAudit.isPresent()) {
			throw new ValidationException("validation.error.exists", "Account already closed");
		}
		if (StringUtils.isBlank(request.getReasonForClosure())) {
			throw new ValidationException("validation.error.reasonForClosure", "reasonForClosure is required");
		}
		CustomerStatusAudit audit = new CustomerStatusAudit();
		audit.setCustomerId(customerId);
		audit.setReason(request.getReasonForClosure());
		audit.setType(CustomerStatusAuditType.ACCOUNT_CLOSURE);
		audit.setActive(true);
		customerStatusAuditRepository.save(audit);
		customer.setActive(false);
		customerRepository.save(customer);
		return GenericApiResponse.builder()
				.status(HttpStatus.OK.name())
				.message("Account closed successfully")
				.build();
	}

	private void validateDuplicateCustomer(CreateCustomerRequest createCustomerRequest, UserType customerType) {
		String emailAddress = createCustomerRequest.getEmailAddress();
		String phoneNumber = createCustomerRequest.getPhoneNumber();
		log.info("Validating duplicate customer email address: {}", emailAddress);
		validateDuplicateCustomerByEmailAddress(emailAddress, customerType);
		log.info("Validating duplicate customer phone number: {}", phoneNumber);
		validateDuplicateCustomerByPhoneNumber(phoneNumber, customerType);
		if (customerType.isCorporate()) {
			validateDuplicateCustomerByRcNumber(createCustomerRequest.getRcNumber());
		}
	}

	private void validateDuplicateCustomerByEmailAddress(String emailAddress, UserType customerType) {
		getCustomerByEmailAddressAndUserType(emailAddress, customerType).ifPresent(existingCustomer -> {
			throw new ValidationException("customer.exist", "Customer already exists. Please proceed to login");
		});
	}

	private void validateDuplicateCustomerByPhoneNumber(String phoneNumber, UserType customerType) {
		log.info("Validating duplicate customer phone number: {}", phoneNumber);
		getCustomerByPhoneNumberAndUserType(phoneNumber, customerType).ifPresent(existingCustomer -> {
			throw new ValidationException("customer.exist", "Customer already exists. Please proceed to login");
		});
	}

	private void validateDuplicateCustomerByBusinessName(String businessName, UserType customerType) {
		log.info("Validating duplicate customer business name: {}", businessName);
		getCustomerByBusinessName(businessName).ifPresent(existingCustomer -> {
			throw new ValidationException("customer.exist", "Customer already exists. Please proceed to login");
		});
	}

	@Override
	public void validateDuplicateCustomerByRcNumber(String rcNumber) {
		log.info("Validating duplicate customer rcNumber: {}", rcNumber);
		getCustomerByRcNumber(rcNumber).ifPresent(existingCustomer -> {
			throw new ValidationException("customer.with.rcNumber.exist", "Customer already exists. Please proceed to login");
		});
	}

	private boolean isInitialRegistration(CreateCustomerRequest createCustomerRequest) {
		return StringUtils.isAllBlank(createCustomerRequest.getBvn(), createCustomerRequest.getNin());
	}

	private Customer buildCustomer(CreateCustomerRequest createCustomerRequest, PostClientsResponse postClientsResponse, boolean isInitialRegistration) {
		Customer customer = new Customer();
		customer.setAccountId(postClientsResponse.getSavingsAccountId());
		customer.setFirstname(createCustomerRequest.getFirstname());
		customer.setLastname(createCustomerRequest.getLastname());
		customer.setEmailAddress(createCustomerRequest.getEmailAddress());
		customer.setPhoneNumber(createCustomerRequest.getPhoneNumber());
		customer.setExternalId(postClientsResponse.getClientId());
		customer.setReferralCode(createCustomerRequest.getReferralCode());
		customer.setTransactionPin(createCustomerRequest.getTransactionPin());
		customer.setTransactionPinSet(StringUtils.isNotBlank(createCustomerRequest.getTransactionPin()));
		customer.setUserType(createCustomerRequest.getCustomerType());
		if (createCustomerRequest.getCustomerType() == UserType.CORPORATE){
			customer.setIndustryId(String.valueOf(createCustomerRequest.getIndustryId()));
			customer.setBusinessName(createCustomerRequest.getBusinessName());
			customer.setRcNumber(createCustomerRequest.getRcNumber());
		}
		customer.setActive(!isInitialRegistration);
		return customer;
	}

	private void updateCustomerDetails(CustomerUpdateRequest customerUpdateRequest, Customer customer) {
		if (customerUpdateRequest != null) {
			if (customer.getExternalId() != null) {
				clientService.updateCustomer(customerUpdateRequest, Long.valueOf(customer.getExternalId()), customer.getUserType());
			}
			updateCustomerFields(customerUpdateRequest, customer);
		}
		customerRepository.save(customer);
	}

	private void updateCustomerFields(CustomerUpdateRequest customerUpdateRequest, Customer customer) {
		if (StringUtils.isNotBlank(customerUpdateRequest.getFirstname())) {
			customer.setFirstname(customerUpdateRequest.getFirstname());
		}
		if (StringUtils.isNotBlank(customerUpdateRequest.getLastname())) {
			customer.setLastname(customerUpdateRequest.getLastname());
		}
		if (StringUtils.isNotBlank(customerUpdateRequest.getEmailAddress())) {
			customer.setEmailAddress(customerUpdateRequest.getEmailAddress());
		}
		if (StringUtils.isNotBlank(customerUpdateRequest.getPhoneNumber())) {
			customer.setPhoneNumber(customerUpdateRequest.getPhoneNumber());
		}
		if (StringUtils.isNotBlank(customerUpdateRequest.getExternalId())) {
			customer.setExternalId(customerUpdateRequest.getExternalId());
		}
		if (StringUtils.isNotBlank(customerUpdateRequest.getBvn())) {
			customer.setBvn(customerUpdateRequest.getBvn());
		}
		if (StringUtils.isNotBlank(customerUpdateRequest.getNin())) {
			customer.setNin(customerUpdateRequest.getNin());
		}
		if (StringUtils.isNotBlank(customerUpdateRequest.getRcNumber())) {
			customer.setRcNumber(customerUpdateRequest.getRcNumber());
		}
		if (StringUtils.isNotBlank(customerUpdateRequest.getBusinessName())) {
			customer.setBusinessName(customerUpdateRequest.getBusinessName());
		}
	}
}
