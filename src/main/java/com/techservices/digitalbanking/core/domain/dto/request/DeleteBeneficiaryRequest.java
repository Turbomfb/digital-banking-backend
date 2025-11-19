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
public class DeleteBeneficiaryRequest {
	// Generate otp
	private Long beneficiaryId;

	// Verification
	private String uniqueId;
	private String otp;

	public void validateGenerate() {

		if (this.beneficiaryId == null || this.beneficiaryId <= 1) {
			throw new ValidationException("beneficiaryId.is.blank", "beneficiaryId is mandatory");
		}
	}

	public void validateVerification() {

		if (StringUtils.isBlank(this.uniqueId)) {
			throw new ValidationException("uniqueId.is.blank", "uniqueId is mandatory");
		}
		if (StringUtils.isBlank(this.otp)) {
			throw new ValidationException("otp.is.blank", "otp is mandatory");
		}
	}
}
