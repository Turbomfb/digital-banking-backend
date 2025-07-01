package com.techservices.digitalbanking.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.techservices.digitalbanking.core.configuration.SystemProperty;
import com.techservices.digitalbanking.core.configuration.resttemplate.ApiService;
import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.core.domain.dto.response.ExternalPaymentServiceAuthenticationResponse;
import com.techservices.digitalbanking.core.exception.PlatformServiceException;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.loan.domain.request.LoanApplicationRequest;
import com.techservices.digitalbanking.loan.domain.response.LoanOfferResponse;
import com.techservices.digitalbanking.savingsaccount.domain.request.SavingsAccountTransactionRequest;
import com.techservices.digitalbanking.savingsaccount.domain.response.ExternalPaymentTransactionOtpGenerationResponse;
import com.techservices.digitalbanking.savingsaccount.domain.response.ExternalPaymentTransactionOtpVerificationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExternalLoanService {

    private final ApiService apiService;
    private final SystemProperty systemProperty;
    private static final String LOAN_OFFER_URL = "/api/ussd/ussd/loan/offer/";
    private static final String LOAN_APPLICATION_URL = "/api/ussd/loan/application";

    public List<LoanOfferResponse> retrieveCustomerLoanOffers(String phoneNumber) {
        String url = systemProperty.getPayinvertLoanIntegrationUrl() + LOAN_OFFER_URL + phoneNumber;
        try {
            return apiService.callExternalApi(url, new TypeReference<>() {}, HttpMethod.GET, null, null);
        } catch (PlatformServiceException e) {
            log.error(e.getMessage());
            throw new ValidationException("external.payment.service.error", e.getDefaultUserMessage());
        } catch (JsonProcessingException e) {
            throw new ValidationException("external.payment.service.error", "Error processing external payment transaction", e.getMessage());
        }
    }

    public GenericApiResponse processLoanApplication(LoanApplicationRequest loanApplicationRequest) {
        String url = systemProperty.getPayinvertLoanIntegrationUrl() + LOAN_APPLICATION_URL;
        try {
            return apiService.callExternalApi(url, GenericApiResponse.class, HttpMethod.POST, loanApplicationRequest, null);
        } catch (PlatformServiceException e) {
            log.error(e.getMessage());
            throw new ValidationException("external.payment.service.error", e.getDefaultUserMessage());
        } catch (JsonProcessingException e) {
            throw new ValidationException("external.payment.service.error", "Error processing external payment transaction", e.getMessage());
        }
    }
}
