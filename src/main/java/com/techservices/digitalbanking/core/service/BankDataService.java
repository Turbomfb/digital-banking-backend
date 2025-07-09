package com.techservices.digitalbanking.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.techservices.digitalbanking.core.configuration.SystemProperty;
import com.techservices.digitalbanking.core.configuration.resttemplate.ApiService;
import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.core.domain.dto.response.BankDataResponse;
import com.techservices.digitalbanking.core.exception.PlatformServiceException;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.loan.domain.request.LoanApplicationRequest;
import com.techservices.digitalbanking.loan.domain.response.LoanOfferResponse;
import com.techservices.digitalbanking.savingsaccount.domain.request.NameEnquiryRequest;
import com.techservices.digitalbanking.savingsaccount.domain.response.NameEnquiryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BankDataService {

    private final ApiService apiService;
    private final SystemProperty systemProperty;
    private final String pathUrl = "/v2/api/identity/ng/bank-account-number/";

    public BankDataResponse retrieveAllBanks() {
        String url = systemProperty.getYouverifyIntegrationUrl() + pathUrl+"bank-list";
        try {
            HttpHeaders headers = this.getHeaders();
            return apiService.callExternalApi(url, BankDataResponse.class, HttpMethod.GET, null, headers);
        } catch (PlatformServiceException e) {
            log.error(e.getMessage());
            throw new ValidationException("external.bank.data.service.error", e.getDefaultUserMessage());
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new ValidationException("external.bank.data.service.error", "Error retrieving Bank data", e.getMessage());
        }
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("token", systemProperty.getYouverifyIntegrationApiKey());
        return headers;
    }

    public NameEnquiryResponse processNameEnquiry(NameEnquiryRequest request) {
        String url = systemProperty.getYouverifyIntegrationUrl() + pathUrl+"resolve";
        request.setSubjectConsent(true);
        try {
            HttpHeaders headers = this.getHeaders();
            return apiService.callExternalApi(url, NameEnquiryResponse.class, HttpMethod.POST, request, headers);
        } catch (PlatformServiceException e) {
            log.error(e.getMessage());
            throw new ValidationException("external.bank.data.service.error", e.getDefaultUserMessage());
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new ValidationException("external.bank.data.service.error", "Error processing name enquiry", e.getMessage());
        }
    }
}
