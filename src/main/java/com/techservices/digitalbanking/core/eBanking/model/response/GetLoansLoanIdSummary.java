/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/** GetLoansLoanIdSummary */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetLoansLoanIdSummary {

  private GetLoansLoanIdCurrency currency;

  private Long principalDisbursed;
  private Long principalPaid;
  private Long principalWrittenOff;
  private Long principalOutstanding;
  private BigDecimal principalOverdue;

  private Long interestCharged;
  private Long interestPaid;
  private Long interestWaived;
  private Long interestWrittenOff;
  private Long interestOutstanding;
  private Long interestOverdue;

  private Long feeChargesCharged;
  private Long feeChargesDueAtDisbursementCharged;
  private Long feeChargesPaid;
  private Long feeChargesWaived;
  private Long feeChargesWrittenOff;
  private Long feeChargesOutstanding;
  private Long feeChargesOverdue;

  private Long penaltyChargesCharged;
  private Long penaltyChargesPaid;
  private Long penaltyChargesWaived;
  private Long penaltyChargesWrittenOff;
  private Long penaltyChargesOutstanding;
  private Long penaltyChargesOverdue;

  private BigDecimal totalExpectedRepayment;
  private BigDecimal totalRepayment;
  private Long totalExpectedCostOfLoan;
  private Long totalCostOfLoan;
  private Long totalWaived;
  private Long totalWrittenOff;
  private BigDecimal totalOutstanding;
  private BigDecimal totalOverdue;

  @JsonProperty("overdueSinceDate")
  private LocalDate overdueSinceDate;

  private GetLoansLoanIdLinkedAccount linkedAccount;
}
