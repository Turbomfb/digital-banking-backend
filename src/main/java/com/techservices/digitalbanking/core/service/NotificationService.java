package com.techservices.digitalbanking.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.techservices.digitalbanking.core.configuration.SystemProperty;
import com.techservices.digitalbanking.core.configuration.resttemplate.ApiService;
import com.techservices.digitalbanking.core.domain.dto.response.NotificationResponse;
import com.techservices.digitalbanking.core.exception.PlatformServiceException;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.loan.domain.response.LoanOfferResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final ApiService apiService;
    private final SystemProperty systemProperty;

    @Async
    public NotificationResponse sendSms(String phoneNumber, String message) {
        try {
            String url = this.buildSmsUrl(phoneNumber, message);
            return apiService.callExternalApi(url, NotificationResponse.class, HttpMethod.POST, null, null);
        } catch (Exception e) {
            logError(e.getMessage());
        }
        return null;
    }

    private static void logError(String e) {
        log.error("Error sending SMS: {}", e);
    }

    private String buildSmsUrl(String phoneNumber, String message) {
        if (phoneNumber == null || !phoneNumber.startsWith("+")) {
            if (phoneNumber == null || phoneNumber.isEmpty()) {
                throw new ValidationException("invalid.phone.number", "Phone number cannot be null or empty.");
            }
            if (phoneNumber.startsWith("0")) {
                phoneNumber = phoneNumber.substring(1);
            }
            phoneNumber = "+234" + phoneNumber.trim();
        }

        String baseUrl = systemProperty.getSmsNotificationServiceUrl();
        String apiKey = systemProperty.getSmsNotificationServiceApiKey();
        String senderId = systemProperty.getSmsNotificationServiceSenderId();

        return String.format("%s?to=%s&from=%s&sms=%s&type=plain&channel=generic&api_key=%s",
                baseUrl,
                phoneNumber,
                senderId,
                message,
                apiKey
        );
    }
}
