/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.customer.service.impl;

import com.techservices.digitalbanking.common.domain.enums.UserType;
import com.techservices.digitalbanking.core.domain.BaseAppResponse;
import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.core.domain.dto.request.IdentityVerificationRequest;
import com.techservices.digitalbanking.core.domain.dto.request.NotificationRequestDto;
import com.techservices.digitalbanking.core.domain.dto.request.OtpDto;
import com.techservices.digitalbanking.core.domain.dto.response.CustomerIdentityVerificationResponse;
import com.techservices.digitalbanking.core.domain.dto.response.IdentityVerificationResponse;
import com.techservices.digitalbanking.core.domain.enums.OtpType;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.core.fineract.configuration.FineractProperty;
import com.techservices.digitalbanking.core.fineract.model.data.FineractPageResponse;
import com.techservices.digitalbanking.core.fineract.model.request.KycTier;
import com.techservices.digitalbanking.core.fineract.model.request.PostClientNonPersonDetails;
import com.techservices.digitalbanking.core.fineract.model.response.GetClientsClientIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetClientsSavingsAccounts;
import com.techservices.digitalbanking.core.fineract.model.response.PostClientsResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostSavingsAccountsResponse;
import com.techservices.digitalbanking.core.fineract.service.AccountService;
import com.techservices.digitalbanking.core.fineract.service.ClientService;
import com.techservices.digitalbanking.core.redis.service.RedisService;
import com.techservices.digitalbanking.core.service.IdentityVerificationService;
import com.techservices.digitalbanking.customer.domian.CustomerKycTier;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import com.techservices.digitalbanking.customer.domian.data.repository.CustomerRepository;
import com.techservices.digitalbanking.customer.domian.dto.request.CreateCustomerRequest;
import com.techservices.digitalbanking.customer.domian.dto.request.CustomerKycRequest;
import com.techservices.digitalbanking.customer.domian.dto.request.CustomerUpdateRequest;
import com.techservices.digitalbanking.customer.domian.dto.response.CustomerDtoResponse;
import com.techservices.digitalbanking.customer.service.CustomerKycService;
import com.techservices.digitalbanking.customer.service.CustomerService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.techservices.digitalbanking.core.util.CommandUtil.GENERATE_OTP_COMMAND;
import static com.techservices.digitalbanking.core.util.CommandUtil.VERIFY_OTP_COMMAND;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerKycServiceImpl implements CustomerKycService {

	private final CustomerService customerService;
	private final ClientService clientService;
	private final CustomerRepository customerRepository;
	private final IdentityVerificationService identityVerificationService;
	private final RedisService redisService;
	private final AccountService accountService;
	private final FineractProperty fineractProperty;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public BaseAppResponse updateCustomerKyc(CustomerKycRequest customerKycRequest, Long customerId, String command) {
		log.info("customerKycRequest: {}", customerKycRequest);
		Customer foundCustomer = this.customerService.getCustomerById(customerId);
		String uniqueId = customerKycRequest.getUniqueId();
		String otp = customerKycRequest.getOtp();
		if (VERIFY_OTP_COMMAND.equalsIgnoreCase(command)) {
			OtpDto otpDto = this.redisService.validateOtpWithoutDeletingRecord(uniqueId, otp, OtpType.KYC_UPGRADE);
			customerKycRequest = (CustomerKycRequest) otpDto.getData();
		}

		IdentityVerificationResponse verificationResponse = this.validateKycParameters(customerKycRequest, foundCustomer, command);
		boolean isIdentityDataRetrieved = verificationResponse != null && GENERATE_OTP_COMMAND.equalsIgnoreCase(command);
		if (isIdentityDataRetrieved) {
			if (verificationResponse.getData() != null) {
				IdentityVerificationResponse.IdentityVerificationResponseData data = verificationResponse.getData();
				if ("EXTERNAL".equalsIgnoreCase(verificationResponse.getDataSource()) && (StringUtils.isBlank(data.getMobile()) || StringUtils.isBlank(data.getFirstName()) || StringUtils.isBlank(data.getLastName()))) {
					log.error("Data source: {}, Identity verification failed for customer id: {}. Null fields were identified", verificationResponse.getDataSource(), data);
					String dataType = StringUtils.isNotBlank(customerKycRequest.getBvn()) ? "BVN" : "NIN";
					throw new ValidationException("validation.error.exists", "Unable to validate your "+dataType+" at the moment. Please try again later.");
				}
			}
			NotificationRequestDto notificationRequestDto = new NotificationRequestDto(verificationResponse);
			OtpDto otpDto = this.redisService.generateOtpRequest(customerKycRequest, OtpType.KYC_UPGRADE, notificationRequestDto, null);
			String message = notificationRequestDto.getOtpResponseMessage();
			return new GenericApiResponse(otpDto.getUniqueId(), message, "success", null);
		}
		CustomerKycTier customerKycTier = this.getCustomerKycTier(foundCustomer);
		if (!foundCustomer.isActive()) {
			this.activateCustomer(foundCustomer, customerKycRequest, customerKycTier);
		} else {
			this.updateCustomerDetails(foundCustomer, customerKycRequest, customerId, customerKycTier);
		}

		foundCustomer.setKycTier(customerKycTier);
		foundCustomer = this.customerRepository.save(foundCustomer);
		this.redisService.validateOtp(uniqueId, otp, OtpType.KYC_UPGRADE);
		return CustomerDtoResponse.parse(foundCustomer);
	}

	@Override
	public FineractPageResponse<KycTier> retrieveAllKycTier() {
		return clientService.retrieveAllKycTier();
	}

	private IdentityVerificationResponse validateKycParameters(CustomerKycRequest customerKycRequest, Customer foundCustomer, String command) {
		boolean includesNoKycParameter = StringUtils.isAllBlank(customerKycRequest.getBvn(), customerKycRequest.getNin());
		if (!foundCustomer.isActive() && includesNoKycParameter) {
			throw new ValidationException("no.kyc.parameter.provided", "At least one KYC parameter (BVN or NIN) must be provided.");
		}

		if (StringUtils.isNotBlank(customerKycRequest.getBvn())) {
			log.info("Validating BVN: {}", customerKycRequest.getBvn());
			customerRepository.findByBvn(customerKycRequest.getBvn()).ifPresent(existedCustomer -> {
				throw new ValidationException("bvn.already.exists", "You have entered and invalid BVN");
			});

			if (GENERATE_OTP_COMMAND.equalsIgnoreCase(command)) {
				GetClientsClientIdResponse client = clientService.getCustomerByBvn(customerKycRequest.getBvn());
				if (client != null) {
					return IdentityVerificationResponse.parse(client);
				}
				return this.identityVerificationService.retrieveBvnData(customerKycRequest.getBvn());
			}

			IdentityVerificationRequest identityVerificationRequest = IdentityVerificationRequest.parse(foundCustomer);
			CustomerIdentityVerificationResponse customerIdentityVerificationResponse = this.identityVerificationService.verifyBvn(customerKycRequest.getBvn(), identityVerificationRequest);
			foundCustomer.setBvn(customerKycRequest.getBvn());
			if (!customerIdentityVerificationResponse.isValid()) {
				throw new ValidationException("bvn.verification.failed", "Verification failed for bvn.", customerIdentityVerificationResponse);
			}
		}

		if (StringUtils.isNotBlank(customerKycRequest.getNin())) {
			log.info("Validating NIN: {}", customerKycRequest.getNin());
			customerRepository.findByNin(customerKycRequest.getNin())
					.ifPresent(existingCustomer -> {
						throw new ValidationException("nin.already.exists", "You have entered and invalid NIN.");
					});

			if (GENERATE_OTP_COMMAND.equalsIgnoreCase(command)) {
				GetClientsClientIdResponse client = clientService.getCustomerByNin(customerKycRequest.getNin());
				if (client != null) {
					return IdentityVerificationResponse.parse(client);
				}
				return this.identityVerificationService.retrieveNinData(customerKycRequest.getNin());
			}

			IdentityVerificationRequest identityVerificationRequest = IdentityVerificationRequest.parse(foundCustomer);
			CustomerIdentityVerificationResponse customerIdentityVerificationResponse = identityVerificationService.verifyNin(customerKycRequest.getNin(), identityVerificationRequest);
			foundCustomer.setNin(customerKycRequest.getNin());
			if (!customerIdentityVerificationResponse.isValid()) {
				log.error("NIN verification failed: {}", customerIdentityVerificationResponse);
				throw new ValidationException("nin.verification.failed", "Verification failed for nin.", customerIdentityVerificationResponse);
			}
		}
		return null;
	}

	private static void validateEntityClient(GetClientsClientIdResponse client) {
		if (client != null && client.getLegalForm() != null && client.getLegalForm().getId() == 2) {
			throw new ValidationException("validation.error.exists", "This profile is currently linked to a corporate entity and is not permitted to perform any operations.");
		}
	}

	private void activateCustomer(Customer foundCustomer, CustomerKycRequest customerKycRequest, CustomerKycTier customerKycTier) {
		GetClientsClientIdResponse client = clientService.searchClients(customerKycRequest.getNin(), customerKycRequest.getBvn(), null, null, foundCustomer.getUserType());
		log.info("Client found: {}", client);
		if (client == null) {
			client = fetchCbaClientByEmailOrPhoneNumber(foundCustomer);
		}
        CreateCustomerRequest createCustomerRequest = buildCreateCustomerRequest(foundCustomer, customerKycRequest, customerKycTier);
        PostClientsResponse createCustomerResponse = null;
        if (client == null) {
            createCustomerResponse = clientService.createCustomer(createCustomerRequest);
        } else {
			this.upgradeClientTier(customerKycTier, client.getId());
		}

        Long clientId = client != null && client.getId() != null ? client.getId() : createCustomerResponse != null ? createCustomerResponse.getClientId() : null;
		if (clientId != null) {
			log.info("Client ID found: {}", clientId);
			Optional<@Valid GetClientsSavingsAccounts> savingsAccount = clientService.getClientAccountsByClientId(String.valueOf(clientId), "savingsAccounts").getSavingsAccounts().stream()
					.findAny();
			log.info("Savings account found: {}", savingsAccount);
			Long savingsAccountId = null;
			if (savingsAccount.isPresent()) {
				savingsAccountId = savingsAccount.get().getId();
			} else {
				PostSavingsAccountsResponse savingsAccountsResponse = accountService.createSavingsAccount(
						clientId, fineractProperty.getDefaultSavingsProductId(), null, null, null,
						null, true, fineractProperty.getSavingsAccountNominalAnnualInterestRate()
				);
				if (savingsAccountsResponse != null) {
					savingsAccountId = savingsAccountsResponse.getSavingsId();
				}
			}
			if (savingsAccountId != null) {
				foundCustomer.setExternalId(String.valueOf(clientId));
				foundCustomer.setAccountId(String.valueOf(savingsAccountId));
				foundCustomer.setActive(true);
			}
		}
	}

	private GetClientsClientIdResponse fetchCbaClientByEmailOrPhoneNumber(Customer foundCustomer) {
		GetClientsClientIdResponse client;
		client = clientService.searchClients(null, null, foundCustomer.getEmailAddress(), null, foundCustomer.getUserType());
		if (client == null) {
			client = clientService.searchClients(null, null, null, foundCustomer.getPhoneNumber(), foundCustomer.getUserType());
		}
		return client;
	}

	private void upgradeClientTier(CustomerKycTier customerKycTier, Long clientId) {
		CustomerUpdateRequest updateRequest = new CustomerUpdateRequest();
		updateRequest.setKycTier(customerKycTier.getCode());
		clientService.updateCustomer(updateRequest, clientId);
	}

	private CustomerKycTier getCustomerKycTier(Customer foundCustomer) {
		if (!StringUtils.isAllBlank(foundCustomer.getNin(), foundCustomer.getBvn())) {
			if (StringUtils.isNoneBlank(foundCustomer.getBvn(), foundCustomer.getNin())) {
				return CustomerKycTier.TIER_2;
			} return CustomerKycTier.TIER_1;
		}
		return null;
	}

	private CreateCustomerRequest buildCreateCustomerRequest(Customer foundCustomer, CustomerKycRequest customerKycRequest, CustomerKycTier customerKycTier) {
		CreateCustomerRequest createCustomerRequest = new CreateCustomerRequest();
		createCustomerRequest.setBvn(customerKycRequest.getBvn());
		createCustomerRequest.setNin(customerKycRequest.getNin());
		createCustomerRequest.setCustomerType(foundCustomer.getUserType());
		if (foundCustomer.getUserType() == UserType.CORPORATE) {
			createCustomerRequest.setBusinessName(foundCustomer.getBusinessName());
			PostClientNonPersonDetails postClientNonPersonDetails = new PostClientNonPersonDetails();
			postClientNonPersonDetails.setIncorpNumber(foundCustomer.getRcNumber());
			postClientNonPersonDetails.setConstitutionId(23L);
			postClientNonPersonDetails.setMainBusinessLineId(Long.valueOf(foundCustomer.getIndustryId()));
			createCustomerRequest.setClientNonPersonDetails(postClientNonPersonDetails);
		} else {
			createCustomerRequest.setFirstname(createCustomerRequest.getFirstname());
			createCustomerRequest.setLastname(createCustomerRequest.getLastname());
		}
		createCustomerRequest.setFirstname(foundCustomer.getFirstname());
		createCustomerRequest.setLastname(foundCustomer.getLastname());
		createCustomerRequest.setEmailAddress(foundCustomer.getEmailAddress());
		createCustomerRequest.setPhoneNumber(foundCustomer.getPhoneNumber());
		if (customerKycTier != null) {
			createCustomerRequest.setKycTier(customerKycTier.getCode());
		}
		return createCustomerRequest;
	}

	private void updateCustomerDetails(Customer foundCustomer, CustomerKycRequest customerKycRequest, Long customerId, CustomerKycTier customerKycTier) {
		CustomerUpdateRequest customerUpdateRequest = new CustomerUpdateRequest();
		customerUpdateRequest.setBvn(customerKycRequest.getBvn());
		customerUpdateRequest.setNin(customerKycRequest.getNin());
		if (customerKycTier != null) {
			customerUpdateRequest.setKycTier(customerKycTier.getCode());
		}
		customerService.updateCustomer(customerUpdateRequest, customerId, foundCustomer);
	}
}
