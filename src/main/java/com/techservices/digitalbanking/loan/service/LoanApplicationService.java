/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.loan.service;

import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.core.fineract.model.data.FineractPageResponse;
import com.techservices.digitalbanking.core.fineract.model.request.LoanRescheduleRequest;
import com.techservices.digitalbanking.core.fineract.model.response.GetLoanTemplateResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetLoansLoanIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetLoansResponse;
import com.techservices.digitalbanking.core.fineract.model.response.LoanRescheduleResponse;
import com.techservices.digitalbanking.core.fineract.model.response.LoanTransactionResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostLoansLoanIdRequest;
import com.techservices.digitalbanking.core.fineract.model.response.PostLoansLoanIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostLoansLoanIdTransactionsResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostLoansResponse;
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

	GetLoansResponse retrieveAllLoans(String sqlSearch, String externalId, Integer offset, Integer limit,
									  String orderBy, String sortOrder, String accountNo, @Valid String clientId, @Valid String status);

	PostLoansLoanIdResponse processLoanCommand(Long loanId, PostLoansLoanIdRequest postLoansLoanIdRequest,
			@Valid String command);

	PostLoansLoanIdTransactionsResponse repayLoan(Long loanId,
			@Valid LoanRepaymentRequest postLoansLoanIdTransactionsRequest, String command);

	GetLoanTemplateResponse retrieveLoanTemplate(@Valid String templateType, @Valid Long clientId,
			@Valid Long productId);

	FineractPageResponse<LoanTransactionResponse> retrieveLoanTransactions(@Valid Long loanId);

	LoanRescheduleResponse createALoanRescheduleRequest(@Valid LoanRescheduleRequest loanRescheduleRequest);

	PostLoansLoanIdResponse processLoanRescheduleCommand(PostLoansLoanIdRequest postLoansLoanIdRequest,
			String requestId, String command);

	LoanTransactionResponse retrieveLoanTransactionDetails(@Valid Long loanId, @Valid Long transactionId);

	BasePageResponse<LoanOfferResponse> retrieveCustomerLoanOffers(Long customerId);

	GenericApiResponse processLoanApplication(Long customerId, @Valid LoanApplicationRequest loanApplicationRequest);

	LoanDashboardResponse retrieveCustomerLoanDashboard(Long customerId);
}
