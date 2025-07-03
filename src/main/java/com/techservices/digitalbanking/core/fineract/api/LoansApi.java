/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.api; /* Developed by MKAN Engineering (C)2024 */

import java.util.List;

import com.techservices.digitalbanking.core.fineract.model.response.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.techservices.digitalbanking.core.fineract.model.data.DeletePostDatedCheck;
import com.techservices.digitalbanking.core.fineract.model.request.LoanRescheduleRequest;
import com.techservices.digitalbanking.core.fineract.model.request.PostLoanApplicationRequest;
import com.techservices.digitalbanking.core.fineract.model.request.PostLoanProductsRequest;
import com.techservices.digitalbanking.core.fineract.model.request.PostLoansLoanIdTransactionsTransactionIdRequest;

import jakarta.validation.Valid;

public interface LoansApi {

	/**
	 * GET /loans/{loanId}/guarantors/accounts/template
	 *
	 * @param loanId
	 *            (required)
	 * @param clientId
	 *            (optional)
	 * @return default response (status code 200)
	 */

	// "/loans/{loanId}/guarantors/accounts/template
	@GetMapping(value = "/loans/{loanId}/guarantors/accounts/template", produces = {"application/json"})
	String accountsTemplate(@PathVariable("loanId") Long loanId,
			@Valid @RequestParam(value = "clientId", required = false) Long clientId);

	/**
	 * PUT /loans/{loanId}/disbursements/editDisbursements
	 *
	 * @param loanId
	 *            (required)
	 * @param body
	 *            (optional)
	 * @return default response (status code 200)
	 */
	@PutMapping(value = "/loans/{loanId}/disbursements/editDisbursements", produces = {"application/json"})
	String addAndDeleteDisbursementDetail(@PathVariable("loanId") Long loanId,
			@Valid @RequestBody(required = false) String body);

	/**
	 * POST /loans/{loanId}/transactions/{transactionId} : Adjust a Transaction
	 * Note: there is no need to specify command&#x3D;{transactionType} parameter.
	 * Mandatory Fields: transactionDate, transactionAmount
	 *
	 * @param loanId
	 *            loanId (required)
	 * @param transactionId
	 *            transactionId (required)
	 * @param postLoansLoanIdTransactionsTransactionIdRequest
	 *            (required)
	 * @return OK (status code 200)
	 */
	@PostMapping(value = "/loans/{loanId}/transactions/{transactionId}", produces = {
			"application/json"}, consumes = "application/json")
	PostLoansLoanIdTransactionsTransactionIdResponse adjustLoanTransaction(@PathVariable("loanId") Long loanId,
			@PathVariable("transactionId") Long transactionId,
			@Valid @RequestBody PostLoansLoanIdTransactionsTransactionIdRequest postLoansLoanIdTransactionsTransactionIdRequest);

	/**
	 * POST /loans : Calculate loan repayment schedule | Submit a new Loan
	 * Application It calculates the loan repayment Schedule Submits a new loan
	 * application Mandatory Fields: clientId, productId, principal,
	 * loanTermFrequency, loanTermFrequencyType, loanType, numberOfRepayments,
	 * repaymentEvery, repaymentFrequencyType, interestRatePerPeriod,
	 * amortizationType, interestType, interestCalculationPeriodType,
	 * transactionProcessingStrategyId, expectedDisbursementDate, submittedOnDate,
	 * loanType Optional Fields: graceOnPrincipalPayment, graceOnInterestPayment,
	 * graceOnInterestCharged, linkAccountId, allowPartialPeriodInterestCalcualtion,
	 * fixedEmiAmount, maxOutstandingLoanBalance, disbursementData,
	 * graceOnArrearsAgeing, createStandingInstructionAtDisbursement (requires
	 * linkedAccountId if set to true) Additional Mandatory Fields if interest
	 * recalculation is enabled for product and Rest frequency not same as repayment
	 * period: recalculationRestFrequencyDate Additional Mandatory Fields if
	 * interest recalculation with interest/fee compounding is enabled for product
	 * and compounding frequency not same as repayment period:
	 * recalculationCompoundingFrequencyDate Additional Mandatory Field if
	 * Entity-Datatable Check is enabled for the entity of type loan: datatables
	 *
	 * @param postLoansRequest
	 *            (required)
	 * @param command
	 *            command (optional)
	 * @return OK (status code 200)
	 */
	@PostMapping(value = "/loans", produces = {"application/json"}, consumes = "application/json")
	PostLoansResponse calculateLoanScheduleOrSubmitLoanApplication(
			@Valid @RequestBody PostLoanApplicationRequest postLoansRequest,
			@Valid @RequestParam(value = "command", required = false) String command);

	/**
	 * POST /loans/{loanId}/schedule : Calculate loan repayment schedule based on
	 * Loan term variations | Updates loan repayment schedule based on Loan term
	 * variations | Updates loan repayment schedule by removing Loan term variations
	 * Calculate loan repayment schedule based on Loan term variations: Mandatory
	 * Fields: exceptions,locale,dateFormat Updates loan repayment schedule based on
	 * Loan term variations: Mandatory Fields: exceptions,locale,dateFormat Updates
	 * loan repayment schedule by removing Loan term variations: It updates the loan
	 * repayment schedule by removing Loan term variations Showing request/response
	 * for &#39;Updates loan repayment schedule by removing Loan term
	 * variations&#39;
	 *
	 * @param loanId
	 *            loanId (required)
	 * @param body
	 *            (required)
	 * @param command
	 *            command (optional)
	 * @return OK (status code 200)
	 */
	@PostMapping(value = "/loans/{loanId}/schedule", produces = {"application/json"}, consumes = "application/json")
	PostLoansLoanIdScheduleResponse calculateLoanScheduleOrSubmitVariableSchedule(@PathVariable("loanId") Long loanId,
			@Valid @RequestBody Object body, @Valid @RequestParam(value = "command", required = false) String command);

