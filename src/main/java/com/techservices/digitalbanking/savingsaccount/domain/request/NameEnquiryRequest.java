package com.techservices.digitalbanking.savingsaccount.domain.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NameEnquiryRequest {
    private String accountNumber;
    private String bankCode;
    @JsonProperty("isSubjectConsent")
    private boolean isSubjectConsent;
}
