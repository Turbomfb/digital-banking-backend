/* (C)2024 */
package com.techservices.digitalbanking.core.domain.dto;

import com.techservices.digitalbanking.core.domain.data.model.KycTier;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class KycTierDto {

  private String tierName;

  private BigDecimal maximumBalance;

  private BigDecimal singleDepositLimit;

  private BigDecimal dailyWithdrawalLimit;

  private BigDecimal singleWithdrawalLimit;

  public static KycTierDto parse(KycTier kycTier) {

    KycTierDto kycTierDto = new KycTierDto();
    kycTierDto.setTierName(kycTier.getTierName());
    kycTierDto.setMaximumBalance(kycTier.getMaximumBalance());
    kycTierDto.setSingleDepositLimit(kycTier.getSingleDepositLimit());
    kycTierDto.setDailyWithdrawalLimit(kycTier.getDailyWithdrawalLimit());
    kycTierDto.setSingleWithdrawalLimit(kycTier.getSingleWithdrawalLimit());
    return kycTierDto;
  }
}
