/* (C)2024 */
package com.techservices.digitalbanking.investment.service;

import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.TransactionDto;
import com.techservices.digitalbanking.core.domain.enums.TransactionType;
import com.techservices.digitalbanking.core.eBanking.model.response.GetRecurringDepositTransactionResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostRecurringDepositTransactionCommandResponse;
import com.techservices.digitalbanking.investment.domain.request.RecurringDepositTransactionCommandRequest;

public interface InvestmentTransactionService {

	GetRecurringDepositTransactionResponse retrieveInvestmentTransactionById(String investmentId, Long transactionId);

	BasePageResponse<TransactionDto> retrieveAllInvestmentTransactions(String investmentId, String startDate,
			String endDate, Long size, TransactionType transactionType);

	PostRecurringDepositTransactionCommandResponse processInvestmentTransactionCommand(String investmentId,
			RecurringDepositTransactionCommandRequest request, String command);
}
