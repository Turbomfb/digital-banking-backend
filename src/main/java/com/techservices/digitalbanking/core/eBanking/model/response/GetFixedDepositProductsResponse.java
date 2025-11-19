/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetFixedDepositProductsResponse {
  private GetFixedDepositProductsAccountingRule accountingRule;

  private GetSavingsCurrency currency;

  private String description;

  private Integer id;

  private EnumOptionData interestCalculationDaysInYearType;

  private EnumOptionData interestCalculationType;

  private EnumOptionData interestCompoundingPeriodType;

  private EnumOptionData interestPostingPeriodType;

  private Integer maxDepositTerm;

  private EnumOptionData maxDepositTermType;

  private Integer minDepositTerm;

  private EnumOptionData minDepositTermType;

  private String name;

  private Double nominalAnnualInterestRate;

  private Boolean preClosurePenalApplicable;

  private String shortName;
}
