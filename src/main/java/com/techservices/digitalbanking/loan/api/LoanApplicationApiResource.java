/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.loan.api;

import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.loan.domain.response.LoanOfferResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techservices.digitalbanking.core.fineract.model.data.FineractPageResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetLoanTemplateResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetLoansLoanIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetLoansResponse;
import com.techservices.digitalbanking.core.fineract.model.response.LoanTransactionResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostLoansLoanIdRequest;
import com.techservices.digitalbanking.core.fineract.model.response.PostLoansLoanIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostLoansLoanIdTransactionsResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostLoansResponse;
import com.techservices.digitalbanking.loan.domain.request.LoanApplicationRequest;
import com.techservices.digitalbanking.loan.domain.request.LoanRepaymentRequest;
import com.techservices.digitalbanking.loan.service.LoanApplicationService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("api/v1/loans/{customerId}")
@RestController
@RequiredArgsConstructor
public class LoanApplicationApiResource {

	private final LoanApplicationService loanService;

	@Operation(summary = "Retrieve Customer Loan offers")
	@GetMapping("offers")
	public ResponseEntity<BasePageResponse<LoanOfferResponse>> retrieveCustomerLoanOffers(@PathVariable Long customerId) {
		BasePageResponse<LoanOfferResponse> loanOfferResponseBasePageResponse = loanService.retrieveCustomerLoanOffers(customerId);

		return ResponseEntity.ok(loanOfferResponseBasePageResponse);
	}


	@Operation(summary = "Apply for a loan")
	@PostMapping("application")
	public ResponseEntity<GenericApiResponse> processLoanApplication(
			@PathVariable Long customerId,
			@RequestBody @Valid LoanApplicationRequest loanApplicationRequest
	) {
		GenericApiResponse genericApiResponse = loanService.processLoanApplication(customerId, loanApplicationRequest);

		return ResponseEntity.ok(genericApiResponse);
	}

	@Operation(summary = "Retrieve a Loan by ID")
	@GetMapping("{loanId}")
	public ResponseEntity<GetLoansLoanIdResponse> retrieveLoanById(@PathVariable Long loanId,
																   @Valid @RequestParam(value = "staffInSelectedOfficeOnly", required = false, defaultValue = "false") Boolean staffInSelectedOfficeOnly,
																   @Valid @RequestParam(value = "associations", required = false, defaultValue = "all") String associations,
																   @Valid @RequestParam(value = "exclude", required = false, defaultValue = "transactions") String exclude,
																   @Valid @RequestParam(value = "fields", required = false) String fields, @PathVariable Long customerId) {
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
			@Valid @RequestParam(value = "accountNo", required = false) String accountNo) {
		GetLoansResponse getLoansResponse = loanService.retrieveAllLoans(sqlSearch, externalId, offset, limit, orderBy,
				sortOrder, accountNo);

		return ResponseEntity.ok(getLoansResponse);
	}

	@Operation(summary = "Retrieve loan transactions")
	@GetMapping("{loanId}/transactions")
	public ResponseEntity<FineractPageResponse<LoanTransactionResponse>> retrieveLoanTransactions(
			@Valid @PathVariable(value = "loanId") Long loanId, @PathVariable String customerId) {
		FineractPageResponse<LoanTransactionResponse> transactions = loanService.retrieveLoanTransactions(loanId);

		return ResponseEntity.ok(transactions);
	}

	@Operation(summary = "Retrieve loan transaction details by ID")
	@GetMapping("{loanId}/transactions/{transactionId}")
	public ResponseEntity<LoanTransactionResponse> retrieveLoanTransactionDetails(
			@Valid @PathVariable(value = "loanId") Long loanId,
			@Valid @PathVariable(value = "transactionId") Long transactionId,
			@PathVariable String customerId) {
		LoanTransactionResponse transaction = loanService.retrieveLoanTransactionDetails(loanId, transactionId);

		return ResponseEntity.ok(transaction);
	}
}
