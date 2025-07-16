/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.walletaccount.service;

import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.core.fineract.model.data.FineractPageResponse;
import com.techservices.digitalbanking.core.fineract.model.response.SavingsAccountTransactionData;

import com.techservices.digitalbanking.walletaccount.domain.request.SavingsAccountTransactionRequest;
import jakarta.validation.Valid;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface SavingsAccountTransactionService {

	FineractPageResponse<SavingsAccountTransactionData> retrieveSavingsAccountTransactions(Long customerId,
																						   String startDate, String endDate, String dateFormat, Long productId, @Valid Long limit, @Valid Long offset, @Valid String transactionType);

	SavingsAccountTransactionData retrieveSavingsAccountTransactionById(Long customerId, Long transactionId,
			Long productId);

	BigDecimal getBalanceAsOfDate(Long savingsId, LocalDate localDate);

	GenericApiResponse processTransactionCommand(@Valid String command, SavingsAccountTransactionRequest request, Long customerId);
}
