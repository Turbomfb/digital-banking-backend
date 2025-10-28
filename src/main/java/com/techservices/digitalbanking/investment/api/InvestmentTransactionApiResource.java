/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.investment.api;

import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.TransactionDto;
import com.techservices.digitalbanking.core.domain.enums.TransactionType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techservices.digitalbanking.core.eBanking.model.response.GetRecurringDepositTransactionResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostRecurringDepositTransactionCommandResponse;
import com.techservices.digitalbanking.investment.domain.request.RecurringDepositTransactionCommandRequest;
import com.techservices.digitalbanking.investment.service.InvestmentTransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Investment Transactions", description = "API for managing investment transactions including deposits and withdrawals")
@RequestMapping("api/v1/investments/{investmentId}/transactions")
@RestController
@RequiredArgsConstructor
public class InvestmentTransactionApiResource {
	private final InvestmentTransactionService investmentTransactionService;

	@Operation(
			summary = "Retrieve all investment transactions",
			description = "Fetches a complete list of all transactions associated with a specific investment account. " +
					"This includes both deposit and withdrawal transactions with their respective details, " +
					"timestamps, and current status."
	)
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "Successfully retrieved all investment transactions",
					content = @Content(
							mediaType = "application/json",
							schema = @Schema(implementation = GetRecurringDepositTransactionResponse.class)
					)
			),
			@ApiResponse(
					responseCode = "404",
					description = "Investment account not found",
					content = @Content(schema = @Schema(hidden = true))
			),
			@ApiResponse(
					responseCode = "400",
					description = "Invalid investment ID format",
					content = @Content(schema = @Schema(hidden = true))
			),
			@ApiResponse(
					responseCode = "500",
					description = "Internal server error occurred while retrieving transactions",
					content = @Content(schema = @Schema(hidden = true))
			)
	})
	@GetMapping
	public ResponseEntity<BasePageResponse<TransactionDto>> retrieveAllInvestmentTransactions(
			@Parameter(
					name = "investmentId",
					description = "Unique identifier of the investment account",
					required = true,
					schema = @Schema(type = "string")
			)
			@PathVariable String investmentId,

			@Parameter(
					name = "size",
					description = "Number of transactions per page",
					schema = @Schema(type = "integer", minimum = "1", maximum = "1000", example = "20")
			)
			@RequestParam(required = false) Long size,

			@Parameter(
					name = "startDate",
					description = "Start date for filtering transactions (format depends on dateFormat parameter)",
					schema = @Schema(type = "string", example = "2024-01-01")
			)
			@RequestParam(required = false) String startDate,

			@Parameter(
					name = "endDate",
					description = "End date for filtering transactions (format depends on dateFormat parameter)",
					schema = @Schema(type = "string", example = "2024-01-31")
			)
			@RequestParam(required = false) String endDate,

			@Parameter(
					name = "transactionType",
					description = "Filter by transaction type",
					schema = @Schema(type = "string", allowableValues = {"CREDIT", "DEBIT", "ALL"})
			)
			@RequestParam(value = "transactionType", required = false) TransactionType transactionType
	) {
		BasePageResponse<TransactionDto>  investmentTransactions = investmentTransactionService
				.retrieveAllInvestmentTransactions(investmentId, startDate, endDate, size, transactionType);
		return ResponseEntity.ok(investmentTransactions);
	}

	@Operation(
			summary = "Retrieve an investment transaction",
			description = "Fetches detailed information about a specific investment transaction using its unique " +
					"transaction identifier. Returns comprehensive transaction data including amount, " +
					"timestamp, type, and processing status."
	)
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "Successfully retrieved the investment transaction",
					content = @Content(
							mediaType = "application/json",
							schema = @Schema(implementation = GetRecurringDepositTransactionResponse.class)
					)
			),
			@ApiResponse(
					responseCode = "404",
					description = "Investment account or transaction not found",
					content = @Content(schema = @Schema(hidden = true))
			),
			@ApiResponse(
					responseCode = "400",
					description = "Invalid investment ID or transaction ID format",
					content = @Content(schema = @Schema(hidden = true))
			),
			@ApiResponse(
					responseCode = "500",
					description = "Internal server error occurred while retrieving the transaction",
					content = @Content(schema = @Schema(hidden = true))
			)
	})
	@GetMapping("{transactionId}")
	public ResponseEntity<GetRecurringDepositTransactionResponse> retrieveInvestmentTransactionById(
			@Parameter(
					name = "investmentId",
					description = "Unique identifier of the investment account",
					required = true,
					schema = @Schema(type = "string", format = "uuid")
			)
			@PathVariable String investmentId,
			@Parameter(
					name = "transactionId",
					description = "Unique identifier of the specific transaction to retrieve",
					required = true,
					schema = @Schema(type = "integer", format = "int64", minimum = "1")
			)
			@PathVariable Long transactionId) {
		GetRecurringDepositTransactionResponse investmentTransaction = investmentTransactionService
				.retrieveInvestmentTransactionById(investmentId, transactionId);
		return ResponseEntity.ok(investmentTransaction);
	}

	@Operation(
			summary = "Process investment transaction command",
			description = "Executes investment transaction commands for recurring deposit accounts. " +
					"Supports both deposit operations (adding funds to the investment) and withdrawal operations " +
					"(removing funds from the investment). The specific operation is determined by the command parameter. " +
					"All transactions are subject to account rules, balance checks, and regulatory compliance."
	)
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "Transaction command processed successfully",
					content = @Content(
							mediaType = "application/json",
							schema = @Schema(implementation = PostRecurringDepositTransactionCommandResponse.class)
					)
			),
			@ApiResponse(
					responseCode = "400",
					description = "Invalid request parameters, malformed request body, or business rule violation",
					content = @Content(schema = @Schema(hidden = true))
			),
			@ApiResponse(
					responseCode = "404",
					description = "Investment account not found",
					content = @Content(schema = @Schema(hidden = true))
			),
			@ApiResponse(
					responseCode = "422",
					description = "Transaction cannot be processed due to insufficient funds, account restrictions, or regulatory constraints",
					content = @Content(schema = @Schema(hidden = true))
			),
			@ApiResponse(
					responseCode = "500",
					description = "Internal server error occurred while processing the transaction",
					content = @Content(schema = @Schema(hidden = true))
			)
	})
	@PostMapping()
	public ResponseEntity<PostRecurringDepositTransactionCommandResponse> processInvestmentTransactionCommand(
			@Parameter(
					name = "investmentId",
					description = "Unique identifier of the investment account on which to perform the transaction",
					required = true,
					schema = @Schema(type = "string", format = "uuid")
			)
			@PathVariable("investmentId") String investmentId,
			@Parameter(
					description = "Transaction request payload containing amount, currency, and additional transaction details. " +
							"Required for deposit and withdrawal operations. Structure may vary based on command type.",
					required = false,
					content = @Content(
							mediaType = "application/json",
							schema = @Schema(implementation = RecurringDepositTransactionCommandRequest.class)
					)
			)
			@Valid @RequestBody(required = false) RecurringDepositTransactionCommandRequest request,
			@Parameter(
					name = "command",
					description = "The type of transaction operation to perform. " +
							"Supported values: 'deposit' (add funds to the investment), " +
							"'withdraw' (remove funds from the investment). " +
							"Each command has specific validation rules and business logic.",
					required = true,
					schema = @Schema(
							type = "string",
							allowableValues = {"deposit", "withdraw"}
					)
			)
			@Valid @RequestParam(value = "command") String command) {
		PostRecurringDepositTransactionCommandResponse postRecurringDepositTransactionCommandResponse = investmentTransactionService
				.processInvestmentTransactionCommand(investmentId, request, command);

		return ResponseEntity.ok(postRecurringDepositTransactionCommandResponse);
	}
}
