/* (C)2024 */
package com.techservices.digitalbanking.walletaccount.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techservices.digitalbanking.core.domain.dto.response.BankDataResponse;
import com.techservices.digitalbanking.core.service.BankDataService;
import com.techservices.digitalbanking.walletaccount.domain.request.NameEnquiryRequest;
import com.techservices.digitalbanking.walletaccount.domain.response.NameEnquiryResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "Bank Data Services", description = "API for retrieving financial institution data and performing account verification services")
@RequestMapping("api/v1/bank-data")
@RestController
@RequiredArgsConstructor
@Slf4j
public class BankDataApiResource {
	private final BankDataService bankDataService;

	@Operation(summary = "Retrieve all bank data", description = "Fetches comprehensive information about all registered financial institutions including "
			+ "bank codes, names, branch details, routing information, and supported services. "
			+ "This data is essential for payment processing, account transfers, and financial institution selection "
			+ "in customer-facing applications.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully retrieved bank data information", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BankDataResponse.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error occurred while retrieving bank data", content = @Content(schema = @Schema(hidden = true))),
			@ApiResponse(responseCode = "503", description = "Bank data service temporarily unavailable", content = @Content(schema = @Schema(hidden = true)))})
	@GetMapping
	public ResponseEntity<BankDataResponse> retrieveBankData() {

		return ResponseEntity.ok(bankDataService.retrieveAllBanks());
	}

	@Operation(summary = "Process account name enquiry", description = "Performs real-time account verification by validating account details with the respective "
			+ "financial institution and retrieving the account holder's name. "
			+ "This service is crucial for preventing payment errors, ensuring transaction accuracy, "
			+ "and providing confirmation before executing financial transfers. "
			+ "The enquiry process validates account number format, checks account status, and returns "
			+ "verified account holder information for secure transaction processing.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Account name enquiry completed successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = NameEnquiryResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid request parameters - missing or malformed account number, invalid bank code, or unsupported account type", content = @Content(schema = @Schema(hidden = true))),
			@ApiResponse(responseCode = "404", description = "Account not found or bank code not recognized", content = @Content(schema = @Schema(hidden = true))),
			@ApiResponse(responseCode = "422", description = "Account verification failed due to inactive account status, restricted account, or validation errors", content = @Content(schema = @Schema(hidden = true))),
			@ApiResponse(responseCode = "500", description = "Internal server error occurred during name enquiry processing", content = @Content(schema = @Schema(hidden = true))),
			@ApiResponse(responseCode = "502", description = "External bank service error - unable to connect to financial institution's verification service", content = @Content(schema = @Schema(hidden = true))),
			@ApiResponse(responseCode = "504", description = "Name enquiry request timeout - financial institution did not respond within acceptable timeframe", content = @Content(schema = @Schema(hidden = true)))})
	@PostMapping("/name-enquiry")
	public ResponseEntity<NameEnquiryResponse> processNameEnquiry(
			@Parameter(description = "Account verification request containing the target account number, "
					+ "destination bank code, and optional transaction reference. "
					+ "Account number should be valid format for the specified bank, "
					+ "and bank code must correspond to a supported financial institution. "
					+ "All fields should be properly formatted and comply with banking standards.", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = NameEnquiryRequest.class))) @RequestBody NameEnquiryRequest request) {

		return ResponseEntity.ok(bankDataService.processNameEnquiry(request));
	}
}
