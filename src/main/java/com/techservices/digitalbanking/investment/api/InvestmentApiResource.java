/* (C)2024 */
package com.techservices.digitalbanking.investment.api;

import java.util.Comparator;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.techservices.digitalbanking.core.configuration.security.SpringSecurityAuditorAware;
import com.techservices.digitalbanking.core.domain.BaseAppResponse;
import com.techservices.digitalbanking.core.domain.dto.AccountDto;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.ProductDto;
import com.techservices.digitalbanking.core.domain.dto.TransactionDto;
import com.techservices.digitalbanking.core.domain.enums.TransactionType;
import com.techservices.digitalbanking.investment.domain.enums.InvestmentType;
import com.techservices.digitalbanking.investment.domain.request.InvestmentApplicationRequest;
import com.techservices.digitalbanking.investment.domain.request.InvestmentCalculatorRequest;
import com.techservices.digitalbanking.investment.domain.request.InvestmentUpdateRequest;
import com.techservices.digitalbanking.investment.domain.response.InvestmentApplicationResponse;
import com.techservices.digitalbanking.investment.domain.response.InvestmentCalculatorResponse;
import com.techservices.digitalbanking.investment.service.InvestmentProductService;
import com.techservices.digitalbanking.investment.service.InvestmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Investment Management", description = "APIs for managing customer investment portfolios, applications, funding, withdrawals, and investment calculations")
@RequestMapping("api/v1/customers/me/investments")
@RestController
@RequiredArgsConstructor
public class InvestmentApiResource {
	private final InvestmentService investmentService;
	private final SpringSecurityAuditorAware springSecurityAuditorAware;
	private final InvestmentProductService investmentProductService;

