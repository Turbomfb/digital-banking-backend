/*
 * Copyright (c) 2025 Techservice Engineering Team.
 * All rights reserved.
 *
 * This software is proprietary and confidential. It may not be reproduced,
 * distributed, or transmitted in any form or by any means, including photocopying,
 * recording, or other electronic or mechanical methods, without the prior written
 * permission of Techservice Engineering Team.
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 *
 * For any questions regarding this license, please contact:
 * Techservice Engineering Team
 * Email: engineering@techservice.com
 */ 
package com.techservices.digitalbanking.authentication.api;
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
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.techservices.digitalbanking.core.util.CommandUtil.GENERATE_OTP_COMMAND;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationApiResource {
    private final AuthenticationService authenticationService;
    private final SpringSecurityAuditorAware springSecurityAuditorAware;

    @Operation(summary = "Authenticate User")
    @PostMapping()
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestHeader(value = "User-Agent", required = false) String userAgent,
            @RequestBody AuthenticationRequest postAuthenticationRequest,
            @RequestParam (value = "customerType", required = false, defaultValue = "RETAIL") UserType customerType,
            HttpServletRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(postAuthenticationRequest, customerType, userAgent, request));
    }

    @Operation(summary = "Logout User")
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



    @Operation(summary = "Create Password")
    @PostMapping("/create-password")
    public ResponseEntity<GenericApiResponse> createPassword(
            @RequestBody PasswordMgtRequest passwordMgtRequest
    ) {
        return ResponseEntity.ok(authenticationService.createPassword(passwordMgtRequest));
    }


    @Operation(summary = "Forgot Password")
    @PostMapping("/forgot-password")
    public ResponseEntity<GenericApiResponse> forgotPassword(
            @RequestBody PasswordMgtRequest passwordMgtRequest,
            @RequestParam (value = "command", required = false, defaultValue = GENERATE_OTP_COMMAND) String command,
            @RequestParam (value = "customerType", required = false, defaultValue = "RETAIL") UserType customerType
    ) {
        return ResponseEntity.ok(authenticationService.forgotPassword(passwordMgtRequest, command, customerType));
    }

    @Operation(summary = "Change Password")
    @PostMapping("/me/change-password")
    public ResponseEntity<GenericApiResponse> changePassword(
            @RequestBody PasswordMgtRequest passwordMgtRequest
    ) {
        Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
        passwordMgtRequest.setCustomerId(customerId);
        return ResponseEntity.ok(authenticationService.changePassword(passwordMgtRequest));
    }

    @Operation(summary = "Change Password")
    @PostMapping("/me/change-pin")
    public ResponseEntity<GenericApiResponse> changeTransactionPin(
            @RequestBody CustomerTransactionPinRequest pinRequest
    ) {
        Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
        pinRequest.setCustomerId(customerId);
        return ResponseEntity.ok(authenticationService.changeTransactionPin(pinRequest));
    }

    @Operation(summary = "Retrieve User Login Activities")
    @GetMapping("/me/login-activities")
    public ResponseEntity<BasePageResponse<UserLoginActivity>> retrieveUserLoginActivities() {
        Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
        return ResponseEntity.ok(authenticationService.retrieveUserLoginActivities(customerId));
    }

}
