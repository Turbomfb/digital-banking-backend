/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.investment.api;

import java.util.List;

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
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("api/v1/investments/{investmentId}/transactions")
@RestController
@RequiredArgsConstructor
public class InvestmentTransactionApiResource {
	private final InvestmentTransactionService investmentTransactionService;

	@Operation(summary = "Retrieve all investment transactions")
	@GetMapping
	public ResponseEntity<List<GetRecurringDepositTransactionResponse>> retrieveAllInvestmentTransactions(
			@PathVariable Long investmentId) {
		List<GetRecurringDepositTransactionResponse> investmentTransactions = investmentTransactionService
				.retrieveAllInvestmentTransactions(investmentId);
		return ResponseEntity.ok(investmentTransactions);
	}

	@Operation(summary = "Retrieve an investment transaction")
	@GetMapping("{transactionId}")
	public ResponseEntity<GetRecurringDepositTransactionResponse> retrieveInvestmentTransactionById(
			@PathVariable Long investmentId, @PathVariable Long transactionId) {
		GetRecurringDepositTransactionResponse investmentTransaction = investmentTransactionService
				.retrieveInvestmentTransactionById(investmentId, transactionId);
		return ResponseEntity.ok(investmentTransaction);
	}

	@Operation(summary = "Deposit into a Recurring Deposit Account | Withdraw from a Recurring Deposit Account")
	@PostMapping()
	public ResponseEntity<PostRecurringDepositTransactionCommandResponse> processInvestmentTransactionCommand(
			@PathVariable("investmentId") Long investmentId,
			@Valid @RequestBody(required = false) RecurringDepositTransactionCommandRequest request,
			@Valid @RequestParam(value = "command") String command) {
		PostRecurringDepositTransactionCommandResponse postRecurringDepositTransactionCommandResponse = investmentTransactionService
				.processInvestmentTransactionCommand(investmentId, request, command);

		return ResponseEntity.ok(postRecurringDepositTransactionCommandResponse);
	}
}
