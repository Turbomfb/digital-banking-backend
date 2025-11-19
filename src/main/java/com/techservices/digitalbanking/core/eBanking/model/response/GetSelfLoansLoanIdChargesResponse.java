/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import lombok.Getter;
import lombok.Setter;

/** GetSelfLoansLoanIdChargesResponse */
@Getter
@Setter
public class GetSelfLoansLoanIdChargesResponse {

  private Double amount;

  private Double amountOrPercentage;

  private Double amountOutstanding;

  private Double amountPaid;

  private Double amountPercentageAppliedTo;

  private Double amountWaived;

  private Double amountWrittenOff;

  private GetSelfLoansChargeCalculationType chargeCalculationType;

  private Integer chargeId;

  private GetSelfLoansChargeTimeType chargeTimeType;

  private GetLoanCurrency currency;

  private Integer id;

  private String name;

  private Boolean penalty;

  private Double percentage;
}
