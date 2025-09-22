/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.techservices.digitalbanking.core.eBanking.model.request.PostFixedDepositAccountsAccountIdRequest;
import com.techservices.digitalbanking.core.eBanking.model.request.PostFixedDepositAccountsRequest;
import com.techservices.digitalbanking.core.eBanking.model.request.PutFixedDepositAccountsAccountIdRequest;
import com.techservices.digitalbanking.core.eBanking.model.response.*;
import com.techservices.digitalbanking.fixeddeposit.domain.request.FixedDepositTransactionCommandRequest;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;

import java.util.List;

public interface FixeddepositaccountsApi {

	/**
	 * GET /fixeddepositaccounts/{accountId}/template
	 *
	 * @param accountId
	 *            accountId (required)
	 * @param command
	 *            command (optional)
	 * @return default response (status code 200)
	 */
	@GetMapping(value = "/fixeddepositaccounts/{accountId}/template")
	String accountClosureTemplate(
			@Parameter(name = "accountId", description = "accountId", required = true, in = ParameterIn.PATH) @PathVariable("accountId") Long accountId,
			@Parameter(name = "command", description = "command", in = ParameterIn.QUERY) @Valid @RequestParam(value = "command", required = false) String command);

	/**
	 * POST
	 * /fixeddepositaccounts/{fixedDepositAccountId}/transactions/{transactionId}
	 *
	 * @param fixedDepositAccountId
	 *            (required)
	 * @param transactionId
	 *            (required)
	 * @param command
	 *            (optional)
	 * @param body
	 *            (optional)
	 * @return default response (status code 200)
	 */
	@PostMapping(value = "/fixeddepositaccounts/{fixedDepositAccountId}/transactions/{transactionId}")
	String adjustTransaction(
			@Parameter(name = "fixedDepositAccountId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("fixedDepositAccountId") Long fixedDepositAccountId,
			@Parameter(name = "transactionId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("transactionId") Long transactionId,
			@Parameter(name = "command", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "command", required = false) String command,
			@Parameter(name = "body", description = "") @Valid @RequestBody(required = false) String body);

	/**
	 * DELETE /fixeddepositaccounts/{accountId} : Delete a fixed deposit application
	 * At present we support hard delete of fixed deposit application so long as its
	 * in &#39;Submitted and pending approval&#39; state. One the application is
	 * moves past this state, it is not possible to do a &#39;hard&#39; delete of
	 * the application or the account. An API endpoint will be added to
	 * close/de-activate the fixed deposit account.
	 *
	 * @param accountId
	 *            accountId (required)
	 * @return OK (status code 200)
	 */
	@DeleteMapping(value = "/fixeddepositaccounts/{accountId}")
	DeleteFixedDepositAccountsAccountIdResponse deleteAccount(
			@Parameter(name = "accountId", description = "accountId", required = true, in = ParameterIn.PATH) @PathVariable("accountId") Long accountId);

	/**
	 * GET /fixeddepositaccounts/downloadtemplate
	 *
	 * @param officeId
	 *            (optional)
	 * @param staffId
	 *            (optional)
	 * @param dateFormat
	 *            (optional)
	 * @return default response (status code 200)
	 */
	@GetMapping(value = "/fixeddepositaccounts/downloadtemplate")
	void getFixedDepositTemplate(
			@Parameter(name = "officeId", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "officeId", required = false) Long officeId,
			@Parameter(name = "staffId", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "staffId", required = false) Long staffId,
			@Parameter(name = "dateFormat", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "dateFormat", required = false) String dateFormat);

	/**
	 * GET /fixeddepositaccounts/transaction/downloadtemplate
	 *
	 * @param officeId
	 *            (optional)
	 * @param dateFormat
	 *            (optional)
	 * @return default response (status code 200)
	 */
	@GetMapping(value = "/fixeddepositaccounts/transaction/downloadtemplate")
	void getFixedDepositTransactionTemplate(
			@Parameter(name = "officeId", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "officeId", required = false) Long officeId,
			@Parameter(name = "dateFormat", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "dateFormat", required = false) String dateFormat);

	/**
	 * POST /fixeddepositaccounts/{accountId} : Approve fixed deposit application |
	 * Undo approval fixed deposit application | Reject fixed deposit application |
	 * Withdraw fixed deposit application | Activate a fixed deposit account | Close
	 * a fixed deposit account | Premature Close a fixed deposit account | Calculate
	 * Premature amount on Fixed deposit account | Calculate Interest on Fixed
	 * Deposit Account | Post Interest on Fixed Deposit Account Approve fixed
	 * deposit application: Approves fixed deposit application so long as its in
	 * &#39;Submitted and pending approval&#39; state. Undo approval fixed deposit
	 * application: Will move &#39;approved&#39; fixed deposit application back to
	 * &#39;Submitted and pending approval&#39; state. Reject fixed deposit
	 * application: Rejects fixed deposit application so long as its in
	 * &#39;Submitted and pending approval&#39; state. Withdraw fixed deposit
	 * application: Used when an applicant withdraws from the fixed deposit
	 * application. It must be in &#39;Submitted and pending approval&#39; state.
	 * Close a fixed deposit account: Results in a Matured fixed deposit account
	 * being converted into a &#39;closed&#39; fixed deposit account. Premature
	 * Close a fixed deposit account: Results in an Active fixed deposit account
	 * being converted into a &#39;Premature Closed&#39; fixed deposit account with
	 * options to withdraw prematured amount. (premature amount is calculated using
	 * interest rate chart applicable along with penal interest if any.) Calculate
	 * Premature amount on Fixed deposit account: Calculate premature amount on
	 * fixed deposit account till premature close date. Premature amount is
	 * calculated based on interest chart and penal interest applicable. Calculate
	 * Interest on Fixed Deposit Account: Calculates interest earned on a fixed
	 * deposit account based on todays date. It does not attempt to post or credit
	 * the interest on the account. That is responsibility of the Post Interest API
	 * that will likely be called by overnight process. Post Interest on Fixed
	 * Deposit Account: Calculates and Posts interest earned on a fixed deposit
	 * account based on today&#39;s date and whether an interest posting or
	 * crediting event is due. Showing request/response for Calculate Interest on
	 * Fixed Deposit Account
	 *
	 * @param accountId
	 *            accountId (required)
	 * @param postFixedDepositAccountsAccountIdRequest
	 *            (required)
	 * @param command
	 *            command (optional)
	 * @return OK (status code 200)
	 */
	@PostMapping(value = "/fixeddepositaccounts/{accountId}")
	PostFixedDepositAccountsAccountIdResponse handleCommands(
			@Parameter(name = "accountId", description = "accountId", required = true, in = ParameterIn.PATH) @PathVariable("accountId") Long accountId,
			@Parameter(name = "body", description = "", required = true) @Valid @RequestBody PostFixedDepositAccountsAccountIdRequest postFixedDepositAccountsAccountIdRequest,
			@Parameter(name = "command", description = "command", in = ParameterIn.QUERY) @Valid @RequestParam(value = "command", required = false) String command);

	/**
	 * POST /fixeddepositaccounts/uploadtemplate
	 *
	 * @param dateFormat
	 *            (optional)
	 * @param locale
	 *            (optional)
	 * @param uploadedInputStream
	 *            (optional)
	 * @return default response (status code 200)
	 */
	@PostMapping(value = "/fixeddepositaccounts/uploadtemplate")
	String postFixedDepositTemplate(
			@Parameter(name = "dateFormat", description = "") @Valid @RequestParam(value = "dateFormat", required = false) String dateFormat,
			@Parameter(name = "locale", description = "") @Valid @RequestParam(value = "locale", required = false) String locale,
			@Parameter(name = "uploadedInputStream", description = "") @RequestPart(value = "uploadedInputStream", required = false) MultipartFile uploadedInputStream);

	/**
	 * POST /fixeddepositaccounts/transaction/uploadtemplate
	 *
	 * @param dateFormat
	 *            (optional)
	 * @param locale
	 *            (optional)
	 * @param uploadedInputStream
	 *            (optional)
	 * @return default response (status code 200)
	 */
	@PostMapping(value = "/fixeddepositaccounts/transaction/uploadtemplate")
	String postFixedDepositTransactionTemplate(
			@Parameter(name = "dateFormat", description = "") @Valid @RequestParam(value = "dateFormat", required = false) String dateFormat,
			@Parameter(name = "locale", description = "") @Valid @RequestParam(value = "locale", required = false) String locale,
			@Parameter(name = "uploadedInputStream", description = "") @RequestPart(value = "uploadedInputStream", required = false) MultipartFile uploadedInputStream);

	/**
	 * GET /fixeddepositaccounts : List Fixed deposit applications/accounts Lists
	 * Fixed Deposit Accounts Example Requests: fixeddepositaccounts
	 * fixeddepositaccounts?fields&#x3D;name
	 *
	 * @param paged
	 *            paged (optional)
	 * @param offset
	 *            offset (optional)
	 * @param limit
	 *            limit (optional)
	 * @param orderBy
	 *            orderBy (optional)
	 * @param sortOrder
	 *            sortOrder (optional)
	 * @return OK (status code 200)
	 */
	@GetMapping(value = "/fixeddepositaccounts")
	List<GetFixedDepositAccountsAccountIdResponse> retrieveAll38(
			@Parameter(name = "paged", description = "paged", in = ParameterIn.QUERY) @Valid @RequestParam(value = "paged", required = false) Boolean paged,
			@Parameter(name = "offset", description = "offset", in = ParameterIn.QUERY) @Valid @RequestParam(value = "offset", required = false) Integer offset,
			@Parameter(name = "limit", description = "limit", in = ParameterIn.QUERY) @Valid @RequestParam(value = "limit", required = false) Integer limit,
			@Parameter(name = "orderBy", description = "orderBy", in = ParameterIn.QUERY) @Valid @RequestParam(value = "orderBy", required = false) String orderBy,
			@Parameter(name = "sortOrder", description = "sortOrder", in = ParameterIn.QUERY) @Valid @RequestParam(value = "sortOrder", required = false) String sortOrder);

	/**
	 * GET
	 * /fixeddepositaccounts/{fixedDepositAccountId}/transactions/{transactionId}
	 *
	 * @param fixedDepositAccountId
	 *            (required)
	 * @param transactionId
	 *            (required)
	 * @return default response (status code 200)
	 */
	@GetMapping(value = "/fixeddepositaccounts/{fixedDepositAccountId}/transactions/{transactionId}")
	GetFixedDepositTransactionResponse retrieveOneTransaction(
			@Parameter(name = "fixedDepositAccountId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("fixedDepositAccountId") Long fixedDepositAccountId,
			@Parameter(name = "transactionId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("transactionId") Long transactionId);

	/**
	 * GET /fixeddepositaccounts/{accountId} : Retrieve a fixed deposit
	 * application/account Retrieves a fixed deposit application/account Example
	 * Requests : fixeddepositaccounts/1
	 * fixeddepositaccounts/1?associations&#x3D;all
	 *
	 * @param accountId
	 *            accountId (required)
	 * @param staffInSelectedOfficeOnly
	 *            staffInSelectedOfficeOnly (optional, default to false)
	 * @param chargeStatus
	 *            chargeStatus (optional, default to all)
	 * @return OK (status code 200)
	 */
	@GetMapping(value = "/fixeddepositaccounts/{accountId}")
	GetFixedDepositAccountsAccountIdResponse retrieveOne(
			@Parameter(name = "accountId", description = "accountId", required = true, in = ParameterIn.PATH) @PathVariable("accountId") Long accountId,
			@Parameter(name = "staffInSelectedOfficeOnly", description = "staffInSelectedOfficeOnly", in = ParameterIn.QUERY) @Valid @RequestParam(value = "staffInSelectedOfficeOnly", required = false, defaultValue = "false") Boolean staffInSelectedOfficeOnly,
			@Parameter(name = "chargeStatus", description = "chargeStatus", in = ParameterIn.QUERY) @Valid @RequestParam(value = "chargeStatus", required = false, defaultValue = "all") String chargeStatus,
			@Parameter(name = "associations", description = "associations", in = ParameterIn.QUERY) @Valid @RequestParam(value = "associations", required = false, defaultValue = "all") String associations);

	/**
	 * GET /fixeddepositaccounts/{fixedDepositAccountId}/transactions/template
	 *
	 * @param fixedDepositAccountId
	 *            (required)
	 * @return default response (status code 200)
	 */
	@GetMapping(value = "/fixeddepositaccounts/{fixedDepositAccountId}/transactions/template")
	String retrieveTemplate13(
			@Parameter(name = "fixedDepositAccountId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("fixedDepositAccountId") Long fixedDepositAccountId);

	/**
	 * POST /fixeddepositaccounts : Submit new fixed deposit application Submits a
	 * new fixed deposit applicationMandatory Fields: clientId or groupId,
	 * productId, submittedOnDate, depositAmount, depositPeriod,
	 * depositPeriodFrequencyId Optional Fields: accountNo, externalId,
	 * fieldOfficerId,linkAccountId(if provided initial deposit amount will be
	 * collected from this account),transferInterestToSavings(By enabling this flag
	 * all interest postings will be transferred to linked saving account )
	 *
	 * @param postFixedDepositAccountsRequest
	 *            (required)
	 * @return OK (status code 200)
	 */
	@PostMapping(value = "/fixeddepositaccounts")
	PostSavingsAccountsResponse submitApplication(
			@Parameter(name = "PostFixedDepositAccountsRequest", description = "", required = true) @Valid @RequestBody PostFixedDepositAccountsRequest postFixedDepositAccountsRequest);

	/**
	 * GET /fixeddepositaccounts/template : Retrieve Fixed Deposit Account Template
	 * This is a convenience resource. It can be useful when building maintenance
	 * user interface screens for fixed deposit applications. The template data
	 * returned consists of any or all of: Field Defaults Allowed Value ListsExample
	 * Requests: fixeddepositaccounts/template?clientId&#x3D;1
	 *
	 * @param clientId
	 *            clientId (optional)
	 * @param groupId
	 *            groupId (optional)
	 * @param productId
	 *            productId (optional)
	 * @param staffInSelectedOfficeOnly
	 *            staffInSelectedOfficeOnly (optional, default to false)
	 * @return OK (status code 200)
	 */
	// GetFixedDepositAccountsTemplateResponse
	@GetMapping(value = "/fixeddepositaccounts/template")
	Object retrieveAccountTemplate(@RequestParam(value = "clientId", required = false) Long clientId,
			@RequestParam(value = "groupId", required = false) Long groupId,
			@RequestParam(value = "productId", required = false) Long productId,
			@RequestParam(value = "staffInSelectedOfficeOnly", required = false, defaultValue = "false") Boolean staffInSelectedOfficeOnly);

	/**
	 * POST /fixeddepositaccounts/{fixedDepositAccountId}/transactions
	 *
	 * @param fixedDepositAccountId
	 *            (required)
	 * @param command
	 *            (optional)
	 * @param request
	 *            (optional)
	 * @return default response (status code 200)
	 */
	@PostMapping(value = "/fixeddepositaccounts/{fixedDepositAccountId}/transactions")
	PostFixedDepositTransactionCommandResponse transaction(
			@Parameter(name = "fixedDepositAccountId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("fixedDepositAccountId") Long fixedDepositAccountId,
			@Parameter(name = "command", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "command", required = false) String command,
			@Parameter(name = "body", description = "") @Valid @RequestBody(required = false) FixedDepositTransactionCommandRequest request);

	/**
	 * PUT /fixeddepositaccounts/{accountId} : Modify a fixed deposit application
	 * Fixed deposit application can only be modified when in &#39;Submitted and
	 * pending approval&#39; state. Once the application is approved, the details
	 * cannot be changed using this method. Specific api endpoints will be created
	 * to allow change of interest detail such as rate, compounding period, posting
	 * period etc
	 *
	 * @param accountId
	 *            accountId (required)
	 * @param putFixedDepositAccountsAccountIdRequest
	 *            (required)
	 * @return OK (status code 200)
	 */
	@PutMapping(value = "/fixeddepositaccounts/{accountId}")
	PutFixedDepositAccountsAccountIdResponse updateAccount(
			@Parameter(name = "accountId", description = "accountId", required = true, in = ParameterIn.PATH) @PathVariable("accountId") Long accountId,
			@Parameter(name = "PutFixedDepositAccountsAccountIdRequest", description = "", required = true) @Valid @RequestBody PutFixedDepositAccountsAccountIdRequest putFixedDepositAccountsAccountIdRequest);
}
