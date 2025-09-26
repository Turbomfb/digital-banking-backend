/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.service;

import java.util.Collections;
import java.util.List;

import com.techservices.digitalbanking.core.domain.dto.LoanDto;
import com.techservices.digitalbanking.core.eBanking.model.request.FilterDto;
import com.techservices.digitalbanking.loan.domain.request.LoanScheduleCalculationRequest;
import com.techservices.digitalbanking.loan.domain.response.LoanScheduleCalculationResponse;
import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.eBanking.api.LoansApiClient;
import com.techservices.digitalbanking.core.eBanking.model.data.FineractPageResponse;
import com.techservices.digitalbanking.core.eBanking.model.request.LoanRescheduleRequest;
import com.techservices.digitalbanking.core.eBanking.model.request.PostLoanApplicationRequest;
import com.techservices.digitalbanking.core.eBanking.model.request.PostLoanProductsRequest;
import com.techservices.digitalbanking.core.eBanking.model.response.GetLoanProductsProductIdResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.GetLoanProductsTemplateResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.GetLoanTemplateResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.GetLoansLoanIdResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.LoanRescheduleResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.LoanTransactionResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostLoanProductsResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostLoansLoanIdRequest;
import com.techservices.digitalbanking.core.eBanking.model.response.PostLoansLoanIdResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostLoansLoanIdTransactionsRequest;
import com.techservices.digitalbanking.core.eBanking.model.response.PostLoansLoanIdTransactionsResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostLoansResponse;
import com.techservices.digitalbanking.loan.domain.request.LoanApplicationRequest;
import com.techservices.digitalbanking.loan.domain.request.LoanRepaymentRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.techservices.digitalbanking.core.util.CommandUtil.APPROVE;
import static com.techservices.digitalbanking.core.util.CommandUtil.DISBURSE;
import static com.techservices.digitalbanking.core.util.CommandUtil.DISBURSE_TO_SAVINGS;
import static com.techservices.digitalbanking.core.util.CommandUtil.REJECT;
import static com.techservices.digitalbanking.core.util.CommandUtil.REPAYMENT;
import static com.techservices.digitalbanking.core.util.DateUtil.DEFAULT_DATE_FORMAT;
import static com.techservices.digitalbanking.core.util.DateUtil.DEFAULT_LOCALE;
import static com.techservices.digitalbanking.core.util.DateUtil.getCurrentDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoanService {

	private final LoansApiClient loansApiClient;

	public PostLoansResponse calculateLoanScheduleOrSubmitLoanApplication(LoanApplicationRequest loanApplicationRequest,
			String command) {
		PostLoanApplicationRequest postLoanApplicationRequest = this
				.populateLoanApplicationData(loanApplicationRequest);
		return loansApiClient.calculateLoanScheduleOrSubmitLoanApplication(postLoanApplicationRequest, command);
	}

	private PostLoanApplicationRequest populateLoanApplicationData(LoanApplicationRequest loanApplicationRequest) {
		PostLoanApplicationRequest postLoanApplicationRequest = new PostLoanApplicationRequest();
		postLoanApplicationRequest.setLocale(DEFAULT_LOCALE);
		postLoanApplicationRequest.setDateFormat(DEFAULT_DATE_FORMAT);
		postLoanApplicationRequest.setSubmittedOnDate(getCurrentDate());

		return postLoanApplicationRequest;
	}

	public List<GetLoanProductsProductIdResponse> getLoanProducts(Long fields) {
		return loansApiClient.getLoanProducts(fields);
	}

	public GetLoanProductsProductIdResponse getLoanProductById(Long productId, Long fields, Long template) {
		return loansApiClient.getLoanProductById(productId, fields, template);
	}

	public GetLoansLoanIdResponse retrieveLoanById(Long loanId, Boolean staffInSelectedOfficeOnly,
			@Valid String associations, String exclude, String fields) {
		return loansApiClient.retrieveLoan(loanId, staffInSelectedOfficeOnly, associations, exclude, fields);
	}

	public List<LoanDto> retrieveAllCustomerLoans(FilterDto filterDto) {
		return loansApiClient.retrieveAllCustomerLoans(filterDto);
	}

	public PostLoansLoanIdResponse processLoanCommand(Long loanId, PostLoansLoanIdRequest postLoansLoanIdRequest,
			@Valid String command) {
		if (APPROVE.equalsIgnoreCase(command)) {
			processLoanApproval(postLoansLoanIdRequest);
		} else if (REJECT.equalsIgnoreCase(command)) {
			processLoanRejection(postLoansLoanIdRequest);
		} else if (DISBURSE.equalsIgnoreCase(command) || DISBURSE_TO_SAVINGS.equalsIgnoreCase(command)) {
			processLoanDisbursal(postLoansLoanIdRequest);
		} else if (REPAYMENT.equalsIgnoreCase(command)) {
			processLoanRepayment(postLoansLoanIdRequest);
		}
		return loansApiClient.stateTransitions(loanId, postLoansLoanIdRequest, command);
	}

	private void processLoanRepayment(PostLoansLoanIdRequest postLoansLoanIdRequest) {
		populateDefaultFields(postLoansLoanIdRequest);
	}

	private static void processLoanDisbursal(PostLoansLoanIdRequest postLoansLoanIdRequest) {
		postLoansLoanIdRequest.setActualDisbursementDate(getCurrentDate());
		populateDefaultFields(postLoansLoanIdRequest);
	}

	private static void processLoanRejection(PostLoansLoanIdRequest postLoansLoanIdRequest) {
		postLoansLoanIdRequest.setRejectedOnDate(getCurrentDate());
		populateDefaultFields(postLoansLoanIdRequest);
	}

	private static void processLoanApproval(PostLoansLoanIdRequest postLoansLoanIdRequest) {
		postLoansLoanIdRequest.setApprovedOnDate(getCurrentDate());
		populateDefaultFields(postLoansLoanIdRequest);
	}

	private static void populateDefaultFields(PostLoansLoanIdRequest postLoansLoanIdRequest) {
		postLoansLoanIdRequest.setLocale(DEFAULT_LOCALE);
		postLoansLoanIdRequest.setDateFormat(DEFAULT_DATE_FORMAT);
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

	public GetLoanTemplateResponse retrieveLoanTemplate(@Valid String templateType, @Valid Long clientId,
			@Valid Long productId) {
		return loansApiClient.getLoanTemplate(templateType, clientId, productId);
	}

	public FineractPageResponse<LoanTransactionResponse> retrieveLoanTransactions(Long loanId) {
		GetLoansLoanIdResponse loanDetails = retrieveLoanById(loanId, false, "all", null, null);
		List<LoanTransactionResponse> transactions = loanDetails.getTransactions();
		if (transactions == null) {
			transactions = Collections.emptyList();
		}
		FineractPageResponse<LoanTransactionResponse> fineractPageResponse = new FineractPageResponse<>();
		fineractPageResponse.setPageItems(transactions);
		fineractPageResponse.setTotalFilteredRecords(transactions.size());
		return fineractPageResponse;
	}

	public LoanRescheduleResponse createALoanRescheduleRequest(LoanRescheduleRequest loanRescheduleRequest) {
		loanRescheduleRequest.setDateFormat(DEFAULT_DATE_FORMAT);
		loanRescheduleRequest.setLocale(DEFAULT_LOCALE);
		loanRescheduleRequest.setSubmittedOnDate(getCurrentDate());
		return loansApiClient.createALoanRescheduleRequest(loanRescheduleRequest);
	}

	public PostLoansLoanIdResponse processLoanRescheduleCommand(PostLoansLoanIdRequest postLoansLoanIdRequest,
			String requestId, String command) {
		if (postLoansLoanIdRequest == null) {
			postLoansLoanIdRequest = new PostLoansLoanIdRequest();
		}
		postLoansLoanIdRequest.setDateFormat(DEFAULT_DATE_FORMAT);
		postLoansLoanIdRequest.setLocale(DEFAULT_LOCALE);
		if (APPROVE.equalsIgnoreCase(command)) {
			return processLoanRescheduleApproval(postLoansLoanIdRequest, requestId, command);
		}
		return loansApiClient.processLoanRescheduleApproval(postLoansLoanIdRequest, requestId, command);
	}

	private PostLoansLoanIdResponse processLoanRescheduleApproval(PostLoansLoanIdRequest postLoansLoanIdRequest,
			String requestId, String command) {
		postLoansLoanIdRequest.setApprovedOnDate(getCurrentDate());
		return loansApiClient.processLoanRescheduleApproval(postLoansLoanIdRequest, requestId, command);
	}

	public LoanTransactionResponse retrieveLoanTransactionDetails(Long loanId, Long transactionId) {
		return loansApiClient.getLoanTransactionDetails(loanId, transactionId);
	}

	public LoanScheduleCalculationResponse calculateLoanSchedule(@Valid LoanScheduleCalculationRequest loanScheduleCalculationRequest) {
		GetLoanProductsProductIdResponse product = loansApiClient.getLoanProductById(loanScheduleCalculationRequest.getProductId(), null, null);
		Double interestRate = product.getInterestRatePerPeriod();
		PostLoanApplicationRequest postLoanApplicationRequest = new PostLoanApplicationRequest();
		postLoanApplicationRequest.setDateFormat(DEFAULT_DATE_FORMAT);
		postLoanApplicationRequest.setLocale(DEFAULT_LOCALE);
		postLoanApplicationRequest.setProductId(loanScheduleCalculationRequest.getProductId());
		postLoanApplicationRequest.setLoanTermFrequency(loanScheduleCalculationRequest.getLoanTermFrequency());
		postLoanApplicationRequest.setLoanTermFrequencyType(2L);
		postLoanApplicationRequest.setNumberOfRepayments(loanScheduleCalculationRequest.getLoanTermFrequency());
		postLoanApplicationRequest.setRepaymentEvery(1L);
		postLoanApplicationRequest.setRepaymentFrequencyType(2L);
		postLoanApplicationRequest.setPrincipal(loanScheduleCalculationRequest.getPrincipal());
		postLoanApplicationRequest.setClientId(1L);
		postLoanApplicationRequest.setAmortizationType(1L);
		postLoanApplicationRequest.setInterestCalculationPeriodType(1L);
		postLoanApplicationRequest.setInterestRatePerPeriod(interestRate);
		postLoanApplicationRequest.setInterestType(0L);
		postLoanApplicationRequest.setExpectedDisbursementDate(getCurrentDate());
		postLoanApplicationRequest.setSubmittedOnDate(getCurrentDate());
		postLoanApplicationRequest.setLoanType("individual");
		postLoanApplicationRequest.setTransactionProcessingStrategyCode("mifos-standard-strategy");
		PostLoansResponse loanSchedule = loansApiClient.calculateLoanScheduleOrSubmitLoanApplication(postLoanApplicationRequest, "calculateLoanSchedule");
		return LoanScheduleCalculationResponse.parse(loanSchedule, product, loanScheduleCalculationRequest);
	}
}
