/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.savingsaccount.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.exception.AbstractPlatformDomainRuleException;
import com.techservices.digitalbanking.core.fineract.model.data.FineractPageResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetSavingsAccountsAccountIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostSavingsAccountTransactionsResponse;
import com.techservices.digitalbanking.core.fineract.model.response.SavingsAccountTransactionData;
import com.techservices.digitalbanking.core.fineract.service.AccountTransactionService;
import com.techservices.digitalbanking.savingsaccount.request.CreateSavingsAccountTransactionRequest;
import com.techservices.digitalbanking.savingsaccount.service.SavingsAccountService;
import com.techservices.digitalbanking.savingsaccount.service.SavingsAccountTransactionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import static com.techservices.digitalbanking.core.util.TransactionUtil.*;
import static com.techservices.digitalbanking.core.util.TransactionUtil.UNDO;

@Service
@RequiredArgsConstructor
public class SavingsAccountTransactionServiceImpl implements SavingsAccountTransactionService {
	private final AccountTransactionService accountTransactionService;
	private final SavingsAccountService savingsAccountService;

	@Override
	public PostSavingsAccountTransactionsResponse processTransaction(
			CreateSavingsAccountTransactionRequest createSavingsAccountTransactionRequest, String command,
			String savingsAccountNumber, Long transactionId, Long productId) {
		GetSavingsAccountsAccountIdResponse savingsAccount = savingsAccountService
				.retrieveSavingsAccountById(savingsAccountNumber, productId);

		// if beneficiary account number is passed , then get details
		// of the beneficiary account
		GetSavingsAccountsAccountIdResponse toSavingsAccount = null;
		if (StringUtils.isNotBlank(createSavingsAccountTransactionRequest.beneficiaryAccountNumber())) {
			toSavingsAccount = savingsAccountService.retrieveSavingsAccountById(
					createSavingsAccountTransactionRequest.beneficiaryAccountNumber(), productId);
		}

		Long savingsAccountId = savingsAccount.getId();

		return switch (command) {
			case DEPOSIT -> accountTransactionService.handleDeposit(savingsAccountId,
					createSavingsAccountTransactionRequest.transactionAmount(),
					createSavingsAccountTransactionRequest.transactionReference(),
					createSavingsAccountTransactionRequest.narration(),
					createSavingsAccountTransactionRequest.additionalInformation());
			case WITHDRAWAL -> accountTransactionService.handleWithdrawal(savingsAccountId,
					createSavingsAccountTransactionRequest.transactionAmount(),
					createSavingsAccountTransactionRequest.transactionReference(),
					createSavingsAccountTransactionRequest.narration(),
					createSavingsAccountTransactionRequest.additionalInformation());
			case TRANSFER -> accountTransactionService.handleSavingsAccountTransfer(savingsAccount, toSavingsAccount,
					createSavingsAccountTransactionRequest.transactionAmount(),
					createSavingsAccountTransactionRequest.narration());
			case HOLD_AMOUNT ->
				accountTransactionService.handleLienAmount(createSavingsAccountTransactionRequest.transactionAmount(),
						savingsAccountId, createSavingsAccountTransactionRequest.reasonForBlock());
			case RELEASE_AMOUNT -> accountTransactionService.handleUnLienAmount(savingsAccountId, transactionId);
			case UNDO -> accountTransactionService.handleUndoTransaction(savingsAccountId, transactionId);
			default -> throw new AbstractPlatformDomainRuleException("error.msg.invalid.transaction.command",
					"Invalid transaction command");
		};
	}

	@Override
	public FineractPageResponse<SavingsAccountTransactionData> retrieveSavingsAccountTransactions(
			Long savingsAccountId, String startDate, String endDate, String dateFormat, Long productId,
			Long limit, @Valid Long offset) {
		return accountTransactionService.retrieveSavingsAccountTransactions(savingsAccountId, startDate, endDate,
				dateFormat, limit, offset);
	}

	@Override
	public SavingsAccountTransactionData retrieveSavingsAccountTransactionById(Long savingsAccountId,
			Long transactionId, Long productId) {
		return accountTransactionService.retrieveSavingsAccountTransactionById(savingsAccountId, transactionId);
	}
}
