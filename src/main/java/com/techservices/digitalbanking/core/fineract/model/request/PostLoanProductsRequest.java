/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.request;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.techservices.digitalbanking.core.fineract.model.response.GetLoanFeeToIncomeAccountMappings;
import com.techservices.digitalbanking.core.fineract.model.response.GetLoanPaymentChannelToFundSourceMappings;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

/** PostLoanProductsRequest */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostLoanProductsRequest {

	private Boolean accountMovesOutOfNPAOnlyOnArrearsCompletion;

	private Integer accountingRule;
	private Long minimumGap;

	private Boolean allowApprovedDisbursedAmountsOverApplied;

	private Object allowAttributeOverrides;

	private Boolean allowPartialPeriodInterestCalcualtion;

	private Boolean allowVariableInstallments;

	private Integer amortizationType;

	private Boolean canDefineInstallmentAmount;

	private Boolean canUseForTopup;

	@Valid
	private List<@Valid Object> charges = new ArrayList<>();

	private String closeDate;

	private String currencyCode;

	private String dateFormat;

	private Integer daysInMonthType;

	private Integer daysInYearType;

	private String description;

	private Integer digitsAfterDecimal;

	private Boolean disallowExpectedDisbursements;

	@Valid
	private Set<@Valid GetLoanFeeToIncomeAccountMappings> feeToIncomeAccountMappings = new LinkedHashSet<>();

	private BigDecimal fixedPrincipalPercentagePerInstallment;

	private Long fundId;

	private Integer fundSourceAccountId;

	private Integer graceOnArrearsAgeing;

	private Boolean holdGuaranteeFunds;

	private Integer inMultiplesOf;

	private Boolean includeInBorrowerCycle;

	private Integer incomeFromFeeAccountId;

	private Integer incomeFromPenaltyAccountId;

	private Long incomeFromRecoveryAccountId;

	private Integer installmentAmountInMultiplesOf;

	private Integer interestCalculationPeriodType;

	private Integer interestOnLoanAccountId;

	private Integer interestRateFrequencyType;

	private Double interestRatePerPeriod;

	@Valid
	private List<Integer> interestRateVariationsForBorrowerCycle = new ArrayList<>();

	private Integer interestRecalculationCompoundingMethod;

	private Integer interestType;

	private Boolean isEqualAmortization;

	private Boolean isInterestRecalculationEnabled;

	private Boolean isLinkedToFloatingInterestRates;

	private Integer loanPortfolioAccountId;

	private String locale;

	private Double maxInterestRatePerPeriod;

	private Integer maxNumberOfRepayments;

	private Double maxPrincipal;

	private Integer maxTrancheCount;

	private Double minInterestRatePerPeriod;

	private Integer minNumberOfRepayments;

	private Double minPrincipal;

	private Integer minimumDaysBetweenDisbursalAndFirstRepayment;

	private Boolean multiDisburseLoan;

	private String name;

	@Valid
	private List<Integer> numberOfRepaymentVariationsForBorrowerCycle = new ArrayList<>();

	private Integer numberOfRepayments;

	private Double outstandingLoanBalance;

	private String overAppliedCalculationType;

	private Integer overAppliedNumber;

	private Integer overdueDaysForNPA;

	private Integer overpaymentLiabilityAccountId;

	@Valid
	private Set<@Valid GetLoanPaymentChannelToFundSourceMappings> paymentChannelToFundSourceMappings = new LinkedHashSet<>();

	@Valid
	private List<@Valid Object> penaltyToIncomeAccountMappings = new ArrayList<>();

	private Integer preClosureInterestCalculationStrategy;

	private Double principal;

	private Integer principalThresholdForLastInstallment;

	@Valid
	private List<Integer> principalVariationsForBorrowerCycle = new ArrayList<>();

	@Valid
	private List<@Valid Object> rates = new ArrayList<>();

	private Integer recalculationRestFrequencyType;

	private Integer receivableFeeAccountId;

	private Integer receivableInterestAccountId;

	private Integer receivablePenaltyAccountId;

	private Integer repaymentEvery;

	private Integer repaymentFrequencyType;

	private Integer rescheduleStrategyMethod;

	private String shortName;

	private String startDate;

	private Integer transactionProcessingStrategyId;

	private Long transfersInSuspenseAccountId;

	private Boolean useBorrowerCycle;

	private Integer writeOffAccountId;

	private Long repaymentStartDateType;
	private Object fixedLength;
	private String transactionProcessingStrategyCode;
	private Object graceOnPrincipalPayment;
	private Object graceOnInterestPayment;
	private BigDecimal inArrearsTolerance;
	private Boolean allowPartialPeriodInterestCalculation;
	private Object delinquencyBucketId;
	private Boolean enableDownPayment;
	private Boolean enableInstallmentLevelDelinquency;
	private Long dueDaysForRepaymentEvent;
	private Long overDueDaysForRepaymentEvent;
	private String loanScheduleType;
	private String isArrearsBasedOnOriginalSchedule;
}
