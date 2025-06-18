/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

/** GetLoansLoanIdResponse */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetLoansLoanIdResponse {

	private Long accountNo;

	private GetLoansLoanIdAmortizationType amortizationType;

	private Integer annualInterestRate;

	private Double approvedPrincipal;

	private Integer clientId;

	private String clientName;

	private Integer clientOfficeId;

	private GetLoansLoanIdCurrency currency;

	private BigDecimal fixedPrincipalPercentagePerInstallment;

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

	private Double netDisbursalAmount;

	private Integer numberOfRepayments;

	private BigDecimal principal;

	private Integer repaymentEvery;

	private GetLoansLoanIdRepaymentFrequencyType repaymentFrequencyType;

	private GetLoansLoanIdRepaymentSchedule repaymentSchedule;

	private GetLoansLoanIdStatus status;

	private GetLoansLoanIdSummary summary;

	private Integer termFrequency;

	private GetLoansLoanIdTermPeriodFrequencyType termPeriodFrequencyType;

	private GetLoansLoanIdTimeline timeline;

	private Integer transactionProcessingStrategyId;

	private List<LoanTransactionResponse> transactions;
}
