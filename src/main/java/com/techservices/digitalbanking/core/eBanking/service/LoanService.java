/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.service;

import java.util.Collections;
import java.util.Comparator;
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

	public PostLoanRepaymentResponse repayLoan(Long loanId, @Valid LoanRepaymentRequest loanRepaymentRequest) {
		PostLoanRepaymentRequest postLoanRepaymentRequest = new PostLoanRepaymentRequest();
		postLoanRepaymentRequest.setAmount(loanRepaymentRequest.getTransactionAmount());
		return loansApiClient.repayLoan(loanId, postLoanRepaymentRequest);
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
		LoanScheduleCalculationResponse response = loansApiClient.calculateLoanSchedule(loanScheduleCalculationRequest);
		if (!response.getPeriods().isEmpty()) {
			response.getPeriods().sort(Comparator.comparing(LoanScheduleCalculationResponse.Period::getPeriod));
		}
		return response;
	}

	public LoanApplicationResponse processLoanApplication(PostNewLoanApplicationRequest loanApplicationRequest) {
		return loansApiClient.submitLoanApplication(loanApplicationRequest);
	}

	public void uploadDocument(Long loanId, LoanDocumentUploadRequest request) {
		loansApiClient.uploadDocument(loanId, request.getFile(), request.getName(), request.getDescription());
	}
}
