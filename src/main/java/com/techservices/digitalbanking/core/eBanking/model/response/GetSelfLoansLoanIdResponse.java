/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import lombok.Getter;
import lombok.Setter;

/** GetSelfLoansLoanIdResponse */
@Getter
@Setter
public class GetSelfLoansLoanIdResponse {

  private Long accountNo;

  private GetLoansLoanIdAmortizationType amortizationType;

  private Integer annualInterestRate;

  private Integer clientId;

  private String clientName;

  private Integer clientOfficeId;

  private GetLoansLoanIdCurrency currency;

  private Long id;

  private GetLoansLoanIdInterestCalculationPeriodType interestCalculationPeriodType;

  private GetLoansLoanIdInterestRateFrequencyType interestRateFrequencyType;

  private Integer interestRatePerPeriod;

  private GetLoansLoanIdInterestType interestType;

  private Integer loanOfficerId;

  private String loanOfficerName;

  private String loanProductDescription;

  private Integer loanProductId;

  private String loanProductName;

  private Integer loanPurposeId;

  private String loanPurposeName;

  private GetLoansLoanIdLoanType loanType;

  private Integer numberOfRepayments;

  private Long principal;

  private Integer repaymentEvery;

  private GetLoansLoanIdRepaymentFrequencyType repaymentFrequencyType;

  private GetLoansLoanIdStatus status;

  private GetLoansLoanIdSummary summary;

  private Integer termFrequency;

  private GetLoansLoanIdTermPeriodFrequencyType termPeriodFrequencyType;

  private GetLoansLoanIdTimeline timeline;

  private Integer transactionProcessingStrategyId;
}
