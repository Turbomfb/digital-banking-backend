package com.techservices.digitalbanking.core.api;

import com.techservices.digitalbanking.core.configuration.security.SpringSecurityAuditorAware;
import com.techservices.digitalbanking.core.domain.dto.request.AddBeneficiaryRequest;
import com.techservices.digitalbanking.core.domain.dto.request.UpdateBeneficiaryRequest;
import com.techservices.digitalbanking.core.domain.dto.response.BeneficiaryListResponse;
import com.techservices.digitalbanking.core.domain.dto.response.BeneficiaryResponse;
import com.techservices.digitalbanking.core.service.BeneficiaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers/me/beneficiaries")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Beneficiaries", description = "Beneficiary management endpoints")
@SecurityRequirement(name = "bearerAuth")
public class BeneficiaryApiResource {

  private final BeneficiaryService beneficiaryService;
  private final SpringSecurityAuditorAware springSecurityAuditorAware;

  @GetMapping
  @Operation(summary = "Get all beneficiaries", description = "Retrieve all active beneficiaries for the authenticated customer")
  public ResponseEntity<BeneficiaryListResponse> getAllBeneficiaries() {
    Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
    return ResponseEntity.ok(beneficiaryService.getAllBeneficiaries(customerId));
  }

  @GetMapping("/frequent")
  @Operation(summary = "Get frequent beneficiaries", description = "Retrieve frequently used beneficiaries")
  public ResponseEntity<BeneficiaryListResponse> getFrequentBeneficiaries() {
    Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
    return ResponseEntity.ok(beneficiaryService.getFrequentBeneficiaries(customerId));
  }

  @GetMapping("/{beneficiaryId}")
  @Operation(summary = "Get beneficiary by ID", description = "Retrieve a specific beneficiary by ID")
  public ResponseEntity<BeneficiaryResponse> getBeneficiaryById(
      @PathVariable Long beneficiaryId) {
    Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
    return ResponseEntity.ok(beneficiaryService.getBeneficiaryById(beneficiaryId, customerId));
  }

  @PostMapping
  @Operation(summary = "Add beneficiary", description = "Add a new beneficiary to the customer's list")
  public ResponseEntity<BeneficiaryResponse> addBeneficiary(
      @RequestBody AddBeneficiaryRequest request) {
    Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
    BeneficiaryResponse response = beneficiaryService.addBeneficiary(request, customerId);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PutMapping("/{beneficiaryId}")
  @Operation(summary = "Update beneficiary", description = "Update beneficiary details (e.g., nickname)")
  public ResponseEntity<BeneficiaryResponse> updateBeneficiary(
      @PathVariable Long beneficiaryId,
      @RequestBody UpdateBeneficiaryRequest request) {
    Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
    return ResponseEntity.ok(beneficiaryService.updateBeneficiary(beneficiaryId, request, customerId));
  }

  @DeleteMapping("/{beneficiaryId}")
  @Operation(summary = "Delete beneficiary", description = "Remove a beneficiary from the customer's list")
  public ResponseEntity<Void> deleteBeneficiary(
      @PathVariable Long beneficiaryId) {
    Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
    beneficiaryService.deleteBeneficiary(beneficiaryId, customerId);
    return ResponseEntity.noContent().build();
  }
}