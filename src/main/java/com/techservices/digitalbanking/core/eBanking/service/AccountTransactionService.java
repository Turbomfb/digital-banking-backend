/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.techservices.digitalbanking.core.domain.dto.AccountDto;
import jakarta.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.eBanking.api.AccountTransferApiClient;
import com.techservices.digitalbanking.core.eBanking.api.WalletAccountApiClient;
import com.techservices.digitalbanking.core.eBanking.configuration.FineractProperty;
import com.techservices.digitalbanking.core.eBanking.model.data.FineractPageResponse;
import com.techservices.digitalbanking.core.eBanking.model.data.FineractPageTransactionResponse;
import com.techservices.digitalbanking.core.eBanking.model.request.PostAccountTransfersRequest;
import com.techservices.digitalbanking.core.eBanking.model.request.PostClientsDatatable;
import com.techservices.digitalbanking.core.eBanking.model.request.PostSavingsAccountTransactionsRequest;
import com.techservices.digitalbanking.core.eBanking.model.response.GetSavingsAccountsAccountIdResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostAccountTransfersResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostSavingsAccountTransactionsResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.SavingsAccountTransactionData;
import com.techservices.digitalbanking.core.eBanking.model.response.TransactionMetadata;
import com.techservices.digitalbanking.core.util.DateUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.techservices.digitalbanking.core.util.TransactionUtil.DEPOSIT;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountTransactionService {
    private final WalletAccountApiClient walletAccountApiClient;
	private final AccountTransferApiClient accountTransferApiClient;
	private final FineractProperty fineractProperty;
	public static final String SAVINGS_ACCOUNT_TRANSACTION_DATATABLE_NAME = "dt_additional_transaction_information";

	private PostSavingsAccountTransactionsResponse getPostSavingsAccountTransactionsResponse(String command,
			Long savingsAccountId, PostSavingsAccountTransactionsRequest postSavingsAccountTransactionsRequest) {

		return walletAccountApiClient.handleTransactionCommand(savingsAccountId, postSavingsAccountTransactionsRequest,
				command);
	}

	public PostSavingsAccountTransactionsResponse handleDeposit(Long savingsAccountId, BigDecimal transactionAmount,
			String transactionReference, String narration, TransactionMetadata transactionMetadata) {
		PostSavingsAccountTransactionsRequest postSavingsAccountTransactionsRequest = getSavingsAccountTransactionRequest(
				transactionAmount, narration, transactionMetadata, transactionReference);

		return getPostSavingsAccountTransactionsResponse(DEPOSIT, savingsAccountId,
				postSavingsAccountTransactionsRequest);
	}

	private PostSavingsAccountTransactionsRequest getSavingsAccountTransactionRequest(BigDecimal transactionAmount,
			String narration, TransactionMetadata transactionMetadata, String transactionReference) {
		PostSavingsAccountTransactionsRequest postSavingsAccountTransactionsRequest = new PostSavingsAccountTransactionsRequest();
		postSavingsAccountTransactionsRequest.setTransactionAmount(transactionAmount);
		postSavingsAccountTransactionsRequest.setNote(narration);
		postSavingsAccountTransactionsRequest.setTransactionDate(DateUtil.getCurrentDate());
		postSavingsAccountTransactionsRequest.setDateFormat(DateUtil.getDefaultDateFormat());
		postSavingsAccountTransactionsRequest.setLocale(DateUtil.DEFAULT_LOCALE);
		postSavingsAccountTransactionsRequest.setPaymentTypeId(fineractProperty.getPaymentTypeId());

		if (transactionMetadata == null && transactionReference != null) {
			transactionMetadata = new TransactionMetadata();
			transactionMetadata.setTransactionReference(transactionReference);
			transactionMetadata.setAmount(transactionAmount);
			transactionMetadata.setCreditAccountName("");
			transactionMetadata.setDebitAccountNumber("");
			transactionMetadata.setCreditAccountNumber("");
			transactionMetadata.setDebitAccountName("");
			transactionMetadata.setDebitBvn("");
		} else if (transactionMetadata != null) {
			transactionMetadata.setTransactionReference(transactionMetadata.getTransactionReference() == null
					? transactionReference
					: transactionMetadata.getTransactionReference());
			transactionMetadata.setAmount(
					transactionMetadata.getAmount() == null ? transactionAmount : transactionMetadata.getAmount());
			PostClientsDatatable postClientsDatatable = getTransactionMetadata(transactionAmount, transactionMetadata);
			postSavingsAccountTransactionsRequest.setDatatables(Collections.singletonList(postClientsDatatable));
		}
		return postSavingsAccountTransactionsRequest;
	}

	private static PostClientsDatatable getTransactionMetadata(BigDecimal transactionAmount,
			TransactionMetadata transactionMetadata) {
		PostClientsDatatable postClientsDatatable = new PostClientsDatatable();
		postClientsDatatable.setRegisteredTableName(SAVINGS_ACCOUNT_TRANSACTION_DATATABLE_NAME);
		Map<String, Object> additionalTransactionInformation = new HashMap<>();
		additionalTransactionInformation.put("debit_account_number", transactionMetadata.getDebitAccountNumber());
		additionalTransactionInformation.put("debit_account_name", transactionMetadata.getDebitAccountName());
		additionalTransactionInformation.put("amount", transactionAmount);
		additionalTransactionInformation.put("credit_account_number", transactionMetadata.getCreditAccountNumber());
		additionalTransactionInformation.put("credit_account_name", transactionMetadata.getCreditAccountName());
		additionalTransactionInformation.put("transaction_reference", transactionMetadata.getTransactionReference());
		additionalTransactionInformation.put("debit_bvn", transactionMetadata.getDebitBvn());
		postClientsDatatable.setData(additionalTransactionInformation);
		return postClientsDatatable;
	}

	public FineractPageResponse<SavingsAccountTransactionData> retrieveSavingsAccountTransactions(String savingsAccountId,
																								  String startDate, String endDate, String dateFormat, Long limit, Long offset, @Valid String transactionType) {

		FineractPageTransactionResponse<SavingsAccountTransactionData> savingsAccountTransactions = walletAccountApiClient
				.searchTransactions(savingsAccountId, startDate, endDate, dateFormat, offset, limit);
		if (StringUtils.isNotBlank(transactionType)) {
			savingsAccountTransactions.setContent(
					savingsAccountTransactions.getContent().stream()
							.filter(transaction -> transaction != null && StringUtils.equalsIgnoreCase(transaction.getActualTransactionType(), transactionType))
							.toList()
			);
		}

		return FineractPageResponse.create(savingsAccountTransactions);
	}

	public PostSavingsAccountTransactionsResponse handleFlexDepositAccountTransfer(
			AccountDto fromSavingsAccount,
			String flexAccountId, BigDecimal transactionAmount, String narration) {

		PostAccountTransfersRequest postAccountTransfersRequest = new PostAccountTransfersRequest();
		postAccountTransfersRequest.setFromAccountId(fromSavingsAccount.getId());
		postAccountTransfersRequest.setFromAccountType(2L);
		postAccountTransfersRequest.setFromClientId(fromSavingsAccount.getCustomerId());
		postAccountTransfersRequest.setFromOfficeId(1L);
		postAccountTransfersRequest.setToClientId(fromSavingsAccount.getCustomerId());
		postAccountTransfersRequest.setToOfficeId(1L);
		postAccountTransfersRequest.setToAccountId(flexAccountId);
		postAccountTransfersRequest.setToAccountType(2L);
		postAccountTransfersRequest.setTransferAmount(transactionAmount);
		postAccountTransfersRequest.setTransferDescription(narration);
		postAccountTransfersRequest.setTransferDate(DateUtil.getCurrentDate());
		postAccountTransfersRequest.setDateFormat(DateUtil.getDefaultDateFormat());
		postAccountTransfersRequest.setLocale(DateUtil.DEFAULT_LOCALE);
		PostAccountTransfersResponse transferResponse = accountTransferApiClient
				.makeTransfer(postAccountTransfersRequest);
		PostSavingsAccountTransactionsResponse postSavingsAccountTransactionsResponse = new PostSavingsAccountTransactionsResponse();
		postSavingsAccountTransactionsResponse.setClientId(fromSavingsAccount.getCustomerId());
		postSavingsAccountTransactionsResponse.setResourceId(transferResponse.getResourceId());
		postSavingsAccountTransactionsResponse.setSavingsId(fromSavingsAccount.getId());
		return postSavingsAccountTransactionsResponse;
	}

	public PostSavingsAccountTransactionsResponse handleFlexWithdrawalAccountTransfer(
			AccountDto toSavingsAccount,
			String flexAccountId, BigDecimal transactionAmount, String narration) {

		PostAccountTransfersRequest postAccountTransfersRequest = new PostAccountTransfersRequest();
		postAccountTransfersRequest.setFromAccountId(flexAccountId);
		postAccountTransfersRequest.setFromAccountType(2L);
		postAccountTransfersRequest.setFromClientId(toSavingsAccount.getCustomerId());
		postAccountTransfersRequest.setFromOfficeId(1L);
		postAccountTransfersRequest.setToClientId(toSavingsAccount.getCustomerId());
		postAccountTransfersRequest.setToOfficeId(1L);
		postAccountTransfersRequest.setToAccountId(toSavingsAccount.getId());
		postAccountTransfersRequest.setToAccountType(2L);
		postAccountTransfersRequest.setTransferAmount(transactionAmount);
		postAccountTransfersRequest.setTransferDescription(narration);
		postAccountTransfersRequest.setTransferDate(DateUtil.getCurrentDate());
		postAccountTransfersRequest.setDateFormat(DateUtil.getDefaultDateFormat());
		postAccountTransfersRequest.setLocale(DateUtil.DEFAULT_LOCALE);
		PostAccountTransfersResponse transferResponse = accountTransferApiClient
				.makeTransfer(postAccountTransfersRequest);
		PostSavingsAccountTransactionsResponse postSavingsAccountTransactionsResponse = new PostSavingsAccountTransactionsResponse();
		postSavingsAccountTransactionsResponse.setClientId(toSavingsAccount.getCustomerId());
		postSavingsAccountTransactionsResponse.setResourceId(transferResponse.getResourceId());
		postSavingsAccountTransactionsResponse.setSavingsId(toSavingsAccount.getId());
		return postSavingsAccountTransactionsResponse;
	}

	public SavingsAccountTransactionData retrieveSavingsAccountTransactionById(String savingsAccountId,
																			   Long transactionId) {
		return walletAccountApiClient.retrieveOneTransaction(savingsAccountId, transactionId);
	}
}
