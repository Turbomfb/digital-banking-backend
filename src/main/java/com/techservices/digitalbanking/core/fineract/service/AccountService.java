/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.exception.AbstractPlatformDomainRuleException;
import com.techservices.digitalbanking.core.exception.AbstractPlatformResourceNotFoundException;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.core.fineract.api.SavingsAccountApiClient;
import com.techservices.digitalbanking.core.fineract.api.SearchApiClient;
import com.techservices.digitalbanking.core.fineract.model.request.PostClientsDatatable;
import com.techservices.digitalbanking.core.fineract.model.request.PostSavingsAccountsAccountIdRequest;
import com.techservices.digitalbanking.core.fineract.model.request.PostSavingsAccountsRequest;
import com.techservices.digitalbanking.core.fineract.model.response.GetSavingsAccountsAccountIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetSavingsAccountsResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetSearchResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostSavingsAccountsAccountIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostSavingsAccountsResponse;
import com.techservices.digitalbanking.core.util.DateUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.techservices.digitalbanking.core.util.CommandUtil.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountService {

	private final SavingsAccountApiClient savingsAccountApiClient;
	private final SearchApiClient searchApiClient;
	private static final Set<String> pepStatusOptions = Set.of("TRUE", "FALSE");
	private static final String CLOSE_COMMAND = "close";
	private static final String NOT_FOUND = "error.msg.savings.account.not.found";

	private PostSavingsAccountsAccountIdResponse manageSavingsAccount(
			PostSavingsAccountsAccountIdRequest postSavingsAccountsAccountIdRequest, String savingsAccountNumber,
			String command) {
		GetSearchResponse searchResponse = searchByAccountNumber(savingsAccountNumber);
		Long savingsAccountId = searchResponse.getEntityId();
		return savingsAccountApiClient.handleCommand(savingsAccountId, postSavingsAccountsAccountIdRequest, command);
	}

	public GetSavingsAccountsResponse retrieveAllSavingsAccounts(Long limit, Long offset, Long productId) {
		return savingsAccountApiClient.retrieveAll(null, null, offset, limit, null, null, productId, null);
	}

	public GetSavingsAccountsAccountIdResponse retrieveSavingsAccount(String savingsAccountNumber, Long productId,
			boolean includeTransactions) {
		GetSearchResponse searchResponse = searchByAccountNumber(savingsAccountNumber);
		Long savingsAccountId = searchResponse.getEntityId();
		String associations = includeTransactions ? "all" : null;
		GetSavingsAccountsAccountIdResponse savingsAccount = savingsAccountApiClient
				.retrieveSavingsAccount(savingsAccountId, false, "all", null, null, associations);
		if (!Objects.equals(productId, savingsAccount.getSavingsProductId()) && productId != null) {
			throw new AbstractPlatformResourceNotFoundException(NOT_FOUND,
					String.format("Savings Account %s not found", savingsAccountNumber));
		}
		return savingsAccount;
	}

	public GetSavingsAccountsAccountIdResponse retrieveSavingsAccount(Long savingsAccountId, boolean includeTransactions) {
		String associations = includeTransactions ? "all" : null;
        return savingsAccountApiClient
                .retrieveSavingsAccount(savingsAccountId, false, "all", null, null, associations);
	}

	public GetSearchResponse searchByAccountNumber(String savingsAccountNumber) {
		List<GetSearchResponse> searchResponseList = searchApiClient.searchData(savingsAccountNumber, "savings", true);
		if (searchResponseList.isEmpty()) {
			throw new AbstractPlatformResourceNotFoundException(NOT_FOUND,
					String.format("account number %s does not exist ", savingsAccountNumber));
		}
		return searchResponseList.stream()
				.filter(i -> i.getEntityAccountNo().equals(Long.parseLong(savingsAccountNumber))).findFirst()
				.orElseThrow();
	}

	public PostSavingsAccountsResponse createSavingsAccount(Long clientId, Long productId, String accountNumber,
			String accountName, String externalId, PostClientsDatatable postClientsDatatable, boolean active) {
		PostSavingsAccountsRequest postSavingsAccountsRequest = new PostSavingsAccountsRequest();
		postSavingsAccountsRequest.setClientId(clientId);
		postSavingsAccountsRequest.setProductId(productId);
		postSavingsAccountsRequest.setActive(active);
		postSavingsAccountsRequest.setLocale("en");
		postSavingsAccountsRequest.setAccountName(accountName);
		postSavingsAccountsRequest.setDateFormat(DateUtil.getDefaultDateFormat());

		postSavingsAccountsRequest.setSubmittedOnDate(DateUtil.getCurrentDate());

		if (StringUtils.isNotBlank(accountNumber)) {
			postSavingsAccountsRequest.setAccountNo(accountNumber);
		}

		if (StringUtils.isNotBlank(externalId)) {
			postSavingsAccountsRequest.setExternalId(externalId);
		}

		if (postClientsDatatable != null) {
			List<PostClientsDatatable> datatables = StringUtils.isEmpty(postClientsDatatable.getRegisteredTableName())
					? Collections.emptyList()
					: List.of(postClientsDatatable);
			postSavingsAccountsRequest.setDatatables(datatables);
		}

		return savingsAccountApiClient.submitApplication(postSavingsAccountsRequest);
	}

	public PostSavingsAccountsAccountIdResponse approveSavingsAccount(Long savingsAccountId) {
		PostSavingsAccountsAccountIdRequest postSavingsAccountsAccountIdRequest = new PostSavingsAccountsAccountIdRequest();
		postSavingsAccountsAccountIdRequest.setApprovedOnDate(DateUtil.getCurrentDate());
		postSavingsAccountsAccountIdRequest.setDateFormat(DateUtil.getDefaultDateFormat());
		postSavingsAccountsAccountIdRequest.setLocale("en");
		return savingsAccountApiClient.handleCommand(savingsAccountId, postSavingsAccountsAccountIdRequest, "approve");
	}

	public PostSavingsAccountsAccountIdResponse activateSavingsAccount(Long savingsAccountId) {
		PostSavingsAccountsAccountIdRequest postSavingsAccountsAccountIdRequest = new PostSavingsAccountsAccountIdRequest();
		postSavingsAccountsAccountIdRequest.setActivatedOnDate(DateUtil.getCurrentDate());
		postSavingsAccountsAccountIdRequest.setDateFormat(DateUtil.getDefaultDateFormat());
		postSavingsAccountsAccountIdRequest.setLocale("en");
		return savingsAccountApiClient.handleCommand(savingsAccountId, postSavingsAccountsAccountIdRequest, "activate");
	}

	public PostSavingsAccountsAccountIdResponse closeSavingsAccount(
			GetSavingsAccountsAccountIdResponse savingsAccount) {
		Long savingsAccountId = savingsAccount.getId();
		// validate balance is zero
		if (savingsAccount.getSummary().getAccountBalance().compareTo(BigDecimal.ZERO) > 0
				|| savingsAccount.getSummary().getAccountBalance().compareTo(BigDecimal.ZERO) < 0) {
			throw new AbstractPlatformDomainRuleException("validation.msg.savings.account.balance.not.zero",
					"Savings account cannot be closed. Please transfer the balance to another account before closing.");
		}
		PostSavingsAccountsAccountIdRequest postSavingsAccountsAccountIdRequest = new PostSavingsAccountsAccountIdRequest();
		postSavingsAccountsAccountIdRequest.setClosedOnDate(DateUtil.getCurrentDate());
		postSavingsAccountsAccountIdRequest.setDateFormat(DateUtil.getDefaultDateFormat());
		postSavingsAccountsAccountIdRequest.setLocale("en");
		postSavingsAccountsAccountIdRequest.setWithdrawBalance(false);
		return savingsAccountApiClient.handleCommand(savingsAccountId, postSavingsAccountsAccountIdRequest, "close");
	}

	public PostSavingsAccountsAccountIdResponse blockSavingsAccount(Long savingsAccountId, String reasonForBlock) {
		PostSavingsAccountsAccountIdRequest postSavingsAccountsAccountIdRequest = new PostSavingsAccountsAccountIdRequest();
		postSavingsAccountsAccountIdRequest.setReasonForBlock(reasonForBlock);
		return savingsAccountApiClient.handleCommand(savingsAccountId, postSavingsAccountsAccountIdRequest, "block");
	}

	public PostSavingsAccountsAccountIdResponse unblockSavingsAccount(Long savingsAccountId) {
		return savingsAccountApiClient.handleCommand(savingsAccountId, new PostSavingsAccountsAccountIdRequest(),
				"unblock");
	}

	public PostSavingsAccountsAccountIdResponse blockDebitSavingsAccount(Long savingsAccountId, String reasonForBlock) {
		PostSavingsAccountsAccountIdRequest postSavingsAccountsAccountIdRequest = new PostSavingsAccountsAccountIdRequest();
		postSavingsAccountsAccountIdRequest.setReasonForBlock(reasonForBlock);
		return savingsAccountApiClient.handleCommand(savingsAccountId, new PostSavingsAccountsAccountIdRequest(),
				"blockDebit");
	}

	public PostSavingsAccountsAccountIdResponse unblockDebitSavingsAccount(Long savingsAccountId) {
		return savingsAccountApiClient.handleCommand(savingsAccountId, new PostSavingsAccountsAccountIdRequest(),
				"unblockDebit");
	}

	public PostSavingsAccountsAccountIdResponse blockCreditSavingsAccount(Long savingsAccountId,
			String reasonForBlock) {
		PostSavingsAccountsAccountIdRequest postSavingsAccountsAccountIdRequest = new PostSavingsAccountsAccountIdRequest();
		postSavingsAccountsAccountIdRequest.setReasonForBlock(reasonForBlock);
		return savingsAccountApiClient.handleCommand(savingsAccountId, postSavingsAccountsAccountIdRequest,
				"blockCredit");
	}

	public PostSavingsAccountsAccountIdResponse unblockCreditSavingsAccount(Long savingsAccountId) {
		return savingsAccountApiClient.handleCommand(savingsAccountId, new PostSavingsAccountsAccountIdRequest(),
				"unblockCredit");
	}

	public GetSavingsAccountsAccountIdResponse retrieveSavingsAccountById(Long savingsAccountId) {
		return savingsAccountApiClient.retrieveSavingsAccount(savingsAccountId, false, "all", null, null, "all");
	}

	public PostSavingsAccountsAccountIdResponse manageSavingsAccountCommand(
			GetSavingsAccountsAccountIdResponse getSavingsAccountsAccountIdResponse, String command) {
		switch (command) {
			case CLOSE -> {
				return closeSavingsAccount(getSavingsAccountsAccountIdResponse);
			}
			case ACTIVATE -> {
				return activateSavingsAccount(getSavingsAccountsAccountIdResponse.getId());
			}
			case APPROVE -> {
				return approveSavingsAccount(getSavingsAccountsAccountIdResponse.getId());
			}
		}
		throw new ValidationException("Invalid command");
	}
}
