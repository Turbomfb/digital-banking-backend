/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.domain.dto.TransactionDto;
import com.techservices.digitalbanking.core.domain.enums.TransactionType;
import com.techservices.digitalbanking.core.eBanking.api.TransactionApiClient;
import com.techservices.digitalbanking.core.eBanking.api.WalletAccountApiClient;
import com.techservices.digitalbanking.core.eBanking.model.request.FilterDto;
import com.techservices.digitalbanking.core.eBanking.model.request.PostAccountTransfersRequest;
import com.techservices.digitalbanking.core.eBanking.model.request.PostSavingsAccountTransactionsRequest;
import com.techservices.digitalbanking.core.eBanking.model.response.PostAccountTransfersResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostSavingsAccountTransactionsResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.SavingsAccountTransactionData;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountTransactionService {
	private final WalletAccountApiClient walletAccountApiClient;
	private final TransactionApiClient transactionApiClient;

	private PostSavingsAccountTransactionsResponse getPostSavingsAccountTransactionsResponse(TransactionType command,
			String savingsAccountId, PostSavingsAccountTransactionsRequest postSavingsAccountTransactionsRequest) {

		return walletAccountApiClient.handleTransactionCommand(savingsAccountId, postSavingsAccountTransactionsRequest,
				command);
	}

	public PostSavingsAccountTransactionsResponse handleDeposit(String savingsAccountId, BigDecimal transactionAmount,
			String transactionReference, String narration) {

		PostSavingsAccountTransactionsRequest postSavingsAccountTransactionsRequest = getSavingsAccountTransactionRequest(
				transactionAmount, narration, transactionReference);

		return getPostSavingsAccountTransactionsResponse(TransactionType.CREDIT, savingsAccountId,
				postSavingsAccountTransactionsRequest);
	}

	public PostSavingsAccountTransactionsResponse handleWithdrawal(String savingsAccountId,
			BigDecimal transactionAmount, String transactionReference, String narration) {

		PostSavingsAccountTransactionsRequest postSavingsAccountTransactionsRequest = getSavingsAccountTransactionRequest(
				transactionAmount, narration, transactionReference);

		return getPostSavingsAccountTransactionsResponse(TransactionType.DEBIT, savingsAccountId,
				postSavingsAccountTransactionsRequest);
	}

	private PostSavingsAccountTransactionsRequest getSavingsAccountTransactionRequest(BigDecimal transactionAmount,
			String narration, String transactionReference) {

		PostSavingsAccountTransactionsRequest postSavingsAccountTransactionsRequest = new PostSavingsAccountTransactionsRequest();
		postSavingsAccountTransactionsRequest.setTransactionAmount(transactionAmount);
		postSavingsAccountTransactionsRequest.setNarration(narration);
		postSavingsAccountTransactionsRequest.setTransactionReference(transactionReference);
		return postSavingsAccountTransactionsRequest;
	}

	public PostAccountTransfersResponse processIntraTransafer(String senderAccountNumber, String recipientAccountNumber,
			BigDecimal transactionAmount, String narration) {

		PostAccountTransfersRequest postAccountTransfersRequest = new PostAccountTransfersRequest();
		postAccountTransfersRequest.setFromAccountId(senderAccountNumber);
		postAccountTransfersRequest.setToAccountId(recipientAccountNumber);
		postAccountTransfersRequest.setTransferAmount(transactionAmount);
		postAccountTransfersRequest.setTransferDescription(narration);
		return transactionApiClient.makeTransfer(TransactionType.INTRA_BANK_TRANSFER, postAccountTransfersRequest);
	}

	public SavingsAccountTransactionData retrieveSavingsAccountTransactionById(String savingsAccountId,
			Long transactionId) {

		return walletAccountApiClient.retrieveOneTransaction(savingsAccountId, transactionId);
	}

	public List<TransactionDto> retrieveAllAccountTransactions(FilterDto filter) {

		return transactionApiClient.retrieveAllTransactionsByAccountNo(filter);
	}
}
