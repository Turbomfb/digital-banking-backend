/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.service;

import com.techservices.digitalbanking.core.domain.dto.ProductDto;
import com.techservices.digitalbanking.core.eBanking.api.TransactionApi;
import com.techservices.digitalbanking.core.eBanking.api.FlexDepositAccountsApiClient;
import com.techservices.digitalbanking.core.eBanking.model.request.FlexInvestmentCreationRequest;
import com.techservices.digitalbanking.core.eBanking.model.request.PostRecurringDepositTransactionCommandRequest;
import com.techservices.digitalbanking.core.eBanking.model.request.PutRecurringDepositProductRequest;
import com.techservices.digitalbanking.core.eBanking.model.response.FlexInvestmentCreationResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.GetRecurringDepositAccountsResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.GetRecurringDepositTransactionResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostRecurringDepositTransactionCommandResponse;
import com.techservices.digitalbanking.investment.domain.request.RecurringDepositTransactionCommandRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.techservices.digitalbanking.core.util.DateUtil.DEFAULT_DATE_FORMAT;
import static com.techservices.digitalbanking.core.util.DateUtil.DEFAULT_LOCALE;
import static com.techservices.digitalbanking.core.util.DateUtil.getCurrentDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class FlexDepositAccountService {
	private final FlexDepositAccountsApiClient flexDepositAccountsApiClient;
	private final TransactionApi transactionApi;

	public FlexInvestmentCreationResponse submitApplication(
			FlexInvestmentCreationRequest flexInvestmentCreationRequest) {
        return flexDepositAccountsApiClient.create(flexInvestmentCreationRequest);
	}

	public ProductDto retrieveFlexProductDetails() {
		return flexDepositAccountsApiClient.retrieveFlexProductDetails();
	}

	public GetRecurringDepositAccountsResponse retrieveInvestmentById(String investmentId) {
		return flexDepositAccountsApiClient.retrieveOne(investmentId);
	}

	public GetRecurringDepositAccountsResponse updateAnInvestment(String investmentId, PutRecurringDepositProductRequest putRecurringDepositProductRequest) {
		return flexDepositAccountsApiClient.update(Long.valueOf(investmentId), putRecurringDepositProductRequest);
	}


	public GetRecurringDepositTransactionResponse retrieveInvestmentTransactionById(String investmentId,
																					Long transactionId) {
		return flexDepositAccountsApiClient.retrieveOneTransaction(investmentId, transactionId);
	}

	public PostRecurringDepositTransactionCommandResponse processInvestmentTransactionCommand(String investmentId,
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
		return flexDepositAccountsApiClient.processTransactionCommand(investmentId,
				postRecurringDepositTransactionCommandRequest, command);
	}
}
