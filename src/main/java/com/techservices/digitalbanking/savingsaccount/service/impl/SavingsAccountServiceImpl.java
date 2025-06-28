/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.savingsaccount.service.impl;

import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.savingsaccount.domain.response.SavingsInterestBreakdownResponse;
import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.fineract.model.response.GetSavingsAccountsAccountIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetSavingsAccountsResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostSavingsAccountsAccountIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostSavingsAccountsResponse;
import com.techservices.digitalbanking.core.fineract.service.AccountService;
import com.techservices.digitalbanking.savingsaccount.domain.request.CreateSavingsAccountRequest;
import com.techservices.digitalbanking.savingsaccount.service.SavingsAccountService;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SavingsAccountServiceImpl implements SavingsAccountService {
	private final AccountService accountService;

	@Override
	public PostSavingsAccountsResponse createSavingsAccount(CreateSavingsAccountRequest createSavingsAccountRequest) {
		return accountService.createSavingsAccount(createSavingsAccountRequest.clientId(),
				createSavingsAccountRequest.productId(), createSavingsAccountRequest.accountNumber(),
				createSavingsAccountRequest.accountName(), createSavingsAccountRequest.externalId(), null,
				createSavingsAccountRequest.isActive(), null);
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
	public GetSavingsAccountsAccountIdResponse retrieveSavingsAccountById(String savingsAccountId) {
		return accountService.retrieveSavingsAccount(Long.valueOf(savingsAccountId), true);
	}

	@Override
	public BasePageResponse<SavingsInterestBreakdownResponse> calculateInterestBreakdown(String savingsAccountId, LocalDate startDate, LocalDate endDate) {
		GetSavingsAccountsAccountIdResponse savingsAccount = retrieveSavingsAccountById(savingsAccountId);
		int dateDifference = startDate.until(endDate).getDays();
		BigDecimal currentBalance = savingsAccount.getSummary().getAvailableBalance() != null ? savingsAccount.getSummary().getAvailableBalance() : BigDecimal.ZERO;
		BigDecimal dailyInterest = currentBalance
				.multiply(BigDecimal.valueOf(savingsAccount.getNominalAnnualInterestRate()))
				.divide(BigDecimal.valueOf(Year.of(LocalDate.now().getYear()).length()), 2, RoundingMode.HALF_UP);

		List<SavingsInterestBreakdownResponse> savingsInterestBreakdownResponses = new ArrayList<>();
		for (int i = 0; i < dateDifference; i++) {
			LocalDate currentDate = startDate.plusDays(i);
			BigDecimal interestForDay = dailyInterest.multiply(BigDecimal.valueOf(currentDate.lengthOfMonth()))
					.divide(BigDecimal.valueOf(30), 2, RoundingMode.HALF_UP);
			currentBalance = currentBalance.add(interestForDay);
			SavingsInterestBreakdownResponse savingsInterestBreakdownResponse = SavingsInterestBreakdownResponse.builder()
					.date(currentDate)
					.interestEarned(interestForDay)
					.closingBalance(currentBalance)
					.build();
			savingsInterestBreakdownResponses.add(savingsInterestBreakdownResponse);
		}
		return BasePageResponse.instance(savingsInterestBreakdownResponses);
	}

	public GetSavingsAccountsAccountIdResponse retrieveSavingsAccount(String savingsAccountId, long productId) {
		return accountService.retrieveSavingsAccount(savingsAccountId, productId, false);
	}
}
