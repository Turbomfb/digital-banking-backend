/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.customer.service.impl;

import com.techservices.digitalbanking.core.configuration.SystemProperty;
import com.techservices.digitalbanking.core.domain.dto.request.IdentityVerificationRequest;
import com.techservices.digitalbanking.core.domain.dto.response.CustomerIdentityVerificationResponse;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.core.fineract.model.response.GetClientsClientIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostClientsResponse;
import com.techservices.digitalbanking.core.fineract.service.ClientService;
import com.techservices.digitalbanking.core.service.IdentityVerificationService;
import com.techservices.digitalbanking.customer.domian.CustomerKycTier;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import com.techservices.digitalbanking.customer.domian.data.repository.CustomerRepository;
import com.techservices.digitalbanking.customer.domian.dto.CustomerTierData;
import com.techservices.digitalbanking.customer.domian.dto.request.CreateCustomerRequest;
import com.techservices.digitalbanking.customer.domian.dto.request.CustomerKycRequest;
import com.techservices.digitalbanking.customer.domian.dto.request.CustomerUpdateRequest;
import com.techservices.digitalbanking.customer.domian.dto.response.CustomerDtoResponse;
import com.techservices.digitalbanking.customer.service.CustomerKycService;
import com.techservices.digitalbanking.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerKycServiceImpl implements CustomerKycService {

	private final CustomerService customerService;
	private final ClientService clientService;
	private final SystemProperty systemProperty;
	private final CustomerRepository customerRepository;
	private final IdentityVerificationService identityVerificationService;

	@Override
	public CustomerDtoResponse updateCustomerKyc(CustomerKycRequest customerKycRequest, Long customerId) {
		Customer foundCustomer = customerService.getCustomerById(customerId);
		validateKycParameters(customerKycRequest, foundCustomer);

		if (!foundCustomer.isActive()) {
			activateCustomer(foundCustomer, customerKycRequest);
		} else {
			updateCustomerDetails(foundCustomer, customerKycRequest, customerId);
		}

		updateCustomerKycTier(foundCustomer, customerKycRequest);
		foundCustomer = customerRepository.save(foundCustomer);

		return customerService.getCustomerDtoResponse(foundCustomer);
	}

	private void validateKycParameters(CustomerKycRequest customerKycRequest, Customer foundCustomer) {
		boolean includesNoKycParameter = StringUtils.isAllBlank(customerKycRequest.getBvn(), customerKycRequest.getNin());
		if (!foundCustomer.isActive() && includesNoKycParameter) {
			throw new ValidationException("no.kyc.parameter.provided", "At least one KYC parameter (BVN or NIN) must be provided.");
		}

		if (StringUtils.isNotBlank(customerKycRequest.getBvn())) {
			customerRepository.findByBvn(customerKycRequest.getBvn()).ifPresent(existedCustomer -> {
				throw new ValidationException("bvn.already.exists", "A customer with this BVN already exists.");
			});
			IdentityVerificationRequest identityVerificationRequest = IdentityVerificationRequest.parse(foundCustomer);
			CustomerIdentityVerificationResponse customerIdentityVerificationResponse = identityVerificationService.verifyBvn(customerKycRequest.getBvn(), identityVerificationRequest);
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
			IdentityVerificationRequest identityVerificationRequest = IdentityVerificationRequest.parse(foundCustomer);
			CustomerIdentityVerificationResponse customerIdentityVerificationResponse = identityVerificationService.verifyNin(customerKycRequest.getNin(), identityVerificationRequest);
			foundCustomer.setNin(customerKycRequest.getNin());
			if (!customerIdentityVerificationResponse.isValid()) {
				throw new ValidationException("nin.verification.failed", "Verification failed for nin.", customerIdentityVerificationResponse);
			}
		}
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
