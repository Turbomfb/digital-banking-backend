/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.savingsaccount.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techservices.digitalbanking.core.fineract.model.response.GetSavingsAccountsAccountIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetSavingsAccountsResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostSavingsAccountsAccountIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostSavingsAccountsResponse;
import com.techservices.digitalbanking.savingsaccount.request.CreateSavingsAccountRequest;
import com.techservices.digitalbanking.savingsaccount.service.SavingsAccountService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RequestMapping("api/v1/savings-accounts")
@RestController
@RequiredArgsConstructor
public class SavingsAccountApiResource {
	private final SavingsAccountService savingsAccountService;

	@Operation(summary = "Create a new savings account")
	@PostMapping
	public ResponseEntity<PostSavingsAccountsResponse> createSavingsAccount(
			@RequestBody CreateSavingsAccountRequest createAccountRequest) {
		PostSavingsAccountsResponse postSavingsAccountsResponse = savingsAccountService
				.createSavingsAccount(createAccountRequest);

		return ResponseEntity.ok(postSavingsAccountsResponse);
	}

	@Operation(summary = "Approve Savings Account | Activate Savings Account | Close Savings Account")
	@PostMapping("/{savingsAccountId}")
	public ResponseEntity<PostSavingsAccountsAccountIdResponse> manageSavingsAccount(
			@PathVariable String savingsAccountId, @RequestParam String command, @RequestParam long productId) {
		return ResponseEntity.ok(savingsAccountService.manageSavingsAccount(savingsAccountId, command, productId));
	}

	@Operation(summary = "Retrieve All Savings Accounts")
	@GetMapping
	public ResponseEntity<GetSavingsAccountsResponse> retrieveAllSavingsAccount(
			@RequestParam(name = "productId") Long productId, @RequestParam(name = "limit") Long limit,
			@RequestParam(name = "offset") Long offset) {
		return ResponseEntity.ok(savingsAccountService.retrieveAllSavingsAccount(limit, offset, productId));
	}

	@Operation(summary = "Get a Savings Account by account number")
	@GetMapping("/{savingsAccountId}")
	public ResponseEntity<GetSavingsAccountsAccountIdResponse> retrieveSavingsAccountById(
			@PathVariable String savingsAccountId, @RequestParam(name = "productId") Long productId) {
		return ResponseEntity.ok(savingsAccountService.retrieveSavingsAccountById(savingsAccountId, productId));
	}
}
