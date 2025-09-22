/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.fixeddeposit.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techservices.digitalbanking.core.eBanking.model.response.GetFixedDepositTransactionResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostFixedDepositTransactionCommandResponse;
import com.techservices.digitalbanking.fixeddeposit.domain.request.FixedDepositTransactionCommandRequest;
import com.techservices.digitalbanking.fixeddeposit.service.FixedDepositInvestmentTransactionService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("api/v1/fixed-investments/{investmentId}/transactions")
@RestController
@RequiredArgsConstructor
public class FixedDepositInvestmentTransactionApiResource {
	private final FixedDepositInvestmentTransactionService investmentTransactionService;

	@Operation(summary = "Retrieve all investment transactions")
	@GetMapping
	public ResponseEntity<List<GetFixedDepositTransactionResponse>> retrieveAllInvestmentTransactions(
			@PathVariable Long investmentId) {
		List<GetFixedDepositTransactionResponse> investmentTransactions = investmentTransactionService
				.retrieveAllInvestmentTransactions(investmentId);
		return ResponseEntity.ok(investmentTransactions);
	}

	@Operation(summary = "Retrieve an investment transaction")
	@GetMapping("{transactionId}")
	public ResponseEntity<GetFixedDepositTransactionResponse> retrieveInvestmentTransactionById(
			@PathVariable Long investmentId, @PathVariable Long transactionId) {
		GetFixedDepositTransactionResponse investmentTransaction = investmentTransactionService
				.retrieveInvestmentTransactionById(investmentId, transactionId);
		return ResponseEntity.ok(investmentTransaction);
	}

	@Operation(summary = "Deposit into a Recurring Deposit Account | Withdraw from a Recurring Deposit Account")
	@PostMapping()
	public ResponseEntity<PostFixedDepositTransactionCommandResponse> processInvestmentTransactionCommand(
			@PathVariable("investmentId") Long investmentId,
			@Valid @RequestBody(required = false) FixedDepositTransactionCommandRequest request,
			@Valid @RequestParam(value = "command") String command) {
		PostFixedDepositTransactionCommandResponse postFixedDepositTransactionCommandResponse = investmentTransactionService
				.processInvestmentTransactionCommand(investmentId, request, command);

		return ResponseEntity.ok(postFixedDepositTransactionCommandResponse);
	}
}
