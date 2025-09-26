/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.api;

import com.techservices.digitalbanking.core.domain.dto.AccountDto;
import com.techservices.digitalbanking.core.eBanking.model.request.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.techservices.digitalbanking.core.eBanking.model.data.FineractPageResponse;
import com.techservices.digitalbanking.core.eBanking.model.data.FineractPageTransactionResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.GetSavingsAccountsAccountIdResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.GetSavingsAccountsResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostSavingsAccountTransactionsResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostSavingsAccountsAccountIdResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostSavingsAccountsResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.SavingsAccountTransactionData;

import jakarta.validation.Valid;

public interface WalletAccountApi {
	@GetMapping(value = "/walletaccounts/{walletAccountNo}")
	AccountDto retrieveSavingsAccount(@PathVariable("walletAccountNo") String walletAccountNo);

	@GetMapping(value = "/walletaccounts/findbyaccountno/{accountNo}")
	GetSavingsAccountsAccountIdResponse retrieveOneByAccountNo(@PathVariable("accountNo") String accountNo);

	@PostMapping(value = "/walletaccounts/{walletAccountNo}")
	PostSavingsAccountsAccountIdResponse handleCommand(@PathVariable("walletAccountNo") Long walletAccountNo,
			@RequestBody @Valid PostSavingsAccountsAccountIdRequest postSavingsAccountsAccountIdRequest,
			@RequestParam(value = "command", required = false) @Valid String command);

	@GetMapping(value = "/walletaccounts")
	GetSavingsAccountsResponse retrieveAll(@RequestParam(value = "sqlSearch", required = false) @Valid String sqlSearch,
			@RequestParam(value = "externalId", required = false) @Valid String externalId,
			@RequestParam(value = "offset", required = false) @Valid Long offset,
			@RequestParam(value = "limit", required = false) @Valid Long limit,
			@RequestParam(value = "orderBy", required = false) @Valid String orderBy,
			@RequestParam(value = "sortOrder", required = false) @Valid String sortOrder,
			@RequestParam(value = "productId", required = false) @Valid Long productId,
			@RequestParam(value = "accountNo", required = false) @Valid String accountNo);

	@PostMapping(value = "/walletaccounts/{walletAccountNo}/transactions")
	PostSavingsAccountTransactionsResponse handleTransactionCommand(@PathVariable("walletAccountNo") Long walletAccountNo,
			@Valid @RequestBody PostSavingsAccountTransactionsRequest postSavingsAccountTransactionsRequest,
			@Valid @RequestParam(value = "command", required = false) String command);

	@PostMapping(value = "/walletaccounts/{walletAccountNo}/transactions/{transactionId}")
	PostSavingsAccountTransactionsResponse handleCommandUsingTransactionId(@PathVariable("walletAccountNo") Long walletAccountNo,
			@Valid @RequestBody PostSavingsAccountTransactionsRequest postSavingsAccountTransactionsRequest,
			@PathVariable("transactionId") Long transactionId,
			@Valid @RequestParam(value = "command", required = false) String command);

	@GetMapping(value = "/walletaccounts/{walletAccountNo}/transactions/{transactionId}")
	SavingsAccountTransactionData retrieveOneTransaction(@PathVariable("walletAccountNo") String walletAccountNo,
			@PathVariable("transactionId") Long transactionId);

	@PostMapping(value = "/walletaccounts")
	PostSavingsAccountsResponse submitApplication(
			@Valid @RequestBody WalletAccountCreationRequest walletAccountCreationRequest);

	@GetMapping(value = "/walletaccounts/{walletAccountNo}/transactions")
	FineractPageResponse<SavingsAccountTransactionData> retrieveTransactions(@PathVariable("walletAccountNo") Long walletAccountNo,
			@RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate,
			@RequestParam(required = false) String dateFormat,
			@RequestParam(value = "offset", required = false) @Valid Integer offset,
			@RequestParam(value = "limit", required = false) @Valid Integer limit);

	@GetMapping(value = "/walletaccounts/{walletAccountNo}/transactions/search")
	FineractPageTransactionResponse<SavingsAccountTransactionData> searchTransactions(
			@PathVariable("walletAccountNo") String walletAccountNo, @RequestParam(required = false) String fromDate,
			@RequestParam(required = false) String toDate, @RequestParam(required = false) String dateFormat,
			@RequestParam(value = "offset", required = false) @Valid Long offset,
			@RequestParam(value = "limit", required = false) @Valid Long limit);

	@PostMapping(value = "/walletaccounts/{walletAccountNo}/transactions/transaction-query")
	SavingsAccountTransactionData getTransactionDetails(@PathVariable("walletAccountNo") Long walletAccountNo,
			@Valid @RequestBody PostTransactionQueryRequest postTransactionQueryRequest,
			@Valid @RequestParam(value = "tenantIdentifier") String tenantIdentifier);
}
