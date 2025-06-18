/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.savingsaccount.service;

import com.techservices.digitalbanking.core.fineract.model.response.GetSavingsAccountsAccountIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetSavingsAccountsResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostSavingsAccountsAccountIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostSavingsAccountsResponse;
import com.techservices.digitalbanking.savingsaccount.request.CreateSavingsAccountRequest;

public interface SavingsAccountService {

	PostSavingsAccountsResponse createSavingsAccount(CreateSavingsAccountRequest fixedDepositApplicationRequest);

	PostSavingsAccountsAccountIdResponse manageSavingsAccount(String savingsAccountId, String command, long productId);

	GetSavingsAccountsResponse retrieveAllSavingsAccount(Long limit, Long offset, Long productId);

	GetSavingsAccountsAccountIdResponse retrieveSavingsAccountById(String savingsAccountId, Long productId);
}
