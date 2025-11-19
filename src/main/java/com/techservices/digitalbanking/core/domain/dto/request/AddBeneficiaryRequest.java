/* (C)2025 */
package com.techservices.digitalbanking.core.domain.dto.request;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.techservices.digitalbanking.core.exception.ValidationException;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddBeneficiaryRequest {

	// generate
	private String accountName;
	private String accountNumber;
	private String bankName;
	private String bankCode;
	private String nickname;

	// verify
	private String uniqueId;
	private String otp;

	public void validate() {

		if (StringUtils.isBlank(this.accountName)) {
			throw new ValidationException("accountName.field.cannot.be.blank", "Account name cannot be empty");
		}
		if (StringUtils.isBlank(this.accountNumber)) {
			throw new ValidationException("accountNumber.field.cannot.be.blank", "Account number cannot be empty");
		}
		if (this.accountNumber.length() != 10) {
			throw new ValidationException("accountNumber.invalid.length", "Account number must be 10 digits");
		}
		if (!this.accountNumber.matches("\\d+")) {
			throw new ValidationException("accountNumber.invalid.format", "Account number must contain only digits");
		}
		if (StringUtils.isBlank(this.bankName)) {
			throw new ValidationException("bankName.field.cannot.be.blank", "Bank name cannot be empty");
		}
		if (StringUtils.isBlank(this.bankCode)) {
			throw new ValidationException("bankCode.field.cannot.be.blank", "Bank code cannot be empty");
		}
		if (StringUtils.isNotBlank(this.nickname) && this.nickname.length() > 25) {
			throw new ValidationException("nickname.too.long", "Nickname cannot exceed 25 characters");
		}
	}
}
