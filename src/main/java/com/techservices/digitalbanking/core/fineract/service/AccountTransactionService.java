/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import jakarta.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.exception.AbstractPlatformDomainRuleException;
import com.techservices.digitalbanking.core.fineract.api.AccountTransferApiClient;
import com.techservices.digitalbanking.core.fineract.api.JournalEntryApiClient;
import com.techservices.digitalbanking.core.fineract.api.SavingsAccountApiClient;
import com.techservices.digitalbanking.core.fineract.configuration.FineractProperty;
import com.techservices.digitalbanking.core.fineract.model.data.FineractPageResponse;
import com.techservices.digitalbanking.core.fineract.model.data.FineractPageTransactionResponse;
import com.techservices.digitalbanking.core.fineract.model.request.GLAccount;
import com.techservices.digitalbanking.core.fineract.model.request.PostAccountTransfersRequest;
import com.techservices.digitalbanking.core.fineract.model.request.PostClientsDatatable;
import com.techservices.digitalbanking.core.fineract.model.request.PostGLToSavingAccountRequest;
import com.techservices.digitalbanking.core.fineract.model.request.PostSavingAccountToGlRequest;
import com.techservices.digitalbanking.core.fineract.model.request.PostSavingsAccountTransactionsRequest;
import com.techservices.digitalbanking.core.fineract.model.request.PostTransactionQueryRequest;
import com.techservices.digitalbanking.core.fineract.model.response.GetSavingsAccountsAccountIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostAccountTransfersResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostJournalEntryResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostSavingsAccountTransactionsResponse;
import com.techservices.digitalbanking.core.fineract.model.response.SavingsAccountTransactionData;
import com.techservices.digitalbanking.core.fineract.model.response.TransactionMetadata;
import com.techservices.digitalbanking.core.util.DateUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.techservices.digitalbanking.core.util.TransactionUtil.DEPOSIT;
import static com.techservices.digitalbanking.core.util.TransactionUtil.HOLD_AMOUNT;
import static com.techservices.digitalbanking.core.util.TransactionUtil.WITHDRAWAL;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountTransactionService {
	private static final String DEFAULT = "default";
	private final SavingsAccountApiClient savingsAccountApiClient;
	private final AccountTransferApiClient accountTransferApiClient;
	private final JournalEntryApiClient journalEntryApiClient;
	private final FineractProperty fineractProperty;
	public static final String SAVINGS_ACCOUNT_TRANSACTION_DATATABLE_NAME = "dt_additional_transaction_information";

	private PostSavingsAccountTransactionsResponse getPostSavingsAccountTransactionsResponse(String command,
			Long savingsAccountId, PostSavingsAccountTransactionsRequest postSavingsAccountTransactionsRequest) {

		return savingsAccountApiClient.handleTransactionCommand(savingsAccountId, postSavingsAccountTransactionsRequest,
				command);
	}

	private PostSavingsAccountTransactionsResponse processReversal(
			PostSavingsAccountTransactionsRequest postSavingsAccountTransactionsRequest,
			GetSavingsAccountsAccountIdResponse savingsAccount) {
		Long savingsAccountId = savingsAccount.getId();
		PostTransactionQueryRequest postTransactionQueryRequest = new PostTransactionQueryRequest();
		postTransactionQueryRequest.setTransactionDate(postSavingsAccountTransactionsRequest.getTransactionDate());
		postTransactionQueryRequest.setAmount(postSavingsAccountTransactionsRequest.getTransactionAmount());
		postTransactionQueryRequest
				.setTransactionReference(postSavingsAccountTransactionsRequest.getTransactionReference());
		postTransactionQueryRequest.setDateFormat(postSavingsAccountTransactionsRequest.getDateFormat());
		postTransactionQueryRequest.setLocale(postSavingsAccountTransactionsRequest.getLocale());

		// return savingsAccountApiClient.handleReversal(savingsAccountId,
		// postTransactionQueryRequest);
		return null;
	}

	public PostSavingsAccountTransactionsResponse handleWithdrawal(Long savingsAccountId, BigDecimal transactionAmount,
			String transactionReference, String narration, TransactionMetadata transactionMetadata) {
		PostSavingsAccountTransactionsRequest postSavingsAccountTransactionsRequest = getSavingsAccountTransactionRequest(
				transactionAmount, narration, transactionMetadata, transactionReference);

		return getPostSavingsAccountTransactionsResponse(WITHDRAWAL, savingsAccountId,
				postSavingsAccountTransactionsRequest);
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
			PostClientsDatatable postClientsDatatable = getTransactionMetadata(transactionAmount, transactionMetadata);
			postSavingsAccountTransactionsRequest.setDatatables(Collections.singletonList(postClientsDatatable));
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

	public PostSavingsAccountTransactionsResponse handleLienAmount(BigDecimal transactionAmount, Long savingsAccountId,
			String reasonForBlock) {
		PostSavingsAccountTransactionsRequest postSavingsAccountTransactionsRequest = new PostSavingsAccountTransactionsRequest();
		postSavingsAccountTransactionsRequest.setTransactionAmount(transactionAmount);
		postSavingsAccountTransactionsRequest.setTransactionDate(DateUtil.getCurrentDate());
		postSavingsAccountTransactionsRequest.setDateFormat(DateUtil.getDefaultDateFormat());
		postSavingsAccountTransactionsRequest.setLocale(DateUtil.DEFAULT_LOCALE);
		postSavingsAccountTransactionsRequest.setReasonForBlock(reasonForBlock);

		return getPostSavingsAccountTransactionsResponse(HOLD_AMOUNT, savingsAccountId,
				postSavingsAccountTransactionsRequest);
	}

	public PostSavingsAccountTransactionsResponse handleUnLienAmount(Long savingsAccountId,
			Long lienAmountTransactionId) {
		// PostSavingsAccountTransactionsRequest postSavingsAccountTransactionsRequest =
		// new
		// PostSavingsAccountTransactionsRequest();
		return savingsAccountApiClient.handleCommandUsingTransactionId(savingsAccountId,
				new PostSavingsAccountTransactionsRequest(), lienAmountTransactionId, "releaseAmount");
	}

	public PostSavingsAccountTransactionsResponse handleUndoTransaction(Long savingsAccountId, Long transactionId) {
		return savingsAccountApiClient.handleCommandUsingTransactionId(savingsAccountId,
				new PostSavingsAccountTransactionsRequest(), transactionId, "undo");
	}

	public FineractPageResponse<SavingsAccountTransactionData> retrieveSavingsAccountTransactions(Long savingsAccountId,
																								  String startDate, String endDate, String dateFormat, Long limit, Long offset, @Valid String transactionType) {

		FineractPageTransactionResponse<SavingsAccountTransactionData> savingsAccountTransactions = savingsAccountApiClient
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

	public PostSavingsAccountTransactionsResponse handleTransactionWithTransactionId(String command, Long transactionId,
			Long savingsAccountId, PostSavingsAccountTransactionsRequest postSavingsAccountTransactionsRequest) {
		return savingsAccountApiClient.handleCommandUsingTransactionId(savingsAccountId,
				postSavingsAccountTransactionsRequest, transactionId, command);
	}

	private PostSavingsAccountTransactionsResponse postGLToSavingsAccountJournalEntry(
			PostSavingsAccountTransactionsRequest postSavingsAccountTransactionsRequest,
			GetSavingsAccountsAccountIdResponse savingsAccount, Long officeId) {
		Long savingsAccountId = savingsAccount.getId();
		PostGLToSavingAccountRequest postGLToSavingAccountRequest = new PostGLToSavingAccountRequest();
		postGLToSavingAccountRequest.setLocale(postSavingsAccountTransactionsRequest.getLocale());
		postGLToSavingAccountRequest.setDateFormat(postSavingsAccountTransactionsRequest.getDateFormat());
		postGLToSavingAccountRequest.setOfficeId(officeId);
		postGLToSavingAccountRequest.setAmount(postSavingsAccountTransactionsRequest.getTransactionAmount());
		postGLToSavingAccountRequest.setTransactionDate(postSavingsAccountTransactionsRequest.getTransactionDate());
		postGLToSavingAccountRequest.setCurrencyCode(savingsAccount.getCurrency().getCode());
		postGLToSavingAccountRequest.setPaymentTypeId(postSavingsAccountTransactionsRequest.getPaymentTypeId());
		postGLToSavingAccountRequest
				.setReferenceNumber(postSavingsAccountTransactionsRequest.getTransactionReference());

		GLAccount account = new GLAccount();
		account.setAmount(postSavingsAccountTransactionsRequest.getTransactionAmount());
		postGLToSavingAccountRequest.setDebit(account);

		PostJournalEntryResponse postJournalEntryResponse = journalEntryApiClient
				.postGlToCasaJournalEntry(postGLToSavingAccountRequest, savingsAccountId);

		PostSavingsAccountTransactionsResponse transactionsResponse = new PostSavingsAccountTransactionsResponse();
		transactionsResponse.setOfficeId(postJournalEntryResponse.getOfficeId());
		transactionsResponse.setTransactionId(postJournalEntryResponse.getTransactionId());
		transactionsResponse.setSavingsId(savingsAccountId);
		return transactionsResponse;
	}

	public PostSavingsAccountTransactionsResponse postSavingsAccountToGlJournalEntry(
			PostSavingsAccountTransactionsRequest postSavingsAccountTransactionsRequest,
			GetSavingsAccountsAccountIdResponse savingsAccount, Long officeId) {
		Long savingsAccountId = savingsAccount.getId();
		PostSavingAccountToGlRequest postSavingAccountToGlRequest = new PostSavingAccountToGlRequest();
		postSavingAccountToGlRequest.setLocale(postSavingsAccountTransactionsRequest.getLocale());
		postSavingAccountToGlRequest.setDateFormat(postSavingsAccountTransactionsRequest.getDateFormat());
		postSavingAccountToGlRequest.setOfficeId(officeId);
		postSavingAccountToGlRequest.setAmount(postSavingsAccountTransactionsRequest.getTransactionAmount());
		postSavingAccountToGlRequest.setTransactionDate(postSavingsAccountTransactionsRequest.getTransactionDate());
		postSavingAccountToGlRequest.setCurrencyCode(savingsAccount.getCurrency().getCode());
		postSavingAccountToGlRequest.setPaymentTypeId(postSavingsAccountTransactionsRequest.getPaymentTypeId());
		postSavingAccountToGlRequest
				.setReferenceNumber(postSavingsAccountTransactionsRequest.getTransactionReference());

		GLAccount account = new GLAccount();
		account.setAmount(postSavingsAccountTransactionsRequest.getTransactionAmount());
		postSavingAccountToGlRequest.setCredits(account);

		PostJournalEntryResponse postJournalEntryResponse = journalEntryApiClient
				.postCasaToGlJournalEntry(postSavingAccountToGlRequest, savingsAccountId, DEFAULT);

		PostSavingsAccountTransactionsResponse transactionsResponse = new PostSavingsAccountTransactionsResponse();
		transactionsResponse.setOfficeId(postJournalEntryResponse.getOfficeId());
		transactionsResponse.setTransactionId(postJournalEntryResponse.getTransactionId());
		transactionsResponse.setSavingsId(savingsAccountId);
		return transactionsResponse;
	}

	public PostAccountTransfersResponse handleSavingsAccountTransfer(
			PostAccountTransfersRequest postAccountTransfersRequest) {
		return accountTransferApiClient.makeTransfer(postAccountTransfersRequest);
	}

	public PostSavingsAccountTransactionsResponse handleSavingsAccountTransfer(
			GetSavingsAccountsAccountIdResponse fromSavingsAccount,
			GetSavingsAccountsAccountIdResponse toSavingsAccount, BigDecimal transactionAmount, String narration) {

		if (toSavingsAccount == null) {
			throw new AbstractPlatformDomainRuleException("error.msg.savings.account.not.found",
					"Beneficiary Savings account not found");
		}

		PostAccountTransfersRequest postAccountTransfersRequest = new PostAccountTransfersRequest();
		postAccountTransfersRequest.setFromAccountId(fromSavingsAccount.getId());
		postAccountTransfersRequest.setFromAccountType(2L);
		postAccountTransfersRequest.setFromClientId(fromSavingsAccount.getClientId());
		postAccountTransfersRequest.setFromOfficeId(1L);
		postAccountTransfersRequest.setToClientId(toSavingsAccount.getClientId());
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
		postSavingsAccountTransactionsResponse.setClientId(fromSavingsAccount.getClientId());
		postSavingsAccountTransactionsResponse.setResourceId(transferResponse.getResourceId());
		postSavingsAccountTransactionsResponse.setSavingsId(fromSavingsAccount.getId());
		return postSavingsAccountTransactionsResponse;
	}

	public SavingsAccountTransactionData retrieveSavingsAccountTransactionById(Long savingsAccountId,
			Long transactionId) {
		return savingsAccountApiClient.retrieveOneTransaction(savingsAccountId, transactionId);
	}
}
