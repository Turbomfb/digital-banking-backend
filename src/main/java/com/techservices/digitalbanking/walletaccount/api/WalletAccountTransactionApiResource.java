/* (C)2024 */
package com.techservices.digitalbanking.walletaccount.api;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techservices.digitalbanking.core.configuration.security.SpringSecurityAuditorAware;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.core.domain.dto.TransactionDto;
import com.techservices.digitalbanking.core.domain.dto.request.ReceiptRequest;
import com.techservices.digitalbanking.core.domain.enums.TransactionType;
import com.techservices.digitalbanking.core.eBanking.model.data.FineractPageResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.SavingsAccountTransactionData;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.core.redis.service.RedisService;
import com.techservices.digitalbanking.core.service.ReceiptService;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import com.techservices.digitalbanking.customer.service.CustomerService;
import com.techservices.digitalbanking.walletaccount.domain.request.SavingsAccountTransactionRequest;
import com.techservices.digitalbanking.walletaccount.domain.request.StatementRequest;
import com.techservices.digitalbanking.walletaccount.domain.request.WalletInboundWebhookRequest;
import com.techservices.digitalbanking.walletaccount.domain.request.WalletPaymentOrderRequest;
import com.techservices.digitalbanking.walletaccount.domain.response.WalletPaymentOrderResponse;
import com.techservices.digitalbanking.walletaccount.service.WalletAccountStatementService;
import com.techservices.digitalbanking.walletaccount.service.WalletAccountTransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.techservices.digitalbanking.core.util.CommandUtil.GENERATE_OTP_COMMAND;

@Tag(name = "Wallet Account Transactions", description = "Operations for managing savings account transactions, payments, statements, and receipts")
@RequestMapping("api/v1/wallet-accounts/me/transactions")
@RestController
@RequiredArgsConstructor
@Slf4j
public class WalletAccountTransactionApiResource {
	private final WalletAccountTransactionService walletAccountTransactionService;
	private final WalletAccountStatementService statementService;
	private final CustomerService customerService;
	private final SpringSecurityAuditorAware springSecurityAuditorAware;
	private final RedisService redisService;
	private final ReceiptService receiptService;

	@Operation(summary = "Process transaction command", description = "Processes various transaction commands for the authenticated customer's savings account. Default command generates OTP for transaction verification.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Transaction command processed successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericApiResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid request data or command parameters", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "401", description = "Authentication required", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "403", description = "Access denied - insufficient permissions", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json"))})
	@PostMapping
	public ResponseEntity<GenericApiResponse> processTransactionCommand(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Transaction request containing amount, recipient details, and transaction parameters", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = SavingsAccountTransactionRequest.class))) @RequestBody SavingsAccountTransactionRequest request,
			@Parameter(name = "command", description = "Transaction command to execute. Defaults to 'GENERATE_OTP_COMMAND' if not specified.", schema = @Schema(type = "string", defaultValue = GENERATE_OTP_COMMAND, example = GENERATE_OTP_COMMAND)) @RequestParam(value = "command", required = false, defaultValue = GENERATE_OTP_COMMAND) @Valid String command) {

		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		return ResponseEntity
				.ok(walletAccountTransactionService.processTransactionCommand(command, request, customerId));
	}

	@Operation(summary = "Initiate payment order", description = "Initiates a new payment order for transferring funds from the customer's wallet account to external recipients.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Payment order initiated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = WalletPaymentOrderResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid payment request or insufficient funds", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "401", description = "Authentication required", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "403", description = "Access denied - insufficient permissions", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "422", description = "Payment processing failed due to business rules", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json"))})
	@PostMapping("/initiate-payment-order")
	public ResponseEntity<WalletPaymentOrderResponse> initiatePaymentOrder(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Payment order request containing recipient details, amount, and payment instructions", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = WalletPaymentOrderRequest.class))) @RequestBody WalletPaymentOrderRequest request) {

		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		WalletPaymentOrderResponse responseOrder = walletAccountTransactionService.initiatePaymentOrder(request,
				customerId);
		return ResponseEntity.ok(responseOrder);
	}

	@Operation(summary = "Receive inbound webhook", description = "Handles incoming webhook notifications from external payment providers to update transaction statuses and process callbacks.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Webhook processed successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericApiResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid webhook payload or signature verification failed", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "422", description = "Webhook payload could not be processed", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json"))})
	@PostMapping("/webhooks")
	public ResponseEntity<GenericApiResponse> receiveInboundWebhook(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Webhook payload from external payment provider containing transaction updates", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = WalletInboundWebhookRequest.class))) @RequestBody WalletInboundWebhookRequest request) {

		try {
			GenericApiResponse webhookResponse = walletAccountTransactionService.receiveInboundWebhook(request);
			return ResponseEntity.ok(webhookResponse);
		} catch (Exception e) {
			log.error("Error processing webhook: {}", e.getMessage(), e);
			throw new ValidationException("error.webhook.processing", "Unable to process webhook notification.",
					e.getMessage());
		}
	}

	@Operation(summary = "Retrieve savings account transactions", description = "Retrieves a paginated list of transactions for the authenticated customer's savings account with optional filtering by date range, transaction type, and product ID.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Transactions retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FineractPageResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid query parameters or date format", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "401", description = "Authentication required", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "403", description = "Access denied - insufficient permissions", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Savings account not found", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json"))})
	@GetMapping
	public ResponseEntity<BasePageResponse<TransactionDto>> retrieveSavingsAccountTransactions(
			@Parameter(name = "size", description = "Number of transactions per page", schema = @Schema(type = "integer", minimum = "1", maximum = "1000", example = "20")) @RequestParam(required = false) Long size,
			@Parameter(name = "startDate", description = "Start date for filtering transactions (format depends on dateFormat parameter)", schema = @Schema(type = "string", example = "2024-01-01")) @RequestParam(required = false) String startDate,
			@Parameter(name = "endDate", description = "End date for filtering transactions (format depends on dateFormat parameter)", schema = @Schema(type = "string", example = "2024-01-31")) @RequestParam(required = false) String endDate,
			@Parameter(name = "transactionType", description = "Filter by transaction type", schema = @Schema(type = "string", allowableValues = {
					"CREDIT", "DEBIT",
					"ALL"})) @RequestParam(value = "transactionType", required = false) TransactionType transactionType) {

		try {
			Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();

			BasePageResponse<TransactionDto> savingsAccountTransactions = walletAccountTransactionService
					.retrieveSavingsAccountTransactions(customerId, startDate, endDate, size, transactionType);
			return ResponseEntity.ok(savingsAccountTransactions);
		} catch (Exception e) {
			log.error("Error retrieving transactions for customer {}: {}",
					springSecurityAuditorAware.getAuthenticatedUser().getUserId(), e.getMessage(), e);
			throw new ValidationException("error.transaction.retrieval",
					"We're unable to retrieve your transaction history at the moment. Please try again later",
					e.getMessage());
		}
	}

