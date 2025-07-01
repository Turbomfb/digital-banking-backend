/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.savingsaccount.service.impl;

import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.core.service.ExternalPaymentService;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import com.techservices.digitalbanking.customer.service.CustomerService;
import com.techservices.digitalbanking.savingsaccount.domain.request.SavingsAccountTransactionRequest;
import com.techservices.digitalbanking.savingsaccount.domain.response.ExternalPaymentTransactionOtpGenerationResponse;
import com.techservices.digitalbanking.savingsaccount.domain.response.ExternalPaymentTransactionOtpVerificationResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.exception.AbstractPlatformDomainRuleException;
import com.techservices.digitalbanking.core.fineract.model.data.FineractPageResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetSavingsAccountsAccountIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostSavingsAccountTransactionsResponse;
import com.techservices.digitalbanking.core.fineract.model.response.SavingsAccountTransactionData;
import com.techservices.digitalbanking.core.fineract.service.AccountTransactionService;
import com.techservices.digitalbanking.savingsaccount.domain.request.CreateSavingsAccountTransactionRequest;
import com.techservices.digitalbanking.savingsaccount.service.SavingsAccountService;
import com.techservices.digitalbanking.savingsaccount.service.SavingsAccountTransactionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;

import static com.techservices.digitalbanking.core.util.CommandUtil.GENERATE_OTP_COMMAND;
import static com.techservices.digitalbanking.core.util.CommandUtil.VERIFY_OTP_COMMAND;
import static com.techservices.digitalbanking.core.util.TransactionUtil.*;
import static com.techservices.digitalbanking.core.util.TransactionUtil.UNDO;

@Slf4j
@Service
@RequiredArgsConstructor
public class SavingsAccountTransactionServiceImpl implements SavingsAccountTransactionService {
	private final AccountTransactionService accountTransactionService;
	private final SavingsAccountService savingsAccountService;
	private final ExternalPaymentService externalPaymentService;
	private final CustomerService customerService;

	@Override
	public PostSavingsAccountTransactionsResponse processTransaction(
			CreateSavingsAccountTransactionRequest createSavingsAccountTransactionRequest, String command,
			String savingsAccountNumber, Long transactionId, Long productId) {
		GetSavingsAccountsAccountIdResponse savingsAccount = savingsAccountService
				.retrieveSavingsAccountById(savingsAccountNumber);

		// if beneficiary account number is passed , then get details
		// of the beneficiary account
		GetSavingsAccountsAccountIdResponse toSavingsAccount = null;
		if (StringUtils.isNotBlank(createSavingsAccountTransactionRequest.beneficiaryAccountNumber())) {
			toSavingsAccount = savingsAccountService.retrieveSavingsAccountById(
					createSavingsAccountTransactionRequest.beneficiaryAccountNumber());
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
			Long customerId, String startDate, String endDate, String dateFormat, Long productId,
			Long limit, @Valid Long offset, @Valid String transactionType) {
		String savingsAccountId = customerService.getCustomerById(customerId).getAccountId();
		return accountTransactionService.retrieveSavingsAccountTransactions(Long.valueOf(savingsAccountId), startDate, endDate,
				dateFormat, limit, offset, transactionType);
	}

	@Override
	public SavingsAccountTransactionData retrieveSavingsAccountTransactionById(Long savingsAccountId,
			Long transactionId, Long productId) {
		return accountTransactionService.retrieveSavingsAccountTransactionById(savingsAccountId, transactionId);
	}

	@Override
	public BigDecimal getBalanceAsOfDate(Long savingsId, LocalDate localDate) {
		return this.retrieveSavingsAccountTransactions(savingsId, localDate.toString(), null, "yyyy-MM-dd", null, null, null, null)
				.getPageItems()
				.stream()
				.filter(transaction -> !transaction.getDate().isAfter(localDate))
				.sorted(Comparator.comparing(SavingsAccountTransactionData::getDate))
				.reduce((first, second) -> second)
				.map(SavingsAccountTransactionData::getRunningBalance)
				.orElse(BigDecimal.ZERO);
	}

	@Override
	public GenericApiResponse processTransactionCommand(String command, SavingsAccountTransactionRequest request, Long customerId) {

		if (GENERATE_OTP_COMMAND.equals(command)) {
			Customer customer = this.validateCustomerAccount(request, customerId);
			request.validateForOtpGeneration();
			if (!StringUtils.equals(customer.getTransactionPin(), request.getTransactionPin())) {
				throw new AbstractPlatformDomainRuleException("error.msg.customer.transaction.pin.mismatch",
						"Customer transaction pin is not correct");
			}
			ExternalPaymentTransactionOtpGenerationResponse response = externalPaymentService.generateOtp(request);
			return new GenericApiResponse(
					response.getMessage(),
					response.getStatus(),
					response.getData()
			);
		} else if (VERIFY_OTP_COMMAND.equals(command)) {
			this.validateCustomerAccount(request, customerId);
			request.validateForOtpVerification();
			ExternalPaymentTransactionOtpVerificationResponse response = externalPaymentService.verifyOtp(request);
			return new GenericApiResponse(
					response.getMessage(),
					response.getStatus(),
					response.getData()
			);
		}
		return null;
	}

	private Customer validateCustomerAccount(SavingsAccountTransactionRequest request, Long customerId) {
		Customer customer = customerService.getCustomerById(customerId);
		request.setSavingsId(customer.getAccountId());
		if (!customer.isTransactionPinSet()) {
			log.error("Customer with ID {} does not have a transaction pin set", customerId);
			throw new AbstractPlatformDomainRuleException("error.msg.customer.transaction.pin.not.set",
					"Customer transaction pin is not set. Please set a transaction pin before proceeding.");
		}
		log.info("Validating customer account for savings transaction: {}", customer);

		GetSavingsAccountsAccountIdResponse savingsAccount = savingsAccountService.retrieveSavingsAccountById(request.getSavingsId());
		if (savingsAccount.getSummary().getAvailableBalance().compareTo(request.getAmount()) < 0) {
			log.error("Insufficient funds: Available balance {} is less than requested amount {}",
					savingsAccount.getSummary().getAvailableBalance(), request.getAmount());
			throw new AbstractPlatformDomainRuleException("error.msg.insufficient.funds",
					"Insufficient funds. Available balance is less than the requested amount.");
		}
		return customer;
	}
}
