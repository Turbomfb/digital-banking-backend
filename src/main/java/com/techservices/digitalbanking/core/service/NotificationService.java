package com.techservices.digitalbanking.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.techservices.digitalbanking.core.configuration.SystemProperty;
import com.techservices.digitalbanking.core.configuration.resttemplate.ApiService;
import com.techservices.digitalbanking.core.domain.dto.response.NotificationResponse;
import com.techservices.digitalbanking.core.exception.PlatformServiceException;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
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

    @Async
    public void notifyUser(Customer customer, String message) {
        if (customer == null) {
            throw new ValidationException("invalid.customer", "Customer cannot be null.");
        }

        boolean notificationSent = false;
        StringBuilder resultLog = new StringBuilder();

        if (customer.getPhoneNumber() != null && !customer.getPhoneNumber().isBlank()) {
            NotificationResponse smsResponse = sendSms(customer.getPhoneNumber(), message);
            if (smsResponse != null) {
                log.info("SMS sent successfully to {}", customer.getPhoneNumber());
                resultLog.append("SMS sent successfully. ");
                notificationSent = true;
            } else {
                log.error("Failed to send SMS to {}", customer.getPhoneNumber());
                resultLog.append("Failed to send SMS. ");
            }
        } else {
            log.warn("No phone number available for customer {}", customer.getId());
            resultLog.append("No phone number available. ");
        }

        if (customer.getEmailAddress() != null && !customer.getEmailAddress().isBlank()) {
            // TODO: Implement sendEmail(customer.getEmail(), message)
            resultLog.append("Email sending not yet implemented. ");
        } else {
            resultLog.append("No email address available. ");
        }

        try {
            if (customer.getEmailAddress() != null && !customer.getEmailAddress().isBlank()) {
                boolean pushSent = sendPushNotification(customer.getEmailAddress(), message);
                if (pushSent) {
                    log.info("Push notification sent successfully.");
                    resultLog.append("Push notification sent successfully. ");
                    notificationSent = true;
                } else {
                    log.error("Failed to send push notification.");
                    resultLog.append("Failed to send push notification. ");
                }
            } else {
                log.warn("No device token available for push notification.");
                resultLog.append("No device token available. ");
            }
        } catch (Exception e) {
            log.error("Error sending push notification: {}", e.getMessage(), e);
            resultLog.append("Push notification error. ");
        }

        if (!notificationSent) {
            throw new PlatformServiceException("notification.failed", "No notification channel succeeded.");
        }
    }

    private boolean sendPushNotification(String deviceToken, String message) {
        log.info("Sending push notification to deviceToken={} with message={}", deviceToken, message);
        return true;
    }

}
