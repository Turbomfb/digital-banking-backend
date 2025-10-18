/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.service;

import java.util.Collections;
import java.util.List;

import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.LoanDto;
import com.techservices.digitalbanking.core.eBanking.model.request.*;
import com.techservices.digitalbanking.core.eBanking.model.response.*;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import com.techservices.digitalbanking.customer.service.CustomerService;
import com.techservices.digitalbanking.loan.domain.request.LoanDocumentUploadRequest;
import com.techservices.digitalbanking.loan.domain.request.LoanScheduleCalculationRequest;
import com.techservices.digitalbanking.loan.domain.response.LoanScheduleCalculationResponse;
import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.eBanking.api.LoansApiClient;
import com.techservices.digitalbanking.loan.domain.request.LoanRepaymentRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.techservices.digitalbanking.core.util.DateUtil.DEFAULT_DATE_FORMAT;
import static com.techservices.digitalbanking.core.util.DateUtil.DEFAULT_LOCALE;
import static com.techservices.digitalbanking.core.util.DateUtil.getCurrentDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoanService {

	private final LoansApiClient loansApiClient;
	private final CustomerService customerService;

	public List<LoanProductListResponse> getLoanProducts(Long fields) {
		return loansApiClient.getLoanProducts(fields);
	}

	public GetLoanProductsProductIdResponse getLoanProductById(Long productId, Long fields, Long template) {
		return loansApiClient.getLoanProductById(productId, fields, template);
	}

	public LoanDto retrieveLoanById(Long loanId, Long customerId) {
		Customer customer = customerService.getCustomerById(customerId);
		FilterDto filterDto = new FilterDto().emailAddress(customer.getEmailAddress()).customerType(customer.getUserType()).loanId(loanId);
		List<LoanDto> customerLoans = this.retrieveAllCustomerLoans(filterDto);
		return customerLoans.stream().findAny().orElseThrow(
				() -> new ValidationException("validation.msg.loan.not.found",
						"Loan with ID " + loanId + " not found for customer")
		);
	}

	public List<LoanDto> retrieveAllCustomerLoans(FilterDto filterDto) {
		return loansApiClient.retrieveAllCustomerLoans(filterDto);
	}

	public PostLoansLoanIdTransactionsResponse repayLoan(Long loanId, @Valid LoanRepaymentRequest loanRepaymentRequest,
			String command) {
		PostLoansLoanIdTransactionsRequest postLoansLoanIdTransactionsRequest = new PostLoansLoanIdTransactionsRequest();
		postLoansLoanIdTransactionsRequest.setLocale(DEFAULT_LOCALE);
		postLoansLoanIdTransactionsRequest.setDateFormat(DEFAULT_DATE_FORMAT);
		postLoansLoanIdTransactionsRequest.setTransactionDate(getCurrentDate());
		postLoansLoanIdTransactionsRequest.setTransactionAmount(loanRepaymentRequest.getTransactionAmount());
		postLoansLoanIdTransactionsRequest.setPaymentTypeId(loanRepaymentRequest.getPaymentTypeId());
		postLoansLoanIdTransactionsRequest.setNote(loanRepaymentRequest.getNote());
		postLoansLoanIdTransactionsRequest.setExternalId(loanRepaymentRequest.getExternalId());
		postLoansLoanIdTransactionsRequest.setAccountNumber(loanRepaymentRequest.getAccountNumber());
		postLoansLoanIdTransactionsRequest.setBankNumber(loanRepaymentRequest.getBankNumber());
		postLoansLoanIdTransactionsRequest.setCheckNumber(loanRepaymentRequest.getCheckNumber());
		postLoansLoanIdTransactionsRequest.setReceiptNumber(loanRepaymentRequest.getReceiptNumber());
		postLoansLoanIdTransactionsRequest.setRoutingCode(loanRepaymentRequest.getRoutingCode());

		return loansApiClient.executeLoanTransaction(loanId, postLoansLoanIdTransactionsRequest, command);
	}

	public PostLoanProductsResponse createALoanProduct(PostLoanProductsRequest postLoanProductRequest) {
		postLoanProductRequest.setDateFormat(DEFAULT_DATE_FORMAT);
		postLoanProductRequest.setLocale(DEFAULT_LOCALE);
		return loansApiClient.createALoanProduct(postLoanProductRequest);
	}

	public GetLoanProductsTemplateResponse retrieveLoanProductTemplate() {
		return loansApiClient.getLoanProductTemplate();
	}

	public BasePageResponse<LoanTransactionResponse> retrieveLoanTransactions(Long loanId, Long customerId) {
		this.retrieveLoanById(loanId, customerId);
		List<LoanTransactionResponse> transactions = loansApiClient.retrieveAllLoanTransactions(loanId);
		if (transactions == null || transactions.isEmpty()) {
			transactions = Collections.emptyList();
		}
		return BasePageResponse.instance(transactions);
	}

	public LoanTransactionResponse retrieveLoanTransactionDetails(Long loanId, Long transactionId, Long customerId) {
		return this.retrieveLoanTransactions(loanId, customerId).getData().stream().filter(trn -> trn.getId().equals(String.valueOf(transactionId))).findFirst()
				.orElseThrow(() -> new ValidationException("validation.msg.loan.transaction.not.found",
						"Loan transaction with ID " + transactionId + " not found for loan ID " + loanId));
	}

	public LoanScheduleCalculationResponse calculateLoanSchedule(@Valid LoanScheduleCalculationRequest loanScheduleCalculationRequest) {
		return loansApiClient.calculateLoanSchedule(loanScheduleCalculationRequest);
	}

	public LoanApplicationResponse processLoanApplication(PostNewLoanApplicationRequest loanApplicationRequest) {
		return loansApiClient.submitLoanApplication(loanApplicationRequest);
	}

	public void uploadDocument(Long loanId, LoanDocumentUploadRequest request) {
		loansApiClient.uploadDocument(loanId, request.getFile(), request.getName(), request.getDescription());
	}
}