	@GetMapping("/statement")
	@Operation(summary = "Generate account statement", description = "Generates and exports account statement in various formats (CSV, PDF, Excel) for a specified date range. The generated file is directly streamed to the client for download.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Statement generated and downloaded successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid request parameters or date range", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "401", description = "Authentication required", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "403", description = "Access denied - insufficient permissions", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Account not found", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json"))})
	public ResponseEntity<Void> generateAccountStatement(
			@Parameter(name = "startDate", description = "Start date for statement period (ISO date format: YYYY-MM-DD)", schema = @Schema(type = "string", format = "date", example = "2024-01-01")) @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@Parameter(name = "endDate", description = "End date for statement period (ISO date format: YYYY-MM-DD)", schema = @Schema(type = "string", format = "date", example = "2024-01-31")) @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
			@Parameter(name = "productId", description = "Filter transactions by specific product ID", schema = @Schema(type = "integer", format = "int64", minimum = "1")) @RequestParam(required = false) @Positive Long productId,
			@Parameter(name = "offset", description = "Number of records to skip for pagination", schema = @Schema(type = "integer", format = "int64", minimum = "0", defaultValue = "0")) @RequestParam(value = "offset", required = false, defaultValue = "0") @Min(0) Long offset,
			@Parameter(name = "limit", description = "Maximum number of records to include in statement", schema = @Schema(type = "integer", format = "int64", minimum = "1", maximum = "10000", defaultValue = "1000")) @RequestParam(value = "limit", required = false, defaultValue = "1000") @Min(1) @Max(10000) Long limit,
			@Parameter(name = "format", description = "Export format for the statement", schema = @Schema(type = "string", allowableValues = {
					"CSV", "PDF",
					"EXCEL"}, defaultValue = "CSV")) @RequestParam(value = "format", required = false, defaultValue = "CSV") @Pattern(regexp = "CSV|PDF|EXCEL") String format,
			@Parameter(name = "includeReversals", description = "Whether to include reversed transactions in the statement", schema = @Schema(type = "boolean", defaultValue = "false")) @RequestParam(value = "includeReversals", required = false, defaultValue = "false") Boolean includeReversals,
			@Parameter(name = "transactionType", description = "Filter transactions by type", schema = @Schema(type = "string", allowableValues = {
					"CREDIT", "DEBIT",
					"ALL"})) @RequestParam(value = "transactionType", required = false) @Pattern(regexp = "CREDIT|DEBIT|ALL") String transactionType,
			HttpServletRequest request, HttpServletResponse response) {

		try {
			Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();

			log.info("Generating statement for account: {} with format: {}", customerId, format);

			StatementRequest statementRequest = StatementRequest.buildStatementRequest(startDate, endDate, productId,
					offset, limit, includeReversals, transactionType);
			String savingsAccountId = customerService.getCustomerById(customerId).getAccountId();
			statementRequest.setSavingsId(savingsAccountId);
			statementRequest.setCustomerId(customerId);
			log.info("Date Range: {} to {}", statementRequest.getStartDate(), statementRequest.getEndDate());
			Customer customer = customerService.getCustomerById(customerId);

			switch (format.toUpperCase()) {
				case "CSV" :
					this.statementService.generateCsvStatement(statementRequest, response, customer);
					break;
				case "PDF" :
					this.statementService.generatePdfStatement(statementRequest, response, customer);
					break;
				case "EXCEL" :
					this.statementService.generateExcelStatement(statementRequest, response, customer);
					break;
				default :
					throw new IllegalArgumentException("Unsupported format: " + format);
			}

			log.info("Statement generated successfully for account: {}", customerId);
			return ResponseEntity.ok().build();

		} catch (Exception e) {
			log.error("Error generating statement for customer {}: {}",
					springSecurityAuditorAware.getAuthenticatedUser().getUserId(), e.getMessage(), e);
			throw new ValidationException("error.statement.generation",
					"We're unable to generate your account statement at the moment. Please try again or contact support",
					e.getMessage());
		}
	}

	@Operation(summary = "Retrieve specific transaction by ID", description = "Retrieves detailed information for a specific transaction belonging to the authenticated customer's savings account.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Transaction retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SavingsAccountTransactionData.class))),
			@ApiResponse(responseCode = "400", description = "Invalid transaction ID", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "401", description = "Authentication required", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "403", description = "Access denied - transaction does not belong to authenticated user", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Transaction not found", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json"))})
	@GetMapping("/{transactionId}")
	public ResponseEntity<SavingsAccountTransactionData> retrieveSavingsAccountTransactionById(
			@Parameter(name = "transactionId", description = "Unique identifier of the transaction to retrieve", required = true, schema = @Schema(type = "integer", format = "int64", example = "12345")) @PathVariable(required = false) Long transactionId) {

		try {
			Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
			return ResponseEntity.ok(
					walletAccountTransactionService.retrieveSavingsAccountTransactionById(customerId, transactionId));
		} catch (Exception e) {
			log.error("Error retrieving transaction {} for customer {}: {}", transactionId,
					springSecurityAuditorAware.getAuthenticatedUser().getUserId(), e.getMessage(), e);
			throw new ValidationException("error.transaction.not.found",
					"We're unable to find the requested transaction.", e.getMessage());
		}
	}

	@GetMapping("/receipt")
	@Operation(summary = "Generate transaction receipt", description = "Generates and exports transaction receipt in various formats (PDF, PNG, JPEG) for a specific transaction. The receipt includes transaction details, customer information, and bank branding.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Receipt generated and downloaded successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid request parameters or transaction reference not found", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "401", description = "Authentication required", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "403", description = "Access denied - transaction does not belong to authenticated user", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Transaction not found", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json"))})
	public ResponseEntity<Void> generateTransactionReceipt(
			@Parameter(name = "format", description = "Export format for the receipt", schema = @Schema(type = "string", allowableValues = {
					"PDF", "PNG",
					"JPEG"}, defaultValue = "PDF")) @RequestParam(value = "format", required = false, defaultValue = "PDF") @Pattern(regexp = "PDF|PNG|JPEG") String format,
			@Parameter(name = "reference", description = "Transaction reference number used to retrieve transaction details from cache", required = true, schema = @Schema(type = "string", example = "TXN123456789")) @RequestParam(value = "reference") String reference,
			HttpServletRequest request, HttpServletResponse response) {

		try {
			SavingsAccountTransactionRequest savingsAccountTransactionRequest = this.redisService
					.retrieveData(reference, SavingsAccountTransactionRequest.class);
			Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();

			log.info("Generating receipt for transaction: {} for customer: {} in format: {}", reference, customerId,
					format);

			ReceiptRequest receiptRequest = ReceiptRequest.builder().transactionId(reference)
					.amount(savingsAccountTransactionRequest.getAmount())
					.recipientBankName(savingsAccountTransactionRequest.getBankName())
					.recipientAccountNumber(savingsAccountTransactionRequest.getAccountNumber())
					.recipientAccountName(savingsAccountTransactionRequest.getAccountName()).currency("NGN")
					.receiptFormat(format).build();

			switch (format.toUpperCase()) {
				case "PDF" :
					receiptService.generatePdfReceipt(receiptRequest, customerId, response);
					break;
				case "PNG" :
				case "JPEG" :
					receiptService.generateImageReceipt(receiptRequest, customerId, response, format);
					break;
				default :
					throw new IllegalArgumentException("Unsupported format: " + format);
			}

			log.info("Receipt generated successfully for transaction: {}", reference);
			return ResponseEntity.ok().build();

		} catch (Exception e) {
			log.error("Error generating receipt for transaction {}: {}", reference, e.getMessage(), e);
			throw new ValidationException("error.receipt.generation",
					"We're unable to generate your transaction receipt at the moment. Please verify the transaction reference and try again",
					e.getMessage());
		}
	}
}
