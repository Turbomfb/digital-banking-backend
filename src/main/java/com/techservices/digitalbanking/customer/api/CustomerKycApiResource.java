/* (C)2024 */
package com.techservices.digitalbanking.customer.api;

import com.techservices.digitalbanking.core.configuration.security.SpringSecurityAuditorAware;
import com.techservices.digitalbanking.core.domain.BaseAppResponse;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.KycTierDto;
import com.techservices.digitalbanking.customer.domian.dto.request.CustomerKycRequest;
import com.techservices.digitalbanking.customer.service.CustomerKycService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
    name = "Customer KYC Management",
    description =
        "APIs for managing customer Know Your Customer (KYC) information and tier management")
@RequestMapping("api/v1")
@RestController
@RequiredArgsConstructor
public class CustomerKycApiResource {

  private final CustomerKycService customerKycService;
  private final String GENERATE_OTP = "";
  private final SpringSecurityAuditorAware springSecurityAuditorAware;

  @Operation(
      summary = "Update Customer KYC Information",
      description =
          "Updates the KYC (Know Your Customer) information for the authenticated customer. This endpoint allows customers to submit additional documentation and information to upgrade their account tier and increase transaction limits. The system may generate an OTP for verification purposes.",
      requestBody =
          @io.swagger.v3.oas.annotations.parameters.RequestBody(
              description =
                  "Customer KYC update request containing verification documents and information",
              required = true,
              content =
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = CustomerKycRequest.class))))
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "KYC information updated successfully",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = BaseAppResponse.class))),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid KYC request data or validation errors",
            content = @Content(mediaType = "application/json")),
        @ApiResponse(
            responseCode = "401",
            description = "Authentication required - user must be logged in",
            content = @Content(mediaType = "application/json")),
        @ApiResponse(
            responseCode = "422",
            description = "KYC documents are invalid or incomplete",
            content = @Content(mediaType = "application/json")),
        @ApiResponse(
            responseCode = "409",
            description =
                "KYC update request already in progress or customer already at maximum tier",
            content = @Content(mediaType = "application/json"))
      })
  @SecurityRequirement(name = "bearerAuth")
  @PostMapping("customers/me/kyc")
  public ResponseEntity<BaseAppResponse> updateCustomerKyc(
      @Parameter(
              description =
                  "KYC update request containing customer verification documents and information",
              required = true)
          @Validated
          @RequestBody
          CustomerKycRequest customerKycRequest,
      @Parameter(
              description =
                  "Command to execute during KYC update process. When not specified, OTP generation is skipped.",
              example = "generate-otp",
              schema =
                  @Schema(
                      allowableValues = {"generate-otp", "verify-otp", "skip-verification"},
                      defaultValue = ""))
          @RequestParam(name = "command", required = false, defaultValue = GENERATE_OTP)
          String command) {

    Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
    BaseAppResponse postClientsResponse =
        customerKycService.updateCustomerKyc(customerKycRequest, customerId, command);

    return new ResponseEntity<>(postClientsResponse, HttpStatus.OK);
  }

  @Operation(
      summary = "Retrieve all KYC Tiers",
      description =
          "Retrieves all available KYC tiers in the system along with their requirements, limits, and benefits. This endpoint provides information about different customer verification levels and what each tier allows customers to do within the banking system.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "KYC tiers retrieved successfully",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = BasePageResponse.class))),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error while retrieving KYC tiers",
            content = @Content(mediaType = "application/json"))
      })
  @GetMapping("kyc-tiers")
  public ResponseEntity<BasePageResponse<KycTierDto>> retrieveAllKycTier() {

    BasePageResponse<KycTierDto> postClientsResponse = customerKycService.retrieveAllKycTier();

    return new ResponseEntity<>(postClientsResponse, HttpStatus.OK);
  }
}
