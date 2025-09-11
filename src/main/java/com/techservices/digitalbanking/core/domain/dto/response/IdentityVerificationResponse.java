package com.techservices.digitalbanking.core.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.techservices.digitalbanking.core.domain.data.model.IdentityVerificationData;
import com.techservices.digitalbanking.core.fineract.model.response.GetClientsClientIdResponse;
import io.micrometer.common.util.StringUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    private String dataSource;


    @Setter
    @Getter
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
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

        private Address address;

//        for business
        private String name;


        @Setter
        @Getter
        @ToString
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Address {
            private String town;
            private String lga;
            private String state;
            private String addressLine;
        }
    }


    public static IdentityVerificationResponse parse(BusinessDataResponse.BusinessData businessData) {
        IdentityVerificationResponse identityVerificationResponse = new IdentityVerificationResponse();
        IdentityVerificationResponse.IdentityVerificationResponseData identityVerificationResponseData = new IdentityVerificationResponse.IdentityVerificationResponseData();
        identityVerificationResponseData.setName(businessData.getName());
        identityVerificationResponseData.setMobile(businessData.getPhone());
        identityVerificationResponseData.setEmail(businessData.getEmail());
        identityVerificationResponse.setData(identityVerificationResponseData);
        identityVerificationResponse.setSuccess(true);
        identityVerificationResponse.setDataSource("INTERNAL");
        return identityVerificationResponse;
    }


    public static IdentityVerificationResponse parse(BusinessDataResponse.BusinessData businessData, String source) {
        IdentityVerificationResponse identityVerificationResponse =IdentityVerificationResponse.parse(businessData);
        identityVerificationResponse.setDataSource(source);
        return identityVerificationResponse;
    }

    public boolean isSuccess() {
        return success && errors == null && data != null && ((StringUtils.isNotBlank(data.firstName) && StringUtils.isNotBlank(data.lastName) || StringUtils.isNotBlank(data.name)));
    }

    public static IdentityVerificationResponse parse(GetClientsClientIdResponse client) {
        IdentityVerificationResponse identityVerificationResponse = new IdentityVerificationResponse();
        IdentityVerificationResponse.IdentityVerificationResponseData identityVerificationResponseData = new IdentityVerificationResponse.IdentityVerificationResponseData();
        identityVerificationResponseData.setFirstName(client.getFirstname());
        identityVerificationResponseData.setLastName(client.getLastname());
        identityVerificationResponseData.setMobile(client.getMobileNo());
        identityVerificationResponseData.setEmail(client.getEmailAddress());
        identityVerificationResponse.setData(identityVerificationResponseData);
        identityVerificationResponse.setSuccess(true);
        identityVerificationResponse.setDataSource("INTERNAL");
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
        identityVerificationResponseData.setReligion(identityVerificationData.getReligion());
        identityVerificationResponseData.setEmail(identityVerificationData.getEmail());
        identityVerificationResponseData.setGender(identityVerificationData.getGender());
        identityVerificationResponseData.setDateOfBirth(identityVerificationData.getDateOfBirth());
        identityVerificationResponse.setData(identityVerificationResponseData);
        identityVerificationResponse.setSuccess(true);
        identityVerificationResponse.setDataSource("EXTERNAL");
        return identityVerificationResponse;
    }
}
