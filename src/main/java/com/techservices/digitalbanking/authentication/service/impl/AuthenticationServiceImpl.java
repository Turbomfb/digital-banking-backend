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

import com.techservices.digitalbanking.authentication.domain.data.model.UserLoginActivity;
import com.techservices.digitalbanking.authentication.domain.data.repository.UserLoginActivityRepository;
import com.techservices.digitalbanking.authentication.domain.request.AuthenticationRequest;
import com.techservices.digitalbanking.authentication.domain.request.PasswordMgtRequest;
import com.techservices.digitalbanking.authentication.domain.response.AuthenticationResponse;
import com.techservices.digitalbanking.authentication.service.AuthenticationService;
import com.techservices.digitalbanking.authentication.util.UserLoginActivityUtil;
import com.techservices.digitalbanking.common.domain.enums.UserType;
import com.techservices.digitalbanking.core.configuration.security.JwtUtil;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.core.domain.dto.request.NotificationRequestDto;
import com.techservices.digitalbanking.core.domain.dto.request.OtpDto;
import com.techservices.digitalbanking.core.domain.enums.OtpType;
import com.techservices.digitalbanking.core.domain.data.model.AppUser;
import com.techservices.digitalbanking.core.exception.UnAuthenticatedUserException;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.core.redis.service.RedisService;
import com.techservices.digitalbanking.core.service.IpLocationService;
import com.techservices.digitalbanking.core.util.AppUtil;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import com.techservices.digitalbanking.customer.domian.dto.response.CustomerDtoResponse;
import com.techservices.digitalbanking.customer.service.CustomerService;
import eu.bitwalker.useragentutils.UserAgent;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.techservices.digitalbanking.authentication.util.UserLoginActivityUtil.extractDeviceName;
import static com.techservices.digitalbanking.authentication.util.UserLoginActivityUtil.extractSource;
import static com.techservices.digitalbanking.core.util.CommandUtil.*;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final long DEFAULT_INVITATION_TOKEN_EXPIRATION_MS = 3600000; // 1 hour

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final CustomerService customerService;
    private final RedisService redisService;
    private final UserLoginActivityRepository userLoginActivityRepository;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest postAuthenticationRequest, UserType customerType, String userAgent, HttpServletRequest request) {
        Map<String, Object> claims = new HashMap<>();
        Customer foundCustomer = getCustomerByEmailOrPhoneNumber(postAuthenticationRequest.getEmailAddress(), postAuthenticationRequest.getPhoneNumber(), customerType);

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

        try {
            this.processUserLoginActivity(userAgent, request, foundCustomer);
        } catch (Exception e) {
            log.error("Error processing user login activity: {}", e.getMessage());
        }
        return authenticationResponse;
    }

    private void processUserLoginActivity(String userAgent, HttpServletRequest request, Customer foundCustomer) {
        log.info("userAgent: {}", userAgent);
        UserAgent userAgentObject = UserAgent.parseUserAgentString(userAgent);
        log.info("userAgentObject: {}", userAgentObject);

        String deviceName = UserLoginActivityUtil.extractDeviceName(userAgentObject, userAgent);
        String source = UserLoginActivityUtil.extractSource(userAgentObject, userAgent);

        String ip = extractClientIp(request);
        String location = UserLoginActivityUtil.getLocationFromRequest(ip, request);

        UserLoginActivity activity;
        Optional<UserLoginActivity> foundActivity = userLoginActivityRepository
                .findByCustomerIdAndDeviceNameAndSource(foundCustomer.getId(), deviceName, source);

        if (foundActivity.isPresent()) {
            activity = foundActivity.get();
            activity.setUpdatedAt(LocalDateTime.now());
        } else {
            activity = new UserLoginActivity();
            activity.setCustomerId(foundCustomer.getId());
            activity.setDeviceName(deviceName);
            activity.setSource(source);
            activity.setCreatedAt(LocalDateTime.now());
            activity.setUpdatedAt(LocalDateTime.now());
        }

        activity.setLocation(location);

        log.info("user activity: {}", activity);
        userLoginActivityRepository.save(activity);
    }

    private String extractClientIp(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        return xfHeader == null ? request.getRemoteAddr() : xfHeader.split(",")[0];
    }

    private Customer getCustomerByEmailOrPhoneNumber(String emailAddress, String phoneNumber, UserType customerType) {
        Customer foundCustomer;
        if (StringUtils.isNotBlank(emailAddress)){
            foundCustomer = customerService.getCustomerByEmailAddressAndUserType(emailAddress, customerType)
                    .orElseThrow(() -> new UnAuthenticatedUserException("Invalid.credentials.provided", "Invalid email or password"));
        } else if (StringUtils.isNotBlank(phoneNumber)){
            foundCustomer = customerService.getCustomerByPhoneNumberAndUserType(phoneNumber, customerType)
                    .orElseThrow(() -> new UnAuthenticatedUserException("Invalid.credentials.provided", "Invalid phoneNumber or password"));
        } else {
            throw new ValidationException("Invalid.credentials.provided", "Email or phone number must be provided");
        }
        return foundCustomer;
    }

    @Override
    public GenericApiResponse createPassword(PasswordMgtRequest passwordMgtRequest) {
        Customer foundCustomer = customerService.getCustomerById(passwordMgtRequest.getCustomerId());
        if (StringUtils.isBlank(foundCustomer.getPassword())){
            if (StringUtils.isNotBlank(passwordMgtRequest.getPassword())){
                foundCustomer.setPassword(passwordEncoder.encode(passwordMgtRequest.getPassword()));
                customerService.updateCustomer(null, foundCustomer.getId(), foundCustomer);
                return new GenericApiResponse("Password created successfully", "success", CustomerDtoResponse.parse(foundCustomer));
            } else {
                throw new ValidationException("Invalid.credentials.provided", "password cannot be blank");
            }
        }
        throw new ValidationException("password.already.exists", "Password has already been set for this customer. Please use the update password endpoint.");
    }

    @Override
    public GenericApiResponse forgotPassword(PasswordMgtRequest passwordMgtRequest, String command, UserType customerType) {
        if (StringUtils.equals(GENERATE_OTP_COMMAND, command)){
            Customer foundCustomer = getCustomerByEmailOrPhoneNumber(passwordMgtRequest.getEmailAddress(), passwordMgtRequest.getPhoneNumber(), customerType);
            NotificationRequestDto notificationRequestDto = new NotificationRequestDto(foundCustomer.getPhoneNumber(), foundCustomer.getEmailAddress());
            OtpDto otpDto = this.redisService.generateOtpRequest(foundCustomer, OtpType.FORGOT_PASSWORD, notificationRequestDto, null);
          return new GenericApiResponse(otpDto.getUniqueId(), "We sent an OTP to "+ AppUtil.maskPhoneNumber(foundCustomer.getPhoneNumber())+" and "+AppUtil.maskEmailAddress(foundCustomer.getEmailAddress()), "success", null);
        } else if (StringUtils.equals(VERIFY_OTP_COMMAND, command)) {
            if (StringUtils.isBlank(passwordMgtRequest.getOtp())){
                throw new ValidationException("Invalid.data.provided", "otp must be provided");
            }
            if (StringUtils.isBlank(passwordMgtRequest.getUniqueId())){
                throw new ValidationException("Invalid.data.provided", "uniqueId must be provided");
            }

            OtpDto otpDto = redisService.validateOtpWithoutDeletingRecord(passwordMgtRequest.getUniqueId(), passwordMgtRequest.getOtp(), OtpType.FORGOT_PASSWORD);
            if (otpDto == null) {
                throw new ValidationException("otp.expired", "OTP has expired or does not exist.");
            }
            return new GenericApiResponse("OTP validated successfully. Kindly proceed to change password", "success", null);
        } else if (StringUtils.equals(CHANGE_PASSWORD_COMMAND, command)) {
            if (StringUtils.isBlank(passwordMgtRequest.getUniqueId())){
                throw new ValidationException("Invalid.data.provided", "uniqueId must be provided");
            }
            if (StringUtils.isBlank(passwordMgtRequest.getPassword())){
                throw new ValidationException("Invalid.data.provided", "password must be provided");
            }
            OtpDto otpDto = redisService.validateOtpWithoutOtp(passwordMgtRequest.getUniqueId());
            String password = passwordEncoder.encode(passwordMgtRequest.getPassword());
            Customer foundCustomer = (Customer) otpDto.getData();
            foundCustomer.setPassword(password);
            customerService.updateCustomer(null, foundCustomer.getId(), foundCustomer);
            return new GenericApiResponse("Password has been changed successfully", "success", null);
        } else {
            throw new ValidationException("Invalid.command", "Invalid command: " + command);
        }

    }

    @Override
    public BasePageResponse<UserLoginActivity> retrieveUserLoginActivities(Long customerId) {
        return BasePageResponse.instance(
                this.userLoginActivityRepository.findAllByCustomerId(customerId)
        );
    }

    private String generateToken(String username, Map<String, Object> claims) {
        return jwtUtil.generateToken(username, DEFAULT_INVITATION_TOKEN_EXPIRATION_MS, claims);
    }
}
