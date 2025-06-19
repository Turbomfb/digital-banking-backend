/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.customer.api;

import com.techservices.digitalbanking.core.domain.BaseAppResponse;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.customer.domian.dto.response.CustomerDtoResponse;
import org.springframework.data.domain.Pageable;
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
import com.techservices.digitalbanking.core.fineract.model.response.PostClientsClientIdResponse;
import com.techservices.digitalbanking.customer.domian.dto.request.CreateCustomerRequest;
import com.techservices.digitalbanking.customer.domian.dto.request.CustomerUpdateRequest;
import com.techservices.digitalbanking.customer.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RequestMapping("api/v1/customers")
@RestController
@RequiredArgsConstructor
public class CustomerApiResource {

	private final CustomerService customerService;

	@Operation(summary = "Create a New Customer")
	@PostMapping
	public ResponseEntity<BaseAppResponse> createMerchantAccount(
			@Validated @RequestBody CreateCustomerRequest createCustomerRequest,
			@RequestParam(name = "command", required = false, defaultValue = "generate-otp") String command
	) {
		BaseAppResponse postClientsResponse = customerService.createCustomer(createCustomerRequest, command);

		return new ResponseEntity<>(postClientsResponse, HttpStatusCode.valueOf(201));
	}

	@Operation(summary = "Update Customer")
	@PutMapping("{customerId}")
	public ResponseEntity<CustomerDtoResponse> updateCustomer(
			@Validated @RequestBody CustomerUpdateRequest customerUpdateRequest, @PathVariable Long customerId) {
		CustomerDtoResponse customerDtoResponse = customerService.getCustomerDtoResponse(
				customerService.updateCustomer(customerUpdateRequest,
				customerId, null)
		);

		return ResponseEntity.ok(customerDtoResponse);
	}

	@Operation(summary = "Get Customer By Id")
	@GetMapping("{customerId}")
	public ResponseEntity<CustomerDtoResponse> getCustomerById(@PathVariable Long customerId) {
		CustomerDtoResponse getSavingsAccountsAccountIdResponse = customerService.getCustomerDtoResponse(
				customerService.getCustomerById(customerId)
		);
		return ResponseEntity.ok(getSavingsAccountsAccountIdResponse);
	}

	@Operation(summary = "Retrieve All Customers")
	@GetMapping
	public ResponseEntity<BasePageResponse<CustomerDtoResponse>> getAllCustomers(Pageable pageable) {
		BasePageResponse<CustomerDtoResponse> customerBasePageResponse = customerService.getAllCustomers(pageable);

		return ResponseEntity.ok(customerBasePageResponse);
	}

	@Operation(summary = "Retrieve All Customer's accounts by customer ID")
	@GetMapping("{customerId}/accounts")
	public ResponseEntity<GetClientsClientIdAccountsResponse> getCustomerAccountsByClientId(
			@PathVariable Long customerId, @RequestParam(name = "accountType", required = false) String accountType
	) {
		GetClientsClientIdAccountsResponse getClientsResponse = customerService.getClientAccountsByClientId(customerId, accountType);

		return ResponseEntity.ok(getClientsResponse);
	}
}
