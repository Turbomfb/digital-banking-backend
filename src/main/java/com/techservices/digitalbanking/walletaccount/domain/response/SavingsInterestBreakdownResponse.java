/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.walletaccount.domain.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@Builder
public class SavingsInterestBreakdownResponse {
    private LocalDate date;
    private BigDecimal interestEarned;
    private BigDecimal closingBalance;
}
