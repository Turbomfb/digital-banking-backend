package com.techservices.digitalbanking.core.service;

import com.techservices.digitalbanking.core.configuration.SystemProperty;
import com.techservices.digitalbanking.core.configuration.resttemplate.ApiService;
import com.techservices.digitalbanking.core.domain.dto.request.IdentityVerificationRequest;
import com.techservices.digitalbanking.core.domain.dto.response.CustomerIdentityVerificationResponse;
import com.techservices.digitalbanking.core.domain.dto.response.IdentityVerificationResponse;
import com.techservices.digitalbanking.core.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class IdentityVerificationService {

    private final ApiService apiService;
    private final SystemProperty systemProperty;
    private static final String IDENTITY_VERIFICATION_URL = "/api/YouVerify/";

    public CustomerIdentityVerificationResponse verifyBvn(String bvn, IdentityVerificationRequest customerData) {
        String url = buildUrl("bvn-detail", bvn);
        IdentityVerificationResponse identityVerificationResponse = fetchIdentityVerificationResponse(url, bvn);
        return processVerificationResponse(identityVerificationResponse, customerData);
    }

    public CustomerIdentityVerificationResponse verifyNin(String nin, IdentityVerificationRequest customerData) {
        String url = buildUrl("nin-detail", nin);
        IdentityVerificationResponse identityVerificationResponse = fetchIdentityVerificationResponse(url, nin);
        return processVerificationResponse(identityVerificationResponse, customerData);
    }

    private String buildUrl(String endpoint, String identifier) {
        return systemProperty.getPayinvertIntegrationUrl() + IDENTITY_VERIFICATION_URL + endpoint + "?"+endpoint.split("-")[0]+"=" + identifier;
    }

    private IdentityVerificationResponse fetchIdentityVerificationResponse(String url, String identifier) {
        try {
            return apiService.callExternalApi(url, IdentityVerificationResponse.class, HttpMethod.GET, null);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ValidationException("verification.failed", "Verification failed for identifier: " + identifier, e);
        }
    }

    private CustomerIdentityVerificationResponse processVerificationResponse(IdentityVerificationResponse response, IdentityVerificationRequest customerData) {
        if (response == null || !response.isSuccess() || response.getData() == null) {
            log.error(response.toString());
            throw new ValidationException("verification.failed", "Verification failed for identifier.");
        }

        IdentityVerificationResponse.IdentityVerificationResponseData responseData = response.getData();
        List<String> mismatchedFields = findMismatchedFields(customerData, responseData);

        CustomerIdentityVerificationResponse customerIdentityVerificationResponse = new CustomerIdentityVerificationResponse();
        customerIdentityVerificationResponse.setMismatchedFields(mismatchedFields);
        customerIdentityVerificationResponse.setValid(mismatchedFields.isEmpty());
        log.info("Verification response: {}", customerIdentityVerificationResponse);
        return customerIdentityVerificationResponse;
    }

    private List<String> findMismatchedFields(IdentityVerificationRequest customerData, IdentityVerificationResponse.IdentityVerificationResponseData responseData) {
        List<String> mismatchedFields = new ArrayList<>();
        if (StringUtils.getLevenshteinDistance(customerData.getFirstName(), responseData.getFirstName()) > 2) {
            mismatchedFields.add("First Name");
        }
        if (StringUtils.getLevenshteinDistance(customerData.getLastName(), responseData.getLastName()) > 2) {
            mismatchedFields.add("Last Name");
        }
        if (!StringUtils.equalsIgnoreCase(customerData.getPhoneNumber(), responseData.getMobile())) {
            mismatchedFields.add("Phone Number");
        }
        return mismatchedFields;
    }
}
