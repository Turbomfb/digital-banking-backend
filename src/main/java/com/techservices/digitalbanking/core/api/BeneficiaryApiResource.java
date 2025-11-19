/* (C)2025 */
package com.techservices.digitalbanking.core.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techservices.digitalbanking.core.configuration.security.SpringSecurityAuditorAware;
import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.core.domain.dto.request.AddBeneficiaryRequest;
import com.techservices.digitalbanking.core.domain.dto.request.DeleteBeneficiaryRequest;
import com.techservices.digitalbanking.core.domain.dto.request.UpdateBeneficiaryRequest;
import com.techservices.digitalbanking.core.domain.dto.response.BeneficiaryListResponse;
import com.techservices.digitalbanking.core.domain.dto.response.BeneficiaryResponse;
import com.techservices.digitalbanking.core.service.BeneficiaryService;

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
import lombok.extern.slf4j.Slf4j;

import static com.techservices.digitalbanking.core.util.CommandUtil.GENERATE_OTP_COMMAND;

@RestController
@RequestMapping("/api/v1/customers/me/beneficiaries")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Beneficiaries", description = "APIs for managing customer beneficiaries including CRUD operations, OTP verification, and search functionality")
@SecurityRequirement(name = "bearerAuth")
public class BeneficiaryApiResource {

	private final BeneficiaryService beneficiaryService;
	private final SpringSecurityAuditorAware springSecurityAuditorAware;

