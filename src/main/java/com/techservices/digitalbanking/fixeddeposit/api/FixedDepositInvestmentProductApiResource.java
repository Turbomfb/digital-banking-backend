/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.fixeddeposit.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techservices.digitalbanking.core.fineract.model.request.PostFixedDepositProductsRequest;
import com.techservices.digitalbanking.core.fineract.model.response.DeleteFixedDepositProductsProductIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetFixedDepositProductsProductIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetFixedDepositProductsResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostFixedDepositProductsResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PutFixedDepositProductsProductIdRequest;
import com.techservices.digitalbanking.fixeddeposit.service.FixedDepositInvestmentProductService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RequestMapping("api/v1/fixed-investment-products")
@RestController
@RequiredArgsConstructor
public class FixedDepositInvestmentProductApiResource {
	private final FixedDepositInvestmentProductService fixedDepositInvestmentProductService;

	@Operation(summary = "Create an Investment Product")
	@PostMapping()
	public ResponseEntity<PostFixedDepositProductsResponse> createProduct(
			@RequestBody PostFixedDepositProductsRequest postFixedDepositProductsRequest) {
		PostFixedDepositProductsResponse postRecurringDepositProductResponse = fixedDepositInvestmentProductService
				.createProduct(postFixedDepositProductsRequest);

		return ResponseEntity.ok(postRecurringDepositProductResponse);
	}

	@Operation(summary = "Retrieve Investment Product List")
	@GetMapping
	public ResponseEntity<List<GetFixedDepositProductsResponse>> retrieveAllProducts() {
		List<GetFixedDepositProductsResponse> investmentProducts = fixedDepositInvestmentProductService
				.retrieveAllProducts();
		return ResponseEntity.ok(investmentProducts);
	}

	@Operation(summary = "Retrieve an Investment product")
	@GetMapping("{productId}")
	public ResponseEntity<GetFixedDepositProductsProductIdResponse> retrieveInvestmentProductById(
			@PathVariable Long productId) {
		GetFixedDepositProductsProductIdResponse products = fixedDepositInvestmentProductService
				.retrieveInvestmentProductById(productId);
		return ResponseEntity.ok(products);
	}

	@PutMapping("{productId}")
	public ResponseEntity<Object> updateProduct(@PathVariable Long productId,
			@RequestBody PutFixedDepositProductsProductIdRequest request) {
		return ResponseEntity.ok(fixedDepositInvestmentProductService.updateProduct(productId, request));
	}

	@DeleteMapping("{productId}")
	public ResponseEntity<DeleteFixedDepositProductsProductIdResponse> deleteProduct(@PathVariable Long productId) {
		return ResponseEntity.ok(fixedDepositInvestmentProductService.deleteProduct(productId));
	}

	@GetMapping(value = "/template")
	public ResponseEntity<Object> retrieveProductTemplate() {
		return ResponseEntity.ok(fixedDepositInvestmentProductService.getTemplate());
	}
}
