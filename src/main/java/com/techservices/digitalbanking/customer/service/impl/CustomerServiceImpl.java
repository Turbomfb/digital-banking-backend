/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.customer.service.impl;

import com.techservices.digitalbanking.common.domain.enums.UserType;
import com.techservices.digitalbanking.core.domain.BaseAppResponse;
import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.core.domain.dto.request.OtpDto;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.enums.AccountType;
import com.techservices.digitalbanking.core.domain.enums.OtpType;
import com.techservices.digitalbanking.core.exception.AbstractPlatformResourceNotFoundException;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.core.fineract.service.AccountService;
import com.techservices.digitalbanking.core.redis.service.RedisService;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import com.techservices.digitalbanking.customer.domian.data.repository.CustomerRepository;
import com.techservices.digitalbanking.customer.domian.dto.response.CustomerDashboardResponse;
import com.techservices.digitalbanking.customer.domian.dto.response.CustomerDtoResponse;
import com.techservices.digitalbanking.core.fineract.model.response.*;
import com.techservices.digitalbanking.core.fineract.service.ClientService;
import com.techservices.digitalbanking.customer.domian.dto.request.CreateCustomerRequest;
import com.techservices.digitalbanking.customer.domian.dto.request.CustomerUpdateRequest;
import com.techservices.digitalbanking.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

	private final ClientService clientService;
	private final AccountService accountService;
	private final CustomerRepository customerRepository;
	private final RedisService redisService;

	@Override
	public BaseAppResponse createCustomer(CreateCustomerRequest createCustomerRequest, String command) {
		if ("generate-otp".equalsIgnoreCase(command)) {
			validateDuplicateCustomer(createCustomerRequest.getEmailAddress(), createCustomerRequest.getPhoneNumber());
			OtpDto otpDto = this.redisService.generateOtpRequest(createCustomerRequest, OtpType.ONBOARDING);
			return new GenericApiResponse(otpDto.getUniqueId(), "OTP sent successfully", "success", null);
		} else if ("verify-otp".equalsIgnoreCase(command)) {
			OtpDto otpDto = this.redisService.validateOtp(createCustomerRequest.getUniqueId(), createCustomerRequest.getOtp(), OtpType.ONBOARDING);
			createCustomerRequest = (CreateCustomerRequest) otpDto.getData();
			return this.completeCustomerRegistration(createCustomerRequest);
		}
		else {
			throw new ValidationException("invalid.command", "Invalid command: " + command);
		}
	}

	private CustomerDtoResponse completeCustomerRegistration(CreateCustomerRequest createCustomerRequest) {
		boolean isInitialRegistration = isInitialRegistration(createCustomerRequest);
		PostClientsResponse postClientsResponse = isInitialRegistration ? new PostClientsResponse() : clientService.createCustomer(createCustomerRequest);

		Customer customer = buildCustomer(createCustomerRequest, postClientsResponse, isInitialRegistration);
		return CustomerDtoResponse.parse(customerRepository.save(customer), clientService);
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
	public Optional<Customer> getCustomerByEmailAddress(String emailAddress) {
		return customerRepository.findByEmailAddress(emailAddress);
	}

	@Override
	public Optional<Customer> getCustomerByPhoneNumber(String phoneNumber) {
		return customerRepository.findByPhoneNumber(phoneNumber);
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
	public CustomerDtoResponse getCustomerDtoResponse(Customer customer) {
		return CustomerDtoResponse.parse(customer, clientService);
	}

	@Override
	public CustomerDashboardResponse retrieveCustomerDashboard(Long customerId) {
		String externalId = this.retrieveCustomerExternalId(customerId);
		GetClientsClientIdAccountsResponse customerAccounts = clientService.getClientAccountsByClientId(externalId, null);
		return CustomerDashboardResponse.builder()
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
								customerAccounts.getTotalAccountBalanceFor(AccountType.FIXED_DEPOSIT),
								customerAccounts.getTotalAccountInterestsFor(AccountType.FIXED_DEPOSIT, accountService),
								customerAccounts.getTotalAccountDepositsFor(AccountType.FIXED_DEPOSIT, accountService),
								customerAccounts.getTotalAccountWithdrawalsFor(AccountType.FIXED_DEPOSIT, accountService),
								customerAccounts.getTotalActivePlanFor(AccountType.FIXED_DEPOSIT, accountService)
						)
				)
				.lockAccount(
						new CustomerDashboardResponse.Account(
								customerAccounts.getTotalAccountBalanceFor(AccountType.RECURRING_DEPOSIT),
								customerAccounts.getTotalAccountInterestsFor(AccountType.RECURRING_DEPOSIT, accountService),
								customerAccounts.getTotalAccountDepositsFor(AccountType.RECURRING_DEPOSIT, accountService),
								customerAccounts.getTotalAccountWithdrawalsFor(AccountType.RECURRING_DEPOSIT, accountService),
								customerAccounts.getTotalActivePlanFor(AccountType.RECURRING_DEPOSIT, accountService)
						)
				)
				.build();
	}

	private void validateDuplicateCustomer(String emailAddress, String phoneNumber) {
		validateDuplicateCustomerByEmailAddress(emailAddress);
		validateDuplicateCustomerByPhoneNumber(phoneNumber);
	}

	private void validateDuplicateCustomerByEmailAddress(String emailAddress) {
		getCustomerByEmailAddress(emailAddress).ifPresent(existingCustomer -> {
			throw new ValidationException("customer.exist", "Customer with email: " + emailAddress + " already exists.", emailAddress);
		});
	}

	private void validateDuplicateCustomerByPhoneNumber(String phoneNumber) {
		getCustomerByPhoneNumber(phoneNumber).ifPresent(existingCustomer -> {
			throw new ValidationException("customer.exist", "Customer with phoneNumber: " + phoneNumber + " already exists.", phoneNumber);
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
		customer.setUserType(UserType.CUSTOMER);
		customer.setActive(!isInitialRegistration);
		return customer;
	}

	private void updateCustomerDetails(CustomerUpdateRequest customerUpdateRequest, Customer customer) {
		if (customerUpdateRequest != null) {
			if (customer.getExternalId() != null) {
				clientService.updateCustomer(customerUpdateRequest, Long.valueOf(customer.getExternalId()));
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
	}
}
