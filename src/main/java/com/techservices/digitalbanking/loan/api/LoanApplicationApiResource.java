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
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Loan Management", description = "API for managing customer loans including applications, offers, repayments, and transaction history")
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("api/v1/customers/me/loans")
@RestController
@RequiredArgsConstructor
public class LoanApplicationApiResource {

	private final LoanApplicationService loanService;
	private final SpringSecurityAuditorAware springSecurityAuditorAware;

	@Operation(
			summary = "Retrieve customer loan offers",
			description = "Fetches all available loan offers for the authenticated customer. " +
					"Returns personalized loan products based on customer eligibility, credit profile, " +
					"and current market conditions. Each offer includes terms, interest rates, and eligibility criteria."
	)
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "Successfully retrieved customer loan offers",
					content = @Content(
							mediaType = "application/json",
							schema = @Schema(implementation = BasePageResponse.class)
					)
			),
			@ApiResponse(
					responseCode = "401",
					description = "User not authenticated",
					content = @Content(schema = @Schema(hidden = true))
			),
			@ApiResponse(
					responseCode = "403",
					description = "User not authorized to access loan offers",
					content = @Content(schema = @Schema(hidden = true))
			),
			@ApiResponse(
					responseCode = "500",
					description = "Internal server error occurred while retrieving loan offers",
					content = @Content(schema = @Schema(hidden = true))
			)
	})
	@GetMapping("offers")
	public ResponseEntity<BasePageResponse<LoanOfferResponse>> retrieveCustomerLoanOffers() {
		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		BasePageResponse<LoanOfferResponse> loanOfferResponseBasePageResponse = loanService.retrieveCustomerLoanOffers(customerId);

		return ResponseEntity.ok(loanOfferResponseBasePageResponse);
	}

	@Operation(
			summary = "Apply for a loan",
			description = "Submits a new loan application for the authenticated customer. " +
					"The application undergoes validation, credit assessment, and approval workflow. " +
					"Requires complete borrower information, loan details, and supporting documentation references."
	)
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "Loan application submitted successfully",
					content = @Content(
							mediaType = "application/json",
							schema = @Schema(implementation = GenericApiResponse.class)
					)
			),
			@ApiResponse(
					responseCode = "400",
					description = "Invalid application data or missing required fields",
					content = @Content(schema = @Schema(hidden = true))
			),
			@ApiResponse(
					responseCode = "401",
					description = "User not authenticated",
					content = @Content(schema = @Schema(hidden = true))
			),
			@ApiResponse(
					responseCode = "403",
					description = "User not authorized to submit loan applications",
					content = @Content(schema = @Schema(hidden = true))
			),
			@ApiResponse(
					responseCode = "422",
					description = "Application cannot be processed due to eligibility restrictions or business rules violation",
					content = @Content(schema = @Schema(hidden = true))
			),
			@ApiResponse(
					responseCode = "500",
					description = "Internal server error occurred while processing the application",
					content = @Content(schema = @Schema(hidden = true))
			)
	})
	@PostMapping("application")
	public ResponseEntity<GenericApiResponse> processLoanApplication(
			@Parameter(
					description = "Complete loan application details including loan amount, purpose, term, " +
							"employment information, financial details, and references to supporting documents.",
					required = true,
					content = @Content(
							mediaType = "application/json",
							schema = @Schema(implementation = LoanApplicationRequest.class)
					)
			)
			@RequestBody @Valid LoanApplicationRequest loanApplicationRequest
	) {
		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		GenericApiResponse genericApiResponse = loanService.processLoanApplication(customerId, loanApplicationRequest);

		return ResponseEntity.ok(genericApiResponse);
	}

	@Operation(
			summary = "Retrieve customer loan dashboard",
			description = "Provides a comprehensive overview of the customer's loan portfolio including " +
					"active loans, payment history, outstanding balances, upcoming payments, and account status. " +
					"Serves as the main dashboard for loan management activities."
	)
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "Successfully retrieved customer loan dashboard",
					content = @Content(
							mediaType = "application/json",
							schema = @Schema(implementation = LoanDashboardResponse.class)
					)
			),
			@ApiResponse(
					responseCode = "401",
					description = "User not authenticated",
					content = @Content(schema = @Schema(hidden = true))
			),
			@ApiResponse(
					responseCode = "403",
					description = "User not authorized to access loan dashboard",
					content = @Content(schema = @Schema(hidden = true))
			),
			@ApiResponse(
					responseCode = "500",
					description = "Internal server error occurred while retrieving dashboard data",
					content = @Content(schema = @Schema(hidden = true))
			)
	})
	@GetMapping("dashboard")
	public ResponseEntity<LoanDashboardResponse> retrieveCustomerLoanDashboard() {
		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		LoanDashboardResponse loanDashboardResponse = loanService.retrieveCustomerLoanDashboard(customerId);

		return ResponseEntity.ok(loanDashboardResponse);
	}

	@Operation(
			summary = "Retrieve a loan by ID",
			description = "Fetches detailed information about a specific loan account including terms, " +
					"current balance, payment schedule, transaction history, and account status. " +
					"Supports various data associations and field filtering for optimized responses."
	)
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "Successfully retrieved loan details",
					content = @Content(
							mediaType = "application/json",
							schema = @Schema(implementation = GetLoansLoanIdResponse.class)
					)
			),
			@ApiResponse(
					responseCode = "401",
					description = "User not authenticated",
					content = @Content(schema = @Schema(hidden = true))
			),
			@ApiResponse(
					responseCode = "403",
					description = "User not authorized to access this loan account",
					content = @Content(schema = @Schema(hidden = true))
			),
			@ApiResponse(
					responseCode = "404",
					description = "Loan account not found",
					content = @Content(schema = @Schema(hidden = true))
			),
			@ApiResponse(
					responseCode = "500",
					description = "Internal server error occurred while retrieving loan details",
					content = @Content(schema = @Schema(hidden = true))
			)
	})
	@GetMapping("{loanId}")
	public ResponseEntity<GetLoansLoanIdResponse> retrieveLoanById(
			@Parameter(
					name = "loanId",
					description = "Unique identifier of the loan account to retrieve",
					required = true,
					schema = @Schema(type = "integer", format = "int64", minimum = "1")
			)
			@PathVariable Long loanId,
			@Parameter(
					name = "staffInSelectedOfficeOnly",
					description = "Filter results to include only staff from the selected office context",
					schema = @Schema(type = "boolean", defaultValue = "false")
			)
			@Valid @RequestParam(value = "staffInSelectedOfficeOnly", required = false, defaultValue = "false") Boolean staffInSelectedOfficeOnly,
			@Parameter(
					name = "associations",
					description = "Comma-separated list of associated data to include in the response. " +
							"Options include: transactions, charges, collateral, guarantors, linkedaccount, multiDisburseDetails",
					schema = @Schema(type = "string", defaultValue = "all")
			)
			@Valid @RequestParam(value = "associations", required = false, defaultValue = "all") String associations,
			@Parameter(
					name = "exclude",
					description = "Comma-separated list of data to exclude from the response for performance optimization",
					schema = @Schema(type = "string", defaultValue = "transactions")
			)
			@Valid @RequestParam(value = "exclude", required = false, defaultValue = "transactions") String exclude,
			@Parameter(
					name = "fields",
					description = "Comma-separated list of specific fields to return, enabling response customization and bandwidth optimization"
			)
			@Valid @RequestParam(value = "fields", required = false) String fields) {
		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		GetLoansLoanIdResponse getLoansLoanIdResponse = loanService.retrieveLoanById(loanId, staffInSelectedOfficeOnly,
				associations, exclude, fields, customerId);

		return ResponseEntity.ok(getLoansLoanIdResponse);
	}

	@Operation(
			summary = "Retrieve loan list",
			description = "Fetches a paginated list of all loans belonging to the authenticated customer. " +
					"Supports advanced filtering, searching, sorting, and pagination capabilities. " +
					"Useful for displaying loan portfolios and account management interfaces."
	)
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "Successfully retrieved loan list",
					content = @Content(
							mediaType = "application/json",
							schema = @Schema(implementation = GetLoansResponse.class)
					)
			),
			@ApiResponse(
					responseCode = "400",
					description = "Invalid query parameters or search criteria",
					content = @Content(schema = @Schema(hidden = true))
			),
			@ApiResponse(
					responseCode = "401",
					description = "User not authenticated",
					content = @Content(schema = @Schema(hidden = true))
			),
			@ApiResponse(
					responseCode = "403",
					description = "User not authorized to access loan list",
					content = @Content(schema = @Schema(hidden = true))
			),
			@ApiResponse(
					responseCode = "500",
					description = "Internal server error occurred while retrieving loan list",
					content = @Content(schema = @Schema(hidden = true))
			)
	})
	@GetMapping
	public ResponseEntity<GetLoansResponse> retrieveAllLoans(
			@Parameter(
					name = "sqlSearch",
					description = "SQL-like search query for advanced filtering across loan attributes"
			)
			@Valid @RequestParam(value = "sqlSearch", required = false) String sqlSearch,
			@Parameter(
					name = "externalId",
					description = "Filter loans by external system identifier"
			)
			@Valid @RequestParam(value = "externalId", required = false) String externalId,
			@Parameter(
					name = "offset",
					description = "Number of records to skip for pagination",
					schema = @Schema(type = "integer", minimum = "0", defaultValue = "0")
			)
			@Valid @RequestParam(value = "offset", required = false) Integer offset,
			@Parameter(
					name = "limit",
					description = "Maximum number of records to return per page",
					schema = @Schema(type = "integer", minimum = "1", maximum = "200", defaultValue = "20")
			)
			@Valid @RequestParam(value = "limit", required = false) Integer limit,
			@Parameter(
					name = "orderBy",
					description = "Field name to sort results by (e.g., 'accountNo', 'submittedOnDate', 'loanProductName')"
			)
			@Valid @RequestParam(value = "orderBy", required = false) String orderBy,
			@Parameter(
					name = "sortOrder",
					description = "Sort direction for the results",
					schema = @Schema(type = "string", allowableValues = {"ASC", "DESC"})
			)
			@Valid @RequestParam(value = "sortOrder", required = false) String sortOrder,
			@Parameter(
					name = "accountNo",
					description = "Filter loans by specific account number"
			)
			@Valid @RequestParam(value = "accountNo", required = false) String accountNo,
			@Parameter(
					name = "status",
					description = "Filter loans by current status",
					schema = @Schema(type = "string", allowableValues = {"ACTIVE", "CLOSED", "PENDING", "APPROVED", "REJECTED"})
			)
			@Valid @RequestParam(value = "status", required = false) String status
	) {
		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		GetLoansResponse getLoansResponse = loanService.retrieveAllLoans(sqlSearch, externalId, offset, limit, orderBy,
				sortOrder, accountNo, customerId, status);

		return ResponseEntity.ok(getLoansResponse);
	}

	@Operation(
			summary = "Retrieve loan transactions",
			description = "Fetches a paginated list of all transactions for a specific loan account including " +
					"payments, disbursements, charges, waivers, and adjustments. " +
					"Provides comprehensive transaction history for account reconciliation and audit purposes."
	)
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "Successfully retrieved loan transactions",
					content = @Content(
							mediaType = "application/json",
							schema = @Schema(implementation = FineractPageResponse.class)
					)
			),
			@ApiResponse(
					responseCode = "401",
					description = "User not authenticated",
					content = @Content(schema = @Schema(hidden = true))
			),
			@ApiResponse(
					responseCode = "403",
					description = "User not authorized to access transactions for this loan",
					content = @Content(schema = @Schema(hidden = true))
			),
			@ApiResponse(
					responseCode = "404",
					description = "Loan account not found",
					content = @Content(schema = @Schema(hidden = true))
			),
			@ApiResponse(
					responseCode = "500",
					description = "Internal server error occurred while retrieving transactions",
					content = @Content(schema = @Schema(hidden = true))
			)
	})
	@GetMapping("{loanId}/transactions")
	public ResponseEntity<FineractPageResponse<LoanTransactionResponse>> retrieveLoanTransactions(
			@Parameter(
					name = "loanId",
					description = "Unique identifier of the loan account whose transactions to retrieve",
					required = true,
					schema = @Schema(type = "integer", format = "int64", minimum = "1")
			)
			@Valid @PathVariable(value = "loanId") Long loanId) {
		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		FineractPageResponse<LoanTransactionResponse> transactions = loanService.retrieveLoanTransactions(loanId, customerId);

		return ResponseEntity.ok(transactions);
	}

	@Operation(
			summary = "Retrieve loan transaction details by ID",
			description = "Fetches detailed information about a specific loan transaction including " +
					"amount breakdowns, processing dates, references, and associated fees or charges. " +
					"Useful for transaction verification and detailed financial reporting."
	)
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "Successfully retrieved transaction details",
					content = @Content(
							mediaType = "application/json",
							schema = @Schema(implementation = LoanTransactionResponse.class)
					)
			),
			@ApiResponse(
					responseCode = "401",
					description = "User not authenticated",
					content = @Content(schema = @Schema(hidden = true))
			),
			@ApiResponse(
					responseCode = "403",
					description = "User not authorized to access this transaction",
					content = @Content(schema = @Schema(hidden = true))
			),
			@ApiResponse(
					responseCode = "404",
					description = "Loan account or transaction not found",
					content = @Content(schema = @Schema(hidden = true))
			),
			@ApiResponse(
					responseCode = "500",
					description = "Internal server error occurred while retrieving transaction details",
					content = @Content(schema = @Schema(hidden = true))
			)
	})
	@GetMapping("{loanId}/transactions/{transactionId}")
	public ResponseEntity<LoanTransactionResponse> retrieveLoanTransactionDetails(
			@Parameter(
					name = "loanId",
					description = "Unique identifier of the loan account",
					required = true,
					schema = @Schema(type = "integer", format = "int64", minimum = "1")
			)
			@Valid @PathVariable(value = "loanId") Long loanId,
			@Parameter(
					name = "transactionId",
					description = "Unique identifier of the specific transaction to retrieve",
					required = true,
					schema = @Schema(type = "integer", format = "int64", minimum = "1")
			)
			@Valid @PathVariable(value = "transactionId") Long transactionId
	) {
		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		LoanTransactionResponse transaction = loanService.retrieveLoanTransactionDetails(loanId, transactionId, customerId);

		return ResponseEntity.ok(transaction);
	}

	@Operation(
			summary = "Repay a loan",
			description = "Processes a loan repayment transaction for the specified loan account. " +
					"Supports various repayment types including principal, interest, fees, and penalty payments. " +
					"Automatically calculates payment allocation and updates loan balances and schedule."
	)
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "Loan repayment processed successfully",
					content = @Content(
							mediaType = "application/json",
							schema = @Schema(implementation = GenericApiResponse.class)
					)
			),
			@ApiResponse(
					responseCode = "400",
					description = "Invalid repayment data or amount",
					content = @Content(schema = @Schema(hidden = true))
			),
			@ApiResponse(
					responseCode = "401",
					description = "User not authenticated",
					content = @Content(schema = @Schema(hidden = true))
			),
			@ApiResponse(
					responseCode = "403",
					description = "User not authorized to make payments on this loan",
					content = @Content(schema = @Schema(hidden = true))
			),
			@ApiResponse(
					responseCode = "404",
					description = "Loan account not found",
					content = @Content(schema = @Schema(hidden = true))
			),
			@ApiResponse(
					responseCode = "422",
					description = "Repayment cannot be processed due to loan status, payment amount restrictions, or business rules",
					content = @Content(schema = @Schema(hidden = true))
			),
			@ApiResponse(
					responseCode = "500",
					description = "Internal server error occurred while processing the repayment",
					content = @Content(schema = @Schema(hidden = true))
			)
	})
	@PostMapping("{loanId}/repayment")
	public GenericApiResponse repayLoan(
			@Parameter(
					name = "loanId",
					description = "Unique identifier of the loan account to repay",
					required = true,
					schema = @Schema(type = "integer", format = "int64", minimum = "1")
			)
			@PathVariable Long loanId,
			@Parameter(
					description = "Repayment details including payment amount, transaction date, payment method, " +
							"and optional payment allocation instructions. Amount should be positive and within acceptable limits.",
					required = true,
					content = @Content(
							mediaType = "application/json",
							schema = @Schema(implementation = LoanRepaymentRequest.class)
					)
			)
			@RequestBody @Valid LoanRepaymentRequest postLoansLoanIdTransactionsRequest,
			@Parameter(
					name = "command",
					description = "Type of loan transaction command to execute",
					schema = @Schema(type = "string", allowableValues = {"repayment", "prepayLoan"}, defaultValue = "repayment")
			)
			@RequestParam(required = false, defaultValue = "repayment") String command
	) {
		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		return loanService.repayLoan(loanId, postLoansLoanIdTransactionsRequest, command, customerId);
	}
}
