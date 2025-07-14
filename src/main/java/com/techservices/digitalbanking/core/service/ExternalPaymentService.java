package com.techservices.digitalbanking.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techservices.digitalbanking.core.configuration.SystemProperty;
import com.techservices.digitalbanking.core.configuration.resttemplate.ApiService;
import com.techservices.digitalbanking.core.domain.dto.response.ExternalPaymentServiceAuthenticationResponse;
import com.techservices.digitalbanking.core.exception.PlatformServiceException;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.savingsaccount.domain.request.SavingsAccountTransactionRequest;
import com.techservices.digitalbanking.savingsaccount.domain.response.ExternalPaymentTransactionOtpGenerationResponse;
import com.techservices.digitalbanking.savingsaccount.domain.response.ExternalPaymentTransactionOtpVerificationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExternalPaymentService {

    private final ApiService apiService;
    private final SystemProperty systemProperty;
    private static final String PAYMENT_URL = "/subsidiary/payout/";

    public ExternalPaymentTransactionOtpGenerationResponse generateOtp(SavingsAccountTransactionRequest request) {
        String url = systemProperty.getPayinvertMerchantIntegrationUrl() + PAYMENT_URL + "initiate/new";
        ExternalPaymentTransactionOtpGenerationResponse response = this.processRequest(url, request, ExternalPaymentTransactionOtpGenerationResponse.class);
        if (!response.isSuccessful()){
            log.error("External payment service error: {}", response);
            throw new ValidationException("external.payment.service.error", response.getMessage());
        }
        return response;
    }

    public ExternalPaymentTransactionOtpVerificationResponse verifyOtp(SavingsAccountTransactionRequest request) {
        String url = systemProperty.getPayinvertMerchantIntegrationUrl() + PAYMENT_URL + "verify/external";
        ExternalPaymentTransactionOtpVerificationResponse response = this.processRequest(url, request, ExternalPaymentTransactionOtpVerificationResponse.class);
        if (!response.isSuccessful()) {
            throw new ValidationException("external.payment.service.error", response.getMessage());
        }
        log.info("External payment transaction verified successfully: {}", response);
        return response;
    }

    public ExternalPaymentTransactionOtpVerificationResponse initiateTransfer(SavingsAccountTransactionRequest request) {
        String url = systemProperty.getPayinvertMerchantIntegrationUrl() + PAYMENT_URL + "initiate/external";
        ExternalPaymentTransactionOtpVerificationResponse response = this.processRequest(url, request, ExternalPaymentTransactionOtpVerificationResponse.class);
        if (!response.isSuccessful()) {
            throw new ValidationException("external.payment.service.error", response.getMessage());
        }
        log.info("External payment transaction verified successfully: {}", response);
        return response;
    }

    private <T> T processRequest(String url, Object request, Class<T> responseType) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + generateAccessToken());
            headers.set("channelId", systemProperty.getPayinvertMerchantChannelId());
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
}