	/**
	 * POST /loans/{loanId}/collaterals : Create a Collateral Note: Currently,
	 * Collaterals may be added only before a Loan is approved
	 *
	 * @param loanId
	 *            loanId (required)
	 * @param postLoansLoanIdCollateralsRequest
	 *            (required)
	 * @return OK (status code 200)
	 */
	@PostMapping(value = "/loans/{loanId}/collaterals", produces = {"application/json"}, consumes = "application/json")
	PostLoansLoanIdCollateralsResponse createCollateral(@PathVariable("loanId") Long loanId,
			@Valid @RequestBody PostLoansLoanIdCollateralsRequest postLoansLoanIdCollateralsRequest);

	/**
	 * POST /loans/{loanId}/guarantors
	 *
	 * @param loanId
	 *            (required)
	 * @param body
	 *            (optional)
	 * @return default response (status code 200)
	 */
	@PostMapping(value = "/loans/{loanId}/guarantors", produces = {"application/json"})
	String createGuarantor(@PathVariable("loanId") Long loanId, @Valid @RequestBody(required = false) String body);

	/**
	 * DELETE /loans/{loanId}/collaterals/{collateralId} : Remove a Collateral Note:
	 * A collateral can only be removed from Loans that are not yet approved.
	 *
	 * @param loanId
	 *            loanId (required)
	 * @param collateralId
	 *            collateralId (required)
	 * @return OK (status code 200)
	 */
	@DeleteMapping(value = "/loans/{loanId}/collaterals/{collateralId}", produces = {"application/json"})
	DeleteLoansLoanIdCollateralsCollateralIdResponse deleteCollateral(@PathVariable("loanId") Long loanId,
			@PathVariable("collateralId") Long collateralId);

	/**
	 * DELETE /loans/{loanId}/guarantors/{guarantorId}
	 *
	 * @param loanId
	 *            (required)
	 * @param guarantorId
	 *            (required)
	 * @param guarantorFundingId
	 *            (optional)
	 * @return default response (status code 200)
	 */
	@DeleteMapping(value = "/loans/{loanId}/guarantors/{guarantorId}", produces = {"application/json"})
	String deleteGuarantor(@PathVariable("loanId") Long loanId, @PathVariable("guarantorId") Long guarantorId,
			@Valid @RequestParam(value = "guarantorFundingId", required = false) Long guarantorFundingId);

	/**
	 * DELETE /loans/{loanId} : Delete a Loan Application Note: Only loans in
	 * \&quot;Submitted and awaiting approval\&quot; status can be deleted.
	 *
	 * @param loanId
	 *            loanId (required)
	 * @return OK (status code 200)
	 */
	@DeleteMapping(value = "/loans/{loanId}", produces = {"application/json"})
	DeleteLoansLoanIdResponse deleteLoanApplication(@PathVariable("loanId") Long loanId);

	/**
	 * DELETE /loans/{loanId}/charges/{chargeId} : Delete a Loan Charge Note:
	 * Currently, A Loan Charge may only be removed from Loans that are not yet
	 * approved.
	 *
	 * @param loanId
	 *            loanId (required)
	 * @param chargeId
	 *            chargeId (required)
	 * @return OK (status code 200)
	 */
	@DeleteMapping(value = "/loans/{loanId}/charges/{chargeId}", produces = {"application/json"})
	DeleteLoansLoanIdChargesChargeIdResponse deleteLoanCharge(@PathVariable("loanId") Long loanId,
			@PathVariable("chargeId") Long chargeId);

	/**
	 * DELETE /loans/{loanId}/postdatedchecks/{postDatedCheckId} : Delete Post Dated
	 * Check Delete Post Dated Check
	 *
	 * @param postDatedCheckId
	 *            postDatedCheckId (required)
	 * @param loanId
	 *            loanId (required)
	 * @return OK (status code 200)
	 */
	@DeleteMapping(value = "/loans/{loanId}/postdatedchecks/{postDatedCheckId}", produces = {"application/json"})
	List<DeletePostDatedCheck> deletePostDatedCheck(@PathVariable("postDatedCheckId") Long postDatedCheckId,
			@PathVariable("loanId") Long loanId);

	/**
	 * POST /loans/{loanId}/charges : Create a Loan Charge It Creates a Loan Charge
	 *
	 * @param loanId
	 *            loanId (required)
	 * @param postLoansLoanIdChargesRequest
	 *            (required)
	 * @param command
	 *            command (optional)
	 * @return OK (status code 200)
	 */
	@PostMapping(value = "/loans/{loanId}/charges", produces = {"application/json"}, consumes = "application/json")
	PostLoansLoanIdChargesResponse executeLoanCharge(@PathVariable("loanId") Long loanId,
			@Valid @RequestBody PostLoansLoanIdChargesRequest postLoansLoanIdChargesRequest,
			@Valid @RequestParam(value = "command", required = false) String command);

