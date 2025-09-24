/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.techservices.digitalbanking.core.domain.dto.AccountDto;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import com.techservices.digitalbanking.customer.domian.data.repository.CustomerRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.exception.AbstractPlatformDomainRuleException;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.core.eBanking.api.WalletAccountApiClient;
import com.techservices.digitalbanking.core.eBanking.model.request.PostClientsDatatable;
import com.techservices.digitalbanking.core.eBanking.model.request.PostSavingsAccountsAccountIdRequest;
import com.techservices.digitalbanking.core.eBanking.model.request.PostSavingsAccountsRequest;
import com.techservices.digitalbanking.core.eBanking.model.response.GetSavingsAccountsAccountIdResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.GetSavingsAccountsResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostSavingsAccountsAccountIdResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostSavingsAccountsResponse;
import com.techservices.digitalbanking.core.util.DateUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.techservices.digitalbanking.core.util.CommandUtil.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountService {

	private final WalletAccountApiClient walletAccountApiClient;
	private static final Set<String> pepStatusOptions = Set.of("TRUE", "FALSE");
	private static final String CLOSE_COMMAND = "close";
	private static final String NOT_FOUND = "error.msg.savings.account.not.found";
	private final CustomerRepository customerRepository;
	private final ClientService clientService;


	public GetSavingsAccountsResponse retrieveAllSavingsAccounts(Long limit, Long offset, Long productId) {
		return walletAccountApiClient.retrieveAll(null, null, offset, limit, null, null, productId, null);
	}

	public PostSavingsAccountsResponse createSavingsAccount(String clientId, Long productId, String accountNumber,
															String accountName, String externalId, PostClientsDatatable postClientsDatatable, boolean active, Long savingsAccountNominalAnnualInterestRate, String gender, Long kycTierCode) {
		PostSavingsAccountsRequest postSavingsAccountsRequest = new PostSavingsAccountsRequest();
		postSavingsAccountsRequest.setClientId(clientId);
		postSavingsAccountsRequest.setProductId(productId);
		postSavingsAccountsRequest.setActive(active);
		postSavingsAccountsRequest.setLocale("en");
		postSavingsAccountsRequest.setGender(gender);
		postSavingsAccountsRequest.setKycTier(kycTierCode);
		postSavingsAccountsRequest.setAccountName(accountName);
		postSavingsAccountsRequest.setNominalAnnualInterestRate(savingsAccountNominalAnnualInterestRate);
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

		return walletAccountApiClient.submitApplication(postSavingsAccountsRequest);
	}

	public PostSavingsAccountsAccountIdResponse approveSavingsAccount(Long savingsAccountId) {
		PostSavingsAccountsAccountIdRequest postSavingsAccountsAccountIdRequest = new PostSavingsAccountsAccountIdRequest();
		postSavingsAccountsAccountIdRequest.setApprovedOnDate(DateUtil.getCurrentDate());
		postSavingsAccountsAccountIdRequest.setDateFormat(DateUtil.getDefaultDateFormat());
		postSavingsAccountsAccountIdRequest.setLocale("en");
		return walletAccountApiClient.handleCommand(savingsAccountId, postSavingsAccountsAccountIdRequest, "approve");
	}

	public PostSavingsAccountsAccountIdResponse activateSavingsAccount(Long savingsAccountId) {
		PostSavingsAccountsAccountIdRequest postSavingsAccountsAccountIdRequest = new PostSavingsAccountsAccountIdRequest();
		postSavingsAccountsAccountIdRequest.setActivatedOnDate(DateUtil.getCurrentDate());
		postSavingsAccountsAccountIdRequest.setDateFormat(DateUtil.getDefaultDateFormat());
		postSavingsAccountsAccountIdRequest.setLocale("en");
		return walletAccountApiClient.handleCommand(savingsAccountId, postSavingsAccountsAccountIdRequest, "activate");
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
		return walletAccountApiClient.handleCommand(savingsAccountId, postSavingsAccountsAccountIdRequest, "close");
	}

	public PostSavingsAccountsAccountIdResponse blockSavingsAccount(Long savingsAccountId, String reasonForBlock) {
		PostSavingsAccountsAccountIdRequest postSavingsAccountsAccountIdRequest = new PostSavingsAccountsAccountIdRequest();
		postSavingsAccountsAccountIdRequest.setReasonForBlock(reasonForBlock);
		return walletAccountApiClient.handleCommand(savingsAccountId, postSavingsAccountsAccountIdRequest, "block");
	}

	public PostSavingsAccountsAccountIdResponse unblockSavingsAccount(Long savingsAccountId) {
		return walletAccountApiClient.handleCommand(savingsAccountId, new PostSavingsAccountsAccountIdRequest(),
				"unblock");
	}

	public PostSavingsAccountsAccountIdResponse blockDebitSavingsAccount(Long savingsAccountId, String reasonForBlock) {
		PostSavingsAccountsAccountIdRequest postSavingsAccountsAccountIdRequest = new PostSavingsAccountsAccountIdRequest();
		postSavingsAccountsAccountIdRequest.setReasonForBlock(reasonForBlock);
		return walletAccountApiClient.handleCommand(savingsAccountId, new PostSavingsAccountsAccountIdRequest(),
				"blockDebit");
	}

	public PostSavingsAccountsAccountIdResponse unblockDebitSavingsAccount(Long savingsAccountId) {
		return walletAccountApiClient.handleCommand(savingsAccountId, new PostSavingsAccountsAccountIdRequest(),
				"unblockDebit");
	}

	public PostSavingsAccountsAccountIdResponse blockCreditSavingsAccount(Long savingsAccountId,
			String reasonForBlock) {
		PostSavingsAccountsAccountIdRequest postSavingsAccountsAccountIdRequest = new PostSavingsAccountsAccountIdRequest();
		postSavingsAccountsAccountIdRequest.setReasonForBlock(reasonForBlock);
		return walletAccountApiClient.handleCommand(savingsAccountId, postSavingsAccountsAccountIdRequest,
				"blockCredit");
	}

	public PostSavingsAccountsAccountIdResponse unblockCreditSavingsAccount(Long savingsAccountId) {
		return walletAccountApiClient.handleCommand(savingsAccountId, new PostSavingsAccountsAccountIdRequest(),
				"unblockCredit");
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

	public AccountDto retrieveSavingsAccount(String walletAccountNo) {
		Customer customer = customerRepository.findByAccountId(walletAccountNo)
				.orElseThrow(() -> new AbstractPlatformDomainRuleException(NOT_FOUND, "Savings account not found"));
		return clientService.getWalletAccountByCustomerId(customer);
	}
}
