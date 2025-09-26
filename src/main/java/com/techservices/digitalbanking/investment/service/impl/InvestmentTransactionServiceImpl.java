/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.investment.service.impl;

import java.util.List;

import com.techservices.digitalbanking.core.domain.dto.TransactionDto;
import com.techservices.digitalbanking.core.eBanking.model.request.TransactionHistoryFilter;
import com.techservices.digitalbanking.core.eBanking.service.AccountTransactionService;
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
	private final AccountTransactionService accountTransactionService;

	@Override
	public GetRecurringDepositTransactionResponse retrieveInvestmentTransactionById(String investmentId,
			Long transactionId) {
		return flexDepositAccountService.retrieveInvestmentTransactionById(investmentId, transactionId);
	}

	@Override
	public List<TransactionDto> retrieveAllInvestmentTransactions(String investmentId) {
		TransactionHistoryFilter filter = new TransactionHistoryFilter().accountNumber(investmentId);
		return accountTransactionService.retrieveAllAccountTransactions(filter);
	}

	@Override
	public PostRecurringDepositTransactionCommandResponse processInvestmentTransactionCommand(String investmentId,
			RecurringDepositTransactionCommandRequest request, String command) {
		return flexDepositAccountService.processInvestmentTransactionCommand(investmentId, request, command);
	}
}
