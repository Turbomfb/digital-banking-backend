/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.investment.service;

import java.util.List;

import com.techservices.digitalbanking.core.fineract.model.response.GetRecurringDepositTransactionResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostRecurringDepositTransactionCommandResponse;
import com.techservices.digitalbanking.investment.domain.request.RecurringDepositTransactionCommandRequest;

public interface InvestmentTransactionService {

	GetRecurringDepositTransactionResponse retrieveInvestmentTransactionById(Long investmentId, Long transactionId);

	List<GetRecurringDepositTransactionResponse> retrieveAllInvestmentTransactions(Long investmentId);

	PostRecurringDepositTransactionCommandResponse processInvestmentTransactionCommand(Long investmentId,
			RecurringDepositTransactionCommandRequest request, String command);
}
