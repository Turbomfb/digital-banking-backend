/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.investment.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.fineract.model.response.GetRecurringDepositTransactionResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostRecurringDepositTransactionCommandResponse;
import com.techservices.digitalbanking.core.fineract.service.RecurringDepositTransactionService;
import com.techservices.digitalbanking.investment.domain.request.RecurringDepositTransactionCommandRequest;
import com.techservices.digitalbanking.investment.service.InvestmentTransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvestmentTransactionServiceImpl implements InvestmentTransactionService {
	private final RecurringDepositTransactionService recurringDepositTransactionService;

	@Override
	public GetRecurringDepositTransactionResponse retrieveInvestmentTransactionById(Long investmentId,
			Long transactionId) {
		return recurringDepositTransactionService.retrieveInvestmentTransactionById(investmentId, transactionId);
	}

	@Override
	public List<GetRecurringDepositTransactionResponse> retrieveAllInvestmentTransactions(Long investmentId) {
		return recurringDepositTransactionService.retrieveAllInvestmentTransactions(investmentId);
	}

	@Override
	public PostRecurringDepositTransactionCommandResponse processInvestmentTransactionCommand(Long investmentId,
			RecurringDepositTransactionCommandRequest request, String command) {
		return recurringDepositTransactionService.processInvestmentTransactionCommand(investmentId, request, command);
	}
}
