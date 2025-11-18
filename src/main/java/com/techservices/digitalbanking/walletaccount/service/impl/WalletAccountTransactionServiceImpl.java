/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.walletaccount.service.impl;

import com.techservices.digitalbanking.core.configuration.BankConfigurationService;
import com.techservices.digitalbanking.core.configuration.resttemplate.ApiService;
import com.techservices.digitalbanking.core.domain.data.model.TransactionLog;
import com.techservices.digitalbanking.core.domain.data.repository.TransactionLogRepository;
import com.techservices.digitalbanking.core.domain.dto.AccountDto;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.core.domain.dto.TransactionDto;
import com.techservices.digitalbanking.core.domain.dto.request.NotificationRequestDto;
import com.techservices.digitalbanking.core.domain.dto.request.OtpDto;
import com.techservices.digitalbanking.core.domain.enums.AlertType;
import com.techservices.digitalbanking.core.domain.enums.OtpType;
import com.techservices.digitalbanking.core.domain.enums.TransactionType;
import com.techservices.digitalbanking.core.eBanking.model.request.FilterDto;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.core.eBanking.service.AccountService;
import com.techservices.digitalbanking.core.redis.service.RedisService;
import com.techservices.digitalbanking.core.service.BeneficiaryService;
import com.techservices.digitalbanking.core.service.ExternalPaymentService;
import com.techservices.digitalbanking.core.service.NotificationService;
import com.techservices.digitalbanking.core.util.NotificationUtil;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import com.techservices.digitalbanking.customer.domian.data.repository.CustomerRepository;
import com.techservices.digitalbanking.customer.service.CustomerService;
import com.techservices.digitalbanking.walletaccount.domain.data.PaymentOrderStatus;
import com.techservices.digitalbanking.walletaccount.domain.data.model.PaymentOrder;
import com.techservices.digitalbanking.walletaccount.domain.data.repository.PaymentOrderRepository;
import com.techservices.digitalbanking.walletaccount.domain.request.SavingsAccountTransactionRequest;
import com.techservices.digitalbanking.walletaccount.domain.request.WalletInboundWebhookRequest;
import com.techservices.digitalbanking.walletaccount.domain.request.WalletPaymentOrderRequest;
import com.techservices.digitalbanking.walletaccount.domain.response.ExternalPaymentTransactionOtpGenerationResponse;
import com.techservices.digitalbanking.walletaccount.domain.response.ExternalPaymentTransactionOtpVerificationResponse;
import com.techservices.digitalbanking.walletaccount.domain.response.WalletPaymentOrderResponse;
import com.techservices.digitalbanking.walletaccount.service.WalletAccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.exception.AbstractPlatformDomainRuleException;
import com.techservices.digitalbanking.core.eBanking.model.response.SavingsAccountTransactionData;
import com.techservices.digitalbanking.core.eBanking.service.AccountTransactionService;
import com.techservices.digitalbanking.walletaccount.service.WalletAccountTransactionService;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Optional;
import java.util.UUID;

