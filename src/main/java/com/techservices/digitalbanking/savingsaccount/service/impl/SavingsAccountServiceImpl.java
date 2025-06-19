/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.savingsaccount.service.impl;

import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.fineract.model.response.GetSavingsAccountsAccountIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetSavingsAccountsResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostSavingsAccountsAccountIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostSavingsAccountsResponse;
import com.techservices.digitalbanking.core.fineract.service.AccountService;
import com.techservices.digitalbanking.savingsaccount.request.CreateSavingsAccountRequest;
import com.techservices.digitalbanking.savingsaccount.service.SavingsAccountService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SavingsAccountServiceImpl implements SavingsAccountService {
	private final AccountService accountService;

	@Override
	public PostSavingsAccountsResponse createSavingsAccount(CreateSavingsAccountRequest createSavingsAccountRequest) {
		return accountService.createSavingsAccount(createSavingsAccountRequest.clientId(),
				createSavingsAccountRequest.productId(), createSavingsAccountRequest.accountNumber(),
				createSavingsAccountRequest.accountName(), createSavingsAccountRequest.externalId(), null,
				createSavingsAccountRequest.isActive());
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
	public GetSavingsAccountsAccountIdResponse retrieveSavingsAccountById(String savingsAccountId, Long productId) {
		return accountService.retrieveSavingsAccount(Long.valueOf(savingsAccountId), true);
	}

	public GetSavingsAccountsAccountIdResponse retrieveSavingsAccount(String savingsAccountId, long productId) {
		return accountService.retrieveSavingsAccount(savingsAccountId, productId, false);
	}
}
