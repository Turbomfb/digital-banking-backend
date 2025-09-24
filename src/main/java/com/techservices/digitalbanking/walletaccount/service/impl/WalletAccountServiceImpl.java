/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.walletaccount.service.impl;

import com.techservices.digitalbanking.core.domain.dto.AccountDto;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.customer.service.CustomerService;
import com.techservices.digitalbanking.walletaccount.domain.response.SavingsInterestBreakdownResponse;
import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.eBanking.model.response.GetSavingsAccountsAccountIdResponse;
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
	public AccountDto retrieveSavingsAccountById(Long customerId) {
		String savingsAccountId = customerService.getCustomerSavingsId(customerId);
		return accountService.retrieveSavingsAccount(savingsAccountId);
	}

	@Override
	public BasePageResponse<SavingsInterestBreakdownResponse> calculateInterestBreakdown(Long customerId, LocalDate startDate, LocalDate endDate) {
		String savingsAccountId = customerService.getCustomerSavingsId(customerId);
		AccountDto savingsAccount = accountService.retrieveSavingsAccount(savingsAccountId);

		// Validate date range
		if (startDate.isAfter(endDate)) {
			throw new ValidationException("Start date cannot be after end date");
		}

		BigDecimal currentBalance = savingsAccount.getAccountBalance();

		// Get annual interest rate as decimal (e.g., 5% = 0.05)
		BigDecimal annualInterestRate = BigDecimal.valueOf(savingsAccount.getAnnualInterestRate())
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
}
