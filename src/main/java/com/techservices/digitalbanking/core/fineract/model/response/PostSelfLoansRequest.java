/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

/** PostSelfLoansRequest */
@Getter
@Setter
public class PostSelfLoansRequest {

	private Integer amortizationType;

	private Integer clientId;

	@Valid
	private Set<@Valid PostSelfLoansDatatables> datatables = new LinkedHashSet<>();

	private String dateFormat;

	@Valid
	private Set<@Valid PostSelfLoansDisbursementData> disbursementData = new LinkedHashSet<>();

	private String expectedDisbursementDate;

	private Integer fixedEmiAmount;

	private Integer interestCalculationPeriodType;

	private Integer interestRatePerPeriod;

	private Integer interestType;

	private Integer linkAccountId;

	private Integer loanTermFrequency;

	private Integer loanTermFrequencyType;

	private String loanType;

	private String locale;

	private Long maxOutstandingLoanBalance;

	private Integer numberOfRepayments;

	private Double principal;

	private Integer productId;

	private Integer repaymentEvery;

	private Integer repaymentFrequencyType;

	private String submittedOnDate;

	private Integer transactionProcessingStrategyId;
}
