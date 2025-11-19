/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class PostSavingsAccountProductRequest {
  private String name;
  private String shortName;
  private String description;
  private String currencyCode;
  private int digitsAfterDecimal;
  private int inMultiplesOf;
  private String locale;
  private String nominalAnnualInterestRate;
  private int interestCompoundingPeriodType;
  private int interestPostingPeriodType;
  private int interestCalculationType;
  private String interestCalculationDaysInYearType;
  private String accountingRule;
  private List<Charge> charges;
}
