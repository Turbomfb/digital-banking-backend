/* (C)2025 */
package com.techservices.digitalbanking.customer.domian.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerTierData {
  private Long id;
  private String tierName;
  private BigDecimal maximumBalance;
  private BigDecimal singleDepositLimit;
  private BigDecimal singleWithdrawalLimit;
  private BigDecimal dailyWithdrawalLimit;
}
