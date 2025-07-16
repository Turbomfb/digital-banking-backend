/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.walletaccount.service;

import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetSavingsAccountsAccountIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetSavingsAccountsResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostSavingsAccountsAccountIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostSavingsAccountsResponse;
import com.techservices.digitalbanking.walletaccount.domain.request.CreateSavingsAccountRequest;
import com.techservices.digitalbanking.walletaccount.domain.response.SavingsInterestBreakdownResponse;

import java.time.LocalDate;

public interface SavingsAccountService {

	PostSavingsAccountsResponse createSavingsAccount(CreateSavingsAccountRequest fixedDepositApplicationRequest);

	PostSavingsAccountsAccountIdResponse manageSavingsAccount(String savingsAccountId, String command, long productId);

	GetSavingsAccountsResponse retrieveAllSavingsAccount(Long limit, Long offset, Long productId);

	GetSavingsAccountsAccountIdResponse retrieveSavingsAccountById(Long customerId);

	BasePageResponse<SavingsInterestBreakdownResponse> calculateInterestBreakdown(Long customerId, LocalDate startDate, LocalDate endDate);
}
