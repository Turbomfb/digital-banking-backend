/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.customer.service.impl;

import com.techservices.digitalbanking.core.configuration.SystemProperty;
import com.techservices.digitalbanking.core.domain.BaseAppResponse;
import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.core.domain.dto.request.IdentityVerificationRequest;
import com.techservices.digitalbanking.core.domain.dto.request.OtpDto;
import com.techservices.digitalbanking.core.domain.dto.response.CustomerIdentityVerificationResponse;
import com.techservices.digitalbanking.core.domain.dto.response.IdentityVerificationResponse;
import com.techservices.digitalbanking.core.domain.enums.OtpType;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.core.fineract.model.response.GetClientsClientIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostClientsResponse;
import com.techservices.digitalbanking.core.fineract.service.ClientService;
import com.techservices.digitalbanking.core.redis.service.RedisService;
import com.techservices.digitalbanking.core.service.IdentityVerificationService;
import com.techservices.digitalbanking.customer.domian.CustomerKycTier;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import com.techservices.digitalbanking.customer.domian.data.repository.CustomerRepository;
import com.techservices.digitalbanking.customer.domian.dto.CustomerTierData;
import com.techservices.digitalbanking.customer.domian.dto.request.CreateCustomerRequest;
import com.techservices.digitalbanking.customer.domian.dto.request.CustomerKycRequest;
import com.techservices.digitalbanking.customer.domian.dto.request.CustomerUpdateRequest;
import com.techservices.digitalbanking.customer.service.CustomerKycService;
import com.techservices.digitalbanking.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import static com.techservices.digitalbanking.core.util.CommandUtil.GENERATE_OTP_COMMAND;
import static com.techservices.digitalbanking.core.util.CommandUtil.VERIFY_OTP_COMMAND;

@Service
@RequiredArgsConstructor
public class CustomerKycServiceImpl implements CustomerKycService {

	private final CustomerService customerService;
	private final ClientService clientService;
	private final SystemProperty systemProperty;
	private final CustomerRepository customerRepository;
	private final IdentityVerificationService identityVerificationService;
	private final RedisService redisService;

	@Override
	public BaseAppResponse updateCustomerKyc(CustomerKycRequest customerKycRequest, Long customerId, String command) {
		Customer foundCustomer = this.customerService.getCustomerById(customerId);
		if (VERIFY_OTP_COMMAND.equalsIgnoreCase(command)) {
			OtpDto otpDto = this.redisService.validateOtp(customerKycRequest.getUniqueId(), customerKycRequest.getOtp(), OtpType.KYC_UPGRADE);
			customerKycRequest = (CustomerKycRequest) otpDto.getData();
		}

		IdentityVerificationResponse verificationResponse = this.validateKycParameters(customerKycRequest, foundCustomer, command);
		boolean isIdentityDataRetrieved = verificationResponse != null && GENERATE_OTP_COMMAND.equalsIgnoreCase(command);
		if (isIdentityDataRetrieved) {
			OtpDto otpDto = this.redisService.generateOtpRequest(customerKycRequest, OtpType.KYC_UPGRADE);
			return new GenericApiResponse(otpDto.getUniqueId(), "OTP sent successfully", "success", null);
		}
		if (!foundCustomer.isActive()) {
			this.activateCustomer(foundCustomer, customerKycRequest);
		} else {
			this.updateCustomerDetails(foundCustomer, customerKycRequest, customerId);
		}

		this.updateCustomerKycTier(foundCustomer, customerKycRequest);
		foundCustomer = this.customerRepository.save(foundCustomer);

		return this.customerService.getCustomerDtoResponse(foundCustomer);
	}

	private IdentityVerificationResponse validateKycParameters(CustomerKycRequest customerKycRequest, Customer foundCustomer, String command) {
		boolean includesNoKycParameter = StringUtils.isAllBlank(customerKycRequest.getBvn(), customerKycRequest.getNin());
		if (!foundCustomer.isActive() && includesNoKycParameter) {
			throw new ValidationException("no.kyc.parameter.provided", "At least one KYC parameter (BVN or NIN) must be provided.");
		}

		if (StringUtils.isNotBlank(customerKycRequest.getBvn())) {
			customerRepository.findByBvn(customerKycRequest.getBvn()).ifPresent(existedCustomer -> {
				throw new ValidationException("bvn.already.exists", "A customer with this BVN already exists.");
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
			customerRepository.findByNin(customerKycRequest.getNin())
					.ifPresent(existingCustomer -> {
						throw new ValidationException("nin.already.exists", "A customer with this NIN already exists.");
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
				throw new ValidationException("nin.verification.failed", "Verification failed for nin.", customerIdentityVerificationResponse);
			}
		}
		return null;
	}

	private void activateCustomer(Customer foundCustomer, CustomerKycRequest customerKycRequest) {
		CreateCustomerRequest createCustomerRequest = buildCreateCustomerRequest(foundCustomer, customerKycRequest);
		PostClientsResponse createCustomerResponse = clientService.createCustomer(createCustomerRequest);

		if (createCustomerResponse != null) {
			foundCustomer.setExternalId(String.valueOf(createCustomerResponse.getClientId()));
			foundCustomer.setAccountId(createCustomerResponse.getSavingsId());
			foundCustomer.setActive(true);
		}
	}

	private CreateCustomerRequest buildCreateCustomerRequest(Customer foundCustomer, CustomerKycRequest customerKycRequest) {
		CreateCustomerRequest createCustomerRequest = new CreateCustomerRequest();
		createCustomerRequest.setBvn(customerKycRequest.getBvn());
		createCustomerRequest.setNin(customerKycRequest.getNin());
		createCustomerRequest.setFirstname(foundCustomer.getFirstname());
		createCustomerRequest.setLastname(foundCustomer.getLastname());
		createCustomerRequest.setEmailAddress(foundCustomer.getEmailAddress());
		createCustomerRequest.setPhoneNumber(foundCustomer.getPhoneNumber());
		createCustomerRequest.setSavingsProductId(systemProperty.getDefaultSavingsProductId());
		return createCustomerRequest;
	}

	private void updateCustomerDetails(Customer foundCustomer, CustomerKycRequest customerKycRequest, Long customerId) {
		CustomerUpdateRequest customerUpdateRequest = new CustomerUpdateRequest();
		customerUpdateRequest.setBvn(customerKycRequest.getBvn());
		customerUpdateRequest.setNin(customerKycRequest.getNin());
		customerService.updateCustomer(customerUpdateRequest, customerId, foundCustomer);
	}

	private void updateCustomerKycTier(Customer foundCustomer, CustomerKycRequest customerKycRequest) {
		GetClientsClientIdResponse getClientsClientIdResponse = clientService.getCustomerById(Long.valueOf(foundCustomer.getExternalId()));
		CustomerTierData kycTier = getClientsClientIdResponse.getKycTier();
		if (kycTier != null) {
			foundCustomer.setKycTier(CustomerKycTier.findByCode(String.valueOf(kycTier.getId())));
		}
	}
}
