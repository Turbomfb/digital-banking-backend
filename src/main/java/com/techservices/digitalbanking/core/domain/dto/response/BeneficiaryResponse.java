/* (C)2025 */
package com.techservices.digitalbanking.core.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.techservices.digitalbanking.core.domain.data.model.Beneficiary;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BeneficiaryResponse {

  private Long id;
  private String accountName;
  private String accountNumber;
  private String bankName;
  private String bankCode;
  private String nickname;
  private Boolean isVerified;
  private Boolean isActive;
  private LocalDateTime createdAt;
  private LocalDateTime lastUsedAt;
  private Integer usageCount;

  public static BeneficiaryResponse from(Beneficiary beneficiary) {

    BeneficiaryResponse response = new BeneficiaryResponse();
    response.setId(beneficiary.getId());
    response.setAccountName(beneficiary.getAccountName());
    response.setAccountNumber(beneficiary.getAccountNumber());
    response.setBankName(beneficiary.getBankName());
    response.setBankCode(beneficiary.getBankCode());
    response.setNickname(beneficiary.getNickname());
    response.setIsVerified(beneficiary.getIsVerified());
    response.setIsActive(beneficiary.getIsActive());
    response.setCreatedAt(beneficiary.getCreatedAt());
    response.setLastUsedAt(beneficiary.getLastUsedAt());
    response.setUsageCount(beneficiary.getUsageCount());
    return response;
  }
}
