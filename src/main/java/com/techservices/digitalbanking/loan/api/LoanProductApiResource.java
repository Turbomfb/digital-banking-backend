/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.loan.api;

import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.fineract.model.request.PostLoanProductsRequest;
import com.techservices.digitalbanking.core.fineract.model.response.GetLoanProductsProductIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetLoanProductsTemplateResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostLoanProductsResponse;
import com.techservices.digitalbanking.core.fineract.service.LoanService;
import com.techservices.digitalbanking.loan.domain.request.LoanScheduleCalculationRequest;
import com.techservices.digitalbanking.loan.domain.response.LoanScheduleCalculationResponse;
import com.techservices.digitalbanking.loan.service.LoanProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/loan-products")
@RestController
@RequiredArgsConstructor
public class LoanProductApiResource {

	private final LoanProductService loanProductService;
	private final LoanService loanService;

	@Operation(summary = "Create a new Loan Product")
	@PostMapping
	public ResponseEntity<PostLoanProductsResponse> createALoanProduct(
			@Validated @RequestBody PostLoanProductsRequest postLoanProductRequest) {
		PostLoanProductsResponse postLoanProductsResponse = loanProductService
				.createALoanProduct(postLoanProductRequest);

		return ResponseEntity.ok(postLoanProductsResponse);
	}

	@Operation(summary = "Retrieve loan product list")
	@GetMapping
	public ResponseEntity<BasePageResponse<GetLoanProductsProductIdResponse>> getLoanProducts(
			@RequestParam(value = "fields", required = false) Long fields) {
		BasePageResponse<GetLoanProductsProductIdResponse> getLoanProductsProductIdResponses = loanProductService
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

	@Operation(summary = "Calculate Loan Schedule")
	@PostMapping("loan-schedule-calculation")
	public ResponseEntity<LoanScheduleCalculationResponse> calculateLoanSchedule(
			@RequestBody @Valid LoanScheduleCalculationRequest loanScheduleCalculationRequest
	) {
		LoanScheduleCalculationResponse loanScheduleCalculationResponse = loanService.calculateLoanSchedule(loanScheduleCalculationRequest);

		return ResponseEntity.ok(loanScheduleCalculationResponse);
	}
}
