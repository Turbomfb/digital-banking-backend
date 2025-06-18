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
package com.techservices.digitalbanking.authentication.service.impl;

import com.techservices.digitalbanking.authentication.domain.request.AuthenticationRequest;
import com.techservices.digitalbanking.authentication.domain.response.AuthenticationResponse;
import com.techservices.digitalbanking.authentication.service.AuthenticationService;
import com.techservices.digitalbanking.core.configuration.security.JwtUtil;
import com.techservices.digitalbanking.core.domain.model.AppUser;
import com.techservices.digitalbanking.core.exception.UnAuthenticatedUserException;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import com.techservices.digitalbanking.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final long DEFAULT_INVITATION_TOKEN_EXPIRATION_MS = 3600000; // 1 hour

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final CustomerService customerService;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest postAuthenticationRequest) {
        Map<String, Object> claims = new HashMap<>();
        Customer foundCustomer = null;

        if (StringUtils.isNotBlank(postAuthenticationRequest.getEmailAddress())){
            foundCustomer = customerService.getCustomerByEmailAddress(postAuthenticationRequest.getEmailAddress())
                    .orElseThrow(() -> new UnAuthenticatedUserException("Invalid.credentials.provided", "Invalid email or password"));
        } else if (StringUtils.isNotBlank(postAuthenticationRequest.getPhoneNumber())){
            foundCustomer = customerService.getCustomerByPhoneNumber(postAuthenticationRequest.getPhoneNumber())
                    .orElseThrow(() -> new UnAuthenticatedUserException("Invalid.credentials.provided", "Invalid phoneNumber or password"));
        }

        assert foundCustomer != null;
        log.info("Found customer with email address {}", foundCustomer.getEmailAddress());
        if (!passwordEncoder.matches(postAuthenticationRequest.getPassword(), foundCustomer.getPassword())) {
            throw new UnAuthenticatedUserException("Invalid.credentials.provided", "Invalid username or password");
        }

        claims.put("customerId", foundCustomer.getId());
        claims.put("email", foundCustomer.getEmailAddress());
        claims.put("userType", foundCustomer.getUserType());
        claims.put("isActive", foundCustomer.isActive());

        String accessToken = generateToken(foundCustomer.getEmailAddress(), claims);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setAccessToken(accessToken);
        authenticationResponse.setId(foundCustomer.getId());
        authenticationResponse.setFirstname(foundCustomer.getFirstname());
        authenticationResponse.setLastname(foundCustomer.getLastname());
        authenticationResponse.setEmailAddress(foundCustomer.getEmailAddress());
        authenticationResponse.setUserType(foundCustomer.getUserType());

        AppUser appUser = new AppUser(
                foundCustomer.getId(),
                foundCustomer.getEmailAddress(),
                accessToken,
                true,
                foundCustomer.isActive(),
                null
        );
        SecurityContextHolder.getContext().setAuthentication(appUser);

        return authenticationResponse;
    }

    private String generateToken(String username, Map<String, Object> claims) {
        return jwtUtil.generateToken(username, DEFAULT_INVITATION_TOKEN_EXPIRATION_MS, claims);
    }
}
