/* (C)2025 */
package com.techservices.digitalbanking.core.domain.dto.request;

import com.techservices.digitalbanking.core.domain.enums.OtpType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class OtpDto {
	private String uniqueId;
	private String otp;
	private Object data;
	private OtpType otpType;
	private boolean isValidated;
}
