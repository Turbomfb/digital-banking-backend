/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.loan.service.impl;

import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.core.service.ExternalLoanService;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import com.techservices.digitalbanking.customer.service.CustomerService;
import com.techservices.digitalbanking.loan.domain.response.LoanOfferResponse;
import org.springframework.stereotype.Service;

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
import com.techservices.digitalbanking.core.fineract.service.LoanService;
import com.techservices.digitalbanking.loan.domain.request.LoanApplicationRequest;
import com.techservices.digitalbanking.loan.domain.request.LoanRepaymentRequest;
import com.techservices.digitalbanking.loan.service.LoanApplicationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanApplicationServiceImpl implements LoanApplicationService {

	private final LoanService loanService;
	private final CustomerService customerService;
	private final ExternalLoanService externalLoanService;

	@Override
	public PostLoansResponse calculateLoanScheduleOrSubmitLoanApplication(LoanApplicationRequest loanApplicationRequest,
			String command) {
		return loanService.calculateLoanScheduleOrSubmitLoanApplication(loanApplicationRequest, command);
	}

	@Override
	public GetLoansLoanIdResponse retrieveLoanById(Long loanId, Boolean staffInSelectedOfficeOnly, String associations,
												   String exclude, String fields, Long customerId) {
		return loanService.retrieveLoanById(loanId, staffInSelectedOfficeOnly, associations, exclude, fields);
	}

	@Override
	public GetLoansResponse retrieveAllLoans(String sqlSearch, String externalId, Integer offset, Integer limit,
			String orderBy, String sortOrder, String accountNo) {
		return loanService.retrieveAllLoans(sqlSearch, externalId, offset, limit, orderBy, sortOrder, accountNo);
	}

	@Override
	public PostLoansLoanIdResponse processLoanCommand(Long loanId, PostLoansLoanIdRequest postLoansLoanIdRequest,
			@Valid String command) {
		return loanService.processLoanCommand(loanId, postLoansLoanIdRequest, command);
	}

	@Override
	public PostLoansLoanIdTransactionsResponse repayLoan(Long loanId, @Valid LoanRepaymentRequest loanRepaymentRequest,
			String command) {
		return loanService.repayLoan(loanId, loanRepaymentRequest, command);
	}

	@Override
	public GetLoanTemplateResponse retrieveLoanTemplate(@Valid String templateType, @Valid Long clientId,
			@Valid Long productId) {
		return loanService.retrieveLoanTemplate(templateType, clientId, productId);
	}

	@Override
	public FineractPageResponse<LoanTransactionResponse> retrieveLoanTransactions(Long loanId) {
		return loanService.retrieveLoanTransactions(loanId);
	}


	@Override
	public LoanTransactionResponse retrieveLoanTransactionDetails(Long loanId, Long transactionId) {
		return loanService.retrieveLoanTransactionDetails(loanId, transactionId);
	}

	@Override
	public BasePageResponse<LoanOfferResponse> retrieveCustomerLoanOffers(Long customerId) {
		Customer customer = customerService.getCustomerById(customerId);
		List<LoanOfferResponse> loanOffers = this.externalLoanService.retrieveCustomerLoanOffers(customer.getPhoneNumber());
		return BasePageResponse.instance(loanOffers);
	}

	@Override
	public GenericApiResponse processLoanApplication(Long customerId, LoanApplicationRequest loanApplicationRequest) {
		Customer customer = customerService.getCustomerById(customerId);
		loanApplicationRequest.setAcceptOffer("Y");
		loanApplicationRequest.setMsisdn(customer.getPhoneNumber());
		return this.externalLoanService.processLoanApplication(loanApplicationRequest);
	}

	@Override
	public LoanRescheduleResponse createALoanRescheduleRequest(LoanRescheduleRequest loanRescheduleRequest) {
		return loanService.createALoanRescheduleRequest(loanRescheduleRequest);
	}

	@Override
	public PostLoansLoanIdResponse processLoanRescheduleCommand(PostLoansLoanIdRequest postLoansLoanIdRequest,
			String requestId, String command) {
		return loanService.processLoanRescheduleCommand(postLoansLoanIdRequest, requestId, command);
	}
}
