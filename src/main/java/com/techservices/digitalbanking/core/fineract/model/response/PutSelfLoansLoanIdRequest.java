/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import lombok.Getter;
import lombok.Setter;

/** PutSelfLoansLoanIdRequest */
@Getter
@Setter
public class PutSelfLoansLoanIdRequest {

	private Integer amortizationType;

	private String dateFormat;

	private String expectedDisbursementDate;

	private Integer interestCalculationPeriodType;

	private Integer interestRatePerPeriod;

	private Integer interestType;

	private Integer loanTermFrequency;

	private Integer loanTermFrequencyType;

	private String locale;

	private Integer numberOfRepayments;

	private Long principal;

	private Integer productId;

	private Integer repaymentEvery;

	private Integer repaymentFrequencyType;

	private Integer transactionProcessingStrategyId;
}
