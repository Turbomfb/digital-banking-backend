package com.techservices.digitalbanking.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techservices.digitalbanking.core.configuration.SystemProperty;
import com.techservices.digitalbanking.core.configuration.resttemplate.ApiService;
import com.techservices.digitalbanking.core.domain.dto.request.IdentityVerificationRequest;
import com.techservices.digitalbanking.core.domain.dto.response.CustomerIdentityVerificationResponse;
import com.techservices.digitalbanking.core.domain.dto.response.IdentityVerificationResponse;
import com.techservices.digitalbanking.core.exception.PlatformServiceException;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.core.fineract.model.response.GetClientsClientIdResponse;
import com.techservices.digitalbanking.core.fineract.service.ClientService;
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
    private final ClientService clientService;
    private static final String IDENTITY_VERIFICATION_URL = "/api/YouVerify/";
    private static final String NIN_DETAIL = "nin-detail";
    private static final String BVN_DETAIL = "bvn-detail";

    public IdentityVerificationResponse retrieveBvnData(String bvn) {
        log.info("Retrieving BVN data for: {}", bvn);

        String url = buildUrl(BVN_DETAIL, bvn);
        return fetchIdentityVerificationResponse(url, bvn);
    }



    public CustomerIdentityVerificationResponse verifyBvn(String bvn, IdentityVerificationRequest customerData) {
        GetClientsClientIdResponse client = clientService.getCustomerByBvn(bvn);
        IdentityVerificationResponse identityVerificationResponse = client != null ? IdentityVerificationResponse.parse(client) : retrieveBvnData(bvn);
        return processVerificationResponse(identityVerificationResponse, customerData);
    }

    public IdentityVerificationResponse retrieveNinData(String nin) {
        String url = buildUrl(NIN_DETAIL, nin);
        return fetchIdentityVerificationResponse(url, nin);
    }

    public CustomerIdentityVerificationResponse verifyNin(String nin, IdentityVerificationRequest customerData) {
        log.info("Verifying nin: {}", nin);
        GetClientsClientIdResponse client = clientService.getCustomerByNin(nin);
        IdentityVerificationResponse identityVerificationResponse = client != null ? IdentityVerificationResponse.parse(client) : retrieveNinData(nin);
        return processVerificationResponse(identityVerificationResponse, customerData);
    }

    private String buildUrl(String endpoint, String identifier) {
        return systemProperty.getPayinvertIntegrationUrl() + IDENTITY_VERIFICATION_URL + endpoint + "?"+endpoint.split("-")[0]+"=" + identifier;
    }

    private IdentityVerificationResponse fetchIdentityVerificationResponse(String url, String identifier) {
        try {
            return apiService.callExternalApi(url, IdentityVerificationResponse.class, HttpMethod.GET, null, null);
        } catch (PlatformServiceException e) {
            log.error(e.getDefaultUserMessage());
            throw new ValidationException("verification.failed","Verification failed", e.getDefaultUserMessage());
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new ValidationException("verification.failed","Verification failed", e.getMessage());
        }
    }

    private CustomerIdentityVerificationResponse processVerificationResponse(IdentityVerificationResponse response, IdentityVerificationRequest customerData) {
        if (response == null || !response.isSuccess() || response.getData() == null) {
            log.error(String.valueOf(response));
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
        if (StringUtils.getLevenshteinDistance(customerData.getFirstName(), responseData.getFirstName()) > systemProperty.getIdentityVerificationThreshold()) {
            mismatchedFields.add("First Name");
        }
        if (StringUtils.getLevenshteinDistance(customerData.getLastName(), responseData.getLastName()) > systemProperty.getIdentityVerificationThreshold()) {
            mismatchedFields.add("Last Name");
        }
        if (!StringUtils.equalsIgnoreCase(customerData.getPhoneNumber(), responseData.getMobile())) {
            mismatchedFields.add("Phone Number");
        }
        return mismatchedFields;
    }
}
