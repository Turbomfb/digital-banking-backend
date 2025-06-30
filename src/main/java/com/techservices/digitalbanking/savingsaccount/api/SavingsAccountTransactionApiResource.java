/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.savingsaccount.api;

import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.customer.service.CustomerService;
import com.techservices.digitalbanking.savingsaccount.domain.request.SavingsAccountTransactionRequest;
import com.techservices.digitalbanking.savingsaccount.domain.request.StatementRequest;
import com.techservices.digitalbanking.savingsaccount.service.SavingsAccountStatementService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.techservices.digitalbanking.core.fineract.model.data.FineractPageResponse;
import com.techservices.digitalbanking.core.fineract.model.response.SavingsAccountTransactionData;
import com.techservices.digitalbanking.savingsaccount.service.SavingsAccountTransactionService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.security.InvalidParameterException;
import java.time.LocalDate;

import static com.techservices.digitalbanking.core.util.CommandUtil.GENERATE_OTP_COMMAND;

@RequestMapping("api/v1/savings-accounts/{customerId}/transactions")
@RestController
@RequiredArgsConstructor
@Slf4j
public class SavingsAccountTransactionApiResource {
	private final SavingsAccountTransactionService savingsAccountTransactionService;
	private final SavingsAccountStatementService statementService;
	private final CustomerService customerService;

	@PostMapping
	public ResponseEntity<GenericApiResponse> processTransactionCommand(
			@RequestBody SavingsAccountTransactionRequest request,
			@RequestParam(value = "command", required = false, defaultValue = GENERATE_OTP_COMMAND) @Valid String command,
			@PathVariable Long customerId
	) {
		return ResponseEntity.ok(savingsAccountTransactionService.processTransactionCommand(command, request, customerId));
	}

	@GetMapping
	public ResponseEntity<FineractPageResponse<SavingsAccountTransactionData>> retrieveSavingsAccountTransactions(
			@PathVariable Long customerId, @RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size, @RequestParam(required = false) String startDate,
			@RequestParam(required = false) String endDate, @RequestParam(required = false) String dateFormat,
			@RequestParam(required = false) Long productId, @RequestParam(value = "offset", required = false) @Valid Long offset,
			@RequestParam(value = "limit", required = false) @Valid Long limit,
			@RequestParam(value = "transactionType", required = false) @Valid String transactionType
	) {

		return ResponseEntity.ok(savingsAccountTransactionService.retrieveSavingsAccountTransactions(
				customerId, startDate, endDate, dateFormat, productId, limit, offset, transactionType));
	}

	@GetMapping("/statement")
	@Operation(summary = "Generate Account Statement",
			description = "Generate and export account statement in various formats (CSV, PDF, Excel)")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Statement generated successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid request parameters"),
			@ApiResponse(responseCode = "404", description = "Account not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Void> generateAccountStatement(
			@PathVariable @NotNull @Positive Long customerId,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
			@RequestParam(required = false) @Positive Long productId,
			@RequestParam(value = "offset", required = false, defaultValue = "0") @Min(0) Long offset,
			@RequestParam(value = "limit", required = false, defaultValue = "1000") @Min(1) @Max(10000) Long limit,
			@RequestParam(value = "format", required = false, defaultValue = "CSV") @Pattern(regexp = "CSV|PDF|EXCEL") String format,
			@RequestParam(value = "includeReversals", required = false, defaultValue = "false") Boolean includeReversals,
			@RequestParam(value = "transactionType", required = false) @Pattern(regexp = "CREDIT|DEBIT|ALL") String transactionType,
			HttpServletRequest request,
			HttpServletResponse response
	) {

		try {

			log.info("Generating statement for account: {} with format: {}", customerId, format);

			StatementRequest statementRequest = StatementRequest.buildStatementRequest(
					startDate, endDate, productId, offset, limit,
					includeReversals, transactionType
			);
			String savingsAccountId = customerService.getCustomerById(customerId).getAccountId();
			statementRequest.setSavingsId(Long.valueOf(savingsAccountId));
			System.err.println("Date Range: " + statementRequest.getStartDate() + " to " + statementRequest.getEndDate());

			switch (format.toUpperCase()) {
				case "CSV":
					this.statementService.generateCsvStatement(statementRequest, response);
					break;
				case "PDF":
					this.statementService.generatePdfStatement(statementRequest, response);
					break;
				case "EXCEL":
					this.statementService.generateExcelStatement(statementRequest, response);
					break;
				default:
					throw new IllegalArgumentException("Unsupported format: " + format);
			}

			log.info("Statement generated successfully for account: {}", customerId);
			return ResponseEntity.ok().build();

		} catch (InvalidParameterException e) {
			log.error("Invalid parameters for statement generation: {}", e.getMessage(), e);
			return ResponseEntity.badRequest().build();
		} catch (Exception e) {
			log.error("Error generating statement for account: {}", customerId, e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/{transactionId}")
	public ResponseEntity<SavingsAccountTransactionData> retrieveSavingsAccountTransactionById(
			@PathVariable Long savingsId, @PathVariable(required = false) Long transactionId,
			@RequestParam(required = false) Long productId) {
		return ResponseEntity.ok(savingsAccountTransactionService
				.retrieveSavingsAccountTransactionById(savingsId, transactionId, productId));
	}
}
