/* (C)2025 */
package com.techservices.digitalbanking.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techservices.digitalbanking.core.configuration.SystemProperty;
import com.techservices.digitalbanking.core.configuration.resttemplate.ApiService;
import com.techservices.digitalbanking.core.domain.data.model.TransactionLog;
import com.techservices.digitalbanking.core.domain.dto.response.ExternalPaymentServiceAuthenticationResponse;
import com.techservices.digitalbanking.core.exception.PlatformServiceException;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.core.util.EncryptionUtil;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import com.techservices.digitalbanking.walletaccount.domain.request.SavingsAccountTransactionRequest;
import com.techservices.digitalbanking.walletaccount.domain.request.WalletPaymentOrderRequest;
import com.techservices.digitalbanking.walletaccount.domain.response.ExternalPaymentTransactionOtpGenerationResponse;
import com.techservices.digitalbanking.walletaccount.domain.response.ExternalPaymentTransactionOtpVerificationResponse;
import com.techservices.digitalbanking.walletaccount.domain.response.WalletPaymentOrderResponse;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExternalPaymentService {

  private final ApiService apiService;
  private final SystemProperty systemProperty;
  private static final String PAYMENT_URL = "/subsidiary/payout/";

  public ExternalPaymentTransactionOtpGenerationResponse generateOtp(
      SavingsAccountTransactionRequest request) {

    String url = systemProperty.getPayinvertMerchantIntegrationUrl() + PAYMENT_URL + "initiate/new";
    ExternalPaymentTransactionOtpGenerationResponse response =
        this.processRequest(
            url, request, ExternalPaymentTransactionOtpGenerationResponse.class, null);
    if (!response.isSuccessful()) {
      log.error("External payment service error: {}", response);
      throw new ValidationException("external.payment.service.error", response.getMessage());
    }
    return response;
  }

  public ExternalPaymentTransactionOtpVerificationResponse verifyOtp(
      SavingsAccountTransactionRequest request) {

    String url =
        systemProperty.getPayinvertMerchantIntegrationUrl() + PAYMENT_URL + "verify/external";
    ExternalPaymentTransactionOtpVerificationResponse response =
        this.processRequest(
            url, request, ExternalPaymentTransactionOtpVerificationResponse.class, null);
    if (!response.isSuccessful()) {
      throw new ValidationException("external.payment.service.error", response.getMessage());
    }
    log.info("External payment transaction verified successfully: {}", response);
    return response;
  }

  public ExternalPaymentTransactionOtpVerificationResponse initiateTransfer(
      SavingsAccountTransactionRequest request, TransactionLog transactionLog) {

    String url =
        systemProperty.getPayinvertMerchantIntegrationUrl() + PAYMENT_URL + "initiate/external";
    ExternalPaymentTransactionOtpVerificationResponse response =
        this.processRequest(
            url, request, ExternalPaymentTransactionOtpVerificationResponse.class, null);
    if (!response.isSuccessful()) {
      transactionLog.setResponseCode(response.getStatusCode());
      transactionLog.setResponseMessage(response.getMessage());
      throw new ValidationException("external.payment.service.error", response.getMessage());
    }
    transactionLog.setResponseCode(response.getStatusCode());
    transactionLog.setResponseMessage(response.getMessage());
    log.info("External payment transaction verified successfully: {}", response);
    return response;
  }

  private <T> T processRequest(
      String url, Object request, Class<T> responseType, HttpHeaders headers) {

    try {
      if (headers == null) {
        headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + generateAccessToken());
        headers.set("channelId", systemProperty.getPayinvertMerchantChannelId());
      }
      return apiService.callExternalApi(url, responseType, HttpMethod.POST, request, headers);
    } catch (PlatformServiceException e) {
      log.error("Error processing external payment transaction {}", e.toString());
      throw new ValidationException("external.payment.service.error", e.getDefaultUserMessage());
    } catch (JsonProcessingException e) {
      throw new ValidationException(
          "external.payment.service.error",
          "Error processing external payment transaction",
          e.getMessage());
    }
  }

  private String generateAccessToken() {

    String url =
        systemProperty.getPayinvertMerchantIntegrationUrl()
            + "/subsidiary/dashboard/business/login";
    Map<String, String> params = new HashMap<>();
    params.put("email", systemProperty.getPayinvertMerchantEmail());
    params.put("password", systemProperty.getPayinvertMerchantPassword());

    try {
      ExternalPaymentServiceAuthenticationResponse response =
          apiService.callExternalApi(
              url,
              ExternalPaymentServiceAuthenticationResponse.class,
              HttpMethod.POST,
              params,
              null);
      if (!response.isSuccessful()) {
        throw new ValidationException("external.payment.service.error", response.getMessage());
      }
      return response.getToken().getAccessToken();
    } catch (Exception e) {
      log.error("Failed to generate access token: {}", e.getMessage());
      throw new ValidationException(
          "external.payment.service.error", "Error generating access token: " + e.getMessage());
    }
  }

  public WalletPaymentOrderResponse initiateOrder(
      WalletPaymentOrderRequest dto, Customer customer, String reference) throws Exception {

    // PRE-ENCRYPTION VALIDATION
    log.info("🔍 Pre-encryption validation starting...");

    // 1. Validate input parameters
    if (dto == null) {
      log.error("❌ WalletPaymentOrderRequest is null");
      throw new ValidationException(
          "external.payment.service.error", "Payment request cannot be null");
    }

    if (customer == null) {
      log.error("❌ Customer is null");
      throw new ValidationException("external.payment.service.error", "Customer cannot be null");
    }

    if (reference == null || reference.trim().isEmpty()) {
      log.error("❌ Reference is null or empty: [{}]", reference);
      throw new ValidationException(
          "external.payment.service.error", "Payment reference cannot be null or empty");
    }

    log.info("✅ Input parameters validated successfully");
    log.info("📝 Reference: [{}]", reference);
    log.info("📝 Amount: [{}]", dto.getAmount());
    log.info("📝 Currency: [{}]", dto.getCurrency());
    log.info("📝 Customer: [{}] [{}]", customer.getFirstname(), customer.getLastname());

    Map<String, Object> payload = buildOrderPayloadWithValidation(dto, reference, customer);

    validatePayloadStructure(payload);

    ObjectMapper mapper = new ObjectMapper();
    String jsonPayload = mapper.writeValueAsString(payload);
    log.info("🔍 JSON payload length: {}", jsonPayload.length());
    log.info("🔍 JSON payload: {}", jsonPayload);

    if (!jsonPayload.contains(reference)) {
      log.error("❌ JSON payload does not contain reference: [{}]", reference);
      throw new ValidationException(
          "external.payment.service.error", "Payload missing reference after JSON conversion");
    }

    if (!jsonPayload.contains("\"order\"") && !jsonPayload.contains("\"Order\"")) {
      log.error("❌ JSON payload does not contain 'order' field");
      throw new ValidationException(
          "external.payment.service.error", "Payload missing order field after JSON conversion");
    }

    log.info("✅ JSON payload validated successfully");

    String encodedKey = systemProperty.getPayinvertPublicKey();
    log.info("🔑 Received encodedKey: {}", encodedKey);

    if (encodedKey == null || encodedKey.trim().isEmpty()) {
      log.error("❌ PayInvert public key is null or empty");
      throw new ValidationException(
          "external.payment.service.error", "PayInvert public key not configured");
    }

    String encryptedData = EncryptionUtil.encryptAesPublicKeyXml(jsonPayload, encodedKey);
    log.info("✅ Encryption completed successfully");
    log.info("🔍 Encrypted data length: {}", encryptedData.length());

    String[] encryptedParts = encryptedData.split("\\.");
    if (encryptedParts.length != 3) {
      log.error(
          "❌ Encrypted data format invalid. Expected 3 parts, got: {}", encryptedParts.length);
      throw new ValidationException(
          "external.payment.service.error", "Encryption resulted in invalid format");
    }

    log.info("✅ Encrypted data format validated (3 parts)");

    String apiKey = systemProperty.getPayinvertApiKey();
    HttpHeaders headers = new HttpHeaders();
    headers.set("api-key", apiKey);
    headers.setContentType(MediaType.APPLICATION_JSON);

    Map<String, String> body = new HashMap<>();
    body.put("data", encryptedData);

    String url = systemProperty.getPayinvertIntegrationUrl() + "/payment/order/create/new";

    log.info("🚀 Making API call to PayInvert...");

    WalletPaymentOrderResponse response =
        this.processRequest(url, body, WalletPaymentOrderResponse.class, headers);

    if (response.isSuccessful()) {
      log.info("✅ PayInvert API call successful");
      return response;
    }

    log.error("❌ PayInvert API call failed: {}", response.getMessage());
    throw new ValidationException(
        "external.payment.service.error", response.getMessage(), response);
  }

  private Map<String, Object> buildOrderPayloadWithValidation(
      WalletPaymentOrderRequest dto, String reference, Customer customerData) {

    log.info("🏗️ Building payload with validation...");

    Map<String, Object> payload = new HashMap<>();

    Map<String, Object> customer = new HashMap<>();

    String firstname = customerData.getFirstname();
    String lastname = customerData.getLastname();
    String email = customerData.getEmailAddress();
    String mobile = customerData.getPhoneNumber();

    if (firstname == null || firstname.trim().isEmpty()) {
      log.warn("⚠️ Customer firstname is null/empty, using default");
      firstname = "Unknown";
    }

    if (lastname == null || lastname.trim().isEmpty()) {
      log.warn("⚠️ Customer lastname is null/empty, using default");
      lastname = "Customer";
    }

    if (email == null || email.trim().isEmpty()) {
      log.error("❌ Customer email is null/empty");
      throw new ValidationException("external.payment.service.error", "Customer email is required");
    }

    if (mobile == null || mobile.trim().isEmpty()) {
      log.error("❌ Customer mobile is null/empty");
      throw new ValidationException(
          "external.payment.service.error", "Customer mobile is required");
    }

    customer.put("firstname", firstname.trim());
    customer.put("lastname", lastname.trim());
    customer.put("email", email.trim());
    customer.put("mobile", mobile.trim());
    customer.put("country", "NG");

    log.info("✅ Customer object built: {} {}", firstname, lastname);

    Map<String, Object> order = new HashMap<>();

    if (dto.getAmount() == null) {
      log.error("❌ Order amount is null");
      throw new ValidationException("external.payment.service.error", "Order amount is required");
    }

    if (dto.getCurrency() == null || dto.getCurrency().trim().isEmpty()) {
      log.error("❌ Order currency is null/empty");
      throw new ValidationException("external.payment.service.error", "Order currency is required");
    }

    if (reference == null || reference.trim().isEmpty()) {
      log.error("❌ Order reference is null/empty in payload building");
      throw new ValidationException(
          "external.payment.service.error", "Order reference is required");
    }

    order.put("amount", dto.getAmount().intValue());
    order.put("reference", reference.trim());
    order.put("description", "Pay");
    order.put("currency", dto.getCurrency().trim());

    log.info(
        "✅ Order object built - Amount: {}, Reference: [{}], Currency: {}",
        dto.getAmount(),
        reference,
        dto.getCurrency());

    String redirectUrl = systemProperty.getClientPaymentRedirectUrl();
    if (redirectUrl == null || redirectUrl.trim().isEmpty()) {
      log.error("❌ Redirect URL is null/empty");
      throw new ValidationException(
          "external.payment.service.error", "Redirect URL not configured");
    }

    Map<String, Object> payment = new HashMap<>();
    payment.put("redirectUrl", redirectUrl.trim());

    Map<String, Object> paymentMeta = new HashMap<>();
    paymentMeta.put("ipAddress", "127.0.0.1");
    paymentMeta.put("userAgent", "Java/SpringBoot");

    payload.put("customer", customer);
    payload.put("order", order);
    payload.put("payment", payment);
    payload.put("paymentMeta", paymentMeta);

    log.info("✅ Complete payload built successfully");
    log.info("🔍 Payload keys: {}", payload.keySet());
    log.info("🔍 Order keys: {}", order.keySet());

    if (!payload.containsKey("order")) {
      log.error("❌ Payload missing 'order' key after building");
      throw new ValidationException(
          "external.payment.service.error", "Payload missing order after building");
    }

    @SuppressWarnings("unchecked")
    Map<String, Object> orderFromPayload = (Map<String, Object>) payload.get("order");
    if (!orderFromPayload.containsKey("reference")) {
      log.error("❌ Order missing 'reference' key after building");
      throw new ValidationException(
          "external.payment.service.error", "Order missing reference after building");
    }

    Object referenceFromPayload = orderFromPayload.get("reference");
    if (!reference.equals(referenceFromPayload)) {
      log.error(
          "❌ Reference mismatch - Expected: [{}], Got: [{}]", reference, referenceFromPayload);
      throw new ValidationException(
          "external.payment.service.error", "Reference mismatch in payload");
    }

    log.info("✅ Final payload validation passed");
    return payload;
  }

  private void validatePayloadStructure(Map<String, Object> payload) {

    log.info("🔍 Validating payload structure...");

    String[] requiredKeys = {"customer", "order", "payment", "paymentMeta"};
    for (String key : requiredKeys) {
      if (!payload.containsKey(key)) {
        log.error("❌ Payload missing required key: {}", key);
        throw new ValidationException(
            "external.payment.service.error", "Payload missing required field: " + key);
      }

      if (payload.get(key) == null) {
        log.error("❌ Payload key [{}] is null", key);
        throw new ValidationException(
            "external.payment.service.error", "Payload field is null: " + key);
      }
    }

    @SuppressWarnings("unchecked")
    Map<String, Object> order = (Map<String, Object>) payload.get("order");
    String[] orderRequiredKeys = {"amount", "reference", "description", "currency"};

    for (String key : orderRequiredKeys) {
      if (!order.containsKey(key)) {
        log.error("❌ Order missing required key: {}", key);
        throw new ValidationException(
            "external.payment.service.error", "Order missing required field: " + key);
      }

      if (order.get(key) == null) {
        log.error("❌ Order key [{}] is null", key);
        throw new ValidationException(
            "external.payment.service.error", "Order field is null: " + key);
      }
    }

    log.info("✅ Payload structure validation passed");
  }
}
