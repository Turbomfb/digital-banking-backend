package com.techservices.digitalbanking.core.domain.dto.request;

import com.techservices.digitalbanking.core.domain.enums.OtpType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OtpDtoRequest {
    private String uniqueId;
    private String otp;
    private Object data;
    private OtpType otpType;
}