	/**
	 * POST /loans/{loanId}/charges/{chargeId} : Pay Loan Charge Loan Charge will be
	 * paid if the loan is linked with a savings account
	 *
	 * @param loanId
	 *            loanId (required)
	 * @param chargeId
	 *            chargeId (required)
	 * @param postLoansLoanIdChargesChargeIdRequest
	 *            (required)
	 * @param command
	 *            command (optional)
	 * @return OK (status code 200)
	 */
	@PostMapping(value = "/loans/{loanId}/charges/{chargeId}", produces = {
			"application/json"}, consumes = "application/json")
	PostLoansLoanIdChargesChargeIdResponse executeLoanCharge1(@PathVariable("loanId") Long loanId,
			@PathVariable("chargeId") Long chargeId,
			@Valid @RequestBody PostLoansLoanIdChargesChargeIdRequest postLoansLoanIdChargesChargeIdRequest,
			@Valid @RequestParam(value = "command", required = false) String command);

	/**
	 * POST /loans/{loanId}/transactions : Significant Loan Transactions This API
	 * covers the major loan transaction functionality Example Requests:
	 * loans/1/transactions?command&#x3D;repayment | Make a Repayment |
	 * loans/1/transactions?command&#x3D;merchantIssuedRefund | Merchant Issued
	 * Refund | loans/1/transactions?command&#x3D;payoutRefund | Payout Refund |
	 * loans/1/transactions?command&#x3D;goodwillCredit | Goodwil Credit |
	 * loans/1/transactions?command&#x3D;waiveinterest | Waive Interest |
	 * loans/1/transactions?command&#x3D;writeoff | Write-off Loan |
	 * loans/1/transactions?command&#x3D;close-rescheduled | Close Rescheduled Loan
	 * | loans/1/transactions?command&#x3D;close | Close Loan |
	 * loans/1/transactions?command&#x3D;undowriteoff | Undo Loan Write-off |
	 * loans/1/transactions?command&#x3D;recoverypayment | Make Recovery Payment |
	 * loans/1/transactions?command&#x3D;refundByCash | Make a Refund of an Active
	 * Loan by Cash | loans/1/transactions?command&#x3D;foreclosure | Foreclosure of
	 * an Active Loan | loans/1/transactions?command&#x3D;creditBalanceRefund |
	 * Credit Balance Refund |
	 *
	 * @param loanId
	 *            loanId (required)
	 * @param postLoansLoanIdTransactionsRequest
	 *            (required)
	 * @param command
	 *            command (optional)
	 * @return OK (status code 200)
	 */
	@PostMapping(value = "/loans/{loanId}/transactions", produces = {"application/json"}, consumes = "application/json")
	PostLoansLoanIdTransactionsResponse executeLoanTransaction(@PathVariable("loanId") Long loanId,
			@Valid @RequestBody PostLoansLoanIdTransactionsRequest postLoansLoanIdTransactionsRequest,
			@Valid @RequestParam(value = "command", required = false) String command);

	/**
	 * GET /loans/glimAccount/{glimId}
	 *
	 * @param glimId
	 *            (required)
	 * @return default response (status code 200)
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/loans/glimAccount/{glimId}", produces = {"application/json"})
	@ResponseStatus(HttpStatus.OK)
	String getGlimRepaymentTemplate(@PathVariable("glimId") Long glimId);

	/**
	 * GET /loans/{loanId}/guarantors/downloadtemplate
	 *
	 * @param loanId
	 *            (required)
	 * @param officeId
	 *            (optional)
	 * @param dateFormat
	 *            (optional)
	 * @return default response (status code 200)
	 */
	@GetMapping(value = "/loans/{loanId}/guarantors/downloadtemplate", produces = {"application/vnd.ms-excel"})
	void getGuarantorTemplate(@PathVariable("loanId") Long loanId,
			@Valid @RequestParam(value = "officeId", required = false) Long officeId,
			@Valid @RequestParam(value = "dateFormat", required = false) String dateFormat);

	/**
	 * GET /loans/repayments/downloadtemplate
	 *
	 * @param officeId
	 *            (optional)
	 * @param dateFormat
	 *            (optional)
	 * @return default response (status code 200)
	 */
	@GetMapping(value = "/loans/repayments/downloadtemplate", produces = {"application/vnd.ms-excel"})
	void getLoanRepaymentTemplate(@Valid @RequestParam(value = "officeId", required = false) Long officeId,
			@Valid @RequestParam(value = "dateFormat", required = false) String dateFormat);

	/**
	 * GET /loans/downloadtemplate
	 *
	 * @param officeId
	 *            (optional)
	 * @param staffId
	 *            (optional)
	 * @param dateFormat
	 *            (optional)
	 * @return default response (status code 200)
	 */
	@GetMapping(value = "/loans/downloadtemplate", produces = {"application/vnd.ms-excel"})
	void getLoansTemplate(@Valid @RequestParam(value = "officeId", required = false) Long officeId,
			@Valid @RequestParam(value = "staffId", required = false) Long staffId,
			@Valid @RequestParam(value = "dateFormat", required = false) String dateFormat);

