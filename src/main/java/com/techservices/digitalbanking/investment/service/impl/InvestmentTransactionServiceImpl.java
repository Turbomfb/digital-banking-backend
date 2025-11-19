/* (C)2024 */
package com.techservices.digitalbanking.investment.service.impl;

import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.TransactionDto;
import com.techservices.digitalbanking.core.domain.enums.TransactionType;
import com.techservices.digitalbanking.core.eBanking.model.request.FilterDto;
import com.techservices.digitalbanking.core.eBanking.model.response.GetRecurringDepositTransactionResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostRecurringDepositTransactionCommandResponse;
import com.techservices.digitalbanking.core.eBanking.service.AccountTransactionService;
import com.techservices.digitalbanking.core.eBanking.service.FlexDepositAccountService;
import com.techservices.digitalbanking.customer.service.CustomerService;
import com.techservices.digitalbanking.investment.domain.request.RecurringDepositTransactionCommandRequest;
import com.techservices.digitalbanking.investment.service.InvestmentTransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvestmentTransactionServiceImpl implements InvestmentTransactionService {
	private final FlexDepositAccountService flexDepositAccountService;
	private final AccountTransactionService accountTransactionService;
	private final CustomerService customerService;

	@Override
	public GetRecurringDepositTransactionResponse retrieveInvestmentTransactionById(String investmentId,
			Long transactionId) {

		return flexDepositAccountService.retrieveInvestmentTransactionById(investmentId, transactionId);
	}

	@Override
	public BasePageResponse<TransactionDto> retrieveAllInvestmentTransactions(String investmentId, String startDate,
			String endDate, Long size, TransactionType transactionType) {

		FilterDto filter = new FilterDto(investmentId, startDate, endDate, size, transactionType);
		return BasePageResponse.instance(accountTransactionService.retrieveAllAccountTransactions(filter));
	}

	@Override
	public PostRecurringDepositTransactionCommandResponse processInvestmentTransactionCommand(String investmentId,
			RecurringDepositTransactionCommandRequest request, String command) {

		return flexDepositAccountService.processInvestmentTransactionCommand(investmentId, request, command);
	}
}
