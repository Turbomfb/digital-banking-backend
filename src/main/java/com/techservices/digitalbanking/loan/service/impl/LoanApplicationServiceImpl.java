/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.loan.service.impl;

import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.core.service.ExternalLoanService;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import com.techservices.digitalbanking.customer.service.CustomerService;
import com.techservices.digitalbanking.loan.domain.response.LoanDashboardResponse;
import com.techservices.digitalbanking.loan.domain.response.LoanOfferResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
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
											 String orderBy, String sortOrder, String accountNo, String clientId, String status) {
		return loanService.retrieveAllLoans(sqlSearch, externalId, offset, limit, orderBy, sortOrder, accountNo, clientId, status);
	}

	@Override
	public PostLoansLoanIdResponse processLoanCommand(Long loanId, PostLoansLoanIdRequest postLoansLoanIdRequest,
			@Valid String command) {
		return loanService.processLoanCommand(loanId, postLoansLoanIdRequest, command);
	}

	@Override
	public GenericApiResponse repayLoan(Long loanId, @Valid LoanRepaymentRequest loanRepaymentRequest,
										String command, Long customerId) {
		if (StringUtils.isBlank(loanRepaymentRequest.getTransactionPin())){
			throw new ValidationException("validation.msg.loan.repayment.transaction.pin.required",
					"Transaction PIN is required for loan repayment");
		}
		Customer customer = customerService.getCustomerById(customerId);
		if (!StringUtils.equals(loanRepaymentRequest.getTransactionPin(), customer.getTransactionPin())){
			throw new ValidationException("validation.msg.loan.repayment.transaction.pin.invalid",
					"Invalid Transaction PIN provided for loan repayment");
		}
		PostLoansLoanIdTransactionsResponse response = loanService.repayLoan(loanId, loanRepaymentRequest, command);
		if (response != null && response.getResourceId() != null) {
			return new GenericApiResponse("success", "Loan repayment successful");
		}
		log.info("Loan repayment failed {}", response);
		return new GenericApiResponse("error", "Loan repayment failed");
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
	public LoanDashboardResponse retrieveCustomerLoanDashboard(Long customerId) {
		Customer customer = customerService.getCustomerById(customerId);
		GetLoansResponse customerLoans = loanService.retrieveAllLoans(null, null, null, null, null, null, null, customer.getExternalId(), null);
		return LoanDashboardResponse.builder()
				.activeLoanBalance(customerLoans.getActiveLoanBalance())
				.totalExpectedRepayment(customerLoans.getTotalExpectedRepayment())
				.totalRepaid(customerLoans.getTotalRepayment())
				.activeLoanCount(customerLoans.getActiveLoanCount())
				.pendingLoanCount(customerLoans.getPendingLoanCount())
				.liquidatedLoanCount(customerLoans.getLiquidatedLoanCount())
				.loans(
						customerLoans.getPageItems().stream()
								.filter(loan -> loan.getStatus().getId() != null && (loan.getStatus().getId() <= 300 || loan.getStatus().getId() >= 600))
								.collect(Collectors.toList())
				)
				.build();
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
