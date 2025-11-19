/* (C)2025 */
package com.techservices.digitalbanking.loan.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class LoanDashboardResponse {
  private BigDecimal activeLoanBalance;
  private BigDecimal totalExpectedRepayment;
  private BigDecimal totalRepaid;
  private Long activeLoanCount;
  private Long pendingLoanCount;
  private Long liquidatedLoanCount;
}
