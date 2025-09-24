/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.investment.service.impl;

import java.util.List;

import com.techservices.digitalbanking.core.eBanking.service.FlexDepositAccountService;
import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.eBanking.model.response.GetRecurringDepositTransactionResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostRecurringDepositTransactionCommandResponse;
import com.techservices.digitalbanking.investment.domain.request.RecurringDepositTransactionCommandRequest;
import com.techservices.digitalbanking.investment.service.InvestmentTransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvestmentTransactionServiceImpl implements InvestmentTransactionService {
	private final FlexDepositAccountService flexDepositAccountService;

	@Override
	public GetRecurringDepositTransactionResponse retrieveInvestmentTransactionById(String investmentId,
			Long transactionId) {
		return flexDepositAccountService.retrieveInvestmentTransactionById(investmentId, transactionId);
	}

	@Override
	public List<GetRecurringDepositTransactionResponse> retrieveAllInvestmentTransactions(String investmentId) {
		return flexDepositAccountService.retrieveAllInvestmentTransactions(investmentId);
	}

	@Override
	public PostRecurringDepositTransactionCommandResponse processInvestmentTransactionCommand(String investmentId,
			RecurringDepositTransactionCommandRequest request, String command) {
		return flexDepositAccountService.processInvestmentTransactionCommand(investmentId, request, command);
	}
}
