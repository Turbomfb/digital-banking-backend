/* (C)2024 */
package com.techservices.digitalbanking.loan.service.impl;

import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.core.domain.dto.LoanDto;
import com.techservices.digitalbanking.core.domain.dto.response.IdentityVerificationResponse;
import com.techservices.digitalbanking.core.eBanking.model.request.FilterDto;
import com.techservices.digitalbanking.core.eBanking.model.request.PostClientsAddressRequest;
import com.techservices.digitalbanking.core.eBanking.model.request.PostNewLoanApplicationRequest;
import com.techservices.digitalbanking.core.eBanking.model.response.*;
import com.techservices.digitalbanking.core.eBanking.service.AccountTransactionService;
import com.techservices.digitalbanking.core.eBanking.service.LoanService;
import com.techservices.digitalbanking.core.exception.PlatformServiceException;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.core.service.ExternalLoanService;
import com.techservices.digitalbanking.core.service.IdentityVerificationService;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import com.techservices.digitalbanking.customer.domian.dto.request.CreateCustomerRequest;
import com.techservices.digitalbanking.customer.service.CustomerService;
import com.techservices.digitalbanking.loan.domain.request.LoanApplicationRequest;
import com.techservices.digitalbanking.loan.domain.request.LoanRepaymentRequest;
import com.techservices.digitalbanking.loan.domain.request.LoanScheduleCalculationRequest;
import com.techservices.digitalbanking.loan.domain.request.NewLoanApplicationRequest;
import com.techservices.digitalbanking.loan.domain.response.LoanDashboardResponse;
import com.techservices.digitalbanking.loan.domain.response.LoanOfferResponse;
import com.techservices.digitalbanking.loan.domain.response.LoanScheduleCalculationResponse;
import com.techservices.digitalbanking.loan.service.LoanApplicationService;
import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoanApplicationServiceImpl implements LoanApplicationService {

  private final LoanService loanService;
  private final CustomerService customerService;
  private final ExternalLoanService externalLoanService;
  private final PasswordEncoder passwordEncoder;
  private final IdentityVerificationService identityVerificationService;
  private final AccountTransactionService accountTransactionService;

  @Override
  public LoanDto retrieveLoanById(Long loanId, Long customerId) {

    return loanService.retrieveLoanById(loanId, customerId);
  }

  public BasePageResponse<LoanDto> retrieveAllLoans(Long limit, String accountNo, Long customerId) {

    Customer customer = customerService.getCustomerById(customerId);
    FilterDto filterDto =
        new FilterDto()
            .emailAddress(customer.getEmailAddress())
            .customerType(customer.getUserType());
    return BasePageResponse.instance(
        loanService.retrieveAllCustomerLoans(filterDto).stream()
            .filter(
                loan ->
                    loan.getStatus() != null
                        && (loan.isActive() || loan.isPending() || loan.isLiquidated()))
            .toList());
  }

  @Override
  public GenericApiResponse repayLoan(
      Long loanId, @Valid LoanRepaymentRequest loanRepaymentRequest, Long customerId) {

    this.retrieveLoanById(loanId, customerId);
    if (StringUtils.isBlank(loanRepaymentRequest.getTransactionPin())) {
      throw new ValidationException(
          "validation.msg.loan.repayment.transaction.pin.required",
          "Transaction PIN is required for loan repayment");
    }
    Customer customer = customerService.getCustomerById(customerId);
    if (!passwordEncoder.matches(
        loanRepaymentRequest.getTransactionPin(), customer.getTransactionPin())) {
      throw new ValidationException(
          "validation.msg.loan.repayment.transaction.pin.invalid",
          "Invalid Transaction PIN provided for loan repayment");
    }

    accountTransactionService.handleWithdrawal(
        customer.getAccountId(),
        loanRepaymentRequest.getTransactionAmount(),
        null,
        "Loan Repayment");

    PostLoanRepaymentResponse response = loanService.repayLoan(loanId, loanRepaymentRequest);
    if (response != null && response.getMessage() != null) {
      return new GenericApiResponse("success", "Loan repayment successful");
    }
    log.info("Loan repayment failed {}", response);
    return new GenericApiResponse("error", "Loan repayment failed");
  }

  @Override
  public BasePageResponse<LoanTransactionResponse> retrieveLoanTransactions(
      Long loanId, Long customerId) {

    return loanService.retrieveLoanTransactions(loanId, customerId);
  }

  @Override
  public LoanTransactionResponse retrieveLoanTransactionDetails(
      Long loanId, Long transactionId, Long customerId) {

    return loanService.retrieveLoanTransactionDetails(loanId, transactionId, customerId);
  }

  @Override
  public BasePageResponse<LoanOfferResponse> retrieveCustomerLoanOffers(Long customerId) {

    Customer customer = customerService.getCustomerById(customerId);
    List<LoanOfferResponse> loanOffers;
    try {
      loanOffers = this.externalLoanService.retrieveCustomerLoanOffers(customer.getPhoneNumber());
    } catch (Exception e) {
      log.error("Error retrieving loan offers for customer {}: {}", customerId, e.getMessage());
      throw new ValidationException(
          "validation.msg.loan.offers.retrieval.failed",
          "Customer is not pre-qualified for loan offers.");
    }
    return BasePageResponse.instance(loanOffers);
  }

  @Override
  public GenericApiResponse processLoanApplication(
      Long customerId, LoanApplicationRequest loanApplicationRequest) {

    Customer customer = customerService.getCustomerById(customerId);
    loanApplicationRequest.setAcceptOffer("Y");
    loanApplicationRequest.setMsisdn(customer.getPhoneNumber());
    return this.externalLoanService.processLoanApplication(loanApplicationRequest);
  }

  @Override
  public LoanApplicationResponse processNewLoanApplication(
      Long customerId, NewLoanApplicationRequest loanApplicationRequest) {

    Customer customer = customerService.getCustomerById(customerId);
    PostNewLoanApplicationRequest postNewLoanApplicationRequest =
        new PostNewLoanApplicationRequest();
    postNewLoanApplicationRequest.setDuration(loanApplicationRequest.getDuration());
    postNewLoanApplicationRequest.setAmount(loanApplicationRequest.getAmount());
    postNewLoanApplicationRequest.setProductId(loanApplicationRequest.getProductId());
    CreateCustomerRequest createCustomerRequest = new CreateCustomerRequest();
    IdentityVerificationResponse ninData =
        identityVerificationService.retrieveNinData(customer.getNin());
    if (ninData.getData().getAddress() != null) {
      IdentityVerificationResponse.IdentityVerificationResponseData data = ninData.getData();
      IdentityVerificationResponse.IdentityVerificationResponseData.Address address =
          data.getAddress();
      PostClientsAddressRequest postClientsAddressRequest =
          PostClientsAddressRequest.parse(address);
      createCustomerRequest.setAddress(List.of(postClientsAddressRequest));
      createCustomerRequest.setGender(data.getGender());
      createCustomerRequest.setDateOfBirth(data.getDateOfBirth());
    }
    createCustomerRequest.setFirstname(customer.getFirstname());
    createCustomerRequest.setLastname(customer.getLastname());
    createCustomerRequest.setEmailAddress(customer.getEmailAddress());
    createCustomerRequest.setPhoneNumber(customer.getPhoneNumber());
    createCustomerRequest.setBvn(customer.getBvn());
    createCustomerRequest.setCustomerType(customer.getUserType());
    createCustomerRequest.setNin(customer.getNin());
    createCustomerRequest.setKycTier(customer.getKycTier().getCode());
    postNewLoanApplicationRequest.setCustomer(createCustomerRequest);
    postNewLoanApplicationRequest.setEmployerSector(loanApplicationRequest.getEmployerSector());
    postNewLoanApplicationRequest.setEmployerCategory(loanApplicationRequest.getEmployerCategory());
    postNewLoanApplicationRequest.setEmployerEmail(loanApplicationRequest.getEmployerEmail());
    postNewLoanApplicationRequest.setEmployerName(loanApplicationRequest.getEmployerName());
    LoanApplicationResponse response =
        this.loanService.processLoanApplication(postNewLoanApplicationRequest);
    if (response == null || response.getLoanId() == null) {
      log.error("Loan application failed for customer {}: {}", customerId, response);
      throw new PlatformServiceException("Loan application failed", "error");
    }
    return response;
  }

  @Override
  public LoanDashboardResponse retrieveCustomerLoanDashboard(Long customerId) {

    Customer customer = customerService.getCustomerById(customerId);
    FilterDto filterDto =
        new FilterDto()
            .emailAddress(customer.getEmailAddress())
            .customerType(customer.getUserType());
    List<LoanDto> customerLoans = loanService.retrieveAllCustomerLoans(filterDto);

    return LoanDashboardResponse.builder()
        .activeLoanBalance(getActiveLoanBalance(customerLoans))
        .totalExpectedRepayment(getTotalExpectedRepayment(customerLoans))
        .totalRepaid(getTotalRepayment(customerLoans))
        .activeLoanCount(getActiveLoanCount(customerLoans))
        .pendingLoanCount(getPendingLoanCount(customerLoans))
        .liquidatedLoanCount(getLiquidatedLoanCount(customerLoans))
        .build();
  }

  @Override
  public LoanScheduleCalculationResponse calculateLoanSchedule(
      LoanScheduleCalculationRequest loanScheduleCalculationRequest) {

    return loanService.calculateLoanSchedule(loanScheduleCalculationRequest);
  }

  public BigDecimal getActiveLoanBalance(List<LoanDto> customerLoans) {

    return customerLoans.stream()
        .filter(LoanDto::isActive)
        .map(loan -> Optional.ofNullable(loan.getOutstandingBalance()).orElse(BigDecimal.ZERO))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  public BigDecimal getTotalExpectedRepayment(List<LoanDto> customerLoans) {

    return customerLoans.stream()
        .filter(LoanDto::isActive)
        .map(loan -> Optional.ofNullable(loan.getTotalExpectedRepayment()).orElse(BigDecimal.ZERO))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  public BigDecimal getTotalRepayment(List<LoanDto> customerLoans) {

    return customerLoans.stream()
        .filter(LoanDto::isActive)
        .map(loan -> Optional.ofNullable(loan.getTotalRepaid()).orElse(BigDecimal.ZERO))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  public Long getActiveLoanCount(List<LoanDto> customerLoans) {

    return customerLoans.stream().filter(LoanDto::isActive).count();
  }

  public Long getPendingLoanCount(List<LoanDto> customerLoans) {

    return customerLoans.stream()
        .filter(loan -> loan.getStatus() != null && loan.isPending())
        .count();
  }

  public Long getLiquidatedLoanCount(List<LoanDto> customerLoans) {

    return customerLoans.stream()
        .filter(loan -> loan.getStatus() != null && loan.isLiquidated())
        .count();
  }
}