	/**
	 * POST /loans/glimAccount/{glimId} : Approve GLIM Application | Undo GLIM
	 * Application Approval | Reject GLIM Application | Disburse Loan Disburse Loan
	 * To Savings Account | Undo Loan Disbursal Approve GLIM Application: Mandatory
	 * Fields: approvedOnDate Optional Fields: approvedLoanAmount and
	 * expectedDisbursementDate Approves the GLIM application Undo GLIM Application
	 * Approval: Undoes the GLIM Application Approval Reject GLIM Application:
	 * Mandatory Fields: rejectedOnDate Allows you to reject the GLIM application
	 * Disburse Loan: Mandatory Fields: actualDisbursementDate Optional Fields:
	 * transactionAmount and fixedEmiAmount Disburses the Loan Disburse Loan To
	 * Savings Account: Mandatory Fields: actualDisbursementDate Optional Fields:
	 * transactionAmount and fixedEmiAmount Disburses the loan to Saving Account
	 * Undo Loan Disbursal: Undoes the Loan Disbursal
	 *
	 * @param glimId
	 *            (required)
	 * @param postLoansLoanIdRequest
	 *            (required)
	 * @param command
	 *            (optional)
	 * @return OK (status code 200)
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/loans/glimAccount/{glimId}", produces = {
			"application/json"}, consumes = "application/json")
	PostLoansLoanIdResponse glimStateTransitions(@PathVariable("glimId") Long glimId,
			@Valid @RequestBody PostLoansLoanIdRequest postLoansLoanIdRequest,
			@Valid @RequestParam(value = "command", required = false) String command);

	/**
	 * POST /loans/loanreassignment
	 *
	 * @param body
	 *            (optional)
	 * @return default response (status code 200)
	 */
	@PostMapping(value = "/loans/loanreassignment", produces = {"application/json"}, consumes = "application/json")
	String loanReassignment(@Valid @RequestBody(required = false) String body);

	/**
	 * GET /loans/loanreassignment/template
	 *
	 * @param officeId
	 *            (optional)
	 * @param fromLoanOfficerId
	 *            (optional)
	 * @return default response (status code 200)
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/loans/loanreassignment/template", produces = {
			"application/json"})
	String loanReassignmentTemplate(@Valid @RequestParam(value = "officeId", required = false) Long officeId,
			@Valid @RequestParam(value = "fromLoanOfficerId", required = false) Long fromLoanOfficerId);

	/**
	 * PUT /loans/{loanId} : Modify a loan application Loan application can only be
	 * modified when in &#39;Submitted and pending approval&#39; state. Once the
	 * application is approved, the details cannot be changed using this method.
	 *
	 * @param loanId
	 *            loanId (required)
	 * @param putLoansLoanIdRequest
	 *            (required)
	 * @return OK (status code 200)
	 */
	@PutMapping(value = "/loans/{loanId}", produces = {"application/json"}, consumes = "application/json")
	PutLoansLoanIdResponse modifyLoanApplication(@PathVariable("loanId") Long loanId,
			@Valid @RequestBody PutLoansLoanIdRequest putLoansLoanIdRequest);

	/**
	 * GET /loans/{loanId}/collaterals/template : Retrieve Collateral Details
	 * Template This is a convenience resource. It can be useful when building
	 * maintenance user interface screens for client applications. The template data
	 * returned consists of any or all of: Field Defaults Allowed Value Lists
	 * Example Request: loans/1/collaterals/template
	 *
	 * @param loanId
	 *            loanId (required)
	 * @return OK (status code 200)
	 */
	@GetMapping(value = "/loans/{loanId}/collaterals/template", produces = {"application/json"})
	GetLoansLoanIdCollateralsTemplateResponse newCollateralTemplate(@PathVariable("loanId") Long loanId);

	/**
	 * GET /loans/{loanId}/guarantors/template
	 *
	 * @param loanId
	 *            (required)
	 * @return default response (status code 200)
	 */
	@GetMapping(value = "/loans/{loanId}/guarantors/template", produces = {"application/json"})
	String newGuarantorTemplate(@PathVariable("loanId") Long loanId);

	/**
	 * POST /loans/{loanId}/guarantors/uploadtemplate
	 *
	 * @param loanId
	 *            (required)
	 * @param dateFormat
	 *            (optional)
	 * @param locale
	 *            (optional)
	 * @param uploadedInputStream
	 *            (optional)
	 * @return default response (status code 200)
	 */
	@PostMapping(value = "/loans/{loanId}/guarantors/uploadtemplate", produces = {
			"*/*"}, consumes = "multipart/form-data")
	String postGuarantorTemplate(@PathVariable("loanId") Long loanId, @RequestParam("dateFormat") String dateFormat,
			@RequestParam("locale") String locale,
			@RequestPart(value = "uploadedInputStream", required = false) MultipartFile uploadedInputStream);

	/**
	 * POST /loans/repayments/uploadtemplate
	 *
	 * @param dateFormat
	 *            (optional)
	 * @param locale
	 *            (optional)
	 * @param uploadedInputStream
	 *            (optional)
	 * @return default response (status code 200)
	 */
	@PostMapping(value = "/loans/repayments/uploadtemplate", produces = {"*/*"}, consumes = "multipart/form-data")
	String postLoanRepaymentTemplate(@Valid @RequestParam(value = "dateFormat", required = false) String dateFormat,
			@Valid @RequestParam(value = "locale", required = false) String locale,
			@RequestPart(value = "uploadedInputStream", required = false) MultipartFile uploadedInputStream);

