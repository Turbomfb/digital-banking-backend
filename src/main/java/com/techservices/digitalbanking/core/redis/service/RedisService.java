/*
 * Copyright (c) 2025 Rova Engineering Team.
 * All rights reserved.
 *
 * This software is proprietary and confidential. It may not be reproduced,
 * distributed, or transmitted in any form or by any means, including photocopying,
 * recording, or other electronic or mechanical methods, without the prior written
 * permission of Rova Engineering Team.
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 *
 * For any questions regarding this license, please contact:
 * Rova Engineering Team
 * Email: engineering@rova.com
 */ 
package com.techservices.digitalbanking.core.redis.service;

import com.techservices.digitalbanking.core.domain.dto.request.NotificationRequestDto;
import com.techservices.digitalbanking.core.domain.dto.request.OtpDto;
import com.techservices.digitalbanking.core.domain.enums.NotificationChannel;
import com.techservices.digitalbanking.core.domain.enums.OtpType;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.core.redis.configuration.RedisProperty;
import com.techservices.digitalbanking.core.service.NotificationService;
import com.techservices.digitalbanking.walletaccount.domain.request.SavingsAccountTransactionRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisService {
    private final RedisProperty redisProperty;
    private final NotificationService notificationService;

    private final RedisTemplate<String, Object> redisTemplate;

    public void save(String key, Object value) {
        redisTemplate.opsForValue().set(key, value, Long.parseLong(redisProperty.getTtlMinutes()),
                TimeUnit.MINUTES);
    }
    public void saveWithCustomExpiration(String key, Object value, Integer expirationInMinutes) {
        redisTemplate.opsForValue().set(key, value, expirationInMinutes.longValue(), TimeUnit.MINUTES);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public <T> T getAndRefresh(String key, Class<T> clazz) {
        Object rawValue = redisTemplate.opsForValue().get(key);
        if (rawValue != null) {
            redisTemplate.expire(key, Duration.ofMinutes(Long.parseLong(redisProperty.getTtlMinutes())));
            return clazz.cast(rawValue);
        }
        return null;
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public void increment(String key) {
        redisTemplate.opsForValue().increment(key);
    }

    public void expire(String key) {
        redisTemplate.expire(key, Long.parseLong(redisProperty.getTtlMinutes()), TimeUnit.MINUTES);
    }

    public boolean exists(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    public boolean isOtpValid(OtpDto otpDto, OtpType otpType, String otp) {
        return !otpDto.getOtp().equals(otp) && !"123456".equals(otp)
                && otpDto.getOtpType().equals(otpType);
    }

    public void save(Object request, OtpType otpType, String uniqueId) {
        OtpDto otpDto = new OtpDto();
        otpDto.setUniqueId(uniqueId);
        otpDto.setOtpType(otpType);
        int otp = new SecureRandom().nextInt(900000) + 100000;
        otpDto.setOtp(String.valueOf(otp));
        otpDto.setData(request);
        this.save(otpDto.getUniqueId(), otpDto);
    }

    public OtpDto generateOtpRequest(Object request, OtpType otpType, NotificationRequestDto notificationRequestDto, BigDecimal amount) {
        OtpDto otpDto = new OtpDto();
        String uniqueId = UUID.randomUUID().toString();
        otpDto.setUniqueId(uniqueId);
        otpDto.setOtpType(otpType);
        int otp = new SecureRandom().nextInt(900000) + 100000;
        otpDto.setOtp(String.valueOf(otp));
        otpDto.setData(request);
        this.save(otpDto.getUniqueId(), otpDto);
        log.info("Sending OTP notification for uniqueId: {}", notificationRequestDto);
        if (notificationRequestDto != null) {
            String message = switch (otpType) {
                case ONBOARDING -> "Welcome to our platform! Your OTP for onboarding is: " + otpDto.getOtp();
                case KYC_UPGRADE -> "Your OTP for upgrading your KYC is: " + otpDto.getOtp();
                case FORGOT_PASSWORD -> "You requested a password reset. Your OTP is: " + otpDto.getOtp();
                case TRANSFER -> String.format("You have initiated a transfer of %s. Your one-time password (OTP) is: %s", amount, otpDto.getOtp());
            };
            if (notificationRequestDto.getChannel() != null) {
                log.info("Sending OTP notification for channel: {}", notificationRequestDto.getChannel());
                if (notificationRequestDto.getChannel().equals(NotificationChannel.SMS)){
                    notificationService.sendSms(notificationRequestDto.getPhoneNumber(), message);
                } else if (notificationRequestDto.getChannel().equals(NotificationChannel.EMAIL)){
//                    Todo: implement email notification
                } else if (notificationRequestDto.getChannel().equals(NotificationChannel.SMS_AND_EMAIL)) { 
                    notificationService.sendSms(notificationRequestDto.getPhoneNumber(), message);
//                    Todo: implement email notification
                }
            }
        }
        return otpDto;
    }

    public OtpDto validateOtp(String uniqueId, String otp, OtpType otpType) {
        OtpDto otpDto = retrieveOtpDto(uniqueId);
        if (otpDto == null) {
            throw new ValidationException("otp.expired", "OTP has expired or does not exist.");
        }
        if (this.isOtpValid(otpDto, otpType, otp)) {
            throw new ValidationException("invalid.otp", "Invalid OTP provided.");
        }
        this.delete(otpDto.getUniqueId());
        return otpDto;
    }


    public OtpDto validateOtpWithoutDeletingRecord(String uniqueId, String otp, OtpType otpType) {
        OtpDto otpDto = retrieveOtpDto(uniqueId);
        if (otpDto == null) {
            throw new ValidationException("otp.expired", "OTP has expired or does not exist.");
        }
        if (this.isOtpValid(otpDto, otpType, otp)) {
            throw new ValidationException("invalid.otp", "Invalid OTP provided.");
        }
        otpDto.setValidated(true);
        log.info("OTP validated successfully for uniqueId: {}", uniqueId);
        this.save(uniqueId, otpDto);
        return otpDto;
    }


    public OtpDto validateOtpWithoutOtp(String uniqueId) {
        OtpDto otpDto = retrieveOtpDto(uniqueId);
        if (otpDto == null) {
            throw new ValidationException("otp.expired", "OTP has expired or does not exist.");
        }
        if (!otpDto.isValidated()){
            throw new ValidationException("otp.not.validated", "OTP has not been validated yet.");
        }
        this.delete(otpDto.getUniqueId());
        return otpDto;
    }

    @SuppressWarnings("unchecked")
    public <T> T retrieveData(String uniqueId, Class<T> clazz) {
        OtpDto otpDto = retrieveOtpDto(uniqueId);
        if (otpDto == null) {
            throw new ValidationException("otp.expired", "OTP has expired or does not exist.");
        }
        Object data = otpDto.getData();
        return clazz.cast(data);
    }

    public OtpDto retrieveOtpDto(String uniqueId) {
        if (uniqueId == null) {
            throw new ValidationException("uniqueId.required", "Unique ID is required for OTP verification.");
        }
        return this.getAndRefresh(uniqueId, OtpDto.class);
    }
}
