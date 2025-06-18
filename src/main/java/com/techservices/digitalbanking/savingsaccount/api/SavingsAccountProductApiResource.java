/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.savingsaccount.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techservices.digitalbanking.core.fineract.model.request.PostSavingsAccountProductRequest;
import com.techservices.digitalbanking.core.fineract.model.request.PutSavingsAccountProductRequest;
import com.techservices.digitalbanking.core.fineract.model.response.GetSavingsAccountProductsResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostSavingsAccountProductsResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PutSavingsAccountProductsProductIdResponse;
import com.techservices.digitalbanking.savingsaccount.service.SavingsAccountProductService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RequestMapping("api/v1/savings-accounts/products")
@RestController
@RequiredArgsConstructor
public class SavingsAccountProductApiResource {
	private final SavingsAccountProductService savingsAccountProductService;

	@Operation(summary = "Create a Savings Account Product")
	@PostMapping()
	public ResponseEntity<PostSavingsAccountProductsResponse> createSavingsAccountProduct(
			@RequestBody PostSavingsAccountProductRequest postSavingsAccountProductRequest) {
		PostSavingsAccountProductsResponse postSavingsAccountProductsResponse = savingsAccountProductService
				.createSavingsAccountProduct(postSavingsAccountProductRequest);

		return ResponseEntity.ok(postSavingsAccountProductsResponse);
	}

	@Operation(summary = "Retrieve Savings Account Product List")
	@GetMapping
	public ResponseEntity<List<GetSavingsAccountProductsResponse>> retrieveAllSavingsAccountProducts() {
		List<GetSavingsAccountProductsResponse> savingsAccountProducts = savingsAccountProductService
				.retrieveAllSavingsAccountProducts();
		return ResponseEntity.ok(savingsAccountProducts);
	}

	@Operation(summary = "Retrieve a Savings product")
	@GetMapping("{productId}")
	public ResponseEntity<GetSavingsAccountProductsResponse> retrieveSavingsProductById(@PathVariable Long productId) {
		GetSavingsAccountProductsResponse products = savingsAccountProductService.retrieveSavingsProductById(productId);
		return ResponseEntity.ok(products);
	}

	@PutMapping("{productId}")
	public ResponseEntity<PutSavingsAccountProductsProductIdResponse> updateSavingsAccountProduct(
			@PathVariable Long productId, @RequestBody PutSavingsAccountProductRequest request) {
		return ResponseEntity.ok(savingsAccountProductService.updateSavingsAccountProduct(productId, request));
	}
}
