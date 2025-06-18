/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.loan.domain.request;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanApplicationRequest {

	private Long clientId;
	private Long productId;
	private BigDecimal principal;
	private Long loanTermFrequency;
	private Long loanTermFrequencyType;
	private String loanType;
	private Long numberOfRepayments;
	private Long repaymentEvery;
	private Long repaymentFrequencyType;
	private Long interestRatePerPeriod;
	private Long amortizationType;
	private Long interestType;
	private Long interestCalculationPeriodType;
	private String transactionProcessingStrategyCode;
	private String expectedDisbursementDate;
	private Long linkAccountId;
	private BigDecimal fixedEmiAmount;
	private BigDecimal maxOutstandingLoanBalance;
	private Long daysInYearType;
	private BigDecimal approvedPrincipal;

	private String externalId;
	private String repaymentsStartingFromDate;
	private boolean createStandingInstructionAtDisbursement;
	private boolean enableInstallmentLevelDelinquency;
	private List<Object> charges = Collections.emptyList();
	private List<Object> collateral = Collections.emptyList();
}
