/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.fineract.api.RecurringDepositAccountsApi;
import com.techservices.digitalbanking.core.fineract.model.request.PostRecurringDepositTransactionCommandRequest;
import com.techservices.digitalbanking.core.fineract.model.response.GetRecurringDepositAccountsResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetRecurringDepositTransactionResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostRecurringDepositTransactionCommandResponse;
import com.techservices.digitalbanking.investment.domain.request.RecurringDepositTransactionCommandRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.techservices.digitalbanking.core.util.DateUtil.DEFAULT_DATE_FORMAT;
import static com.techservices.digitalbanking.core.util.DateUtil.DEFAULT_LOCALE;
import static com.techservices.digitalbanking.core.util.DateUtil.getCurrentDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecurringDepositTransactionService {
	private final RecurringDepositAccountsApi recurringDepositAccountsApi;

	public GetRecurringDepositTransactionResponse retrieveInvestmentTransactionById(Long investmentId,
			Long transactionId) {
		return recurringDepositAccountsApi.retrieveOneTransaction(investmentId, transactionId);
	}

	public List<GetRecurringDepositTransactionResponse> retrieveAllInvestmentTransactions(Long investmentId) {
		GetRecurringDepositAccountsResponse fixedDepositAccount = recurringDepositAccountsApi.retrieveOne(investmentId,
				false, null, "all");
		return fixedDepositAccount.getTransactions();
	}

	public PostRecurringDepositTransactionCommandResponse processInvestmentTransactionCommand(Long investmentId,
			RecurringDepositTransactionCommandRequest request, String command) {
		PostRecurringDepositTransactionCommandRequest postRecurringDepositTransactionCommandRequest = new PostRecurringDepositTransactionCommandRequest();
		postRecurringDepositTransactionCommandRequest.setNote(request.getNote());
		postRecurringDepositTransactionCommandRequest.setTransactionAmount(request.getTransactionAmount());
		postRecurringDepositTransactionCommandRequest.setPaymentTypeId(request.getPaymentTypeId());
		postRecurringDepositTransactionCommandRequest.setAccountNumber(request.getAccountNumber());
		postRecurringDepositTransactionCommandRequest.setCheckNumber(request.getCheckNumber());
		postRecurringDepositTransactionCommandRequest.setRoutingCode(request.getRoutingCode());
		postRecurringDepositTransactionCommandRequest.setReceiptNumber(request.getReceiptNumber());
		postRecurringDepositTransactionCommandRequest.setBankNumber(request.getBankNumber());
		postRecurringDepositTransactionCommandRequest.setLocale(DEFAULT_LOCALE);
		postRecurringDepositTransactionCommandRequest.setDateFormat(DEFAULT_DATE_FORMAT);
		postRecurringDepositTransactionCommandRequest.setTransactionDate(getCurrentDate());
		postRecurringDepositTransactionCommandRequest.setLinkAccountId(request.getLinkAccountId());
		return recurringDepositAccountsApi.processTransactionCommand(investmentId,
				postRecurringDepositTransactionCommandRequest, command);
	}
}
