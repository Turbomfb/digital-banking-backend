/* (C)2025 */
package com.techservices.digitalbanking.authentication.domain.request;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PasswordMgtRequest {
	private String customerId;
	private String password;
	private String emailAddress;
	private String phoneNumber;
	private String uniqueId;
	private String otp;

	private String newPassword;
}
