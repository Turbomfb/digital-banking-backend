/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.customer.api;

import com.techservices.digitalbanking.core.configuration.security.SpringSecurityAuditorAware;
import com.techservices.digitalbanking.core.domain.BaseAppResponse;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.core.domain.dto.response.IdentityVerificationResponse;
import com.techservices.digitalbanking.core.domain.enums.IdentityVerificationType;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.core.service.IdentityVerificationService;
import com.techservices.digitalbanking.customer.domian.dto.request.CustomerTransactionPinRequest;
import com.techservices.digitalbanking.customer.domian.dto.response.CustomerDashboardResponse;
import com.techservices.digitalbanking.customer.domian.dto.response.CustomerDtoResponse;
import com.techservices.digitalbanking.loan.domain.response.LoanOfferResponse;
import com.techservices.digitalbanking.loan.service.LoanApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techservices.digitalbanking.core.fineract.model.response.GetClientsClientIdAccountsResponse;
import com.techservices.digitalbanking.customer.domian.dto.request.CreateCustomerRequest;
import com.techservices.digitalbanking.customer.domian.dto.request.CustomerUpdateRequest;
import com.techservices.digitalbanking.customer.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RequestMapping("api/v1/customers")
@RestController
@RequiredArgsConstructor
@Slf4j
public class CustomerApiResource {

	private final CustomerService customerService;
	private final LoanApplicationService loanApplicationService;
	private final SpringSecurityAuditorAware springSecurityAuditorAware;
	private final IdentityVerificationService identityVerificationService;

	@Operation(summary = "Create a New Customer")
	@PostMapping
	public ResponseEntity<BaseAppResponse> createMerchantAccount(
			@Validated @RequestBody CreateCustomerRequest createCustomerRequest,
			@RequestParam(name = "command", required = false, defaultValue = "generate-otp") String command
	) {
		BaseAppResponse postClientsResponse = customerService.createCustomer(createCustomerRequest, command);

		return new ResponseEntity<>(postClientsResponse, HttpStatusCode.valueOf(201));
	}

	@Operation(summary = "Retrieve Identity Data")
	@GetMapping("identity-verification")
	public ResponseEntity<IdentityVerificationResponse> retrieveIdentityData(
			@RequestParam(name = "identityType") IdentityVerificationType identityType,
			@RequestParam(name = "identityValue") String identityValue
	) {
		IdentityVerificationResponse identityResponse;
		if (identityType == IdentityVerificationType.NIN) {
			identityResponse = identityVerificationService.retrieveNinData(identityValue);
		} else if (identityType == IdentityVerificationType.BVN) {
			identityResponse = identityVerificationService.retrieveBvnData(identityValue);
		} else {
			log.error("Unsupported identity type: {}", identityType);
			throw new ValidationException("validation.error.exists", "Unsupported identity type: " + identityType);
		}
		return new ResponseEntity<>(identityResponse, HttpStatusCode.valueOf(200));
	}

	@Operation(summary = "Update Customer")
	@PutMapping("{customerId}")
	public ResponseEntity<CustomerDtoResponse> updateCustomer(
			@Validated @RequestBody CustomerUpdateRequest customerUpdateRequest, @PathVariable Long customerId) {
		CustomerDtoResponse customerDtoResponse = CustomerDtoResponse.parse(
				customerService.updateCustomer(customerUpdateRequest,
				customerId, null)
		);

		return ResponseEntity.ok(customerDtoResponse);
	}

	@Operation(summary = "Get Customer By Id")
	@GetMapping("me")
	public ResponseEntity<CustomerDtoResponse> getCustomerById() {
		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
        CustomerDtoResponse response = CustomerDtoResponse.parse(
                customerService.getCustomerById(customerId)
        );
        BasePageResponse<LoanOfferResponse> offers = null;
        try {
            offers = loanApplicationService.retrieveCustomerLoanOffers(customerId);
        } catch (Exception e) {
            log.error(e.getMessage());
            response.setPreQualifiedForLoan(false);
        }
        response.setPreQualifiedForLoan(offers != null && !offers.getData().isEmpty());
        return ResponseEntity.ok(response);
    }

	@Operation(summary = "Retrieve Customer's dashboard by ID")
	@GetMapping("me/dashboard")
	public ResponseEntity<CustomerDashboardResponse> retrieveCustomerDashboard() {
		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		return ResponseEntity.ok(customerService.retrieveCustomerDashboard(customerId));
	}

	@Operation(summary = "Retrieve All Customer's accounts by customer ID")
	@GetMapping("me/accounts")
	public ResponseEntity<GetClientsClientIdAccountsResponse> getCustomerAccountsByClientId(
			@RequestParam(name = "accountType", required = false) String accountType
	) {
		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		GetClientsClientIdAccountsResponse getClientsResponse = customerService.getClientAccountsByClientId(customerId, accountType);

		return ResponseEntity.ok(getClientsResponse);
	}

	@Operation(summary = "Create Transaction Pin")
	@PostMapping("{customerId}/transaction-pin")
	public ResponseEntity<GenericApiResponse> createTransactionPin(
			@RequestBody CustomerTransactionPinRequest customerTransactionPinRequest,
			@PathVariable Long customerId) {
		GenericApiResponse genericApiResponse = customerService.createTransactionPin(customerId, customerTransactionPinRequest);

		return ResponseEntity.ok(genericApiResponse);
	}
}
