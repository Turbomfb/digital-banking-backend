package com.techservices.digitalbanking.core.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.techservices.digitalbanking.core.domain.BaseAppResponse;
import com.techservices.digitalbanking.core.util.AppUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class GenericApiResponse extends BaseAppResponse {
    private String uniqueId;
    private String message;
    private String status;
    private Object data;

    public GenericApiResponse() {
    }

    public GenericApiResponse(String message, String status, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public GenericApiResponse(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public GenericApiResponse(String uniqueId, String message, String status, Object data) {
        this.uniqueId = uniqueId;
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public GenericApiResponse(String uniqueId, String phoneNumber, String emailAddress, boolean isOtp) {
        this.uniqueId = uniqueId;
        this.status = "success";
        if (isOtp) {
            if (StringUtils.isNoneBlank(phoneNumber, emailAddress)) {
                this.message = "We sent an OTP to " + AppUtil.maskPhoneNumber(phoneNumber) + " and " + AppUtil.maskEmailAddress(emailAddress);
            } else if (StringUtils.isNotBlank(emailAddress)) {
                this.message = "We sent an OTP to " + AppUtil.maskEmailAddress(emailAddress);
            } else if (StringUtils.isNotBlank(phoneNumber)) {
                this.message = "We sent an OTP to " + AppUtil.maskPhoneNumber(phoneNumber);
            }
        }
    }
}
