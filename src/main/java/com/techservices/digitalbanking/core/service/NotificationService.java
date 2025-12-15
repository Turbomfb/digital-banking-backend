/* (C)2025 */
package com.techservices.digitalbanking.core.service;

import java.util.Optional;

import com.techservices.digitalbanking.core.domain.dto.request.SmsNotificationRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.configuration.SystemProperty;
import com.techservices.digitalbanking.core.configuration.resttemplate.ApiService;
import com.techservices.digitalbanking.core.domain.data.model.AlertPreference;
import com.techservices.digitalbanking.core.domain.dto.response.NotificationResponse;
import com.techservices.digitalbanking.core.domain.enums.AlertType;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.techservices.digitalbanking.core.util.NotificationUtil.DEFAULT_SUBJECT;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

  private final ApiService apiService;
  private final SystemProperty systemProperty;
  private final AlertPreferenceService alertPreferenceService;
  private final SesSmtpEmailSender emailSender;

  public NotificationResponse sendSms(String phoneNumber, String message) {

    try {
      SmsNotificationRequest smsPayload = this.buildSmsPayload(phoneNumber, message);
      String url = systemProperty.getSmsNotificationServiceUrl();
      return apiService.callExternalApi(url, NotificationResponse.class, HttpMethod.POST, smsPayload, null);
    } catch (Exception e) {
      logError(e.getMessage());
    }
    return null;
  }

  private static void logError(String e) {

    log.error("Error sending SMS: {}", e);
  }

  private SmsNotificationRequest buildSmsPayload(String phoneNumber, String message) {

    if (phoneNumber == null || !phoneNumber.startsWith("+")) {
      if (phoneNumber == null || phoneNumber.isEmpty()) {
        throw new ValidationException("invalid.phone.number", "Phone number cannot be null or empty.");
      }
      if (phoneNumber.startsWith("0")) {
        phoneNumber = phoneNumber.substring(1);
      }
      phoneNumber = "+234" + phoneNumber.trim();
    }

    String apiKey = systemProperty.getSmsNotificationServiceApiKey();
    String senderId = systemProperty.getSmsNotificationServiceSenderId();

    return SmsNotificationRequest.builder()
        .from(senderId)
        .to(phoneNumber)
        .sms(message)
        .channel("generic")
        .messageType("NUMERIC")
        .type("plain")
        .apiKey(apiKey)
        .build();
  }

  @Async
  public void notifyUser(Customer customer, String message, AlertType alertType,
      String emailSubject, String htmlMessage) {

    if (customer == null) {
      throw new ValidationException("invalid.customer", "Customer cannot be null.");
    }

    boolean notificationSent = false;
    StringBuilder resultLog = new StringBuilder();
    Optional<AlertPreference> alertPreference = alertPreferenceService
        .getPreferencesForCustomerByAlertType(customer.getId(), alertType);
    if (alertPreference.isPresent()) {
      AlertPreference preference = alertPreference.get();

      if (preference.isViaSms()) {
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
      }

      if (preference.isViaEmail()) {
        if (customer.getEmailAddress() != null && !customer.getEmailAddress().isBlank()) {

          try {
            emailSender.sendEmail(
                systemProperty.getSmtpSenderEmail(),
                customer.getEmailAddress(),
                StringUtils.isBlank(emailSubject) ? DEFAULT_SUBJECT : emailSubject,
                message,
                htmlMessage
            );

            log.info("Email sent successfully to {}", customer.getEmailAddress());
            notificationSent = true;

          } catch (Exception e) {
            log.error("Failed to send email to {}", customer.getEmailAddress(), e);
          }

        } else {
          log.warn("No email address available for customer {}", customer.getId());
        }
      }

      // Send Push Notification
      if (preference.isViaPush()) {
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
      }
      if (!notificationSent) {
        log.info("No notification channel succeeded.");
      }
    } else {
      log.info("No alert preferences set for customer {} and alert type {}", customer.getId(), alertType);
    }
  }

  // Overloaded method for backward compatibility
  @Async
  public void notifyUser(Customer customer, String message, AlertType alertType, String emailSubject) {
    // Use simple HTML wrapper if no HTML template provided
    String htmlMessage = "<p>" + message.replace("\n", "<br>") + "</p>";
    notifyUser(customer, message, alertType, emailSubject, htmlMessage);
  }

  private boolean sendPushNotification(String deviceToken, String message) {

    log.info("Sending push notification to deviceToken={} with message={}", deviceToken, message);
    return true;
  }
}