	/**
	 * POST /loans/uploadtemplate
	 *
	 * @param dateFormat
	 *            (optional)
	 * @param locale
	 *            (optional)
	 * @param uploadedInputStream
	 *            (optional)
	 * @return default response (status code 200)
	 */
	@PostMapping(value = "/loans/uploadtemplate", produces = {"*/*"}, consumes = "multipart/form-data")
	String postLoanTemplate(@Valid @RequestParam(value = "dateFormat", required = false) String dateFormat,
			@Valid @RequestParam(value = "locale", required = false) String locale,
			@RequestPart(value = "uploadedInputStream", required = false) MultipartFile uploadedInputStream);

	/**
	 * GET /loans : List Loans The list capability of loans can support pagination
	 * and sorting. Example Requests: loans loans?fields&#x3D;accountNo
	 * loans?offset&#x3D;10&amp;limit&#x3D;50
	 * loans?orderBy&#x3D;accountNo&amp;sortOrder&#x3D;DESC
	 *
	 * @param sqlSearch  sqlSearch (optional)
	 * @param externalId externalId (optional)
	 * @param offset     offset (optional)
	 * @param limit      limit (optional)
	 * @param orderBy    orderBy (optional)
	 * @param sortOrder  sortOrder (optional)
	 * @param accountNo  accountNo (optional)
	 * @param clientId
	 * @param status
	 * @return OK (status code 200)
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/loans", produces = {"application/json"})
	@ResponseStatus(HttpStatus.OK)
	GetLoansResponse retrieveAll36(@Valid @RequestParam(value = "sqlSearch", required = false) String sqlSearch,
								   @Valid @RequestParam(value = "externalId", required = false) String externalId,
								   @Valid @RequestParam(value = "offset", required = false) Integer offset,
								   @Valid @RequestParam(value = "limit", required = false) Integer limit,
								   @Valid @RequestParam(value = "orderBy", required = false) String orderBy,
								   @Valid @RequestParam(value = "sortOrder", required = false) String sortOrder,
								   @Valid @RequestParam(value = "accountNo", required = false) String accountNo,
								   @Valid @RequestParam(value = "clientId", required = false) String clientId,
								   @Valid @RequestParam(value = "status", required = false) String status
	);

	/**
	 * GET /loans/{loanId}/charges : List Loan Charges It lists all the Loan Charges
	 * specific to a Loan Example Requests: loans/1/charges
	 * loans/1/charges?fields&#x3D;name,amountOrPercentage
	 *
	 * @param loanId
	 *            loanId (required)
	 * @return OK (status code 200)
	 */
	@GetMapping(value = "/loans/{loanId}/charges", produces = {"application/json"})
	List<GetLoansLoanIdChargesChargeIdResponse> retrieveAllLoanCharges(@PathVariable("loanId") Long loanId);

	/**
	 * GET /loans/{loanId}/template
	 *
	 * @param loanId
	 *            loanId (required)
	 * @param templateType
	 *            templateType (optional)
	 * @return default response (status code 200)
	 */
	@GetMapping(value = "/loans/{loanId}/template", produces = {"application/json"})
	String retrieveApprovalTemplate(@PathVariable("loanId") Long loanId,
			@Valid @RequestParam(value = "templateType", required = false) String templateType);

	/**
	 * GET /loans/{loanId}/collaterals : List Loan Collaterals Example Requests:
	 * loans/1/collaterals loans/1/collaterals?fields&#x3D;value,description
	 *
	 * @param loanId
	 *            loanId (required)
	 * @return OK (status code 200)
	 */
	@GetMapping(value = "/loans/{loanId}/collaterals", produces = {"application/json"})
	List<GetLoansLoanIdCollateralsResponse> retrieveCollateralDetails(@PathVariable("loanId") Long loanId);

	/**
	 * GET /loans/{loanId}/collaterals/{collateralId} : Retrieve a Collateral
	 * Example Requests: /loans/1/collaterals/1
	 * /loans/1/collaterals/1?fields&#x3D;description,description
	 *
	 * @param loanId
	 *            loanId (required)
	 * @param collateralId
	 *            collateralId (required)
	 * @return OK (status code 200)
	 */
	@GetMapping(value = "/loans/{loanId}/collaterals/{collateralId}", produces = {"application/json"})
	@ResponseStatus(HttpStatus.OK)
	GetLoansLoanIdCollateralsResponse retrieveCollateralDetails1(@PathVariable("loanId") Long loanId,
			@PathVariable("collateralId") Long collateralId);

	/**
	 * GET /loans/{loanId}/guarantors
	 *
	 * @param loanId
	 *            (required)
	 * @return default response (status code 200)
	 */
	@GetMapping(value = "/loans/{loanId}/guarantors", produces = {"application/json"})
	@ResponseStatus(HttpStatus.OK)
	String retrieveGuarantorDetails(@PathVariable("loanId") Long loanId);

