/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.walletaccount.service.impl;

import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.core.domain.dto.request.NotificationRequestDto;
import com.techservices.digitalbanking.core.domain.dto.request.OtpDto;
import com.techservices.digitalbanking.core.domain.enums.OtpType;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.core.fineract.service.AccountService;
import com.techservices.digitalbanking.core.redis.service.RedisService;
import com.techservices.digitalbanking.core.service.ExternalPaymentService;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import com.techservices.digitalbanking.customer.service.CustomerService;
import com.techservices.digitalbanking.walletaccount.domain.request.SavingsAccountTransactionRequest;
import com.techservices.digitalbanking.walletaccount.domain.request.WalletPaymentOrderRequest;
import com.techservices.digitalbanking.walletaccount.domain.response.ExternalPaymentTransactionOtpGenerationResponse;
import com.techservices.digitalbanking.walletaccount.domain.response.ExternalPaymentTransactionOtpVerificationResponse;
import com.techservices.digitalbanking.walletaccount.domain.response.WalletPaymentOrderResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.exception.AbstractPlatformDomainRuleException;
import com.techservices.digitalbanking.core.fineract.model.data.FineractPageResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetSavingsAccountsAccountIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.SavingsAccountTransactionData;
import com.techservices.digitalbanking.core.fineract.service.AccountTransactionService;
import com.techservices.digitalbanking.walletaccount.service.WalletAccountTransactionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;

import static com.techservices.digitalbanking.core.util.CommandUtil.GENERATE_OTP_COMMAND;
import static com.techservices.digitalbanking.core.util.CommandUtil.VERIFY_OTP_COMMAND;

@Slf4j
@Service
@RequiredArgsConstructor
public class WalletAccountTransactionServiceImpl implements WalletAccountTransactionService {
	private final AccountTransactionService accountTransactionService;
    private final ExternalPaymentService externalPaymentService;
	private final CustomerService customerService;
	private final AccountService accountService;
	private final RedisService redisService;


	@Override
	public FineractPageResponse<SavingsAccountTransactionData> retrieveSavingsAccountTransactions(
			Long customerId, String startDate, String endDate, String dateFormat, Long productId,
			Long limit, @Valid Long offset, @Valid String transactionType) {
		String savingsAccountId = customerService.getCustomerById(customerId).getAccountId();
		return accountTransactionService.retrieveSavingsAccountTransactions(Long.valueOf(savingsAccountId), startDate, endDate,
				dateFormat, limit, offset, transactionType);
	}

	@Override
	public SavingsAccountTransactionData retrieveSavingsAccountTransactionById(Long customerId,
			Long transactionId, Long productId) {
		String savingsAccountId = customerService.getCustomerById(customerId).getAccountId();
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
			request.setTransactionPin(null);
			NotificationRequestDto notificationRequestDto = new NotificationRequestDto(customer.getPhoneNumber(), customer.getEmailAddress());
			OtpDto otpDto = this.redisService.generateOtpRequest(request, OtpType.TRANSFER, notificationRequestDto, request.getAmount());
			String message = notificationRequestDto.getOtpResponseMessage();
			ExternalPaymentTransactionOtpGenerationResponse.Data responseData = new ExternalPaymentTransactionOtpGenerationResponse.Data(request.getAmount(), otpDto.getUniqueId());
			return new GenericApiResponse(message, "success", responseData);
		} else if (VERIFY_OTP_COMMAND.equals(command)) {
			SavingsAccountTransactionRequest otpData = (SavingsAccountTransactionRequest) this.redisService.validateOtpWithoutDeletingRecord(request.getReference(), request.getOtp(), OtpType.KYC_UPGRADE).getData();
			if (otpData.getAmount().compareTo(request.getAmount()) != 0) {
				throw new ValidationException("error.msg.customer.transaction.amount.mismatch",
						"Invalid payment reference provided");
			}
			this.validateCustomerAccount(request, customerId);
			request.validateForOtpVerification();
			ExternalPaymentTransactionOtpVerificationResponse response = externalPaymentService.initiateTransfer(request);
			this.redisService.validateOtp(request.getReference(), request.getOtp(), OtpType.KYC_UPGRADE);
			return new GenericApiResponse(
					response.getMessage(),
					response.getStatus(),
					response.getData()
			);
		}
		return null;
	}

	@Override
	public WalletPaymentOrderResponse initiatePaymentOrder(WalletPaymentOrderRequest request, Long customerId) throws Exception {
		Customer customer = customerService.getCustomerById(customerId);
		return externalPaymentService.initiateOrder(request, customer);
	}

	private Customer validateCustomerAccount(SavingsAccountTransactionRequest request, Long customerId) {
		Customer customer = customerService.getCustomerById(customerId);
		if (StringUtils.isBlank(customer.getAccountId())){
			log.error("Customer with ID {} does not have an account ID", customerId);
			throw new AbstractPlatformDomainRuleException("error.msg.customer.account.not.found",
					"Customer account not found. Please ensure the customer has upgraded their kyc.");
		}
		request.setSavingsId(customer.getAccountId());
		if (!customer.isTransactionPinSet()) {
			log.error("Customer with ID {} does not have a transaction pin set", customerId);
			throw new AbstractPlatformDomainRuleException("error.msg.customer.transaction.pin.not.set",
					"Customer transaction pin is not set. Please set a transaction pin before proceeding.");
		}
		log.info("Validating customer account for savings transaction: {}", customer);

		GetSavingsAccountsAccountIdResponse savingsAccount = accountService.retrieveSavingsAccount(Long.valueOf(request.getSavingsId()), true);
		if (savingsAccount.getSummary().getAvailableBalance().compareTo(request.getAmount()) < 0) {
			log.error("Insufficient funds: Available balance {} is less than requested amount {}",
					savingsAccount.getSummary().getAvailableBalance(), request.getAmount());
			throw new AbstractPlatformDomainRuleException("error.msg.insufficient.funds",
					"Insufficient funds. Available balance is less than the requested amount.");
		}
		return customer;
	}
}
