/* (C)2025 */
package com.techservices.digitalbanking.core.service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
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

  @Async
  public CompletableFuture<NotificationResponse> sendSmsAsync(String phoneNumber, String message) {
    try {
      SmsNotificationRequest smsPayload = this.buildSmsPayload(phoneNumber, message);
      String url = systemProperty.getSmsNotificationServiceUrl();
      NotificationResponse response = apiService.callExternalApi(
          url,
          NotificationResponse.class,
          HttpMethod.POST,
          smsPayload,
          null
      );
      log.info("SMS sent successfully to {}", phoneNumber);
      return CompletableFuture.completedFuture(response);
    } catch (Exception e) {
      log.error("Error sending SMS to {}: {}", phoneNumber, e.getMessage());
      return CompletableFuture.completedFuture(null);
    }
  }

  @Async
  public CompletableFuture<Boolean> sendEmailAsync(
      String to,
      String subject,
      String textMessage,
      String htmlMessage) {
    try {
      emailSender.sendEmail(
          systemProperty.getSmtpSenderEmail(),
          to,
          StringUtils.isBlank(subject) ? DEFAULT_SUBJECT : subject,
          textMessage,
          htmlMessage
      );
      log.info("Email sent successfully to {}", to);
      return CompletableFuture.completedFuture(true);
    } catch (Exception e) {
      log.error("Failed to send email to {}: {}", to, e.getMessage());
      return CompletableFuture.completedFuture(false);
    }
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
  public void notifyUser(Customer customer, String message, AlertType alertType, String emailSubject, String htmlMessage) {
    if (customer == null) {
      throw new ValidationException("invalid.customer", "Customer cannot be null.");
    }

    Optional<AlertPreference> alertPreference = alertPreferenceService
        .getPreferencesForCustomerByAlertType(customer.getId(), alertType);

    if (alertPreference.isEmpty()) {
      log.info("No alert preferences set for customer {} and alert type {}", customer.getId(), alertType);
      return;
    }

    AlertPreference preference = alertPreference.get();
    boolean notificationAttempted = false;

    if (preference.isViaSms() && customer.getPhoneNumber() != null && !customer.getPhoneNumber().isBlank()) {
      sendSmsAsync(customer.getPhoneNumber(), message);
      notificationAttempted = true;
    } else if (preference.isViaSms()) {
      log.warn("SMS notification enabled but no phone number available for customer {}", customer.getId());
    }

    if (preference.isViaEmail() && customer.getEmailAddress() != null && !customer.getEmailAddress().isBlank()) {
      sendEmailAsync(
          customer.getEmailAddress(),
          emailSubject,
          message,
          htmlMessage
      );
      notificationAttempted = true;
    } else if (preference.isViaEmail()) {
      log.warn("Email notification enabled but no email address available for customer {}", customer.getId());
    }

    if (preference.isViaPush()) {
      if (customer.getEmailAddress() != null && !customer.getEmailAddress().isBlank()) {
        sendPushNotificationAsync(customer.getEmailAddress(), message);
        notificationAttempted = true;
      } else {
        log.warn("Push notification enabled but no device token available for customer {}", customer.getId());
      }
    }

    if (!notificationAttempted) {
      log.info("No notification channels available for customer {}", customer.getId());
    }
  }

  @Async
  public void notifyUser(Customer customer, String message, AlertType alertType, String emailSubject) {
    String htmlMessage = "<div>" + message.replace("\n", "<br>") + "</div>";
    notifyUser(customer, message, alertType, emailSubject, htmlMessage);
  }

  @Async
  public CompletableFuture<Boolean> sendPushNotificationAsync(String deviceToken, String message) {
    log.info("Sending push notification to deviceToken={} with message={}", deviceToken, message);
    // TODO: Implement actual push notification logic
    return CompletableFuture.completedFuture(true);
  }

  private boolean sendPushNotification(String deviceToken, String message) {
    log.info("Sending push notification to deviceToken={} with message={}", deviceToken, message);
    return true;
  }
}