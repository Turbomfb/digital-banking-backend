/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.walletaccount.service.impl;

import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.customer.service.CustomerService;
import com.techservices.digitalbanking.walletaccount.domain.response.SavingsInterestBreakdownResponse;
import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.eBanking.model.response.GetSavingsAccountsAccountIdResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.GetSavingsAccountsResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostSavingsAccountsAccountIdResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostSavingsAccountsResponse;
import com.techservices.digitalbanking.core.eBanking.service.AccountService;
import com.techservices.digitalbanking.walletaccount.domain.request.CreateSavingsAccountRequest;
import com.techservices.digitalbanking.walletaccount.service.WalletAccountService;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletAccountServiceImpl implements WalletAccountService {
	private final AccountService accountService;
	private final CustomerService customerService;

	@Override
	public PostSavingsAccountsResponse createSavingsAccount(CreateSavingsAccountRequest createSavingsAccountRequest) {
		return accountService.createSavingsAccount(createSavingsAccountRequest.getClientId(),
				createSavingsAccountRequest.getProductId(), createSavingsAccountRequest.getAccountNumber(),
				createSavingsAccountRequest.getAccountName(), createSavingsAccountRequest.getExternalId(), null,
				createSavingsAccountRequest.isActive(), null, null, null);
	}

	@Override
	public PostSavingsAccountsAccountIdResponse manageSavingsAccount(String savingsAccountId, String command,
			long productId) {
		GetSavingsAccountsAccountIdResponse getSavingsAccountsAccountIdResponse = retrieveSavingsAccount(
				savingsAccountId, productId);
		return accountService.manageSavingsAccountCommand(getSavingsAccountsAccountIdResponse, command);
	}

	@Override
	public GetSavingsAccountsResponse retrieveAllSavingsAccount(Long limit, Long offset, Long productId) {
		return accountService.retrieveAllSavingsAccounts(limit, offset, productId);
	}

	@Override
	public GetSavingsAccountsAccountIdResponse retrieveSavingsAccountById(Long customerId) {
		String savingsAccountId = customerService.getCustomerSavingsId(customerId);
		return accountService.retrieveSavingsAccount(Long.valueOf(savingsAccountId), true);
	}

	@Override
	public BasePageResponse<SavingsInterestBreakdownResponse> calculateInterestBreakdown(Long customerId, LocalDate startDate, LocalDate endDate) {
		String savingsAccountId = customerService.getCustomerSavingsId(customerId);
		GetSavingsAccountsAccountIdResponse savingsAccount = accountService.retrieveSavingsAccount(Long.valueOf(savingsAccountId), true);

		// Validate date range
		if (startDate.isAfter(endDate)) {
			throw new ValidationException("Start date cannot be after end date");
		}

		BigDecimal currentBalance = savingsAccount.getSummary().getAvailableBalance() != null
				? savingsAccount.getSummary().getAvailableBalance()
				: BigDecimal.ZERO;

		// Get annual interest rate as decimal (e.g., 5% = 0.05)
		BigDecimal annualInterestRate = BigDecimal.valueOf(savingsAccount.getNominalAnnualInterestRate())
				.divide(BigDecimal.valueOf(100), 6, RoundingMode.HALF_UP);

		List<SavingsInterestBreakdownResponse> savingsInterestBreakdownResponses = new ArrayList<>();

		LocalDate currentDate = startDate;

		// Loop through each day in the date range (inclusive)
		while (!currentDate.isAfter(endDate)) {
			// Calculate daily interest rate based on the year the current date falls in
			int daysInYear = Year.of(currentDate.getYear()).length();
			BigDecimal dailyInterestRate = annualInterestRate
					.divide(BigDecimal.valueOf(daysInYear), 8, RoundingMode.HALF_UP);

			// Calculate interest for this specific day
			BigDecimal interestForDay = currentBalance
					.multiply(dailyInterestRate)
					.setScale(4, RoundingMode.HALF_UP);

			currentBalance = currentBalance.add(interestForDay);

			SavingsInterestBreakdownResponse response = SavingsInterestBreakdownResponse.builder()
					.date(currentDate)
					.interestEarned(interestForDay)
					.closingBalance(currentBalance)
					.build();

			savingsInterestBreakdownResponses.add(response);

			currentDate = currentDate.plusDays(1);
		}

		return BasePageResponse.instance(savingsInterestBreakdownResponses);
	}

	public GetSavingsAccountsAccountIdResponse retrieveSavingsAccount(String savingsAccountId, long productId) {
		return accountService.retrieveSavingsAccount(savingsAccountId, productId, false);
	}
}
