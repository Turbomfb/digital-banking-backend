package com.techservices.digitalbanking.core.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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

    @Setter
    @Getter
    public static class IdentityVerificationResponseData {
        private String id;
        private String firstName;
        private String middleName;
        private String lastName;
        private String mobile;
        private String email;
    }
}
