/* (C)2024 */
package com.techservices.digitalbanking.walletaccount.domain.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class SavingsInterestBreakdownResponse {
  private LocalDate date;
  private BigDecimal interestEarned;
  private BigDecimal closingBalance;
}
