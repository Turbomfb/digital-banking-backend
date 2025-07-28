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
import com.techservices.digitalbanking.authentication.domain.request.AuthenticationRequest;
import com.techservices.digitalbanking.authentication.domain.request.PasswordMgtRequest;
import com.techservices.digitalbanking.authentication.domain.response.AuthenticationResponse;
import com.techservices.digitalbanking.authentication.service.AuthenticationService;
import com.techservices.digitalbanking.common.domain.enums.UserType;
import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.techservices.digitalbanking.core.util.CommandUtil.GENERATE_OTP_COMMAND;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationApiResource {
    private final AuthenticationService authenticationService;

    @Operation(summary = "Authenticate User")
    @PostMapping()
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest postAuthenticationRequest,
            @RequestParam (value = "customerType", required = false, defaultValue = "RETAIL") UserType customerType
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(postAuthenticationRequest, customerType));
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

}
