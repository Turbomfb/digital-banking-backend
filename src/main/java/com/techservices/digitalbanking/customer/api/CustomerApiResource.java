/* (C)2024 */
package com.techservices.digitalbanking.customer.api;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techservices.digitalbanking.common.domain.enums.UserType;
import com.techservices.digitalbanking.core.configuration.security.SpringSecurityAuditorAware;
import com.techservices.digitalbanking.core.domain.BaseAppResponse;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.core.domain.dto.response.BusinessDataResponse;
import com.techservices.digitalbanking.core.domain.dto.response.IdentityVerificationResponse;
import com.techservices.digitalbanking.core.domain.enums.IdentityVerificationDataType;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.core.service.IdentityVerificationService;
import com.techservices.digitalbanking.customer.domian.dto.request.*;
import com.techservices.digitalbanking.customer.domian.dto.response.CustomerDashboardResponse;
import com.techservices.digitalbanking.customer.domian.dto.response.CustomerDtoResponse;
import com.techservices.digitalbanking.customer.service.CustomerService;
import com.techservices.digitalbanking.loan.domain.response.LoanOfferResponse;
import com.techservices.digitalbanking.loan.service.LoanApplicationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "Customer Management", description = "APIs for managing customer accounts, profiles, and related operations")
@RequestMapping("api/v1/customers")
@RestController
@RequiredArgsConstructor
@Slf4j
public class CustomerApiResource {

	private final CustomerService customerService;
	private final LoanApplicationService loanApplicationService;
	private final SpringSecurityAuditorAware springSecurityAuditorAware;
	private final IdentityVerificationService identityVerificationService;

