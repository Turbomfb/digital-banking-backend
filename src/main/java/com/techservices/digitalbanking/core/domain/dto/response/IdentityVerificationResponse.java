package com.techservices.digitalbanking.core.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.techservices.digitalbanking.core.domain.data.model.IdentityVerificationData;
import com.techservices.digitalbanking.core.fineract.model.response.GetClientsClientIdResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class IdentityVerificationResponse {
    private IdentityVerificationResponseData data;
    private Object errors;
    private boolean success;
    private String statusMessage;
    private String statusCode;
    private String message;

    public static IdentityVerificationResponse parse(GetClientsClientIdResponse client) {
        IdentityVerificationResponse identityVerificationResponse = new IdentityVerificationResponse();
        IdentityVerificationResponse.IdentityVerificationResponseData identityVerificationResponseData = new IdentityVerificationResponse.IdentityVerificationResponseData();
        identityVerificationResponseData.setFirstName(client.getFirstname());
        identityVerificationResponseData.setLastName(client.getLastname());
        identityVerificationResponseData.setMobile(client.getMobileNo());
        identityVerificationResponse.setData(identityVerificationResponseData);
        identityVerificationResponse.setSuccess(true);
        return identityVerificationResponse;
    }

    public static IdentityVerificationResponse parse(IdentityVerificationData identityVerificationData) {
        IdentityVerificationResponse identityVerificationResponse = new IdentityVerificationResponse();
        IdentityVerificationResponse.IdentityVerificationResponseData identityVerificationResponseData = new IdentityVerificationResponse.IdentityVerificationResponseData();
        identityVerificationResponseData.setId(identityVerificationData.getId());
        identityVerificationResponseData.setFirstName(identityVerificationData.getFirstName());
        identityVerificationResponseData.setMiddleName(identityVerificationData.getMiddleName());
        identityVerificationResponseData.setLastName(identityVerificationData.getLastName());
        identityVerificationResponseData.setMobile(identityVerificationData.getMobile());
        identityVerificationResponseData.setEmail(identityVerificationData.getEmail());
        identityVerificationResponse.setData(identityVerificationResponseData);
        identityVerificationResponse.setSuccess(true);
        return identityVerificationResponse;
    }

    @Setter
    @Getter
    @ToString
    public static class IdentityVerificationResponseData {
        private String id;
        private String firstName;
        private String middleName;
        private String lastName;
        private String mobile;
        private String email;
        private String dateOfBirth;
        private String gender;
        private String image;
        private String religion;
        private String signature;
    }
}
