/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.customer.api;

import com.techservices.digitalbanking.core.configuration.security.SpringSecurityAuditorAware;
import com.techservices.digitalbanking.core.domain.BaseAppResponse;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.fineract.model.data.FineractPageResponse;
import com.techservices.digitalbanking.core.fineract.model.request.KycTier;
import com.techservices.digitalbanking.core.fineract.model.response.GetClientsClientIdAccountsResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetClientsResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostClientsClientIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PutClientsClientIdResponse;
import com.techservices.digitalbanking.customer.domian.dto.request.CreateCustomerRequest;
import com.techservices.digitalbanking.customer.domian.dto.request.CustomerKycRequest;
import com.techservices.digitalbanking.customer.domian.dto.request.CustomerUpdateRequest;
import com.techservices.digitalbanking.customer.domian.dto.response.CustomerDtoResponse;
import com.techservices.digitalbanking.customer.service.CustomerKycService;
import com.techservices.digitalbanking.customer.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1")
@RestController
@RequiredArgsConstructor
public class CustomerKycApiResource {

	private final CustomerKycService customerKycService;
	private final String GENERATE_OTP = "";
	private final SpringSecurityAuditorAware springSecurityAuditorAware;

	@Operation(summary = "Update Customer KYC Information", description = "This endpoint allows you to update the KYC (Know Your Customer) information for a specific customer.")
	@PostMapping("customers/me/kyc")
	public ResponseEntity<BaseAppResponse> updateCustomerKyc(
			@Validated @RequestBody CustomerKycRequest customerKycRequest,
			@RequestParam(name = "command", required = false, defaultValue = GENERATE_OTP) String command
	) {
		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		BaseAppResponse postClientsResponse = customerKycService.updateCustomerKyc(customerKycRequest, customerId, command);

		return new ResponseEntity<>(postClientsResponse, HttpStatus.OK);
	}

	@Operation(summary = "Retrieve all KYC Tiers", description = "This endpoint allows you to retrieve the KYC tiers for a specific customer.")
	@GetMapping("kyc-tiers")
	public ResponseEntity<FineractPageResponse<KycTier>> retrieveAllKycTier() {
		FineractPageResponse<KycTier> postClientsResponse = customerKycService.retrieveAllKycTier();

		return new ResponseEntity<>(postClientsResponse, HttpStatus.OK);
	}
}
