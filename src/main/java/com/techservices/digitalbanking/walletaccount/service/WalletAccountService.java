/* (C)2024 */
package com.techservices.digitalbanking.walletaccount.service;

import java.time.LocalDate;

import com.techservices.digitalbanking.core.domain.dto.AccountDto;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostSavingsAccountsResponse;
import com.techservices.digitalbanking.walletaccount.domain.request.CreateSavingsAccountRequest;
import com.techservices.digitalbanking.walletaccount.domain.response.SavingsInterestBreakdownResponse;

public interface WalletAccountService {

	PostSavingsAccountsResponse createSavingsAccount(CreateSavingsAccountRequest fixedDepositApplicationRequest);

	AccountDto retrieveSavingsAccountById(Long customerId);

	BasePageResponse<SavingsInterestBreakdownResponse> calculateInterestBreakdown(Long customerId, LocalDate startDate,
			LocalDate endDate);
}