import static com.techservices.digitalbanking.core.domain.enums.TransactionType.INTER_BANK_OUTBOUND;
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
  private final PaymentOrderRepository paymentOrderRepository;
  private final ApiService apiService;
  private final PasswordEncoder passwordEncoder;
  private final NotificationService notificationService;
  private final NotificationUtil notificationUtil;
  private final WalletAccountService walletAccountService;
  private final BankConfigurationService bankConfigurationService;
  private final CustomerRepository customerRepository;
  private final TransactionLogRepository transactionLogRepository;
  private final BeneficiaryService beneficiaryService;


  @Override
  public BasePageResponse<TransactionDto> retrieveSavingsAccountTransactions(
      Long customerId, String startDate, String endDate,
      Long limit, TransactionType transactionType) {
    String savingsAccountId = customerService.getCustomerById(customerId).getAccountId();
    FilterDto filter = new FilterDto(savingsAccountId, startDate, endDate, limit, transactionType);
    return BasePageResponse.instance(
        accountTransactionService.retrieveAllAccountTransactions(filter));
  }

  @Override
  public SavingsAccountTransactionData retrieveSavingsAccountTransactionById(Long customerId,
      Long transactionId) {
    String savingsAccountId = customerService.getCustomerById(customerId).getAccountId();
    return accountTransactionService.retrieveSavingsAccountTransactionById(savingsAccountId,
        transactionId);
  }

  @Override
  public BigDecimal getBalanceAsOfDate(Long customerId, LocalDate startDate) {
    return this.retrieveSavingsAccountTransactions(customerId, startDate.toString(), null, null,
            TransactionType.ALL)
        .getData()
        .stream()
        .filter(transaction -> !transaction.getDate().isAfter(startDate.atStartOfDay()))
        .sorted(Comparator.comparing(TransactionDto::getDate))
        .reduce((first, second) -> second)
        .map(TransactionDto::getRunningBalance)
        .orElse(BigDecimal.ZERO);
  }

  @Override
  public GenericApiResponse processTransactionCommand(String command,
      SavingsAccountTransactionRequest request, Long customerId) {

    Customer sender = this.validateCustomerAccount(request, customerId);
    if (GENERATE_OTP_COMMAND.equals(command)) {
      request.validateForOtpGeneration();
      if (!passwordEncoder.matches(request.getTransactionPin(), sender.getTransactionPin())) {
        throw new AbstractPlatformDomainRuleException("error.msg.sender.transaction.pin.mismatch",
            "Customer transaction pin is not correct");
      }
      request.setTransactionPin(null);
      NotificationRequestDto notificationRequestDto = new NotificationRequestDto(
          sender.getPhoneNumber(), sender.getEmailAddress());
      OtpDto otpDto = this.redisService.generateOtpRequest(request, OtpType.TRANSFER,
          notificationRequestDto, request.getAmount());
      String message = notificationRequestDto.getOtpResponseMessage();
      ExternalPaymentTransactionOtpGenerationResponse.Data responseData = new ExternalPaymentTransactionOtpGenerationResponse.Data(
          request.getAmount(), otpDto.getUniqueId());
      return new GenericApiResponse(message, "success", responseData);
    } else if (VERIFY_OTP_COMMAND.equals(command)) {
      Optional<Customer> recipient = customerRepository.findByNuban(request.getAccountNumber());

      SavingsAccountTransactionRequest otpData = (SavingsAccountTransactionRequest) this.redisService.validateOtpWithoutDeletingRecord(
          request.getReference(), request.getOtp(), OtpType.KYC_UPGRADE).getData();
      if (otpData.getAmount().compareTo(request.getAmount()) != 0) {
        throw new ValidationException("error.msg.sender.transaction.amount.mismatch",
            "Invalid payment reference provided");
      }
      this.validateCustomerAccount(request, customerId);
      request.validateForOtpVerification();
      ExternalPaymentTransactionOtpVerificationResponse response;

      boolean isIntraBankTransfer = StringUtils.equalsIgnoreCase(request.getBankNipCode(),
          bankConfigurationService.getBankCode());
      TransactionLog transactionLog = null;
      AccountDto accountResponse = walletAccountService.retrieveSavingsAccountById(sender.getId());
      BigDecimal balance = accountResponse.getAccountBalance().subtract(otpData.getAmount());
      BigDecimal recipientBalance = null;
      if (isIntraBankTransfer) {
        try {
          if (recipient.isPresent()) {
            Customer foundRecipient = recipient.get();
            recipientBalance = walletAccountService.retrieveSavingsAccountById(
                foundRecipient.getId()).getAccountBalance().add(otpData.getAmount());
            accountTransactionService.processIntraTransafer(sender.getAccountId(),
                foundRecipient.getAccountId(), request.getAmount(),
                "Transfer | " + request.getNarration());
          } else {
            log.error("Recipient with account number {} not found for intra-bank transfer",
                request.getAccountNumber());
            throw new ValidationException("error.processing.transaction",
                "We're unable to process your transaction command at the moment. Please try again or contact support if the issue persists");
          }
        } catch (Exception e) {
          log.error("Error processing transaction command: {}", e.getMessage(), e);
          throw new ValidationException("error.processing.transaction",
              "We're unable to process your transaction command at the moment. Please try again or contact support if the issue persists",
              e);
        }
      } else {
        try {
          transactionLog = createInterbankTransactionLog(request, sender);
          transactionLog = transactionLogRepository.save(transactionLog);
          log.info("Created interbank transaction log with PENDING status: {}",
              transactionLog.getTransactionReference());

          accountTransactionService.handleWithdrawal(sender.getAccountId(), request.getAmount(),
              null, "Transfer | " + request.getNarration());
          log.info("Successfully debited sender account for transaction: {}",
              transactionLog.getTransactionReference());
        } catch (Exception e) {
          log.error("Error processing transaction command: {}", e.getMessage(), e);
          throw new ValidationException("error.processing.transaction",
              "We're unable to process your transaction command at the moment. Please try again or contact support if the issue persists",
              e);
        }
        try {
          externalPaymentService.initiateTransfer(request, transactionLog);
        } catch (Exception e) {
          transactionLog.setResponseCode("99");
          transactionLog.setResponseMessage(e.getMessage());
          log.error("Error processing transaction command: {}", e.getMessage(), e);
        }
      }
      if (transactionLog != null) {
        transactionLogRepository.save(transactionLog);
      }
      this.redisService.validateOtp(request.getReference(), request.getOtp(), OtpType.TRANSFER);
      this.redisService.save(request, OtpType.TRANSFER, request.getReference());
      try {
        String transactionMessage = notificationUtil.getTransactionNotificationTemplate(
            TransactionType.DEBIT.name(), request.getAmount().toString(), balance,
            request.getNarration());
        notificationService.notifyUser(sender, transactionMessage, AlertType.TRANSACTION);

        if (isIntraBankTransfer) {
          Customer foundRecipient = recipient.get();
          transactionMessage = notificationUtil.getTransactionNotificationTemplate(
              TransactionType.CREDIT.name(), request.getAmount().toString(), recipientBalance,
              request.getNarration());
          notificationService.notifyUser(foundRecipient, transactionMessage, AlertType.TRANSACTION);
        }
      } catch (Exception e) {
        log.error("Error sending transaction notification: {}", e.getMessage(), e);
      }

      try {
        beneficiaryService.markBeneficiaryAsUsed(
            request.getAccountNumber(),
            request.getBankNipCode(),
            request.getAccountName(),
            request.getBankName(),
            request.isAddToBeneficiary(),
            customerId
        );
      } catch (Exception e) {
        log.error("Error marking beneficiary as used: {}", e.getMessage(), e);
      }
      return new GenericApiResponse(
          "Transaction has been processed successfully",
          "success",
          null
      );
    }
    throw new ValidationException("error.command.not.supported",
        "Command not supported. Supported commands are: " + GENERATE_OTP_COMMAND + ", "
            + VERIFY_OTP_COMMAND);
  }

  private TransactionLog createInterbankTransactionLog(SavingsAccountTransactionRequest request,
      Customer customer) {
    TransactionLog log = new TransactionLog();
    log.setId(UUID.randomUUID().toString());
    log.setCustomerId(customer.getId());
    log.setBeneficiaryAccountNumber(request.getAccountNumber());
    log.setBeneficiaryAccountName(request.getAccountName());
    log.setBeneficiaryBankName(request.getBankName());
    log.setBeneficiaryBankCode(request.getBankNipCode());
    log.setAmount(request.getAmount());
    log.setNarration(request.getNarration());
    log.setTransactionReference(request.getReference());
    log.setStatus("PENDING");
    log.setTransactionType(INTER_BANK_OUTBOUND.name());
    return log;
  }

  @Override
  public WalletPaymentOrderResponse initiatePaymentOrder(WalletPaymentOrderRequest request,
      Long customerId) {
    Customer customer = customerService.getCustomerById(customerId);
    PaymentOrder paymentOrder = new PaymentOrder();
    paymentOrder.setAmount(request.getAmount());
    String reference = UUID.randomUUID().toString();
    paymentOrder.setReference(reference);
    paymentOrder.setCurrency(request.getCurrency());
    paymentOrder.setCustomerId(customerId);
    paymentOrder.setStatus(PaymentOrderStatus.IN_PROGRESS);
    WalletPaymentOrderResponse response;
    try {
      response = externalPaymentService.initiateOrder(request, customer, reference);
    } catch (Exception e) {
      log.error("Error initiating payment order for customer {}: {}", customerId, e.getMessage(),
          e);
      throw new ValidationException("error.payment.order.initiation",
          "We're unable to process your payment order at the moment. Please try again or contact support if the issue persists",
          e);
    }
    paymentOrderRepository.save(paymentOrder);
    return response;
  }

  @Override
  public GenericApiResponse receiveInboundWebhook(WalletInboundWebhookRequest request) {
    Optional<PaymentOrder> paymentOrder = paymentOrderRepository.findByReference(
        request.getReference());
    if (paymentOrder.isPresent()) {
      PaymentOrder paymentOrderEntity = paymentOrder.get();
      if (paymentOrderEntity.getAmount().compareTo(request.getAmount()) != 0) {
        log.error("Payment order amount mismatch: expected {}, received {}",
            paymentOrderEntity.getAmount(), request.getAmount());
        throw new ValidationException("error.msg.payment.order.amount.mismatch",
            "Payment order amount mismatch. Please check the transaction details.");
      }
      if (paymentOrderEntity.getStatus() != PaymentOrderStatus.IN_PROGRESS) {
        log.error("Payment order status is not in progress: {}", paymentOrderEntity.getStatus());
        throw new ValidationException("error.msg.payment.order.status.invalid",
            "Payment order status is not valid for processing. Current status: "
                + paymentOrderEntity.getStatus());
      }
      if (StringUtils.equalsIgnoreCase(PaymentOrderStatus.COMPLETED.name(), request.getStatus())
          || StringUtils.equalsIgnoreCase("Successful", request.getStatus())) {
        Customer customer = customerService.getCustomerById(paymentOrderEntity.getCustomerId());
        try {
          accountTransactionService.handleDeposit(customer.getAccountId(),
              paymentOrderEntity.getAmount(),
              paymentOrderEntity.getReference(), "Deposit | " + paymentOrderEntity.getReference());
          paymentOrderEntity.setStatus(PaymentOrderStatus.COMPLETED);
          paymentOrderRepository.save(paymentOrderEntity);
          AccountDto accountResponse = walletAccountService.retrieveSavingsAccountById(
              customer.getId());
          BigDecimal balance = accountResponse.getAccountBalance();
          String transactionMessage = notificationUtil.getTransactionNotificationTemplate(
              TransactionType.CREDIT.name(), paymentOrderEntity.getAmount().toString(), balance,
              paymentOrderEntity.getReference());
          notificationService.notifyUser(customer, transactionMessage, AlertType.TRANSACTION);
          return new GenericApiResponse("Inbound transaction successfully processed", "success");
        } catch (Exception e) {
          log.error("Error processing deposit for payment order {}: {}",
              paymentOrderEntity.getReference(), e.getMessage());
          throw new ValidationException("error.msg.payment.order.deposit.failed",
              "Failed to process deposit for payment order. Please try again later.");
        }
      }
    }
    throw new ValidationException("error.msg.payment.order.not.found",
        "Payment order not found or invalid. Please check the reference and try again.");
  }

  private Customer validateCustomerAccount(SavingsAccountTransactionRequest request,
      Long customerId) {
    Customer customer = customerService.getCustomerById(customerId);
    if (StringUtils.isBlank(customer.getAccountId())) {
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

    AccountDto savingsAccount = accountService.retrieveSavingsAccount(request.getSavingsId());
    if (savingsAccount.getAccountBalance().compareTo(request.getAmount()) < 0) {
      log.error("Insufficient funds: Available balance {} is less than requested amount {}",
          savingsAccount.getAccountBalance(), request.getAmount());
      throw new AbstractPlatformDomainRuleException("error.msg.insufficient.funds",
          "Insufficient funds. Available balance is less than the requested amount.");
    }
    return customer;
  }
}