	/**
	 * GET /loans/{loanId}/guarantors/{guarantorId}
	 *
	 * @param loanId
	 *            (required)
	 * @param guarantorId
	 *            (required)
	 * @return default response (status code 200)
	 */
	@GetMapping(value = "/loans/{loanId}/guarantors/{guarantorId}", produces = {"application/json"})
	String retrieveGuarantorDetails1(@PathVariable("loanId") Long loanId,
			@PathVariable("guarantorId") Long guarantorId);

	/**
	 * GET /loans/{loanId} : Retrieve a Loan Note: template&#x3D;true parameter
	 * doesn&#39;t apply to this resource.Example Requests: loans/1
	 * loans/1?fields&#x3D;id,principal,annualInterestRate
	 * loans/1?associations&#x3D;all
	 * loans/1?associations&#x3D;all&amp;exclude&#x3D;guarantors
	 * loans/1?fields&#x3D;id,principal,annualInterestRate&amp;associations&#x3D;repaymentSchedule,transactions
	 *
	 * @param loanId
	 *            loanId (required)
	 * @param staffInSelectedOfficeOnly
	 *            staffInSelectedOfficeOnly (optional, default to false)
	 * @param associations
	 *            Loan object relations to be included in the response (optional,
	 *            default to all)
	 * @param exclude
	 *            Optional Loan object relation list to be filtered in the response
	 *            (optional)
	 * @param fields
	 *            Optional Loan attribute list to be in the response (optional)
	 * @return OK (status code 200)
	 */
	@GetMapping(value = "/loans/{loanId}", produces = {"application/json"})
	GetLoansLoanIdResponse retrieveLoan(@PathVariable("loanId") Long loanId,
			@Valid @RequestParam(value = "staffInSelectedOfficeOnly", required = false, defaultValue = "false") Boolean staffInSelectedOfficeOnly,
			@Valid @RequestParam(value = "associations", required = false, defaultValue = "all") String associations,
			@Valid @RequestParam(value = "exclude", required = false) String exclude,
			@Valid @RequestParam(value = "fields", required = false) String fields);

	/**
	 * GET /loans/{loanId}/charges/{chargeId} : Retrieve a Loan Charge Retrieves
	 * Loan Charge according to the Loan ID and Charge IDExample Requests:
	 * /loans/1/charges/1 /loans/1/charges/1?fields&#x3D;name,amountOrPercentage
	 *
	 * @param loanId
	 *            loanId (required)
	 * @param chargeId
	 *            chargeId (required)
	 * @return OK (status code 200)
	 */
	@GetMapping(value = "/loans/{loanId}/charges/{chargeId}", produces = {"application/json"})
	GetLoansLoanIdChargesChargeIdResponse retrieveLoanCharge(@PathVariable("loanId") Long loanId,
			@PathVariable("chargeId") Long chargeId);

	/**
	 * GET /loans/{loanId}/charges/template : Retrieve Loan Charges Template This is
	 * a convenience resource. It can be useful when building maintenance user
	 * interface screens for client applications. The template data returned
	 * consists of any or all of: Field Defaults Allowed description Lists Example
	 * Request: loans/1/charges/template
	 *
	 * @param loanId
	 *            loanId (required)
	 * @return OK (status code 200)
	 */
	@GetMapping(value = "/loans/{loanId}/charges/template", produces = {"application/json"})
	GetLoansLoanIdChargesTemplateResponse retrieveTemplate8(@PathVariable("loanId") Long loanId);

	/**
	 * GET /loans/{loanId}/transactions/{transactionId} : Retrieve a Transaction
	 * Details Retrieves a Transaction Details Example Request:
	 * loans/5/transactions/3
	 *
	 * @param loanId
	 *            loanId (required)
	 * @param transactionId
	 *            transactionId (required)
	 * @param fields
	 *            Optional Loan Transaction attribute list to be in the response
	 *            (optional)
	 * @return OK (status code 200)
	 */
	@GetMapping(value = "/loans/{loanId}/transactions/{transactionId}", produces = {"application/json"})
	GetLoansLoanIdTransactionsTransactionIdResponse retrieveTransaction(@PathVariable("loanId") Long loanId,
			@PathVariable("transactionId") Long transactionId,
			@Valid @RequestParam(value = "fields", required = false) String fields);

