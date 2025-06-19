/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.customer.api;

import com.techservices.digitalbanking.core.domain.BaseAppResponse;
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

@RequestMapping("api/v1/customers/{customerId}/kyc")
@RestController
@RequiredArgsConstructor
public class CustomerKycApiResource {

	private final CustomerKycService customerKycService;

	@Operation(summary = "Update Customer KYC Information", description = "This endpoint allows you to update the KYC (Know Your Customer) information for a specific customer.")
	@PostMapping
	public ResponseEntity<BaseAppResponse> updateCustomerKyc(
			@Validated @RequestBody CustomerKycRequest customerKycRequest,
			@PathVariable Long customerId,
			@RequestParam(name = "command", required = false, defaultValue = "generate-otp") String command
	) {
		BaseAppResponse postClientsResponse = customerKycService.updateCustomerKyc(customerKycRequest, customerId, command);

		return new ResponseEntity<>(postClientsResponse, HttpStatus.OK);
	}
}
