/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.loan.api;

import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.GetLoanProductsProductIdResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.GetLoanProductsTemplateResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.LoanProductListResponse;
import com.techservices.digitalbanking.core.eBanking.service.LoanService;
import com.techservices.digitalbanking.loan.domain.request.LoanScheduleCalculationRequest;
import com.techservices.digitalbanking.loan.domain.response.LoanScheduleCalculationResponse;
import com.techservices.digitalbanking.loan.service.LoanProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Loan Products", description = "API for managing loan product configurations, templates, and loan schedule calculations")
@RequestMapping("api/v1/loan-products")
@RestController
@RequiredArgsConstructor
public class LoanProductApiResource {

	private final LoanProductService loanProductService;



	@Operation(summary = "Retrieve loan product list")
	@GetMapping
	public ResponseEntity<BasePageResponse<LoanProductListResponse>> getLoanProducts(
			@RequestParam(value = "fields", required = false) Long fields) {
		BasePageResponse<LoanProductListResponse> getLoanProductsProductIdResponses = loanProductService
				.getLoanProducts(fields);

		return ResponseEntity.ok(getLoanProductsProductIdResponses);
	}

	@Operation(
			summary = "Retrieve a loan product by ID",
			description = "Fetches detailed configuration information for a specific loan product including " +
					"interest rates, terms, eligibility criteria, fees structure, repayment options, " +
					"and all associated business rules. Supports field filtering and template inclusion for optimized responses."
	)
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "Successfully retrieved loan product details",
					content = @Content(
							mediaType = "application/json",
							schema = @Schema(implementation = GetLoanProductsProductIdResponse.class)
					)
			),
			@ApiResponse(
					responseCode = "400",
					description = "Invalid product ID format or query parameters",
					content = @Content(schema = @Schema(hidden = true))
			),
			@ApiResponse(
					responseCode = "404",
					description = "Loan product not found or not available",
					content = @Content(schema = @Schema(hidden = true))
			),
			@ApiResponse(
					responseCode = "500",
					description = "Internal server error occurred while retrieving loan product details",
					content = @Content(schema = @Schema(hidden = true))
			)
	})
	@GetMapping("/{productId}")
	public ResponseEntity<GetLoanProductsProductIdResponse> getLoanProductById(
			@Parameter(
					name = "productId",
					description = "Unique identifier of the loan product to retrieve",
					required = true,
					schema = @Schema(type = "integer", format = "int64", minimum = "1")
			)
			@PathVariable Long productId,
			@Parameter(
					name = "fields",
					description = "Comma-separated list of specific fields to include in the response for bandwidth optimization. " +
							"If not specified, all available fields are returned. " +
							"Example field names: name, shortName, interestRatePerPeriod, numberOfRepayments, repaymentEvery",
					schema = @Schema(type = "integer", format = "int64")
			)
			@RequestParam(value = "fields", required = false) Long fields,
			@Parameter(
					name = "template",
					description = "Flag to include template data with dropdown options, reference data, and default values " +
							"useful for loan product configuration and loan application forms. " +
							"Set to 1 to include template data, 0 or omit to exclude.",
					schema = @Schema(type = "integer", format = "int64", allowableValues = {"0", "1"})
			)
			@RequestParam(value = "template", required = false) Long template) {
		GetLoanProductsProductIdResponse getLoanProductsProductIdResponse = loanProductService
				.getLoanProductById(productId, fields, template);

		return ResponseEntity.ok(getLoanProductsProductIdResponse);
	}

	@Operation(
			summary = "Retrieve loan product template",
			description = "Fetches the comprehensive loan product template containing all available configuration options, " +
					"reference data, dropdown values, default settings, and validation rules. " +
					"Essential for loan product creation, modification workflows, and understanding available options " +
					"for loan product management systems."
	)
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "Successfully retrieved loan product template",
					content = @Content(
							mediaType = "application/json",
							schema = @Schema(implementation = GetLoanProductsTemplateResponse.class)
					)
			),
			@ApiResponse(
					responseCode = "500",
					description = "Internal server error occurred while retrieving loan product template",
					content = @Content(schema = @Schema(hidden = true))
			)
	})
	@GetMapping("/template")
	public ResponseEntity<GetLoanProductsTemplateResponse> retrieveLoanProductTemplate() {
		GetLoanProductsTemplateResponse getLoanProductsTemplateResponse = loanProductService
				.retrieveLoanProductTemplate();

		return ResponseEntity.ok(getLoanProductsTemplateResponse);
	}
}
