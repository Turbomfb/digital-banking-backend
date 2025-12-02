/* (C)2024 */
package com.techservices.digitalbanking.walletaccount.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.core.domain.dto.TransactionDto;
import com.techservices.digitalbanking.core.domain.enums.TransactionType;
import com.techservices.digitalbanking.core.eBanking.model.response.SavingsAccountTransactionData;
import com.techservices.digitalbanking.walletaccount.domain.request.SavingsAccountTransactionRequest;
import com.techservices.digitalbanking.walletaccount.domain.request.WalletInboundWebhookRequest;
import com.techservices.digitalbanking.walletaccount.domain.request.WalletPaymentOrderRequest;
import com.techservices.digitalbanking.walletaccount.domain.response.WalletPaymentOrderResponse;

import jakarta.validation.Valid;

public interface WalletAccountTransactionService {

	BasePageResponse<TransactionDto> retrieveSavingsAccountTransactions(Long customerId, String startDate,
			String endDate, @Valid Long limit, TransactionType transactionType);

	SavingsAccountTransactionData retrieveSavingsAccountTransactionById(Long customerId, Long transactionId);

	BigDecimal getBalanceAsOfDate(Long customerId, LocalDate localDate);

	GenericApiResponse processTransactionCommand(@Valid String command, SavingsAccountTransactionRequest request,
			Long customerId);

	WalletPaymentOrderResponse initiatePaymentOrder(WalletPaymentOrderRequest request, Long customerId);

	GenericApiResponse receiveInboundWebhook(WalletInboundWebhookRequest request);
}
