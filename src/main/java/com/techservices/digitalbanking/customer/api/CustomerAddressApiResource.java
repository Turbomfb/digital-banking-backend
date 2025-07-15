/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.customer.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.techservices.digitalbanking.core.fineract.model.request.PutClientClientIdAddressesRequest;
import com.techservices.digitalbanking.core.fineract.model.response.GetClientAddressTemplateResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetClientClientIdAddressesResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PutClientClientIdAddressesResponse;
import com.techservices.digitalbanking.customer.service.CustomerAddressService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RequestMapping("api/v1/customers")
@RestController
@RequiredArgsConstructor
public class CustomerAddressApiResource {

	private final CustomerAddressService customerAddressService;

	@Operation(summary = "Get Address Template")
	@GetMapping("addresses/template")
	public ResponseEntity<GetClientAddressTemplateResponse> getCustomerAddressTemplate() {
		GetClientAddressTemplateResponse getClientAddressTemplateResponse = customerAddressService
				.getCustomerAddressTemplate();

		return ResponseEntity.ok(getClientAddressTemplateResponse);
	}

	@Operation(summary = "Get Customer's Addresses")
	@GetMapping("{customerId}/addresses")
	public ResponseEntity<List<GetClientClientIdAddressesResponse>> getCustomerAddresses(
			@PathVariable Long customerId) {
		List<GetClientClientIdAddressesResponse> getClientAddressTemplateResponse = customerAddressService
				.getCustomerAddresses(customerId);

		return ResponseEntity.ok(getClientAddressTemplateResponse);
	}

	@Operation(summary = "Update Customer's Address")
	@PutMapping("{customerId}/addresses")
	public ResponseEntity<PutClientClientIdAddressesResponse> updateCustomerAddress(@PathVariable Long customerId,
			@RequestBody PutClientClientIdAddressesRequest putClientClientIdAddressesRequest) {
		PutClientClientIdAddressesResponse putClientClientIdAddressesResponse = customerAddressService
				.updateCustomerAddress(customerId, putClientClientIdAddressesRequest);

		return ResponseEntity.ok(putClientClientIdAddressesResponse);
	}
}
