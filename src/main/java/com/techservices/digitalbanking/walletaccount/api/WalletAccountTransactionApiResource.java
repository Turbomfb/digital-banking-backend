/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.walletaccount.api;

import com.techservices.digitalbanking.core.configuration.security.SpringSecurityAuditorAware;
import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.core.domain.dto.request.ReceiptRequest;
import com.techservices.digitalbanking.core.redis.service.RedisService;
import com.techservices.digitalbanking.core.service.ReceiptService;
import com.techservices.digitalbanking.customer.service.CustomerService;
import com.techservices.digitalbanking.walletaccount.domain.request.SavingsAccountTransactionRequest;
import com.techservices.digitalbanking.walletaccount.domain.request.StatementRequest;
import com.techservices.digitalbanking.walletaccount.domain.request.WalletInboundWebhookRequest;
import com.techservices.digitalbanking.walletaccount.domain.request.WalletPaymentOrderRequest;
import com.techservices.digitalbanking.walletaccount.domain.response.WalletPaymentOrderResponse;
import com.techservices.digitalbanking.walletaccount.service.WalletAccountStatementService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.techservices.digitalbanking.core.eBanking.model.data.FineractPageResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.SavingsAccountTransactionData;
import com.techservices.digitalbanking.walletaccount.service.WalletAccountTransactionService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.Comparator;

import static com.techservices.digitalbanking.core.util.CommandUtil.GENERATE_OTP_COMMAND;

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

	@PostMapping
	public ResponseEntity<GenericApiResponse> processTransactionCommand(
			@RequestBody SavingsAccountTransactionRequest request,
			@RequestParam(value = "command", required = false, defaultValue = GENERATE_OTP_COMMAND) @Valid String command
	) {
		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		return ResponseEntity.ok(walletAccountTransactionService.processTransactionCommand(command, request, customerId));
	}

	@PostMapping("/initiate-payment-order")
	public ResponseEntity<WalletPaymentOrderResponse> initiatePaymentOrder(@RequestBody WalletPaymentOrderRequest request) throws Exception {
		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		WalletPaymentOrderResponse responseOrder = walletAccountTransactionService.initiatePaymentOrder(request, customerId);
		return ResponseEntity.ok(responseOrder);
	}


	@PostMapping("/webhooks")
	public ResponseEntity<GenericApiResponse> receiveInboundWebhook(@RequestBody WalletInboundWebhookRequest request) throws Exception {
		GenericApiResponse webhookResponse = walletAccountTransactionService.receiveInboundWebhook(request);
		return ResponseEntity.ok(webhookResponse);
	}

	@GetMapping
	public ResponseEntity<FineractPageResponse<SavingsAccountTransactionData>> retrieveSavingsAccountTransactions(
			@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size, @RequestParam(required = false) String startDate,
			@RequestParam(required = false) String endDate, @RequestParam(required = false) String dateFormat,
			@RequestParam(required = false) Long productId, @RequestParam(value = "offset", required = false) @Valid Long offset,
			@RequestParam(value = "limit", required = false) @Valid Long limit,
			@RequestParam(value = "transactionType", required = false) @Valid String transactionType
	) {
		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();

		FineractPageResponse<SavingsAccountTransactionData> savingsAccountTransactions = walletAccountTransactionService.retrieveSavingsAccountTransactions(
				customerId, startDate, endDate, dateFormat, productId, limit, offset, transactionType);
		savingsAccountTransactions.setPageItems(
				savingsAccountTransactions.getPageItems().stream()
						.sorted(Comparator.comparing(SavingsAccountTransactionData::getTransactionDateTime).reversed())
						.toList()
		);
		return ResponseEntity.ok(savingsAccountTransactions);
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
		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();

		try {

			log.info("Generating statement for account: {} with format: {}", customerId, format);

			StatementRequest statementRequest = StatementRequest.buildStatementRequest(
					startDate, endDate, productId, offset, limit,
					includeReversals, transactionType
			);
			String savingsAccountId = customerService.getCustomerById(customerId).getAccountId();
			statementRequest.setSavingsId(Long.valueOf(savingsAccountId));
			statementRequest.setCustomerId(customerId);
            log.info("Date Range: {} to {}", statementRequest.getStartDate(), statementRequest.getEndDate());

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
			@PathVariable(required = false) Long transactionId
	) {
		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		return ResponseEntity.ok(walletAccountTransactionService
				.retrieveSavingsAccountTransactionById(customerId, transactionId));
	}

	@GetMapping("/receipt")
	@Operation(summary = "Generate Transaction Receipt",
			description = "Generate and export transaction receipt in various formats (PDF, PNG, JPEG)")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Receipt generated successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid request parameters"),
			@ApiResponse(responseCode = "404", description = "Transaction not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Void> generateTransactionReceipt(
			@RequestParam(value = "format", required = false, defaultValue = "PDF") @Pattern(regexp = "PDF|PNG|JPEG") String format,
			@RequestParam(value = "reference") String reference,
			HttpServletRequest request,
			HttpServletResponse response
	) {
		SavingsAccountTransactionRequest savingsAccountTransactionRequest = this.redisService.retrieveData(reference, SavingsAccountTransactionRequest.class);
		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();

		try {
			log.info("Generating receipt for transaction: {} for customer: {} in format: {}",
					reference, customerId, format);

			ReceiptRequest receiptRequest = ReceiptRequest.builder()
					.transactionId(reference)
					.amount(savingsAccountTransactionRequest.getAmount())
					.recipientBankName(savingsAccountTransactionRequest.getBankName())
					.recipientAccountNumber(savingsAccountTransactionRequest.getAccountNumber())
					.recipientAccountName(savingsAccountTransactionRequest.getAccountName())
					.currency("NGN")
					.receiptFormat(format)
					.build();

			switch (format.toUpperCase()) {
				case "PDF":
					receiptService.generatePdfReceipt(receiptRequest, customerId, response);
					break;
				case "PNG":
				case "JPEG":
					receiptService.generateImageReceipt(receiptRequest, customerId, response, format);
					break;
				default:
					throw new IllegalArgumentException("Unsupported format: " + format);
			}

			log.info("Receipt generated successfully for transaction: {}", reference);
			return ResponseEntity.ok().build();

		} catch (InvalidParameterException e) {
			log.error("Invalid parameters for receipt generation: {}", e.getMessage(), e);
			return ResponseEntity.badRequest().build();
		} catch (Exception e) {
			log.error("Error generating receipt for transaction: {}", reference, e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
