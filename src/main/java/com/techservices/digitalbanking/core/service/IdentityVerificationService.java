/* (C)2025 */
package com.techservices.digitalbanking.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techservices.digitalbanking.core.configuration.SystemProperty;
import com.techservices.digitalbanking.core.configuration.resttemplate.ApiService;
import com.techservices.digitalbanking.core.domain.data.model.IdentityVerificationData;
import com.techservices.digitalbanking.core.domain.data.repository.IdentityVerificationDataRepository;
import com.techservices.digitalbanking.core.domain.dto.request.BusinessDataRequest;
import com.techservices.digitalbanking.core.domain.dto.request.ImageComparisonRequest;
import com.techservices.digitalbanking.core.domain.dto.response.BusinessDataResponse;
import com.techservices.digitalbanking.core.domain.dto.response.CustomerIdentityVerificationResponse;
import com.techservices.digitalbanking.core.domain.dto.response.IdentityVerificationResponse;
import com.techservices.digitalbanking.core.domain.dto.response.ImageComparisonResponse;
import com.techservices.digitalbanking.core.domain.enums.IdentityVerificationDataType;
import com.techservices.digitalbanking.core.eBanking.service.ClientService;
import com.techservices.digitalbanking.core.exception.PlatformServiceException;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

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
    Optional<IdentityVerificationData> data =
        identityVerificationDataRepository.findByIdentifierAndType(
            bvn, IdentityVerificationDataType.BVN.name());
    if (data.isPresent()) {
      log.info("Bvn data found for: {} <==> {}", bvn, data.get());
      return IdentityVerificationResponse.parse(data.get());
    }
    IdentityVerificationResponse verificationResponse = fetchIdentityVerificationResponse(url, bvn);
    if (!verificationResponse.isSuccess()) {
      throw new ValidationException(IdentityVerificationDataType.BVN);
    }
    IdentityVerificationData identityVerificationData =
        IdentityVerificationData.parse(verificationResponse);
    identityVerificationData.setType(IdentityVerificationDataType.BVN.name());
    identityVerificationData.setIdentifier(bvn);
    identityVerificationData.setCreatedAt(LocalDateTime.now());
    identityVerificationDataRepository.save(identityVerificationData);
    verificationResponse.setDataSource("EXTERNAL");
    return verificationResponse;
  }

  public CustomerIdentityVerificationResponse verifyBvn(String bvn, Customer foundCustomer) {

    IdentityVerificationResponse identityVerificationResponse = retrieveBvnData(bvn);
    return processVerificationResponse(
        identityVerificationResponse, foundCustomer, IdentityVerificationDataType.BVN);
  }

  @Transactional
  public IdentityVerificationResponse retrieveNinData(String nin) {

    log.info("Retrieving NIN data for: {}", nin);

    String url = buildUrl(NIN_DETAIL);
    Optional<IdentityVerificationData> data =
        identityVerificationDataRepository.findByIdentifierAndType(
            nin, IdentityVerificationDataType.NIN.name());
    if (data.isPresent()) {
      log.info("Nin data found for: {} <==> {}", nin, data.get());
      return IdentityVerificationResponse.parse(data.get());
    }
    IdentityVerificationResponse verificationResponse = fetchIdentityVerificationResponse(url, nin);
    if (!verificationResponse.isSuccess()) {
      throw new ValidationException(IdentityVerificationDataType.NIN);
    }
    IdentityVerificationData identityVerificationData =
        IdentityVerificationData.parse(verificationResponse);
    identityVerificationData.setType(IdentityVerificationDataType.NIN.name());
    identityVerificationData.setIdentifier(nin);
    identityVerificationData.setCreatedAt(LocalDateTime.now());
    log.info("Saving identity verification data: {}", identityVerificationData);
    identityVerificationDataRepository.save(identityVerificationData);
    verificationResponse.setDataSource("EXTERNAL");
    return verificationResponse;
  }

  public CustomerIdentityVerificationResponse verifyNin(String nin, Customer foundCustomer) {

    log.info("Verifying nin: {}", nin);
    IdentityVerificationResponse identityVerificationResponse = retrieveNinData(nin);
    return processVerificationResponse(
        identityVerificationResponse, foundCustomer, IdentityVerificationDataType.NIN);
  }

  public CustomerIdentityVerificationResponse verifyRcNumber(
      String rcNumber, Customer foundCustomer) {

    log.info("Verifying rcNumber: {}", rcNumber);
    BusinessDataResponse businessDataResponse = this.retrieveRcNumberData(rcNumber);
    IdentityVerificationResponse identityVerificationResponse =
        IdentityVerificationResponse.parse(businessDataResponse.getData());
    return processVerificationResponse(
        identityVerificationResponse, foundCustomer, IdentityVerificationDataType.RC_NUMBER);
  }

  public CustomerIdentityVerificationResponse verifyTin(String tin, Customer foundCustomer) {

    log.info("Verifying tin: {}", tin);
    BusinessDataResponse businessDataResponse = this.retrieveTinData(tin);
    IdentityVerificationResponse identityVerificationResponse =
        IdentityVerificationResponse.parse(businessDataResponse.getData());
    return processVerificationResponse(
        identityVerificationResponse, foundCustomer, IdentityVerificationDataType.TIN);
  }

  public String buildUrl(String endpoint) {

    return systemProperty.getYouverifyIntegrationUrl() + IDENTITY_VERIFICATION_URL + endpoint;
  }

  public IdentityVerificationResponse fetchIdentityVerificationResponse(
      String url, String identifier) {

    try {
      Map<String, Object> requestPayload = new HashMap<>();
      requestPayload.put("id", identifier);
      requestPayload.put("isSubjectConsent", true);
      HttpHeaders headers = getTokenHeader();
      return apiService.callExternalApi(
          url, IdentityVerificationResponse.class, HttpMethod.POST, requestPayload, headers);
    } catch (PlatformServiceException e) {
      log.error(e.getDefaultUserMessage());
      throw new ValidationException(
          "verification.failed", "Verification failed", e.getDefaultUserMessage());
    } catch (JsonProcessingException e) {
      log.error(e.getMessage());
      throw new ValidationException("verification.failed", "Verification failed", e.getMessage());
    }
  }

  private ImageComparisonResponse compareImage(ImageComparisonRequest imageComparisonRequest) {

    try {
      String url = systemProperty.getYouverifyIntegrationUrl() + "/v2/api/identity/compare-image";
      HttpHeaders headers = getTokenHeader();
      return apiService.callExternalApi(
          url, ImageComparisonResponse.class, HttpMethod.POST, imageComparisonRequest, headers);
    } catch (PlatformServiceException e) {
      log.error(e.getDefaultUserMessage());
      throw new ValidationException(
          "verification.failed", "Verification failed", e.getDefaultUserMessage());
    } catch (JsonProcessingException e) {
      log.error(e.getMessage());
      throw new ValidationException("verification.failed", "Verification failed", e.getMessage());
    }
  }

  private CustomerIdentityVerificationResponse processVerificationResponse(
      IdentityVerificationResponse response,
      Customer foundCustomer,
      IdentityVerificationDataType dataType) {

    if (response == null || !response.isSuccess() || response.getData() == null) {
      log.error(String.valueOf(response));
      throw new ValidationException("verification.failed", "Verification failed for identifier.");
    }

    List<String> mismatchedFields = findMismatchedFields(response, foundCustomer, dataType);

    CustomerIdentityVerificationResponse customerIdentityVerificationResponse =
        new CustomerIdentityVerificationResponse();
    customerIdentityVerificationResponse.setMismatchedFields(mismatchedFields);
    customerIdentityVerificationResponse.setValid(mismatchedFields.isEmpty());
    log.info("Verification response: {}", customerIdentityVerificationResponse);
    return customerIdentityVerificationResponse;
  }

  private List<String> findMismatchedFields(
      IdentityVerificationResponse verificationResponse,
      Customer customerData,
      IdentityVerificationDataType dataType) {

    IdentityVerificationResponse.IdentityVerificationResponseData responseData =
        verificationResponse.getData();
    List<String> mismatchedFields = new ArrayList<>();
    if (dataType.isIndividual()) {
      if (StringUtils.getLevenshteinDistance(
              customerData.getFirstname().toLowerCase(), responseData.getFirstName().toLowerCase())
          > systemProperty.getIdentityVerificationThreshold()) {
        mismatchedFields.add("First Name");
      }
      if (StringUtils.getLevenshteinDistance(
              customerData.getLastname().toLowerCase(), responseData.getLastName().toLowerCase())
          > systemProperty.getIdentityVerificationThreshold()) {
        mismatchedFields.add("Last Name");
      }
    } else if (dataType.isCorporate()) {
      if (StringUtils.getLevenshteinDistance(
              customerData.getBusinessName().toLowerCase(), responseData.getName().toLowerCase())
          > systemProperty.getIdentityVerificationThreshold()) {
        mismatchedFields.add("Business Name");
      }
    }
    log.info("Mismatched fields for {} are {}", customerData.getPhoneNumber(), mismatchedFields);
    return mismatchedFields;
  }

  @Transactional
  public void validateImageMismatch(String base64Image, String bvn, String nin) {

    if (StringUtils.isBlank(bvn) && StringUtils.isBlank(nin)) {
      throw new ValidationException("validation.error.imageMismatch", "Bvn or Nin is required");
    }
    if (StringUtils.isNotBlank(base64Image)) {
      IdentityVerificationResponse data;
      if (StringUtils.isNotBlank(bvn)) {
        data = this.retrieveBvnData(bvn);
      } else {
        data = this.retrieveNinData(nin);
      }
      String image2 = data.getData().getImage();
      ImageComparisonRequest imageComparisonRequest =
          ImageComparisonRequest.builder()
              .isSubjectConsent(true)
              .image1(image2)
              .image2(base64Image)
              .build();
      ImageComparisonResponse compareResponse = this.compareImage(imageComparisonRequest);
      log.info("Verification response: {}", compareResponse);
      if (compareResponse.isSuccess()) {
        if (compareResponse.getData() == null
            || compareResponse.getData().getImageComparison() == null
            || compareResponse.getData().getImageComparison().getMatch() == null
            || !compareResponse.getData().getImageComparison().getMatch()) {
          log.info("Provided image does not match ID image");
          throw new ValidationException(
              "validation.error.imageMismatch", "Provided image does not match ID image");
        } else return;
      }
      throw new ValidationException(
          "validation.error.imageMismatch", "Provided image does not match ID");
    }
  }

  @Transactional
  public BusinessDataResponse retrieveRcNumberData(String rcNumber) {

    log.info("Retrieving RC Number data for: {}", rcNumber);

    try {
      Optional<IdentityVerificationData> data =
          identityVerificationDataRepository.findByIdentifierAndType(
              rcNumber, IdentityVerificationDataType.RC_NUMBER.name());
      if (data.isPresent()) {
        log.info("RC Number data found in cache for: {} <==> {}", rcNumber, data.get());
        return convertToBusinessDataResponse(data.get());
      }

      String url =
          systemProperty.getYouverifyIntegrationUrl()
              + "/v2/api/verifications/global/company-advance-check";
      BusinessDataRequest businessDataRequest = new BusinessDataRequest();
      businessDataRequest.setCountryCode("NG");
      businessDataRequest.setRegistrationNumber(rcNumber);
      HttpHeaders headers = getTokenHeader();

      BusinessDataResponse response =
          apiService.callExternalApi(
              url, BusinessDataResponse.class, HttpMethod.POST, businessDataRequest, headers);

      if (response == null
          || !response.isSuccess()
          || response.getData() == null
          || StringUtils.equalsIgnoreCase(response.getData().getStatus(), "not_found")) {
        log.error("Business data retrieval failed for RC number: {}", rcNumber);
        throw new ValidationException("verification.failed", "Verification failed for RC number.");
      }

      IdentityVerificationData verificationData = IdentityVerificationData.parse(response);
      verificationData.setType(IdentityVerificationDataType.RC_NUMBER.name());
      verificationData.setIdentifier(rcNumber);
      verificationData.setCreatedAt(LocalDateTime.now());
      log.info("Saving RC Number verification data: {}", verificationData);
      identityVerificationDataRepository.save(verificationData);

      response.setSuccess(true);
      return response;
    } catch (PlatformServiceException e) {
      log.error(e.getDefaultUserMessage());
      throw new ValidationException(
          "verification.failed", "Verification failed", e.getDefaultUserMessage());
    } catch (JsonProcessingException e) {
      log.error(e.getMessage());
      throw new ValidationException("verification.failed", "Verification failed", e.getMessage());
    }
  }

  @Transactional
  public BusinessDataResponse retrieveTinData(String tin) {

    log.info("Retrieving TIN data for: {}", tin);

    try {
      Optional<IdentityVerificationData> data =
          identityVerificationDataRepository.findByIdentifierAndType(
              tin, IdentityVerificationDataType.TIN.name());
      if (data.isPresent()) {
        log.info("TIN data found in cache for: {} <==> {}", tin, data.get());
        return convertToBusinessDataResponse(data.get());
      }

      String url = systemProperty.getYouverifyIntegrationUrl() + "/v2/api/verifications/ng/tin";
      BusinessDataRequest businessDataRequest = new BusinessDataRequest();
      businessDataRequest.setTin(tin);
      HttpHeaders headers = getTokenHeader();

      BusinessDataResponse response =
          apiService.callExternalApi(
              url, BusinessDataResponse.class, HttpMethod.POST, businessDataRequest, headers);

      if (response == null
          || !response.isSuccess()
          || response.getData() == null
          || StringUtils.equalsIgnoreCase(response.getData().getStatus(), "not_found")) {
        log.error("Business data retrieval failed for TIN: {}", tin);
        throw new ValidationException("verification.failed", "Verification failed for TIN.");
      }

      IdentityVerificationData verificationData = IdentityVerificationData.parse(response);
      verificationData.setType(IdentityVerificationDataType.TIN.name());
      verificationData.setIdentifier(tin);
      verificationData.setCreatedAt(LocalDateTime.now());
      log.info("Saving TIN verification data: {}", verificationData);
      identityVerificationDataRepository.save(verificationData);

      response.setSuccess(true);
      return response;
    } catch (PlatformServiceException e) {
      log.error(e.getDefaultUserMessage());
      throw new ValidationException(
          "verification.failed", "Verification failed", e.getDefaultUserMessage());
    } catch (JsonProcessingException e) {
      log.error(e.getMessage());
      throw new ValidationException("verification.failed", "Verification failed", e.getMessage());
    }
  }

  private BusinessDataResponse convertToBusinessDataResponse(IdentityVerificationData data) {

    BusinessDataResponse response = new BusinessDataResponse();
    response.setSuccess(true);
    response.setStatusCode(200);
    response.setMessage("Data retrieved from cache");

    BusinessDataResponse.BusinessData businessData = response.new BusinessData();
    businessData.setId(data.getId());
    businessData.setName(data.getBusinessName());
    businessData.setRegistrationNumber(data.getRegistrationNumber());
    businessData.setRegistrationDate(data.getRegistrationDate());
    businessData.setTin(data.getTin());
    businessData.setVatNumber(data.getVatNumber());
    businessData.setCompanyStatus(data.getCompanyStatus());
    businessData.setTypeOfEntity(data.getBusinessType());
    businessData.setActivity(data.getActivity());
    businessData.setEmail(data.getBusinessEmail());
    businessData.setPhone(data.getBusinessPhone());
    businessData.setHeadOfficeAddress(data.getHeadOfficeAddress());
    businessData.setBranchAddress(data.getBranchAddress());
    businessData.setObjectives(data.getObjectives());
    businessData.setPaidShareCapital(data.getShareCapital());
    businessData.setCountryCode(data.getCountryCode());
    businessData.setStatus(data.getStatus());
    businessData.setState(data.getState());
    businessData.setLga(data.getLga());
    businessData.setAddress(data.getAddressLine());
    businessData.setCity(data.getTown());

    response.setData(businessData);
    return response;
  }

  private HttpHeaders getTokenHeader() {

    HttpHeaders headers = new HttpHeaders();
    headers.set("token", systemProperty.getYouverifyIntegrationApiKey());
    return headers;
  }
}
