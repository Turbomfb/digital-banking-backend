/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.savingsaccount.domain.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Setter
@Getter
@Builder
public class SavingsInterestBreakdownResponse {
    private LocalDate date;
    private BigDecimal interestEarned;
    private BigDecimal closingBalance;
}
