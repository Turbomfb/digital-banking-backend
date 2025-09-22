/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.techservices.digitalbanking.core.eBanking.model.request.PostRecurringDepositProductsRequest;
import com.techservices.digitalbanking.core.eBanking.model.request.PutRecurringDepositProductRequest;
import com.techservices.digitalbanking.core.eBanking.model.response.DeleteRecurringDepositProductResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.GetRecurringDepositProductProductIdResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostRecurringDepositProductResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PutRecurringDepositProductResponse;

import io.swagger.v3.oas.annotations.Operation;

public interface RecurringDepositProductsApi {

	@GetMapping("recurringdepositproducts")
	@Operation(summary = "List Recuring Deposit Products", description = "Lists Recuring Deposit Products")
	List<GetRecurringDepositProductProductIdResponse> retrieveAll();

	@GetMapping("recurringdepositproducts/{productId}")
	@Operation(summary = "Retrieve a Recurring Deposit Product", description = "Retrieve a Recurring Deposit Product")
	GetRecurringDepositProductProductIdResponse retrieveOne(@PathVariable Long productId);

	@DeleteMapping("recurringdepositproducts/{productId}")
	@Operation(summary = "Delete a Recurring Deposit Product", description = "Deletes a Recurring Deposit Product")
	DeleteRecurringDepositProductResponse delete(@PathVariable Long productId);

	@PutMapping("recurringdepositproducts/{productId}")
	@Operation(summary = "Update a Recurring Deposit Product", description = "Updates a Recurring Deposit Product")
	PutRecurringDepositProductResponse update(@PathVariable Long productId,
			@RequestBody PutRecurringDepositProductRequest putRecurringDepositProductRequest);

	@PostMapping("recurringdepositproducts")
	@Operation(summary = "Create a Recurring Deposit Product", description = "Creates a Recurring Deposit Product")
	PostRecurringDepositProductResponse create(
			@RequestBody PostRecurringDepositProductsRequest postRecurringDepositProductsRequest);

	@GetMapping(value = "/recurringdepositproducts/template")
	Object retrieveAccountTemplate(@RequestParam(value = "clientId", required = false) Long clientId,
			@RequestParam(value = "groupId", required = false) Long groupId,
			@RequestParam(value = "productId", required = false) Long productId,
			@RequestParam(value = "staffInSelectedOfficeOnly", required = false, defaultValue = "false") Boolean staffInSelectedOfficeOnly);

	@GetMapping(value = "/recurringdepositproducts/template")
	Object retrieveProductTemplate();
}
