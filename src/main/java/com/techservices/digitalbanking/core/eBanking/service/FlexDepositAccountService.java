/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.service;

import java.util.List;

import com.techservices.digitalbanking.core.eBanking.api.FlexDepositAccountsApiClient;
import com.techservices.digitalbanking.core.eBanking.model.request.PostRecurringDepositTransactionCommandRequest;
import com.techservices.digitalbanking.core.eBanking.model.request.PutRecurringDepositProductRequest;
import com.techservices.digitalbanking.core.eBanking.model.response.*;
import com.techservices.digitalbanking.investment.domain.request.RecurringDepositTransactionCommandRequest;
import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.eBanking.model.request.PostRecurringDepositAccountsAccountIdRequest;
import com.techservices.digitalbanking.core.eBanking.model.request.PostRecurringDepositAccountsRequest;
import com.techservices.digitalbanking.investment.domain.request.RecurringDepositCommandRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.techservices.digitalbanking.core.util.CommandUtil.ACTIVATE;
import static com.techservices.digitalbanking.core.util.CommandUtil.APPROVE;
import static com.techservices.digitalbanking.core.util.CommandUtil.CLOSE;
import static com.techservices.digitalbanking.core.util.CommandUtil.PREMATURE_CLOSE;
import static com.techservices.digitalbanking.core.util.CommandUtil.REJECT;
import static com.techservices.digitalbanking.core.util.DateUtil.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class FlexDepositAccountService {
	private final FlexDepositAccountsApiClient recurringDepositAccountApiClient;

	public PostRecurringDepositAccountsResponse submitApplication(
			PostRecurringDepositAccountsRequest postRecurringDepositAccountsRequest, boolean activate) {
		postRecurringDepositAccountsRequest.setLocale(DEFAULT_LOCALE);
		postRecurringDepositAccountsRequest.setDateFormat(getDefaultDateFormat());
		postRecurringDepositAccountsRequest.setSubmittedOnDate(getCurrentDate());
		postRecurringDepositAccountsRequest.setAllowWithdrawal(true);
		PostRecurringDepositAccountsResponse response = recurringDepositAccountApiClient.create(postRecurringDepositAccountsRequest);
		if (activate) {
			RecurringDepositCommandRequest request = new RecurringDepositCommandRequest();
			this.processInvestmentCommand(response.getResourceId(), request, APPROVE);
			this.processInvestmentCommand(response.getResourceId(), request, ACTIVATE);
		}
		return response;
	}

	public GetRecurringDepositProductProductIdResponse retrieveAProduct(String productId) {
		return recurringDepositAccountApiClient.retrieveAProduct(productId);
	}


	public List<GetRecurringDepositAccountsResponse> retrieveAllInvestments(Boolean paged, Integer offset,
			Integer limit, String orderBy, String sortOrder) {
		return recurringDepositAccountApiClient.retrieveAll();
	}

	public GetRecurringDepositAccountsResponse retrieveInvestmentById(String investmentId) {
		return recurringDepositAccountApiClient.retrieveOne(investmentId);
	}

	public PostRecurringDepositAccountsResponse processInvestmentCommand(Long investmentId,
			@Valid RecurringDepositCommandRequest recurringDepositCommandRequest, String command) {
		PostRecurringDepositAccountsAccountIdRequest request = new PostRecurringDepositAccountsAccountIdRequest();
		if (APPROVE.equalsIgnoreCase(command)) {
			processInvestmentApproval(request);
		} else if (REJECT.equalsIgnoreCase(command)) {
			processInvestmentRejection(request);
		} else if (ACTIVATE.equalsIgnoreCase(command)) {
			processInvestmentActivation(request);
		} else if (CLOSE.equalsIgnoreCase(command) || PREMATURE_CLOSE.equalsIgnoreCase(command)) {
			processInvestmentClosureOnMaturity(request, recurringDepositCommandRequest);
		}
		return recurringDepositAccountApiClient.processCommand(investmentId, request, command);
	}

	private void processInvestmentClosureOnMaturity(PostRecurringDepositAccountsAccountIdRequest request,
			RecurringDepositCommandRequest recurringDepositCommandRequest) {
		populateDefaultFields(request);
		request.setClosedOnDate(getCurrentDate());
		request.setNote(recurringDepositCommandRequest.getNote());
		request.setOnAccountClosureId(recurringDepositCommandRequest.getOnAccountClosureId());
		request.setToSavingsAccountId(recurringDepositCommandRequest.getToSavingsAccountId());
		request.setTransferDescription(recurringDepositCommandRequest.getTransferDescription());
	}

	private void processInvestmentActivation(PostRecurringDepositAccountsAccountIdRequest request) {
		populateDefaultFields(request);
		request.setActivatedOnDate(getCurrentDate());
	}

	private void processInvestmentRejection(PostRecurringDepositAccountsAccountIdRequest request) {
		populateDefaultFields(request);
		request.setRejectedOnDate(getCurrentDate());
	}

	private void processInvestmentApproval(PostRecurringDepositAccountsAccountIdRequest request) {
		populateDefaultFields(request);
		request.setApprovedOnDate(getCurrentDate());
	}

	private static void populateDefaultFields(
			PostRecurringDepositAccountsAccountIdRequest postRecurringDepositAccountsAccountIdRequest) {
		postRecurringDepositAccountsAccountIdRequest.setLocale(DEFAULT_LOCALE);
		postRecurringDepositAccountsAccountIdRequest.setDateFormat(DEFAULT_DATE_FORMAT);
	}

	public GetRecurringDepositAccountsResponse updateAnInvestment(String investmentId, PutRecurringDepositProductRequest putRecurringDepositProductRequest) {
		return recurringDepositAccountApiClient.update(Long.valueOf(investmentId), putRecurringDepositProductRequest);
	}


	public GetRecurringDepositTransactionResponse retrieveInvestmentTransactionById(String investmentId,
																					Long transactionId) {
		return recurringDepositAccountApiClient.retrieveOneTransaction(investmentId, transactionId);
	}

	public List<GetRecurringDepositTransactionResponse> retrieveAllInvestmentTransactions(String investmentId) {
		GetRecurringDepositAccountsResponse fixedDepositAccount = recurringDepositAccountApiClient.retrieveOne(investmentId);
		return fixedDepositAccount.getTransactions();
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
		return recurringDepositAccountApiClient.processTransactionCommand(investmentId,
				postRecurringDepositTransactionCommandRequest, command);
	}
}
