/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.service;

import com.techservices.digitalbanking.core.fineract.model.response.*;
import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.fineract.api.FixeddepositaccountsApiClient;
import com.techservices.digitalbanking.core.fineract.model.request.PostFixedDepositAccountsAccountIdRequest;
import com.techservices.digitalbanking.core.fineract.model.request.PostFixedDepositAccountsRequest;
import com.techservices.digitalbanking.fixeddeposit.domain.request.FixedDepositCommandRequest;
import com.techservices.digitalbanking.investment.domain.request.FixedDepositApplicationRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.List;

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
public class FixedDepositService {
	private final FixeddepositaccountsApiClient fixeddepositaccountsApiClient;

	public PostSavingsAccountsResponse submitApplication(FixedDepositApplicationRequest fixedDepositApplicationRequest,
			@Valid boolean activate) {
		PostFixedDepositAccountsRequest postFixedDepositAccountsRequest = new PostFixedDepositAccountsRequest();
		postFixedDepositAccountsRequest.setDepositAmount(fixedDepositApplicationRequest.getDepositAmount());
		postFixedDepositAccountsRequest.setDepositPeriod(fixedDepositApplicationRequest.getDepositPeriod());
		postFixedDepositAccountsRequest
				.setDepositPeriodFrequencyId(fixedDepositApplicationRequest.getDepositPeriodFrequencyId());
		postFixedDepositAccountsRequest.setClientId(fixedDepositApplicationRequest.getClientId());
		postFixedDepositAccountsRequest.setProductId(fixedDepositApplicationRequest.getProductId());
		postFixedDepositAccountsRequest.setLocale(DEFAULT_LOCALE);
		postFixedDepositAccountsRequest.setDateFormat(DEFAULT_DATE_FORMAT);
		postFixedDepositAccountsRequest.setSubmittedOnDate(getCurrentDate());
		postFixedDepositAccountsRequest.setAccountNo(fixedDepositApplicationRequest.getAccountNo());
		postFixedDepositAccountsRequest.setLinkAccountId(fixedDepositApplicationRequest.getLinkAccountId());
		PostSavingsAccountsResponse postSavingsAccountsResponse = fixeddepositaccountsApiClient
				.submitApplication(postFixedDepositAccountsRequest);
		if (activate) {
			processInvestmentApproval(new PostFixedDepositAccountsAccountIdRequest());
			processInvestmentActivation(new PostFixedDepositAccountsAccountIdRequest());
		}
		return postSavingsAccountsResponse;
	}

	public PostFixedDepositAccountsAccountIdResponse processInvestmentCommand(Long investmentId,
			FixedDepositCommandRequest fixedDepositCommandRequest, String command) {
		PostFixedDepositAccountsAccountIdRequest postFixedDepositAccountsAccountIdRequest = new PostFixedDepositAccountsAccountIdRequest();
		if (APPROVE.equalsIgnoreCase(command)) {
			processInvestmentApproval(postFixedDepositAccountsAccountIdRequest);
		} else if (REJECT.equalsIgnoreCase(command)) {
			processInvestmentRejection(postFixedDepositAccountsAccountIdRequest);
		} else if (ACTIVATE.equalsIgnoreCase(command)) {
			processInvestmentActivation(postFixedDepositAccountsAccountIdRequest);
		} else if (CLOSE.equalsIgnoreCase(command) || PREMATURE_CLOSE.equalsIgnoreCase(command)) {
			processInvestmentClosureOnMaturity(postFixedDepositAccountsAccountIdRequest, fixedDepositCommandRequest);
		}
		return fixeddepositaccountsApiClient.handleCommands(investmentId, postFixedDepositAccountsAccountIdRequest,
				command);
	}

	private void processInvestmentClosureOnMaturity(
			PostFixedDepositAccountsAccountIdRequest postFixedDepositAccountsAccountIdRequest,
			FixedDepositCommandRequest fixedDepositCommandRequest) {
		populateDefaultFields(postFixedDepositAccountsAccountIdRequest);
		postFixedDepositAccountsAccountIdRequest.setClosedOnDate(getCurrentDate());
		postFixedDepositAccountsAccountIdRequest.setNote(fixedDepositCommandRequest.getNote());
		postFixedDepositAccountsAccountIdRequest
				.setOnAccountClosureId(fixedDepositCommandRequest.getOnAccountClosureId());
		postFixedDepositAccountsAccountIdRequest
				.setToSavingsAccountId(fixedDepositCommandRequest.getToSavingsAccountId());
		postFixedDepositAccountsAccountIdRequest
				.setTransferDescription(fixedDepositCommandRequest.getTransferDescription());
	}

	private void processInvestmentActivation(
			PostFixedDepositAccountsAccountIdRequest postFixedDepositAccountsAccountIdRequest) {
		populateDefaultFields(postFixedDepositAccountsAccountIdRequest);
		postFixedDepositAccountsAccountIdRequest.setActivatedOnDate(getCurrentDate());
	}

	private void processInvestmentRejection(
			PostFixedDepositAccountsAccountIdRequest postFixedDepositAccountsAccountIdRequest) {
		populateDefaultFields(postFixedDepositAccountsAccountIdRequest);
		postFixedDepositAccountsAccountIdRequest.setRejectedOnDate(getCurrentDate());
	}

	private void processInvestmentApproval(
			PostFixedDepositAccountsAccountIdRequest postFixedDepositAccountsAccountIdRequest) {
		populateDefaultFields(postFixedDepositAccountsAccountIdRequest);
		postFixedDepositAccountsAccountIdRequest.setApprovedOnDate(getCurrentDate());
	}

	private static void populateDefaultFields(
			PostFixedDepositAccountsAccountIdRequest postFixedDepositAccountsAccountIdRequest) {
		postFixedDepositAccountsAccountIdRequest.setLocale(DEFAULT_LOCALE);
		postFixedDepositAccountsAccountIdRequest.setDateFormat(DEFAULT_DATE_FORMAT);
	}

	public GetFixedDepositAccountsResponse retrieveAllInvestments(Boolean paged, Integer offset, Integer limit,
			String orderBy, String sortOrder) {
		List<GetFixedDepositAccountsAccountIdResponse> fixedDepositAccountsAccountIdResponseList = fixeddepositaccountsApiClient.retrieveAll38(paged, offset, limit, orderBy, sortOrder);
		GetFixedDepositAccountsResponse getFixedDepositAccountsResponse = new GetFixedDepositAccountsResponse();
		getFixedDepositAccountsResponse.setPageItems(new HashSet<>(fixedDepositAccountsAccountIdResponseList));
		getFixedDepositAccountsResponse.setTotalFilteredRecords(fixedDepositAccountsAccountIdResponseList.size());
		return getFixedDepositAccountsResponse;
	}

	public GetFixedDepositAccountsAccountIdResponse retrieveInvestmentById(Long id, Boolean staffInSelectedOfficeOnly,
																		   @Valid String chargeStatus, String associations) {
		return fixeddepositaccountsApiClient.retrieveOne(id, staffInSelectedOfficeOnly, chargeStatus, associations);
	}

	public Object retrieveTemplate(Long clientId, Long productId) {
		return fixeddepositaccountsApiClient.retrieveAccountTemplate(clientId, null, productId, null);
	}
}
