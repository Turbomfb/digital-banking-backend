package com.techservices.digitalbanking.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techservices.digitalbanking.core.configuration.SystemProperty;
import com.techservices.digitalbanking.core.configuration.resttemplate.ApiService;
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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExternalPaymentService {

    private final ApiService apiService;
    private final SystemProperty systemProperty;
    private static final String PAYMENT_URL = "/subsidiary/payout/";

    public ExternalPaymentTransactionOtpGenerationResponse generateOtp(SavingsAccountTransactionRequest request) {
        String url = systemProperty.getPayinvertMerchantIntegrationUrl() + PAYMENT_URL + "initiate/new";
        ExternalPaymentTransactionOtpGenerationResponse response = this.processRequest(url, request, ExternalPaymentTransactionOtpGenerationResponse.class, null);
        if (!response.isSuccessful()){
            log.error("External payment service error: {}", response);
            throw new ValidationException("external.payment.service.error", response.getMessage());
        }
        return response;
    }

    public ExternalPaymentTransactionOtpVerificationResponse verifyOtp(SavingsAccountTransactionRequest request) {
        String url = systemProperty.getPayinvertMerchantIntegrationUrl() + PAYMENT_URL + "verify/external";
        ExternalPaymentTransactionOtpVerificationResponse response = this.processRequest(url, request, ExternalPaymentTransactionOtpVerificationResponse.class, null);
        if (!response.isSuccessful()) {
            throw new ValidationException("external.payment.service.error", response.getMessage());
        }
        log.info("External payment transaction verified successfully: {}", response);
        return response;
    }

    public ExternalPaymentTransactionOtpVerificationResponse initiateTransfer(SavingsAccountTransactionRequest request) {
        String url = systemProperty.getPayinvertMerchantIntegrationUrl() + PAYMENT_URL + "initiate/external";
        ExternalPaymentTransactionOtpVerificationResponse response = this.processRequest(url, request, ExternalPaymentTransactionOtpVerificationResponse.class, null);
        if (!response.isSuccessful()) {
            throw new ValidationException("external.payment.service.error", response.getMessage());
        }
        log.info("External payment transaction verified successfully: {}", response);
        return response;
    }

    private <T> T processRequest(String url, Object request, Class<T> responseType, HttpHeaders headers) {
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
            throw new ValidationException("external.payment.service.error", "Error processing external payment transaction", e.getMessage());
        }
    }

    private String generateAccessToken() {
        String url = systemProperty.getPayinvertMerchantIntegrationUrl() + "/subsidiary/dashboard/business/login";
        Map<String, String> params = new HashMap<>();
        params.put("email", systemProperty.getPayinvertMerchantEmail());
        params.put("password", systemProperty.getPayinvertMerchantPassword());

        try {
            ExternalPaymentServiceAuthenticationResponse response = apiService.callExternalApi(
                    url, ExternalPaymentServiceAuthenticationResponse.class, HttpMethod.POST, params, null
            );
            if (!response.isSuccessful()) {
                throw new ValidationException("external.payment.service.error", response.getMessage());
            }
            return response.getToken().getAccessToken();
        } catch (Exception e) {
            log.error("Failed to generate access token: {}", e.getMessage());
            throw new ValidationException("external.payment.service.error", "Error generating access token: " + e.getMessage());
        }
    }


    public WalletPaymentOrderResponse initiateOrder(WalletPaymentOrderRequest dto, Customer customer) throws Exception {
        String reference = UUID.randomUUID().toString();
        Map<String, Object> payload = buildOrderPayload(dto, reference, customer);
        ObjectMapper mapper = new ObjectMapper();
        String jsonPayload = mapper.writeValueAsString(payload);
        log.info("Initiate order payload: {}", jsonPayload);

        String encodedKey = systemProperty.getPayinvertPublicKey();
        log.info("🔑 Received encodedKey: {}", encodedKey);
        String encryptedData = EncryptionUtil.encryptAesPublicKeyXml(jsonPayload, encodedKey);
        log.info("Encrypted data: {}", encryptedData);

        String apiKey = systemProperty.getPayinvertApiKey();
        HttpHeaders headers = new HttpHeaders();
        headers.set("api-key", apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = new HashMap<>();
        body.put("data", encryptedData);

        String url = systemProperty.getPayinvertIntegrationUrl() + "/payment/order/create/new";

        WalletPaymentOrderResponse response = this.processRequest(url, body, WalletPaymentOrderResponse.class, headers);

        if (response.isSuccessful()) {
            return response;
        }

        throw new ValidationException("external.payment.service.error", response.getMessage(), response);
    }

    private Map<String, Object> buildOrderPayload(WalletPaymentOrderRequest dto, String reference, Customer customerData) {
        Map<String, Object> payload = new HashMap<>();

        Map<String, Object> customer = Map.of(
                "firstname", customerData.getFirstname(),
                "lastname", customerData.getLastname(),
                "email", customerData.getEmailAddress(),
                "mobile", customerData.getPhoneNumber(),
                "country", "NG"
        );

        Map<String, Object> order = Map.of(
                "amount", dto.getAmount(),
                "reference", reference,
                "description", "Pay",
                "currency", dto.getCurrency()
        );

        Map<String, Object> payment = Map.of("redirectUrl", systemProperty.getClientPaymentRedirectUrl());
        Map<String, Object> meta = Map.of(
                "ipAddress", "127.0.0.1",
                "userAgent", "Java/SpringBoot"
        );

        payload.put("customer", customer);
        payload.put("order", order);
        payload.put("payment", payment);
        payload.put("paymentMeta", meta);

        log.info("Payment payload: {}", payload);
        return payload;
    }
}
