/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.service;

import java.util.List;

import com.techservices.digitalbanking.core.fineract.model.request.PutRecurringDepositProductRequest;
import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.fineract.api.RecurringDepositAccountsApiClient;
import com.techservices.digitalbanking.core.fineract.model.request.PostRecurringDepositAccountsAccountIdRequest;
import com.techservices.digitalbanking.core.fineract.model.request.PostRecurringDepositAccountsRequest;
import com.techservices.digitalbanking.core.fineract.model.response.GetRecurringDepositAccountsResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostRecurringDepositAccountsResponse;
import com.techservices.digitalbanking.investment.domain.request.RecurringDepositCommandRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.techservices.digitalbanking.core.util.CommandUtil.ACTIVATE;
import static com.techservices.digitalbanking.core.util.CommandUtil.APPROVE;
import static com.techservices.digitalbanking.core.util.CommandUtil.CLOSE;
import static com.techservices.digitalbanking.core.util.CommandUtil.PREMATURE_CLOSE;
import static com.techservices.digitalbanking.core.util.CommandUtil.REJECT;
import static com.techservices.digitalbanking.core.util.DateUtil.DEFAULT_DATE_FORMAT;
import static com.techservices.digitalbanking.core.util.DateUtil.DEFAULT_LOCALE;
import static com.techservices.digitalbanking.core.util.DateUtil.getCurrentDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecurringDepositAccountService {
	private final RecurringDepositAccountsApiClient recurringDepositAccountApiClient;

	public PostRecurringDepositAccountsResponse submitApplication(
			PostRecurringDepositAccountsRequest postRecurringDepositAccountsRequest, boolean activate) {
		PostRecurringDepositAccountsResponse response = recurringDepositAccountApiClient.create(postRecurringDepositAccountsRequest);
		if (activate) {
			RecurringDepositCommandRequest request = new RecurringDepositCommandRequest();
			this.processInvestmentCommand(response.getResourceId(), request, APPROVE);
			this.processInvestmentCommand(response.getResourceId(), request, ACTIVATE);
		}
		return response;
	}

	public List<GetRecurringDepositAccountsResponse> retrieveAllInvestments(Boolean paged, Integer offset,
			Integer limit, String orderBy, String sortOrder) {
		return recurringDepositAccountApiClient.retrieveAll();
	}

	public GetRecurringDepositAccountsResponse retrieveInvestmentById(Long investmentId,
																	  Boolean staffInSelectedOfficeOnly, @Valid String chargeStatus, String associations) {
		return recurringDepositAccountApiClient.retrieveOne(investmentId, staffInSelectedOfficeOnly, null, associations);
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
}
