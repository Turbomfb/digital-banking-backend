/* (C)2025 */
package com.techservices.digitalbanking.core.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.techservices.digitalbanking.core.exception.ValidationException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

@Setter
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateBeneficiaryRequest {

  // Generate otp
  private String nickname;
  private Long beneficiaryId;

  // Verification
  private String uniqueId;
  private String otp;

  public void validateGenerate() {

    if (StringUtils.isNotBlank(this.nickname) && this.nickname.length() > 25) {
      throw new ValidationException("nickname.too.long", "Nickname cannot exceed 25 characters");
    }
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
