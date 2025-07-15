/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.loan.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.techservices.digitalbanking.core.fineract.model.request.PostLoanProductsRequest;
import com.techservices.digitalbanking.core.fineract.model.response.GetLoanProductsProductIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetLoanProductsTemplateResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostLoanProductsResponse;
import com.techservices.digitalbanking.loan.service.LoanProductService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RequestMapping("api/v1/loan-products")
@RestController
@RequiredArgsConstructor
public class LoanProductApiResource {

	private final LoanProductService loanProductService;

	@Operation(summary = "Retrieve loan product list")
	@GetMapping
	public ResponseEntity<List<GetLoanProductsProductIdResponse>> getLoanProducts(
			@RequestParam(value = "fields", required = false) Long fields) {
		List<GetLoanProductsProductIdResponse> getLoanProductsProductIdResponses = loanProductService
				.getLoanProducts(fields);

		return ResponseEntity.ok(getLoanProductsProductIdResponses);
	}

	@Operation(summary = "Retrieve a loan product details by ID")
	@GetMapping("/{productId}")
	public ResponseEntity<GetLoanProductsProductIdResponse> getLoanProductById(@PathVariable Long productId,
			@RequestParam(value = "fields", required = false) Long fields,
			@RequestParam(value = "template", required = false) Long template) {
		GetLoanProductsProductIdResponse getLoanProductsProductIdResponse = loanProductService
				.getLoanProductById(productId, fields, template);

		return ResponseEntity.ok(getLoanProductsProductIdResponse);
	}

	@Operation(summary = "Retrieve a loan product template")
	@GetMapping("/template")
	public ResponseEntity<GetLoanProductsTemplateResponse> retrieveLoanProductTemplate() {
		GetLoanProductsTemplateResponse getLoanProductsTemplateResponse = loanProductService
				.retrieveLoanProductTemplate();

		return ResponseEntity.ok(getLoanProductsTemplateResponse);
	}
}
