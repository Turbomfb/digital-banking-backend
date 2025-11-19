/* (C)2025 */
package com.techservices.digitalbanking.authentication.api;

import static com.techservices.digitalbanking.core.util.CommandUtil.GENERATE_OTP_COMMAND;

import com.techservices.digitalbanking.authentication.domain.data.model.UserLoginActivity;
import com.techservices.digitalbanking.authentication.domain.request.AuthenticationRequest;
import com.techservices.digitalbanking.authentication.domain.request.PasswordMgtRequest;
import com.techservices.digitalbanking.authentication.domain.response.AuthenticationResponse;
import com.techservices.digitalbanking.authentication.service.AuthenticationService;
import com.techservices.digitalbanking.common.domain.enums.UserType;
import com.techservices.digitalbanking.core.configuration.security.SpringSecurityAuditorAware;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.customer.domian.dto.request.CustomerTransactionPinRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(
    name = "Authentication & Security",
    description =
        "APIs for user authentication, password management, and security-related operations")
public class AuthenticationApiResource {
  private final AuthenticationService authenticationService;
  private final SpringSecurityAuditorAware springSecurityAuditorAware;

  @Operation(
      summary = "Authenticate user",
      description =
          "Authenticates a user (RETAIL or CORPORATE) with their credentials and returns authentication tokens and user information")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Authentication successful - returns access token and user details",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AuthenticationResponse.class))),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid request - missing or malformed authentication data",
            content = @Content),
        @ApiResponse(
            responseCode = "401",
            description = "Authentication failed - invalid credentials",
            content = @Content),
        @ApiResponse(
            responseCode = "403",
            description = "Account locked or suspended",
            content = @Content)
      })
  @PostMapping()
  public ResponseEntity<AuthenticationResponse> authenticate(
      @Parameter(
              description = "User-Agent header from the client browser/application",
              required = false)
          @RequestHeader(value = "User-Agent", required = false)
          String userAgent,
      @Parameter(
              description = "Authentication credentials including username/email and password",
              required = true,
              content = @Content(schema = @Schema(implementation = AuthenticationRequest.class)))
          @RequestBody
          AuthenticationRequest postAuthenticationRequest,
      @Parameter(
              description =
                  "Type of customer account - RETAIL for individual customers, CORPORATE for business accounts",
              schema = @Schema(allowableValues = {"RETAIL", "CORPORATE"}))
          @RequestParam(value = "customerType", required = false, defaultValue = "RETAIL")
          UserType customerType,
      HttpServletRequest request) {

    return ResponseEntity.ok(
        authenticationService.authenticate(
            postAuthenticationRequest, customerType, userAgent, request));
  }

  @Operation(
      summary = "Logout user",
      description =
          "Logs out the authenticated user by invalidating their session and clearing security context")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully logged out",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Map.class))),
        @ApiResponse(
            responseCode = "400",
            description = "No authentication token provided",
            content = @Content),
        @ApiResponse(
            responseCode = "401",
            description = "Invalid or expired token",
            content = @Content)
      })
  @SecurityRequirement(name = "bearerAuth")
  @PostMapping("/logout")
  public ResponseEntity<Map<String, String>> logout(HttpServletRequest request) {

    String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      return ResponseEntity.badRequest().body(Map.of("message", "No token provided"));
    }

    String token = authHeader.substring(7);

    if (!authenticationService.isTokenValid(token)) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
          .body(Map.of("message", "Invalid or expired token"));
    }

    Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
    authenticationService.logout(customerId);
    SecurityContextHolder.clearContext();
    return ResponseEntity.ok(Map.of("message", "Successfully logged out"));
  }

  @Operation(
      summary = "Create new password",
      description =
          "Creates a new password for a user account, typically used during account setup or password reset completion")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Password created successfully",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = GenericApiResponse.class))),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid password format or missing required fields",
            content = @Content),
        @ApiResponse(
            responseCode = "409",
            description = "Password already exists for this account",
            content = @Content)
      })
  @PostMapping("/create-password")
  public ResponseEntity<GenericApiResponse> createPassword(
      @Parameter(
              description =
                  "Password creation request containing user identification and new password",
              required = true,
              content = @Content(schema = @Schema(implementation = PasswordMgtRequest.class)))
          @RequestBody
          PasswordMgtRequest passwordMgtRequest) {

    return ResponseEntity.ok(authenticationService.createPassword(passwordMgtRequest));
  }

  @Operation(
      summary = "Initiate password reset",
      description =
          "Initiates the forgot password process by generating and sending an OTP to the user's registered contact method")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Password reset initiated - OTP sent successfully",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = GenericApiResponse.class))),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid request or user identification not found",
            content = @Content),
        @ApiResponse(
            responseCode = "404",
            description = "User account not found",
            content = @Content),
        @ApiResponse(
            responseCode = "429",
            description = "Too many password reset attempts - rate limit exceeded",
            content = @Content)
      })
  @PostMapping("/forgot-password")
  public ResponseEntity<GenericApiResponse> forgotPassword(
      @Parameter(
              description =
                  "Password reset request containing user identification (email/phone/username)",
              required = true,
              content = @Content(schema = @Schema(implementation = PasswordMgtRequest.class)))
          @RequestBody
          PasswordMgtRequest passwordMgtRequest,
      @Parameter(
              description = "Command type for the operation - defaults to generating OTP",
              schema = @Schema(defaultValue = "GENERATE_OTP"))
          @RequestParam(value = "command", required = false, defaultValue = GENERATE_OTP_COMMAND)
          String command,
      @Parameter(
              description =
                  "Type of customer account - RETAIL for individual customers, CORPORATE for business accounts",
              schema = @Schema(allowableValues = {"RETAIL", "CORPORATE"}))
          @RequestParam(value = "customerType", required = false, defaultValue = "RETAIL")
          UserType customerType) {

    return ResponseEntity.ok(
        authenticationService.forgotPassword(passwordMgtRequest, command, customerType));
  }

  @Operation(
      summary = "Change password",
      description =
          "Allows authenticated users to change their current password by providing old and new passwords")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Password changed successfully",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = GenericApiResponse.class))),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid password format or current password incorrect",
            content = @Content),
        @ApiResponse(
            responseCode = "401",
            description = "Authentication required - invalid or missing token",
            content = @Content)
      })
  @SecurityRequirement(name = "bearerAuth")
  @PostMapping("/me/change-password")
  public ResponseEntity<GenericApiResponse> changePassword(
      @Parameter(
              description = "Password change request containing current password and new password",
              required = true,
              content = @Content(schema = @Schema(implementation = PasswordMgtRequest.class)))
          @RequestBody
          PasswordMgtRequest passwordMgtRequest) {

    Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
    passwordMgtRequest.setCustomerId(customerId);
    return ResponseEntity.ok(authenticationService.changePassword(passwordMgtRequest));
  }

  @Operation(
      summary = "Change transaction PIN",
      description =
          "Allows authenticated users to change their transaction PIN for secure financial operations")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Transaction PIN changed successfully",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = GenericApiResponse.class))),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid PIN format or current PIN incorrect",
            content = @Content),
        @ApiResponse(
            responseCode = "401",
            description = "Authentication required - invalid or missing token",
            content = @Content)
      })
  @SecurityRequirement(name = "bearerAuth")
  @PostMapping("/me/change-pin")
  public ResponseEntity<GenericApiResponse> changeTransactionPin(
      @Parameter(
              description = "Transaction PIN change request containing current PIN and new PIN",
              required = true,
              content =
                  @Content(schema = @Schema(implementation = CustomerTransactionPinRequest.class)))
          @RequestBody
          CustomerTransactionPinRequest pinRequest) {

    Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
    pinRequest.setCustomerId(customerId);
    return ResponseEntity.ok(authenticationService.changeTransactionPin(pinRequest));
  }

  @Operation(
      summary = "Retrieve user login activities",
      description =
          "Fetches a paginated list of login activities and session history for the authenticated user for security monitoring")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved login activities",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = BasePageResponse.class))),
        @ApiResponse(
            responseCode = "401",
            description = "Authentication required - invalid or missing token",
            content = @Content)
      })
  @SecurityRequirement(name = "bearerAuth")
  @GetMapping("/me/login-activities")
  public ResponseEntity<BasePageResponse<UserLoginActivity>> retrieveUserLoginActivities() {

    Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
    return ResponseEntity.ok(authenticationService.retrieveUserLoginActivities(customerId));
  }
}
