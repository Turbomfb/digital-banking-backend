package com.techservices.digitalbanking.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techservices.digitalbanking.core.configuration.SystemProperty;
import com.techservices.digitalbanking.core.configuration.resttemplate.ApiService;
import com.techservices.digitalbanking.core.domain.data.model.IdentityVerificationData;
import com.techservices.digitalbanking.core.domain.data.repository.IdentityVerificationDataRepository;
import com.techservices.digitalbanking.core.domain.dto.request.IdentityVerificationRequest;
import com.techservices.digitalbanking.core.domain.dto.response.CustomerIdentityVerificationResponse;
import com.techservices.digitalbanking.core.domain.dto.response.IdentityVerificationResponse;
import com.techservices.digitalbanking.core.domain.enums.IdentityVerificationType;
import com.techservices.digitalbanking.core.exception.PlatformServiceException;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.core.fineract.model.response.GetClientsClientIdResponse;
import com.techservices.digitalbanking.core.fineract.service.ClientService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class IdentityVerificationService {

    private final ApiService apiService;
    private final SystemProperty systemProperty;
    private final ClientService clientService;
    private final IdentityVerificationDataRepository identityVerificationDataRepository;
    private static final String IDENTITY_VERIFICATION_URL = "/v2/api/identity/ng/";
    private static final String NIN_DETAIL = "nin";
    private static final String BVN_DETAIL = "bvn";

    @Transactional
    public IdentityVerificationResponse retrieveBvnData(String bvn) {
        log.info("Retrieving BVN data for: {}", bvn);

        String url = buildUrl(BVN_DETAIL);
        Optional<IdentityVerificationData> data = identityVerificationDataRepository.findByIdentifierAndType(bvn, IdentityVerificationType.BVN.name());
        if (data.isPresent()) {
            return IdentityVerificationResponse.parse(data.get());
        }
        IdentityVerificationResponse verificationResponse = fetchIdentityVerificationResponse(url, bvn);
        if (!verificationResponse.isSuccess()) {
            throw new ValidationException("validation.error.exists", "BVN is invalid");
        }
        IdentityVerificationData identityVerificationData = IdentityVerificationData.parse(verificationResponse);
        identityVerificationData.setType(IdentityVerificationType.BVN.name());
        identityVerificationData.setIdentifier(bvn);
        identityVerificationDataRepository.save(identityVerificationData);
        return verificationResponse;
    }



    public CustomerIdentityVerificationResponse verifyBvn(String bvn, IdentityVerificationRequest customerData) {
        GetClientsClientIdResponse client = clientService.getCustomerByBvn(bvn);
        IdentityVerificationResponse identityVerificationResponse = client != null ? IdentityVerificationResponse.parse(client) : retrieveBvnData(bvn);
        return processVerificationResponse(identityVerificationResponse, customerData);
    }

    @Transactional
    public IdentityVerificationResponse retrieveNinData(String nin) {
        String url = buildUrl(NIN_DETAIL);
        Optional<IdentityVerificationData> data = identityVerificationDataRepository.findByIdentifierAndType(nin, IdentityVerificationType.NIN.name());
        if (data.isPresent()) {
            return IdentityVerificationResponse.parse(data.get());
        }
        IdentityVerificationResponse verificationResponse = fetchIdentityVerificationResponse(url, nin);
        if (!verificationResponse.isSuccess()) {
            throw new ValidationException("validation.error.exists", "NIN is invalid");
        }
        IdentityVerificationData identityVerificationData = IdentityVerificationData.parse(verificationResponse);
        identityVerificationData.setType(IdentityVerificationType.NIN.name());
        identityVerificationData.setIdentifier(nin);
        log.info("Saving identity verification data: {}", identityVerificationData);
        identityVerificationDataRepository.save(identityVerificationData);
        return verificationResponse;
    }

    public CustomerIdentityVerificationResponse verifyNin(String nin, IdentityVerificationRequest customerData) {
        log.info("Verifying nin: {}", nin);
        GetClientsClientIdResponse client = clientService.getCustomerByNin(nin);
        IdentityVerificationResponse identityVerificationResponse = client != null ? IdentityVerificationResponse.parse(client) : retrieveNinData(nin);
        return processVerificationResponse(identityVerificationResponse, customerData);
    }

    private String buildUrl(String endpoint) {
        return systemProperty.getYouverifyIntegrationUrl() + IDENTITY_VERIFICATION_URL + endpoint;
    }

    private IdentityVerificationResponse fetchIdentityVerificationResponse(String url, String identifier) {
        try {
            Map<String, Object> requestPayload = new HashMap<>();
            requestPayload.put("id", identifier);
            requestPayload.put("isSubjectConsent", true);
            HttpHeaders headers = new HttpHeaders();
            headers.set("token", systemProperty.getYouverifyIntegrationApiKey());
            return apiService.callExternalApi(url, IdentityVerificationResponse.class, HttpMethod.POST, requestPayload, headers);
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
        if (StringUtils.getLevenshteinDistance(
                customerData.getFirstName().toLowerCase(),
                responseData.getFirstName().toLowerCase()) > systemProperty.getIdentityVerificationThreshold()) {
            mismatchedFields.add("First Name");
        }

        if (StringUtils.getLevenshteinDistance(customerData.getLastName().toLowerCase(), responseData.getLastName().toLowerCase()) > systemProperty.getIdentityVerificationThreshold()) {
            mismatchedFields.add("Last Name");
        }
        if (!StringUtils.equalsIgnoreCase(customerData.getPhoneNumber(), responseData.getMobile())) {
            mismatchedFields.add("Phone Number");
        }
        return mismatchedFields;
    }
}