	/**
	 * GET /loans/{loanId}/transactions/template : Retrieve Loan Transaction
	 * Template This is a convenience resource. It can be useful when building
	 * maintenance user interface screens for client applications. The template data
	 * returned consists of any or all of: Field Defaults Allowed Value Lists
	 * Example Requests:
	 * loans/1/transactions/template?command&#x3D;repaymentloans/1/transactions/template?command&#x3D;merchantIssuedRefundloans/1/transactions/template?command&#x3D;payoutRefundloans/1/transactions/template?command&#x3D;goodwillCredit
	 * loans/1/transactions/template?command&#x3D;waiveinterest
	 * loans/1/transactions/template?command&#x3D;writeoff
	 * loans/1/transactions/template?command&#x3D;close-rescheduled
	 * loans/1/transactions/template?command&#x3D;close
	 * loans/1/transactions/template?command&#x3D;disburse
	 * loans/1/transactions/template?command&#x3D;disburseToSavings
	 * loans/1/transactions/template?command&#x3D;recoverypayment
	 * loans/1/transactions/template?command&#x3D;prepayLoan
	 * loans/1/transactions/template?command&#x3D;refundbycash
	 * loans/1/transactions/template?command&#x3D;refundbytransfer
	 * loans/1/transactions/template?command&#x3D;foreclosure
	 * loans/1/transactions/template?command&#x3D;creditBalanceRefund (returned
	 * &#39;amount&#39; field will have the overpaid value
	 *
	 * @param loanId
	 *            loanId (required)
	 * @param command
	 *            command (optional)
	 * @param dateFormat
	 *            dateFormat (optional)
	 * @param transactionDate
	 *            transactionDate (optional)
	 * @param locale
	 *            locale (optional)
	 * @return OK (status code 200)
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/loans/{loanId}/transactions/template", produces = {
			"application/json"})
	@ResponseStatus(HttpStatus.OK)
	GetLoansLoanIdTransactionsTemplateResponse retrieveTransactionTemplate(@PathVariable("loanId") Long loanId,
			@Valid @RequestParam(value = "command", required = false) String command,
			@Valid @RequestParam(value = "dateFormat", required = false) String dateFormat,
			@Valid @RequestParam(value = "", required = false) Object transactionDate,
			@Valid @RequestParam(value = "locale", required = false) String locale);

	/**
	 * GET /loans/{loanId}/disbursements/{disbursementId}
	 *
	 * @param loanId
	 *            (required)
	 * @param disbursementId
	 *            (required)
	 * @return default response (status code 200)
	 */
	@GetMapping(value = "/loans/{loanId}/disbursements/{disbursementId}", produces = {"application/json"})
	String retriveDetail(@PathVariable("loanId") Long loanId, @PathVariable("disbursementId") Long disbursementId);

	/**
	 * POST /loans/{loanId} : Approve Loan Application | Recover Loan Guarantee |
	 * Undo Loan Application Approval | Assign a Loan Officer | Unassign a Loan
	 * Officer | Reject Loan Application | Applicant Withdraws from Loan Application
	 * | Disburse Loan Disburse Loan To Savings Account | Undo Loan Disbursal
	 * Approve Loan Application: Mandatory Fields: approvedOnDate Optional Fields:
	 * approvedLoanAmount and expectedDisbursementDate Approves the loan application
	 * Recover Loan Guarantee: Recovers the loan guarantee Undo Loan Application
	 * Approval: Undoes the Loan Application Approval Assign a Loan Officer: Allows
	 * you to assign Loan Officer for existing Loan. Unassign a Loan Officer: Allows
	 * you to unassign the Loan Officer. Reject Loan Application: Mandatory Fields:
	 * rejectedOnDate Allows you to reject the loan application Applicant Withdraws
	 * from Loan Application: Mandatory Fields: withdrawnOnDate Allows the applicant
	 * to withdraw the loan application Disburse Loan: Mandatory Fields:
	 * actualDisbursementDate Optional Fields: transactionAmount and fixedEmiAmount
	 * Disburses the Loan Disburse Loan To Savings Account: Mandatory Fields:
	 * actualDisbursementDate Optional Fields: transactionAmount and fixedEmiAmount
	 * Disburses the loan to Saving Account Undo Loan Disbursal: Undoes the Loan
	 * Disbursal Showing request and response for Assign a Loan Officer
	 *
	 * @param loanId
	 *            loanId (required)
	 * @param postLoansLoanIdRequest
	 *            (required)
	 * @param command
	 *            command (optional)
	 * @return OK (status code 200)
	 */
	@PostMapping(value = "/loans/{loanId}", produces = {"application/json"}, consumes = "application/json")
	PostLoansLoanIdResponse stateTransitions(@PathVariable("loanId") Long loanId,
			@Valid @RequestBody PostLoansLoanIdRequest postLoansLoanIdRequest,
			@Valid @RequestParam(value = "command", required = false) String command);

	/**
	 * GET /loans/template : Retrieve Loan Details Template This is a convenience
	 * resource. It can be useful when building maintenance user interface screens
	 * for client applications. The template data returned consists of any or all
	 * of: Field Defaults Allowed description Lists Example Requests:
	 * loans/template?templateType&#x3D;individual&amp;clientId&#x3D;1
	 * loans/template?templateType&#x3D;individual&amp;clientId&#x3D;1&amp;productId&#x3D;1
	 *
	 * @param clientId
	 *            clientId (optional)
	 * @param groupId
	 *            groupId (optional)
	 * @param productId
	 *            productId (optional)
	 * @param templateType
	 *            templateType (optional)
	 * @param staffInSelectedOfficeOnly
	 *            staffInSelectedOfficeOnly (optional, default to false)
	 * @param activeOnly
	 *            activeOnly (optional, default to false)
	 * @return OK (status code 200)
	 */
	@GetMapping(value = "/loans/template", produces = {"application/json"})
	GetLoansTemplateResponse template10(@Valid @RequestParam(value = "clientId", required = false) Long clientId,
			@Valid @RequestParam(value = "groupId", required = false) Long groupId,
			@Valid @RequestParam(value = "productId", required = false) Long productId,
			@Valid @RequestParam(value = "templateType", required = false) String templateType,
			@Valid @RequestParam(value = "staffInSelectedOfficeOnly", required = false, defaultValue = "false") Boolean staffInSelectedOfficeOnly,
			@Valid @RequestParam(value = "activeOnly", required = false, defaultValue = "false") Boolean activeOnly);

