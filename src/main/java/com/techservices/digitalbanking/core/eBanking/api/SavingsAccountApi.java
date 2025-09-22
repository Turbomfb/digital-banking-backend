/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.techservices.digitalbanking.core.eBanking.model.data.FineractPageResponse;
import com.techservices.digitalbanking.core.eBanking.model.data.FineractPageTransactionResponse;
import com.techservices.digitalbanking.core.eBanking.model.request.PostSavingsAccountTransactionsRequest;
import com.techservices.digitalbanking.core.eBanking.model.request.PostSavingsAccountsAccountIdRequest;
import com.techservices.digitalbanking.core.eBanking.model.request.PostSavingsAccountsRequest;
import com.techservices.digitalbanking.core.eBanking.model.request.PostTransactionQueryRequest;
import com.techservices.digitalbanking.core.eBanking.model.response.GetSavingsAccountsAccountIdResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.GetSavingsAccountsResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostSavingsAccountTransactionsResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostSavingsAccountsAccountIdResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostSavingsAccountsResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.SavingsAccountTransactionData;

import jakarta.validation.Valid;

public interface SavingsAccountApi {
	@GetMapping(value = "/savingsaccounts/{accountId}")
	GetSavingsAccountsAccountIdResponse retrieveSavingsAccount(@PathVariable("accountId") Long accountId,
			@RequestParam(value = "staffInSelectedOfficeOnly", required = false, defaultValue = "false") @Valid Boolean staffInSelectedOfficeOnly,
			@RequestParam(value = "chargeStatus", required = false, defaultValue = "all") @Valid String chargeStatus,
			@RequestParam(value = "offset", required = false) @Valid Long offset,
			@RequestParam(value = "limit", required = false) @Valid Long limit,
			@RequestParam(value = "associations", required = false) @Valid String associations);

	@GetMapping(value = "/savingsaccounts/findbyaccountno/{accountNo}")
	GetSavingsAccountsAccountIdResponse retrieveOneByAccountNo(@PathVariable("accountNo") String accountNo);

	@PostMapping(value = "/savingsaccounts/{accountId}")
	PostSavingsAccountsAccountIdResponse handleCommand(@PathVariable("accountId") Long accountId,
			@RequestBody @Valid PostSavingsAccountsAccountIdRequest postSavingsAccountsAccountIdRequest,
			@RequestParam(value = "command", required = false) @Valid String command);

	@GetMapping(value = "/savingsaccounts")
	GetSavingsAccountsResponse retrieveAll(@RequestParam(value = "sqlSearch", required = false) @Valid String sqlSearch,
			@RequestParam(value = "externalId", required = false) @Valid String externalId,
			@RequestParam(value = "offset", required = false) @Valid Long offset,
			@RequestParam(value = "limit", required = false) @Valid Long limit,
			@RequestParam(value = "orderBy", required = false) @Valid String orderBy,
			@RequestParam(value = "sortOrder", required = false) @Valid String sortOrder,
			@RequestParam(value = "productId", required = false) @Valid Long productId,
			@RequestParam(value = "accountNo", required = false) @Valid String accountNo);

	@PostMapping(value = "/savingsaccounts/{savingsId}/transactions")
	PostSavingsAccountTransactionsResponse handleTransactionCommand(@PathVariable("savingsId") Long savingsId,
			@Valid @RequestBody PostSavingsAccountTransactionsRequest postSavingsAccountTransactionsRequest,
			@Valid @RequestParam(value = "command", required = false) String command);

	@PostMapping(value = "/savingsaccounts/{savingsId}/transactions/{transactionId}")
	PostSavingsAccountTransactionsResponse handleCommandUsingTransactionId(@PathVariable("savingsId") Long savingsId,
			@Valid @RequestBody PostSavingsAccountTransactionsRequest postSavingsAccountTransactionsRequest,
			@PathVariable("transactionId") Long transactionId,
			@Valid @RequestParam(value = "command", required = false) String command);

	@GetMapping(value = "/savingsaccounts/{savingsId}/transactions/{transactionId}")
	SavingsAccountTransactionData retrieveOneTransaction(@PathVariable("savingsId") String savingsId,
			@PathVariable("transactionId") Long transactionId);

	@PostMapping(value = "/savingsaccounts")
	PostSavingsAccountsResponse submitApplication(
			@Valid @RequestBody PostSavingsAccountsRequest postSavingsAccountsRequest);

	@GetMapping(value = "/savingsaccounts/{savingsId}/transactions")
	FineractPageResponse<SavingsAccountTransactionData> retrieveTransactions(@PathVariable("savingsId") Long savingsId,
			@RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate,
			@RequestParam(required = false) String dateFormat,
			@RequestParam(value = "offset", required = false) @Valid Integer offset,
			@RequestParam(value = "limit", required = false) @Valid Integer limit);

	@GetMapping(value = "/savingsaccounts/{savingsId}/transactions/search")
	FineractPageTransactionResponse<SavingsAccountTransactionData> searchTransactions(
			@PathVariable("savingsId") Long savingsId, @RequestParam(required = false) String fromDate,
			@RequestParam(required = false) String toDate, @RequestParam(required = false) String dateFormat,
			@RequestParam(value = "offset", required = false) @Valid Long offset,
			@RequestParam(value = "limit", required = false) @Valid Long limit);

	@PostMapping(value = "/savingsaccounts/{savingsId}/transactions/transaction-query")
	SavingsAccountTransactionData getTransactionDetails(@PathVariable("savingsId") Long savingsId,
			@Valid @RequestBody PostTransactionQueryRequest postTransactionQueryRequest,
			@Valid @RequestParam(value = "tenantIdentifier") String tenantIdentifier);
}
