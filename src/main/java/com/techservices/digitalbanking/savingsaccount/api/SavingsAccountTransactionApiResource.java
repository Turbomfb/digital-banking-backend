/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.savingsaccount.api;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techservices.digitalbanking.core.fineract.model.data.FineractPageResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostSavingsAccountTransactionsResponse;
import com.techservices.digitalbanking.core.fineract.model.response.SavingsAccountTransactionData;
import com.techservices.digitalbanking.savingsaccount.request.CreateSavingsAccountTransactionRequest;
import com.techservices.digitalbanking.savingsaccount.service.SavingsAccountTransactionService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("api/v1/savings-accounts/{savingsId}/transactions")
@RestController
@RequiredArgsConstructor
public class SavingsAccountTransactionApiResource {
	private final SavingsAccountTransactionService savingsAccountTransactionService;

	@GetMapping
	public ResponseEntity<FineractPageResponse<SavingsAccountTransactionData>> retrieveSavingsAccountTransactions(
			@PathVariable Long savingsId, @RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size, @RequestParam(required = false) String startDate,
			@RequestParam(required = false) String endDate, @RequestParam(required = false) String dateFormat,
			@RequestParam(required = false) Long productId, @RequestParam(value = "offset", required = false) @Valid Long offset,
			@RequestParam(value = "limit", required = false) @Valid Long limit) {

		return ResponseEntity.ok(savingsAccountTransactionService.retrieveSavingsAccountTransactions(
				savingsId, startDate, endDate, dateFormat, productId, limit, offset));
	}

	@GetMapping("/{transactionId}")
	public ResponseEntity<SavingsAccountTransactionData> retrieveSavingsAccountTransactionById(
			@PathVariable Long savingsId, @PathVariable(required = false) Long transactionId,
			@RequestParam Long productId) {
		return ResponseEntity.ok(savingsAccountTransactionService
				.retrieveSavingsAccountTransactionById(savingsId, transactionId, productId));
	}
}
