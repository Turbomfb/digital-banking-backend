/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.fineract.api.FixeddepositaccountsApi;
import com.techservices.digitalbanking.core.fineract.model.response.GetFixedDepositAccountsAccountIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetFixedDepositTransactionResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostFixedDepositTransactionCommandResponse;
import com.techservices.digitalbanking.fixeddeposit.domain.request.FixedDepositTransactionCommandRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class FixedDepositTransactionService {
	private final FixeddepositaccountsApi fixeddepositaccountsApi;

	public GetFixedDepositTransactionResponse retrieveInvestmentTransactionById(Long investmentId, Long transactionId) {
		return fixeddepositaccountsApi.retrieveOneTransaction(investmentId, transactionId);
	}

	public List<GetFixedDepositTransactionResponse> retrieveAllInvestmentTransactions(Long investmentId) {
		GetFixedDepositAccountsAccountIdResponse fixedDepositAccount = fixeddepositaccountsApi.retrieveOne(investmentId,
				false, null, "all");
		return fixedDepositAccount.getTransactions();
	}

	public PostFixedDepositTransactionCommandResponse processInvestmentTransactionCommand(Long investmentId,
			FixedDepositTransactionCommandRequest request, String command) {
		return fixeddepositaccountsApi.transaction(investmentId, command, request);
	}
}
