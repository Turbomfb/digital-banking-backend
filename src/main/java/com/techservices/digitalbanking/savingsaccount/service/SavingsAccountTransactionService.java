/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.savingsaccount.service;

import com.techservices.digitalbanking.core.fineract.model.data.FineractPageResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostSavingsAccountTransactionsResponse;
import com.techservices.digitalbanking.core.fineract.model.response.SavingsAccountTransactionData;
import com.techservices.digitalbanking.savingsaccount.request.CreateSavingsAccountTransactionRequest;

import jakarta.validation.Valid;

public interface SavingsAccountTransactionService {

	PostSavingsAccountTransactionsResponse processTransaction(CreateSavingsAccountTransactionRequest request,
			String command, String savingsAccountNumber, Long transactionId, Long productId);

	FineractPageResponse<SavingsAccountTransactionData> retrieveSavingsAccountTransactions(String savingsAccountNumber,
			String startDate, String endDate, String dateFormat, Long productId, @Valid Long limit, @Valid Long offset);

	SavingsAccountTransactionData retrieveSavingsAccountTransactionById(String savingsAccountNumber, Long transactionId,
			Long productId);
}