	@Operation(summary = "Create a New Customer Account", description = "Creates a new customer account in the digital banking system. Supports both retail and corporate customer types with optional OTP generation.", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Customer creation request payload", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateCustomerRequest.class))))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Customer account created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseAppResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid request parameters or validation errors", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "409", description = "Customer already exists with provided email or phone number", content = @Content(mediaType = "application/json"))})
	@PostMapping
	public ResponseEntity<BaseAppResponse> createMerchantAccount(
			@Parameter(description = "Customer creation request details", required = true) @Validated @RequestBody CreateCustomerRequest createCustomerRequest,
			@Parameter(description = "Command to execute during customer creation", example = "generate-otp", schema = @Schema(allowableValues = {
					"generate-otp",
					"verify-otp"})) @RequestParam(name = "command", required = false, defaultValue = "generate-otp") String command,
			@Parameter(description = "Type of customer account to create", example = "RETAIL", schema = @Schema(implementation = UserType.class)) @RequestParam(name = "customerType", required = false, defaultValue = "RETAIL") UserType customerType) {

		BaseAppResponse postClientsResponse = customerService.createCustomer(createCustomerRequest, command,
				customerType);
		return new ResponseEntity<>(postClientsResponse, HttpStatusCode.valueOf(201));
	}

	@Operation(summary = "Retrieve Identity Verification Data", description = "Fetches identity verification data using NIN (National Identification Number) or BVN (Bank Verification Number). Returns personal information for verification purposes.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Identity data retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = IdentityVerificationResponse.IdentityVerificationResponseData.class))),
			@ApiResponse(responseCode = "400", description = "Invalid identity type or identity value format", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Identity data not found for the provided value", content = @Content(mediaType = "application/json"))})
	@GetMapping("identity-verification")
	public ResponseEntity<IdentityVerificationResponse.IdentityVerificationResponseData> retrieveIdentityData(
			@Parameter(description = "Type of identity verification to perform", required = true, example = "NIN", schema = @Schema(implementation = IdentityVerificationDataType.class)) @RequestParam(name = "identityType") IdentityVerificationDataType identityType,
			@Parameter(description = "Identity verification value (NIN or BVN number)", required = true, example = "12345678901") @RequestParam(name = "identityValue") String identityValue) {

		try {
			IdentityVerificationResponse identityResponse;
			if (identityType == IdentityVerificationDataType.NIN) {
				identityResponse = identityVerificationService.retrieveNinData(identityValue);
			} else if (identityType == IdentityVerificationDataType.BVN) {
				identityResponse = identityVerificationService.retrieveBvnData(identityValue);
			} else {
				log.error("Unsupported identity type: {}", identityType);
				throw new ValidationException("validation.error.exists", "Unsupported identity type: " + identityType);
			}
			identityResponse.getData().setImage(null);
			return new ResponseEntity<>(identityResponse.getData(), HttpStatusCode.valueOf(200));
		} catch (Exception e) {
			log.error("Error retrieving identity data: {}", e.getMessage());
			throw new ValidationException("validation.error.exists",
					"We’re unable to retrieve your identity details at the moment. Please check your BVN/NIN and try again, or try later.",
					e.getMessage());
		}
	}

	@Operation(summary = "Retrieve Business Identity Verification Data", description = "Fetches business identity verification data using RC Number (Registration Certificate) or TIN (Tax Identification Number). Used for corporate customer verification.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Business identity data retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessDataResponse.BusinessData.class))),
			@ApiResponse(responseCode = "400", description = "Either RC Number or TIN must be provided", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Business data not found for the provided identifier", content = @Content(mediaType = "application/json"))})
	@GetMapping("business-identity-verification")
	public ResponseEntity<BusinessDataResponse.BusinessData> retrieveIdentityData(
			@Parameter(description = "Business Registration Certificate Number", example = "RC123456") @RequestParam(name = "rcNumber", required = false) String rcNumber,
			@Parameter(description = "Tax Identification Number", example = "12345678-0001") @RequestParam(name = "tin", required = false) String tin) {

		BusinessDataResponse businessDataResponse = null;

		if (StringUtils.isNotBlank(rcNumber)) {
			businessDataResponse = identityVerificationService.retrieveRcNumberData(rcNumber);
		} else if (StringUtils.isNotBlank(tin)) {
			businessDataResponse = identityVerificationService.retrieveTinData(tin);
		} else
			throw new ValidationException("validation.error.exists", "No Business Identity Data found");
		return new ResponseEntity<>(businessDataResponse.getData(), HttpStatusCode.valueOf(200));
	}

	@Operation(summary = "Update Customer Profile", description = "Updates an existing customer's profile information. Requires authentication and appropriate permissions.", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Customer update request payload", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerUpdateRequest.class))))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Customer profile updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerDtoResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid request parameters or validation errors", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Customer not found with the specified ID", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "403", description = "Insufficient permissions to update this customer", content = @Content(mediaType = "application/json"))})
	@SecurityRequirement(name = "bearerAuth")
	@PutMapping("{customerId}")
	public ResponseEntity<CustomerDtoResponse> updateCustomer(
			@Parameter(description = "Customer update request details", required = true) @Validated @RequestBody CustomerUpdateRequest customerUpdateRequest,
			@Parameter(description = "Unique identifier of the customer to update", required = true, example = "12345") @PathVariable Long customerId) {

		CustomerDtoResponse customerDtoResponse = CustomerDtoResponse
				.parse(customerService.updateCustomer(customerUpdateRequest, customerId, null, true));
		return ResponseEntity.ok(customerDtoResponse);
	}

	@Operation(summary = "Get Current Customer Profile", description = "Retrieves the profile information of the currently authenticated customer, including loan pre-qualification status.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Customer profile retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerDtoResponse.class))),
			@ApiResponse(responseCode = "401", description = "Authentication required", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Customer profile not found", content = @Content(mediaType = "application/json"))})
	@SecurityRequirement(name = "bearerAuth")
	@GetMapping("me")
	public ResponseEntity<CustomerDtoResponse> getCustomerById() {

		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		CustomerDtoResponse response = CustomerDtoResponse.parse(customerService.getCustomerById(customerId));
		BasePageResponse<LoanOfferResponse> offers = null;
		try {
			offers = loanApplicationService.retrieveCustomerLoanOffers(customerId);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			response.setPreQualifiedForLoan(false);
		}
		response.setPreQualifiedForLoan(offers != null && !offers.getData().isEmpty());
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "Get All Customers", description = "Retrieves a paginated list of all customers in the system. This endpoint is typically used by administrators or staff members.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Customers retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BasePageResponse.class))),
			@ApiResponse(responseCode = "401", description = "Authentication required", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "403", description = "Insufficient permissions to access customer list", content = @Content(mediaType = "application/json"))})
	@SecurityRequirement(name = "bearerAuth")
	@GetMapping
	public ResponseEntity<BasePageResponse<CustomerDtoResponse>> getCustomers(
			@Parameter(description = "Pagination parameters (page, size, sort)", example = "page=0&size=20&sort=firstName,asc") Pageable pageable) {

		return ResponseEntity.ok(customerService.getAllCustomers(pageable));
	}

	@Operation(summary = "Get Customer Dashboard", description = "Retrieves comprehensive dashboard information for the authenticated customer, including account balances, recent transactions, and financial summary.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Dashboard data retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerDashboardResponse.class))),
			@ApiResponse(responseCode = "401", description = "Authentication required", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Customer dashboard data not found", content = @Content(mediaType = "application/json"))})
	@SecurityRequirement(name = "bearerAuth")
	@GetMapping("me/dashboard")
	public ResponseEntity<CustomerDashboardResponse> retrieveCustomerDashboard() {

		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		return ResponseEntity.ok(customerService.retrieveCustomerDashboard(customerId));
	}

	@Operation(summary = "Create Transaction PIN", description = "Creates or updates a transaction PIN for the specified customer. This PIN is used for authorizing financial transactions.", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Transaction PIN creation request", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerTransactionPinRequest.class))))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Transaction PIN created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericApiResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid PIN format or PIN confirmation mismatch", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Customer not found", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "403", description = "Insufficient permissions to create PIN for this customer", content = @Content(mediaType = "application/json"))})
	@SecurityRequirement(name = "bearerAuth")
	@PostMapping("{customerId}/transaction-pin")
	public ResponseEntity<GenericApiResponse> createTransactionPin(
			@Parameter(description = "Transaction PIN creation request details", required = true) @RequestBody CustomerTransactionPinRequest customerTransactionPinRequest,
			@Parameter(description = "Unique identifier of the customer", required = true, example = "12345") @PathVariable Long customerId) {

		GenericApiResponse genericApiResponse = customerService.createTransactionPin(customerId,
				customerTransactionPinRequest);
		return ResponseEntity.ok(genericApiResponse);
	}

	@Operation(summary = "Close Customer Account", description = "Initiates the account closure process for the authenticated customer. This action may require additional verification steps and cannot be undone.", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Account closure request details", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerAccountClosureRequest.class))))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Account closure request processed successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericApiResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid closure request or outstanding obligations", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "401", description = "Authentication required", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "409", description = "Account cannot be closed due to pending transactions or active services", content = @Content(mediaType = "application/json"))})
	@SecurityRequirement(name = "bearerAuth")
	@PostMapping("me/close-account")
	public ResponseEntity<GenericApiResponse> closeAccount(
			@Parameter(description = "Account closure request details", required = true) @RequestBody CustomerAccountClosureRequest request) {

		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		GenericApiResponse genericApiResponse = customerService.closeAccount(customerId, request);
		return ResponseEntity.ok(genericApiResponse);
	}

	@PostMapping("me/activate-account")
	public ResponseEntity<GenericApiResponse> activateAccount(@RequestBody CustomerAccountActivationRequest request,
			@RequestParam UserType userType) {

		GenericApiResponse genericApiResponse = customerService.activateAccount(request, userType);
		return ResponseEntity.ok(genericApiResponse);
	}
}
