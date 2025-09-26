/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.loan.service;

import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.core.domain.dto.LoanDto;
import com.techservices.digitalbanking.core.eBanking.model.data.FineractPageResponse;
import com.techservices.digitalbanking.core.eBanking.model.request.LoanRescheduleRequest;
import com.techservices.digitalbanking.core.eBanking.model.response.GetLoanTemplateResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.GetLoansLoanIdResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.GetLoansResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.LoanRescheduleResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.LoanTransactionResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostLoansLoanIdRequest;
import com.techservices.digitalbanking.core.eBanking.model.response.PostLoansLoanIdResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostLoansResponse;
import com.techservices.digitalbanking.loan.domain.request.LoanApplicationRequest;
import com.techservices.digitalbanking.loan.domain.request.LoanRepaymentRequest;

import com.techservices.digitalbanking.loan.domain.response.LoanDashboardResponse;
import com.techservices.digitalbanking.loan.domain.response.LoanOfferResponse;
import jakarta.validation.Valid;

public interface LoanApplicationService {
	PostLoansResponse calculateLoanScheduleOrSubmitLoanApplication(LoanApplicationRequest loanApplicationRequest,
			String command);

	GetLoansLoanIdResponse retrieveLoanById(Long loanId, Boolean staffInSelectedOfficeOnly, String associations,
											String exclude, String fields, Long customerId);

	BasePageResponse<LoanDto> retrieveAllLoans(Long limit, String accountNo, Long customerId);

	PostLoansLoanIdResponse processLoanCommand(Long loanId, PostLoansLoanIdRequest postLoansLoanIdRequest,
			@Valid String command);

	GenericApiResponse repayLoan(Long loanId,
								 @Valid LoanRepaymentRequest postLoansLoanIdTransactionsRequest, String command, Long customerId);

	GetLoanTemplateResponse retrieveLoanTemplate(@Valid String templateType, @Valid Long clientId,
			@Valid Long productId);

	FineractPageResponse<LoanTransactionResponse> retrieveLoanTransactions(@Valid Long loanId, Long customerId);

	LoanRescheduleResponse createALoanRescheduleRequest(@Valid LoanRescheduleRequest loanRescheduleRequest);

	PostLoansLoanIdResponse processLoanRescheduleCommand(PostLoansLoanIdRequest postLoansLoanIdRequest,
			String requestId, String command);

	LoanTransactionResponse retrieveLoanTransactionDetails(@Valid Long loanId, @Valid Long transactionId, Long customerId);

	BasePageResponse<LoanOfferResponse> retrieveCustomerLoanOffers(Long customerId);

	GenericApiResponse processLoanApplication(Long customerId, @Valid LoanApplicationRequest loanApplicationRequest);

	LoanDashboardResponse retrieveCustomerLoanDashboard(Long customerId);
}
