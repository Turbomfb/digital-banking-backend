/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.loan.api;

import com.techservices.digitalbanking.core.configuration.security.SpringSecurityAuditorAware;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.loan.domain.response.LoanDashboardResponse;
import com.techservices.digitalbanking.loan.domain.response.LoanOfferResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techservices.digitalbanking.core.eBanking.model.data.FineractPageResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.GetLoansLoanIdResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.GetLoansResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.LoanTransactionResponse;
import com.techservices.digitalbanking.loan.domain.request.LoanApplicationRequest;
import com.techservices.digitalbanking.loan.domain.request.LoanRepaymentRequest;
import com.techservices.digitalbanking.loan.service.LoanApplicationService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("api/v1/customers/me/loans")
@RestController
@RequiredArgsConstructor
public class LoanApplicationApiResource {

	private final LoanApplicationService loanService;
	private final SpringSecurityAuditorAware springSecurityAuditorAware;

	@Operation(summary = "Retrieve Customer Loan offers")
	@GetMapping("offers")
	public ResponseEntity<BasePageResponse<LoanOfferResponse>> retrieveCustomerLoanOffers() {
		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		BasePageResponse<LoanOfferResponse> loanOfferResponseBasePageResponse = loanService.retrieveCustomerLoanOffers(customerId);

		return ResponseEntity.ok(loanOfferResponseBasePageResponse);
	}


	@Operation(summary = "Apply for a loan")
	@PostMapping("application")
	public ResponseEntity<GenericApiResponse> processLoanApplication(
			@RequestBody @Valid LoanApplicationRequest loanApplicationRequest
	) {
		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		GenericApiResponse genericApiResponse = loanService.processLoanApplication(customerId, loanApplicationRequest);

		return ResponseEntity.ok(genericApiResponse);
	}

	@Operation(summary = "Retrieve Customer Loan Dashboard")
	@GetMapping("dashboard")
	public ResponseEntity<LoanDashboardResponse> retrieveCustomerLoanDashboard() {
		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		LoanDashboardResponse loanDashboardResponse = loanService.retrieveCustomerLoanDashboard(customerId);

		return ResponseEntity.ok(loanDashboardResponse);
	}

	@Operation(summary = "Retrieve a Loan by ID")
	@GetMapping("{loanId}")
	public ResponseEntity<GetLoansLoanIdResponse> retrieveLoanById(@PathVariable Long loanId,
																   @Valid @RequestParam(value = "staffInSelectedOfficeOnly", required = false, defaultValue = "false") Boolean staffInSelectedOfficeOnly,
																   @Valid @RequestParam(value = "associations", required = false, defaultValue = "all") String associations,
																   @Valid @RequestParam(value = "exclude", required = false, defaultValue = "transactions") String exclude,
																   @Valid @RequestParam(value = "fields", required = false) String fields) {
		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		GetLoansLoanIdResponse getLoansLoanIdResponse = loanService.retrieveLoanById(loanId, staffInSelectedOfficeOnly,
				associations, exclude, fields, customerId);

		return ResponseEntity.ok(getLoansLoanIdResponse);
	}

	@Operation(summary = "Retrieve Loan List")
	@GetMapping
	public ResponseEntity<GetLoansResponse> retrieveAllLoans(
			@Valid @RequestParam(value = "sqlSearch", required = false) String sqlSearch,
			@Valid @RequestParam(value = "externalId", required = false) String externalId,
			@Valid @RequestParam(value = "offset", required = false) Integer offset,
			@Valid @RequestParam(value = "limit", required = false) Integer limit,
			@Valid @RequestParam(value = "orderBy", required = false) String orderBy,
			@Valid @RequestParam(value = "sortOrder", required = false) String sortOrder,
			@Valid @RequestParam(value = "accountNo", required = false) String accountNo,
			@Valid @RequestParam(value = "status", required = false) String status
	) {
		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		GetLoansResponse getLoansResponse = loanService.retrieveAllLoans(sqlSearch, externalId, offset, limit, orderBy,
				sortOrder, accountNo, customerId, status);

		return ResponseEntity.ok(getLoansResponse);
	}

	@Operation(summary = "Retrieve loan transactions")
	@GetMapping("{loanId}/transactions")
	public ResponseEntity<FineractPageResponse<LoanTransactionResponse>> retrieveLoanTransactions(
			@Valid @PathVariable(value = "loanId") Long loanId) {
		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		FineractPageResponse<LoanTransactionResponse> transactions = loanService.retrieveLoanTransactions(loanId, customerId);

		return ResponseEntity.ok(transactions);
	}

	@Operation(summary = "Retrieve loan transaction details by ID")
	@GetMapping("{loanId}/transactions/{transactionId}")
	public ResponseEntity<LoanTransactionResponse> retrieveLoanTransactionDetails(
			@Valid @PathVariable(value = "loanId") Long loanId,
			@Valid @PathVariable(value = "transactionId") Long transactionId
	) {
		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		LoanTransactionResponse transaction = loanService.retrieveLoanTransactionDetails(loanId, transactionId, customerId);

		return ResponseEntity.ok(transaction);
	}

	@Operation(summary = "Repay a loan")
	@PostMapping("{loanId}/repayment")
	public GenericApiResponse repayLoan(
		@PathVariable Long loanId,
		@RequestBody @Valid LoanRepaymentRequest postLoansLoanIdTransactionsRequest,
		@RequestParam(required = false, defaultValue = "repayment") String command
	) {
		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		return loanService.repayLoan(loanId, postLoansLoanIdTransactionsRequest, command, customerId);
	}
}
