/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.investment.api;

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

import com.techservices.digitalbanking.core.fineract.model.request.PostRecurringDepositProductsRequest;
import com.techservices.digitalbanking.core.fineract.model.request.PutRecurringDepositProductRequest;
import com.techservices.digitalbanking.core.fineract.model.response.DeleteRecurringDepositProductResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetRecurringDepositProductProductIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostRecurringDepositProductResponse;
import com.techservices.digitalbanking.investment.service.InvestmentProductService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RequestMapping("api/v1/investment-products")
@RestController
@RequiredArgsConstructor
public class InvestmentProductApiResource {
	private final InvestmentProductService investmentProductService;

	@Operation(summary = "Retrieve Investment Product List")
	@GetMapping
	public ResponseEntity<List<GetRecurringDepositProductProductIdResponse>> retrieveAllProducts() {
		List<GetRecurringDepositProductProductIdResponse> investmentProducts = investmentProductService
				.retrieveAllProducts();
		return ResponseEntity.ok(investmentProducts);
	}

	@Operation(summary = "Retrieve an Investment product")
	@GetMapping("{productId}")
	public ResponseEntity<GetRecurringDepositProductProductIdResponse> retrieveInvestmentProductById(
			@PathVariable Long productId) {
		GetRecurringDepositProductProductIdResponse products = investmentProductService
				.retrieveInvestmentProductById(productId);
		return ResponseEntity.ok(products);
	}

	@GetMapping(value = "/template")
	public ResponseEntity<Object> retrieveProductTemplate() {
		return ResponseEntity.ok(investmentProductService.retrieveProductTemplate());
	}
}