	/**
	 * PUT /loans/{loanId}/transactions/{transactionId} : Undo a Waive Charge
	 * Transaction Undo a Waive Charge Transaction
	 *
	 * @param loanId
	 *            loanId (required)
	 * @param transactionId
	 *            transactionId (required)
	 * @param putChargeTransactionChangesRequest
	 *            (required)
	 * @return OK (status code 200)
	 */
	@PutMapping(value = "/loans/{loanId}/transactions/{transactionId}", produces = {
			"application/json"}, consumes = "application/json")
	Object undoWaiveCharge(@PathVariable("loanId") Long loanId, @PathVariable("transactionId") Long transactionId,
			@Valid @RequestBody Object putChargeTransactionChangesRequest);

	/**
	 * PUT /loans/{loanId}/collaterals/{collateralId} : Update a Collateral
	 *
	 * @param loanId
	 *            loanId (required)
	 * @param collateralId
	 *            collateralId (required)
	 * @param putLoansLoandIdCollateralsCollateralIdRequest
	 *            (required)
	 * @return OK (status code 200)
	 */
	@PutMapping(value = "/loans/{loanId}/collaterals/{collateralId}", produces = {
			"application/json"}, consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	PutLoansLoanIdCollateralsCollateralIdResponse updateCollateral(@PathVariable("loanId") Long loanId,
			@PathVariable("collateralId") Long collateralId,
			@Valid @RequestBody PutLoansLoandIdCollateralsCollateralIdRequest putLoansLoandIdCollateralsCollateralIdRequest);

	/**
	 * PUT /loans/{loanId}/disbursements/{disbursementId}
	 *
	 * @param loanId
	 *            (required)
	 * @param disbursementId
	 *            (required)
	 * @param body
	 *            (optional)
	 * @return default response (status code 200)
	 */
	@PutMapping(value = "/loans/{loanId}/disbursements/{disbursementId}", produces = {
			"application/json"}, consumes = "application/json")
	String updateDisbursementDate(@PathVariable("loanId") Long loanId,
			@PathVariable("disbursementId") Long disbursementId, @Valid @RequestBody(required = false) String body);

	/**
	 * PUT /loans/{loanId}/guarantors/{guarantorId}
	 *
	 * @param loanId
	 *            (required)
	 * @param guarantorId
	 *            (required)
	 * @param body
	 *            (optional)
	 * @return default response (status code 200)
	 */
	@PutMapping(value = "/loans/{loanId}/guarantors/{guarantorId}", produces = {
			"application/json"}, consumes = "application/json")
	String updateGuarantor(@PathVariable("loanId") Long loanId, @PathVariable("guarantorId") Long guarantorId,
			@Valid @RequestBody(required = false) String body);

	/**
	 * PUT /loans/{loanId}/charges/{chargeId} : Update a Loan Charge Currently Loan
	 * Charges may be updated only if the Loan is not yet approved
	 *
	 * @param loanId
	 *            loanId (required)
	 * @param chargeId
	 *            chargeId (required)
	 * @param putLoansLoanIdChargesChargeIdRequest
	 *            (required)
	 * @return OK (status code 200)
	 */
	@PutMapping(value = "/loans/{loanId}/charges/{chargeId}", produces = {
			"application/json"}, consumes = "application/json")
	PutLoansLoanIdChargesChargeIdResponse updateLoanCharge(@PathVariable("loanId") Long loanId,
			@PathVariable("chargeId") Long chargeId,
			@Valid @RequestBody PutLoansLoanIdChargesChargeIdRequest putLoansLoanIdChargesChargeIdRequest);

	@PostMapping(value = "/loanproducts")
	PostLoanProductsResponse createALoanProduct(@RequestBody PostLoanProductsRequest postLoanProductsRequest);

	@GetMapping(value = "/loanproducts")
	List<GetLoanProductsProductIdResponse> getLoanProducts(
			@RequestParam(value = "fields", required = false) Long fields);

	@GetMapping(value = "/loanproducts/{productId}")
	GetLoanProductsProductIdResponse getLoanProductById(@PathVariable Long productId,
			@RequestParam(value = "fields", required = false) Long fields,
			@RequestParam(value = "template", required = false) Long template);

	@GetMapping(value = "/loanproducts/template")
	GetLoanProductsTemplateResponse getLoanProductTemplate();

	@GetMapping(value = "/loans/template")
	GetLoanTemplateResponse getLoanTemplate(@Valid @RequestParam(value = "templateType") String templateType,
			@Valid @RequestParam(value = "clientId", required = false) Long clientId,
			@Valid @RequestParam(value = "productId", required = false) Long productId);

	@PostMapping(value = "rescheduleloans")
	LoanRescheduleResponse createALoanRescheduleRequest(LoanRescheduleRequest loanRescheduleRequest);

	@PostMapping(value = "rescheduleloans/{requestId}")
	PostLoansLoanIdResponse processLoanRescheduleApproval(@RequestBody PostLoansLoanIdRequest postLoansLoanIdRequest,
			@PathVariable String requestId, @RequestParam(value = "command") String command);

	@GetMapping(value = "/loans/{loanId}/transactions/{transactionId}")
    LoanTransactionResponse getLoanTransactionDetails(
			@Valid @PathVariable(value = "loanId") Long loanId,
			@Valid @PathVariable(value = "transactionId") Long transactionId
	);
}
