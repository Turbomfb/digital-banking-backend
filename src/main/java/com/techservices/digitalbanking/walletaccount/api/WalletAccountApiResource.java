/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.walletaccount.api;

import com.techservices.digitalbanking.core.configuration.security.SpringSecurityAuditorAware;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.walletaccount.domain.response.SavingsInterestBreakdownResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techservices.digitalbanking.core.fineract.model.response.GetSavingsAccountsAccountIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostSavingsAccountsResponse;
import com.techservices.digitalbanking.walletaccount.domain.request.CreateSavingsAccountRequest;
import com.techservices.digitalbanking.walletaccount.service.WalletAccountService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequestMapping("api/v1/wallet-accounts")
@RestController
@RequiredArgsConstructor
public class WalletAccountApiResource {
	private final WalletAccountService walletAccountService;
	private final SpringSecurityAuditorAware springSecurityAuditorAware;

	@Operation(summary = "Create a new savings account")
	@PostMapping
	public ResponseEntity<PostSavingsAccountsResponse> createSavingsAccount(
			@RequestBody CreateSavingsAccountRequest createAccountRequest) {
		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		createAccountRequest.setClientId(customerId);
		PostSavingsAccountsResponse postSavingsAccountsResponse = walletAccountService
				.createSavingsAccount(createAccountRequest);

		return ResponseEntity.ok(postSavingsAccountsResponse);
	}

	@Operation(summary = "Get a Customer's Savings Account details")
	@GetMapping("/me")
	public ResponseEntity<GetSavingsAccountsAccountIdResponse> retrieveSavingsAccountById() {
		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		return ResponseEntity.ok(walletAccountService.retrieveSavingsAccountById(customerId));
	}

	@Operation(summary = "Calculate Total Interest Breakdown for a Savings Account")
	@GetMapping("/me/interest-breakdown")
	public ResponseEntity<BasePageResponse<SavingsInterestBreakdownResponse>> calculateInterestBreakdown(
			@RequestParam(name = "startDate", required = false) LocalDate startDate,
			@RequestParam(name = "endDate", required = false) LocalDate endDate
	) {
		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		startDate = startDate == null ? LocalDate.now() : startDate;
		endDate = endDate == null || endDate.equals(LocalDate.now()) ? LocalDate.now().plusDays(24) : endDate;
		return ResponseEntity.ok(walletAccountService.calculateInterestBreakdown(customerId, startDate, endDate));
	}
}
