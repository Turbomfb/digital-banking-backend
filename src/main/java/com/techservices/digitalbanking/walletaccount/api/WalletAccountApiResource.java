/* (C)2024 */
package com.techservices.digitalbanking.walletaccount.api;

import com.techservices.digitalbanking.core.configuration.security.SpringSecurityAuditorAware;
import com.techservices.digitalbanking.core.domain.dto.AccountDto;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostSavingsAccountsResponse;
import com.techservices.digitalbanking.walletaccount.domain.request.CreateSavingsAccountRequest;
import com.techservices.digitalbanking.walletaccount.domain.response.SavingsInterestBreakdownResponse;
import com.techservices.digitalbanking.walletaccount.service.WalletAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(
    name = "Wallet Account Management",
    description = "Operations for managing customer savings accounts and wallet functionality")
@RequestMapping("api/v1/wallet-accounts")
@RestController
@RequiredArgsConstructor
public class WalletAccountApiResource {
  private final WalletAccountService walletAccountService;
  private final SpringSecurityAuditorAware springSecurityAuditorAware;

  @Operation(
      summary = "Create a new savings account",
      description =
          "Creates a new savings account for the authenticated customer. The customer ID is automatically extracted from the security context.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Savings account created successfully",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = PostSavingsAccountsResponse.class))),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid request data or business validation failure",
            content = @Content(mediaType = "application/json")),
        @ApiResponse(
            responseCode = "401",
            description = "Authentication required",
            content = @Content(mediaType = "application/json")),
        @ApiResponse(
            responseCode = "403",
            description = "Access denied - insufficient permissions",
            content = @Content(mediaType = "application/json")),
        @ApiResponse(
            responseCode = "409",
            description = "Conflict - customer already has a savings account",
            content = @Content(mediaType = "application/json")),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = @Content(mediaType = "application/json"))
      })
  @PostMapping
  public ResponseEntity<PostSavingsAccountsResponse> createSavingsAccount(
      @io.swagger.v3.oas.annotations.parameters.RequestBody(
              description =
                  "Savings account creation request containing account details and preferences",
              required = true,
              content =
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = CreateSavingsAccountRequest.class)))
          @RequestBody
          CreateSavingsAccountRequest createAccountRequest) {

    Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
    createAccountRequest.setClientId(String.valueOf(customerId));
    PostSavingsAccountsResponse postSavingsAccountsResponse =
        walletAccountService.createSavingsAccount(createAccountRequest);

    return ResponseEntity.ok(postSavingsAccountsResponse);
  }

  @Operation(
      summary = "Get customer's savings account details",
      description =
          "Retrieves the savings account information for the currently authenticated customer. Returns comprehensive account details including balance, status, and account metadata.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved savings account details",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AccountDto.class))),
        @ApiResponse(
            responseCode = "401",
            description = "Authentication required",
            content = @Content(mediaType = "application/json")),
        @ApiResponse(
            responseCode = "403",
            description = "Access denied - insufficient permissions",
            content = @Content(mediaType = "application/json")),
        @ApiResponse(
            responseCode = "404",
            description = "Savings account not found for the customer",
            content = @Content(mediaType = "application/json")),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = @Content(mediaType = "application/json"))
      })
  @GetMapping("/me")
  public ResponseEntity<AccountDto> retrieveSavingsAccountById() {

    Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
    return ResponseEntity.ok(walletAccountService.retrieveSavingsAccountById(customerId));
  }

  @Operation(
      summary = "Calculate interest breakdown for savings account",
      description =
          "Calculates and returns a detailed breakdown of interest earnings for the customer's savings account over a specified date range. If no dates are provided, it defaults to a 24-day period starting from today.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully calculated interest breakdown",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = BasePageResponse.class))),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid date range or request parameters",
            content = @Content(mediaType = "application/json")),
        @ApiResponse(
            responseCode = "401",
            description = "Authentication required",
            content = @Content(mediaType = "application/json")),
        @ApiResponse(
            responseCode = "403",
            description = "Access denied - insufficient permissions",
            content = @Content(mediaType = "application/json")),
        @ApiResponse(
            responseCode = "404",
            description = "Savings account not found for the customer",
            content = @Content(mediaType = "application/json")),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = @Content(mediaType = "application/json"))
      })
  @GetMapping("/me/interest-breakdown")
  public ResponseEntity<BasePageResponse<SavingsInterestBreakdownResponse>>
      calculateInterestBreakdown(
          @Parameter(
                  name = "startDate",
                  description =
                      "Start date for interest calculation period (inclusive). Format: YYYY-MM-DD. Defaults to current date if not provided.",
                  schema = @Schema(type = "string", format = "date", example = "2024-01-01"))
              @RequestParam(name = "startDate", required = false)
              LocalDate startDate,
          @Parameter(
                  name = "endDate",
                  description =
                      "End date for interest calculation period (inclusive). Format: YYYY-MM-DD. Defaults to 24 days from start date if not provided.",
                  schema = @Schema(type = "string", format = "date", example = "2024-01-31"))
              @RequestParam(name = "endDate", required = false)
              LocalDate endDate) {

    Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
    startDate = startDate == null ? LocalDate.now() : startDate;
    endDate =
        endDate == null || endDate.equals(LocalDate.now()) ? LocalDate.now().plusDays(24) : endDate;
    return ResponseEntity.ok(
        walletAccountService.calculateInterestBreakdown(customerId, startDate, endDate));
  }
}
