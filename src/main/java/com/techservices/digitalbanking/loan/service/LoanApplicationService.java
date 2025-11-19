/* (C)2024 */
package com.techservices.digitalbanking.loan.service;

import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.core.domain.dto.LoanDto;
import com.techservices.digitalbanking.core.eBanking.model.response.LoanApplicationResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.LoanTransactionResponse;
import com.techservices.digitalbanking.loan.domain.request.LoanApplicationRequest;
import com.techservices.digitalbanking.loan.domain.request.LoanRepaymentRequest;
import com.techservices.digitalbanking.loan.domain.request.LoanScheduleCalculationRequest;
import com.techservices.digitalbanking.loan.domain.request.NewLoanApplicationRequest;
import com.techservices.digitalbanking.loan.domain.response.LoanDashboardResponse;
import com.techservices.digitalbanking.loan.domain.response.LoanOfferResponse;
import com.techservices.digitalbanking.loan.domain.response.LoanScheduleCalculationResponse;
import jakarta.validation.Valid;

public interface LoanApplicationService {

  LoanDto retrieveLoanById(Long loanId, Long customerId);

  BasePageResponse<LoanDto> retrieveAllLoans(Long limit, String accountNo, Long customerId);

  GenericApiResponse repayLoan(
      Long loanId, @Valid LoanRepaymentRequest postLoansLoanIdTransactionsRequest, Long customerId);

  BasePageResponse<LoanTransactionResponse> retrieveLoanTransactions(
      @Valid Long loanId, Long customerId);

  LoanTransactionResponse retrieveLoanTransactionDetails(
      @Valid Long loanId, @Valid Long transactionId, Long customerId);

  BasePageResponse<LoanOfferResponse> retrieveCustomerLoanOffers(Long customerId);

  GenericApiResponse processLoanApplication(
      Long customerId, @Valid LoanApplicationRequest loanApplicationRequest);

  LoanApplicationResponse processNewLoanApplication(
      Long customerId, NewLoanApplicationRequest loanApplicationRequest);

  LoanDashboardResponse retrieveCustomerLoanDashboard(Long customerId);

  LoanScheduleCalculationResponse calculateLoanSchedule(
      @Valid LoanScheduleCalculationRequest loanScheduleCalculationRequest);
}