	@GetMapping
	@Operation(summary = "Retrieve all beneficiaries for authenticated customer", description = "Fetches a list of all active beneficiaries associated with the currently authenticated customer")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully retrieved customer beneficiaries", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BeneficiaryListResponse.class))),
			@ApiResponse(responseCode = "401", description = "Authentication required - invalid or missing token", content = @Content),
			@ApiResponse(responseCode = "403", description = "Access forbidden - insufficient permissions", content = @Content)})
	public ResponseEntity<BeneficiaryListResponse> getAllBeneficiaries() {

		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		return ResponseEntity.ok(beneficiaryService.getAllBeneficiaries(customerId));
	}

	@GetMapping("/frequent")
	@Operation(summary = "Retrieve frequently used beneficiaries", description = "Fetches a list of beneficiaries that the customer transacts with most frequently, ordered by transaction frequency")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully retrieved frequent beneficiaries", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BeneficiaryListResponse.class))),
			@ApiResponse(responseCode = "401", description = "Authentication required - invalid or missing token", content = @Content),
			@ApiResponse(responseCode = "403", description = "Access forbidden - insufficient permissions", content = @Content)})
	public ResponseEntity<BeneficiaryListResponse> getFrequentBeneficiaries() {

		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		return ResponseEntity.ok(beneficiaryService.getFrequentBeneficiaries(customerId));
	}

	@GetMapping("/{beneficiaryId}")
	@Operation(summary = "Retrieve specific beneficiary by ID", description = "Fetches detailed information for a specific beneficiary belonging to the authenticated customer")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully retrieved the beneficiary", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BeneficiaryResponse.class))),
			@ApiResponse(responseCode = "401", description = "Authentication required - invalid or missing token", content = @Content),
			@ApiResponse(responseCode = "404", description = "Beneficiary not found or does not belong to the authenticated customer", content = @Content)})
	public ResponseEntity<BeneficiaryResponse> getBeneficiaryById(
			@Parameter(description = "Unique identifier of the beneficiary to retrieve", required = true, example = "12345") @PathVariable Long beneficiaryId) {

		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		return ResponseEntity.ok(beneficiaryService.getBeneficiaryById(beneficiaryId, customerId));
	}

	@PostMapping
	@Operation(summary = "Add new beneficiary", description = "Initiates the process to add a new beneficiary to the customer's list. This is a two-step process: "
			+ "first call generates OTP (command=generate-otp), second call verifies OTP (command=verify-otp) and completes the addition")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OTP generated successfully or beneficiary added successfully after verification", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericApiResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid request body, validation failed, or OTP verification failed", content = @Content),
			@ApiResponse(responseCode = "401", description = "Authentication required - invalid or missing token", content = @Content),
			@ApiResponse(responseCode = "409", description = "Beneficiary already exists for this customer", content = @Content)})
	public ResponseEntity<GenericApiResponse> addBeneficiary(
			@Parameter(description = "Beneficiary details including account information and optional nickname. "
					+ "For OTP generation: provide accountName, accountNumber, bankName, bankCode, and optional nickname. "
					+ "For OTP verification: provide uniqueId and otp received from generation step", required = true, content = @Content(schema = @Schema(implementation = AddBeneficiaryRequest.class))) @RequestBody AddBeneficiaryRequest request,
			@Parameter(description = "Command to execute: 'generate-otp' to initiate OTP generation, 'verify-otp' to complete beneficiary addition with OTP", example = "generate-otp") @RequestParam(value = "command", required = false, defaultValue = GENERATE_OTP_COMMAND) @Valid String command) {

		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		GenericApiResponse response = beneficiaryService.addBeneficiary(request, customerId, command);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PutMapping("/update")
	@Operation(summary = "Update existing beneficiary", description = "Updates beneficiary details such as nickname. This is a two-step process: "
			+ "first call generates OTP (command=generate-otp), second call verifies OTP (command=verify-otp) and completes the update")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OTP generated successfully or beneficiary updated successfully after verification", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericApiResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid request body, validation failed, or OTP verification failed", content = @Content),
			@ApiResponse(responseCode = "401", description = "Authentication required - invalid or missing token", content = @Content),
			@ApiResponse(responseCode = "404", description = "Beneficiary not found or does not belong to the authenticated customer", content = @Content)})
	public ResponseEntity<GenericApiResponse> updateBeneficiary(
			@Parameter(description = "Command to execute: 'generate-otp' to initiate OTP generation, 'verify-otp' to complete beneficiary update with OTP", example = "generate-otp") @RequestParam(value = "command", required = false, defaultValue = GENERATE_OTP_COMMAND) @Valid String command,
			@Parameter(description = "Updated beneficiary information. "
					+ "For OTP generation: provide beneficiaryId and new nickname. "
					+ "For OTP verification: provide uniqueId and otp received from generation step", required = true, content = @Content(schema = @Schema(implementation = UpdateBeneficiaryRequest.class))) @RequestBody UpdateBeneficiaryRequest request) {

		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		return ResponseEntity.ok(beneficiaryService.updateBeneficiary(request, customerId, command));
	}

	@DeleteMapping("/delete")
	@Operation(summary = "Delete beneficiary", description = "Removes a beneficiary from the customer's list. This is a two-step process: "
			+ "first call generates OTP (command=generate-otp), second call verifies OTP (command=verify-otp) and completes the deletion")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Beneficiary successfully deleted after OTP verification", content = @Content),
			@ApiResponse(responseCode = "200", description = "OTP generated successfully for deletion verification", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericApiResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid request, validation failed, or OTP verification failed", content = @Content),
			@ApiResponse(responseCode = "401", description = "Authentication required - invalid or missing token", content = @Content),
			@ApiResponse(responseCode = "404", description = "Beneficiary not found or does not belong to the authenticated customer", content = @Content)})
	public ResponseEntity<Void> deleteBeneficiary(@Parameter(description = "Deletion request details. "
			+ "For OTP generation: provide beneficiaryId. "
			+ "For OTP verification: provide uniqueId and otp received from generation step", required = true) @RequestParam DeleteBeneficiaryRequest deleteBeneficiaryRequest,
			@Parameter(description = "Command to execute: 'generate-otp' to initiate OTP generation, 'verify-otp' to complete beneficiary deletion with OTP", example = "generate-otp") @RequestParam(value = "command", required = false, defaultValue = GENERATE_OTP_COMMAND) @Valid String command) {

		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		beneficiaryService.deleteBeneficiary(customerId, command, deleteBeneficiaryRequest);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/search")
	@Operation(summary = "Search beneficiaries", description = "Searches through the customer's beneficiaries using a search term that matches against account number, nickname, or account name. Returns all matching beneficiaries")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully retrieved matching beneficiaries (may be empty if no matches found)", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BeneficiaryListResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid search term provided", content = @Content),
			@ApiResponse(responseCode = "401", description = "Authentication required - invalid or missing token", content = @Content)})
	public ResponseEntity<BeneficiaryListResponse> searchBeneficiaries(
			@Parameter(description = "Search term to match against account number, nickname, or account name (case-insensitive, partial matches supported)", required = true, example = "John") @RequestParam String searchTerm) {

		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		log.info("GET /api/v1/customers/me/beneficiaries/search?searchTerm={} - customerId: {}", searchTerm,
				customerId);
		return ResponseEntity.ok(beneficiaryService.searchBeneficiaries(customerId, searchTerm));
	}
}
