/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.investment.service;

import java.util.List;

import com.techservices.digitalbanking.core.eBanking.model.response.GetRecurringDepositTransactionResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostRecurringDepositTransactionCommandResponse;
import com.techservices.digitalbanking.investment.domain.request.RecurringDepositTransactionCommandRequest;

public interface InvestmentTransactionService {

	GetRecurringDepositTransactionResponse retrieveInvestmentTransactionById(String investmentId, Long transactionId);

	List<GetRecurringDepositTransactionResponse> retrieveAllInvestmentTransactions(String investmentId);

	PostRecurringDepositTransactionCommandResponse processInvestmentTransactionCommand(String investmentId,
			RecurringDepositTransactionCommandRequest request, String command);
}
