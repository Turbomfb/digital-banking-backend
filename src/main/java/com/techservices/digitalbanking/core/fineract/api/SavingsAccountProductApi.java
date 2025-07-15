/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.api;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.techservices.digitalbanking.core.fineract.model.request.PostSavingsAccountProductRequest;
import com.techservices.digitalbanking.core.fineract.model.request.PutSavingsAccountProductRequest;
import com.techservices.digitalbanking.core.fineract.model.response.GetSavingsAccountProductsResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostSavingsAccountProductsResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PutSavingsAccountProductsProductIdResponse;

public interface SavingsAccountProductApi {
	@GetMapping(value = "/savingsproducts/{productId}")
	GetSavingsAccountProductsResponse retrieveById(@PathVariable Long productId);

	@GetMapping(value = "/savingsproducts")
	List<GetSavingsAccountProductsResponse> retrieveAll();

	@PostMapping(value = "/savingsproducts")
	PostSavingsAccountProductsResponse createSavingsAccountProduct(
			@RequestBody PostSavingsAccountProductRequest postSavingsAccountProductRequest);

	@PutMapping(value = "/savingsproducts/{productId}")
	PutSavingsAccountProductsProductIdResponse updateSavingsAccountProduct(
			@RequestBody PutSavingsAccountProductRequest putSavingsAccountProductRequest, @PathVariable Long productId);
}