	@Operation(summary = "Create a Lock Investment Application", description = "Submits a new lock investment application for the authenticated customer. Supports various investment types including fixed deposits, flex savings, and other investment products. The application will be processed according to the selected investment type and product terms.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Investment application created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = InvestmentApplicationResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid investment application data or business rule violation", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "401", description = "Authentication required", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "403", description = "Customer not eligible for the requested investment type", content = @Content(mediaType = "application/json"))})
	@SecurityRequirement(name = "bearerAuth")
	@PostMapping
	public ResponseEntity<InvestmentApplicationResponse> createAnInvestment(
			@Parameter(description = "Investment application request details including amount, tenor, and product preferences", required = true, content = @Content(schema = @Schema(implementation = InvestmentApplicationRequest.class))) @RequestBody InvestmentApplicationRequest request) {

		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		InvestmentApplicationResponse investment = investmentService.submitApplication(customerId, request);
		return ResponseEntity.ok(investment);
	}

	@Operation(summary = "Update Investment Details", description = "Updates the details of an existing investment such as auto-renewal settings, liquidation account, or other modifiable parameters. Not all investment parameters can be modified after creation.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Investment updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseAppResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid update request or investment cannot be modified in current state", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Investment not found or does not belong to the customer", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "409", description = "Investment is in a state that cannot be modified", content = @Content(mediaType = "application/json"))})
	@SecurityRequirement(name = "bearerAuth")
	@PutMapping("/{investmentId}")
	public ResponseEntity<BaseAppResponse> updateAnInvestment(
			@Parameter(description = "Investment update request with modifiable fields", required = true, content = @Content(schema = @Schema(implementation = InvestmentUpdateRequest.class))) @RequestBody InvestmentUpdateRequest request,
			@Parameter(description = "Type of investment being updated", schema = @Schema(implementation = InvestmentType.class)) @RequestParam(required = false) InvestmentType investmentType,
			@Parameter(description = "Unique identifier of the investment to update", required = true, example = "INV-2024-001") @PathVariable String investmentId) {

		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		BaseAppResponse investment = investmentService.updateAnInvestment(customerId, investmentType, request,
				investmentId);
		return ResponseEntity.ok(investment);
	}

	@Operation(summary = "Fund Lock Investment Account", description = "Adds funds to an existing lock investment account. This operation increases the principal amount and may affect interest calculations based on the investment type and terms.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Investment funded successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseAppResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid funding amount or insufficient balance in source account", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Investment account not found", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "422", description = "Investment type does not support additional funding", content = @Content(mediaType = "application/json"))})
	@SecurityRequirement(name = "bearerAuth")
	@PostMapping("/{investmentId}/fund")
	public ResponseEntity<BaseAppResponse> fundInvestment(
			@Parameter(description = "Funding request details including amount and source account", content = @Content(schema = @Schema(implementation = InvestmentUpdateRequest.class))) @RequestBody(required = false) InvestmentUpdateRequest request,
			@Parameter(description = "Type of investment being funded", schema = @Schema(implementation = InvestmentType.class)) @RequestParam(required = false) InvestmentType investmentType,
			@Parameter(description = "Unique identifier of the investment to fund", required = true, example = "INV-2024-001") @PathVariable String investmentId) {

		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		BaseAppResponse investment = investmentService.fundInvestment(customerId, investmentType, request,
				investmentId);
		return ResponseEntity.ok(investment);
	}

	@Operation(summary = "Fund Flex Investment", description = "Adds funds to a flexible investment account. Flex investments allow for frequent funding and withdrawals with competitive interest rates and high liquidity.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Flex investment funded successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseAppResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid funding request or insufficient balance", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "401", description = "Authentication required", content = @Content(mediaType = "application/json"))})
	@SecurityRequirement(name = "bearerAuth")
	@PostMapping("/flex/fund")
	public ResponseEntity<BaseAppResponse> fundFlexInvestment(
			@Parameter(description = "Flex investment funding request with amount and source account details", content = @Content(schema = @Schema(implementation = InvestmentUpdateRequest.class))) @RequestBody(required = false) InvestmentUpdateRequest request) {

		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		BaseAppResponse investment = investmentService.fundInvestment(customerId, InvestmentType.FLEX, request, "");
		return ResponseEntity.ok(investment);
	}

	@Operation(summary = "Withdraw from Flex Investment", description = "Withdraws funds from a flexible investment account. Flex investments offer the flexibility to withdraw funds at any time, though some restrictions may apply based on minimum balance requirements.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Withdrawal processed successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseAppResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid withdrawal amount or would violate minimum balance requirement", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Flex investment not found", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "422", description = "Insufficient balance for withdrawal", content = @Content(mediaType = "application/json"))})
	@SecurityRequirement(name = "bearerAuth")
	@PostMapping("/me/withdraw-flex")
	public ResponseEntity<BaseAppResponse> withdrawFlexInvestment(
			@Parameter(description = "Withdrawal request details including amount and destination account", content = @Content(schema = @Schema(implementation = InvestmentUpdateRequest.class))) @RequestBody(required = false) InvestmentUpdateRequest request) {

		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		BaseAppResponse investment = investmentService.withdrawFlexInvestment(customerId, request);
		return ResponseEntity.ok(investment);
	}

	@Operation(summary = "Calculate Investment Returns", description = "Calculates potential returns for an investment based on principal amount, interest rate, tenor, and compounding frequency. This tool helps customers make informed investment decisions.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Investment calculation completed successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = InvestmentCalculatorResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid calculation parameters", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "401", description = "Authentication required", content = @Content(mediaType = "application/json"))})
	@SecurityRequirement(name = "bearerAuth")
	@PostMapping("/calculate-investment")
	public ResponseEntity<InvestmentCalculatorResponse> calculateInvestment(
			@Parameter(description = "Investment calculation parameters including principal, rate, tenor, and compounding frequency", required = true, content = @Content(schema = @Schema(implementation = InvestmentCalculatorRequest.class))) @RequestBody InvestmentCalculatorRequest request) {

		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		InvestmentCalculatorResponse response = investmentService.calculateInvestment(customerId, request);
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "Retrieve Customer Investment Portfolio", description = "Retrieves all investments belonging to the authenticated customer, filtered by investment type. Returns investments sorted by creation date (newest first) with account details, balances, and status information.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Investment portfolio retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BasePageResponse.class))),
			@ApiResponse(responseCode = "401", description = "Authentication required", content = @Content(mediaType = "application/json"))})
	@SecurityRequirement(name = "bearerAuth")
	@GetMapping
	public ResponseEntity<BasePageResponse<AccountDto>> retrieveAllCustomerInvestments(
			@Parameter(description = "Filter investments by type", example = "FLEX", schema = @Schema(allowableValues = {
					"FLEX", "FIXED_DEPOSIT", "TARGET_SAVINGS",
					"ALL"}, defaultValue = "FLEX")) @RequestParam(required = false, defaultValue = "FLEX") String investmentType) {

		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		BasePageResponse<AccountDto> investment = investmentService.retrieveAllCustomerInvestments(customerId,
				investmentType);
		investment.setData(investment
				.getData().stream().sorted(Comparator
						.comparing(AccountDto::getId, Comparator.nullsLast(Comparator.naturalOrder())).reversed())
				.toList());
		return ResponseEntity.ok(investment);
	}

	@Operation(summary = "Retrieve Investment Details", description = "Retrieves detailed information about a specific investment including account balance, interest earned, transaction history summary, and investment terms.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Investment details retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AccountDto.class))),
			@ApiResponse(responseCode = "404", description = "Investment not found or does not belong to the customer", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "401", description = "Authentication required", content = @Content(mediaType = "application/json"))})
	@SecurityRequirement(name = "bearerAuth")
	@GetMapping("{id}")
	public ResponseEntity<AccountDto> retrieveInvestmentById(
			@Parameter(description = "Unique identifier of the investment", required = true, example = "12345") @PathVariable String id,
			@Parameter(description = "Type of investment to retrieve", schema = @Schema(implementation = InvestmentType.class)) @RequestParam(required = false) InvestmentType investmentType) {

		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		AccountDto investment = investmentService.retrieveInvestmentById(id, investmentType, customerId);
		return ResponseEntity.ok(investment);
	}

	@Operation(summary = "Retrieve Current Investment Product Information", description = "Retrieves information about the current investment product offerings including interest rates, terms, minimum investment amounts, and product features. This information helps customers understand available investment options.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Investment product information retrieved successfully", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Investment product not found for the specified type", content = @Content(mediaType = "application/json"))})
	@GetMapping("products/current")
	public ResponseEntity<ProductDto> retrieveCurrentInvestmentProduct(
			@Parameter(description = "Type of investment product to retrieve information for", example = "FLEX", schema = @Schema(allowableValues = {
					"FLEX", "FIXED_DEPOSIT",
					"TARGET_SAVINGS"}, defaultValue = "FLEX")) @RequestParam(required = false, defaultValue = "FLEX") String investmentType) {

		ProductDto product = investmentProductService.retrieveCurrentInvestmentProduct(investmentType);
		return ResponseEntity.ok(product);
	}

	@Operation(summary = "Retrieve Investment Transaction History", description = "Retrieves the complete transaction history for a specific investment including deposits, withdrawals, interest payments, and fees. Transactions can be filtered by charge status and other parameters.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Investment transaction history retrieved successfully", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Investment not found or no transactions available", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "401", description = "Authentication required", content = @Content(mediaType = "application/json"))})
	@SecurityRequirement(name = "bearerAuth")
	@GetMapping("{investmentId}/transactions")
	public ResponseEntity<BasePageResponse<TransactionDto>> retrieveInvestmentTransactionsById(
			@Parameter(name = "investmentId", description = "Unique identifier of the investment account", required = true, schema = @Schema(type = "string")) @PathVariable String investmentId,
			@Parameter(name = "size", description = "Number of transactions per page", schema = @Schema(type = "integer", minimum = "1", maximum = "1000", example = "20")) @RequestParam(required = false) Long size,
			@Parameter(name = "startDate", description = "Start date for filtering transactions (format depends on dateFormat parameter)", schema = @Schema(type = "string", example = "2024-01-01")) @RequestParam(required = false) String startDate,
			@Parameter(name = "endDate", description = "End date for filtering transactions (format depends on dateFormat parameter)", schema = @Schema(type = "string", example = "2024-01-31")) @RequestParam(required = false) String endDate,
			@Parameter(name = "transactionType", description = "Filter by transaction type", schema = @Schema(type = "string", allowableValues = {
					"CREDIT", "DEBIT",
					"ALL"})) @RequestParam(value = "transactionType", required = false) TransactionType transactionType,
			@Parameter(description = "Type of investment for transaction filtering", schema = @Schema(implementation = InvestmentType.class)) @RequestParam(required = false) String investmentType) {

		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		BasePageResponse<TransactionDto> investment = investmentService.retrieveInvestmentTransactionsById(investmentId,
				investmentType, customerId, transactionType, startDate, endDate, size);
		return ResponseEntity.ok(investment);
	}
}
