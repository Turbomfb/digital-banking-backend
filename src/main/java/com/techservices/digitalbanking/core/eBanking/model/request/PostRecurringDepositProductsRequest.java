/* (C)2025 */
package com.techservices.digitalbanking.core.eBanking.model.request;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PostRecurringDepositProductsRequest {

	private Integer accountingRule;

	@Valid
	private Set<@Valid PostRecurringDepositProductsCharts> charts = new LinkedHashSet<>();

	private String currencyCode;

	private String description;

	private Integer digitsAfterDecimal;

	private Integer inMultiplesOf;

	private Integer interestCalculationDaysInYearType;

	private Integer interestCalculationType;

	private Integer interestCompoundingPeriodType;

	private Integer interestPostingPeriodType;

	private String locale;

	private Integer maxDepositTerm;

	private Integer maxDepositTermTypeId;

	private Integer minDepositTerm;

	private Integer minDepositTermTypeId;

	private String name;

	private Boolean preClosurePenalApplicable;

	private Double preClosurePenalInterest;

	private Integer preClosurePenalInterestOnTypeId;

	private String shortName;

	private BigDecimal depositAmount;

	private BigDecimal minDepositAmount;

	private BigDecimal maxDepositAmount;

	private Integer withdrawalFrequencyType;

	private Integer withdrawalFrequencyValue;

	private Boolean isMandatoryDeposit;

	private Double nominalAnnualInterestRate;

	private Boolean adjustAdvanceTowardsFuturePayments;

	private Boolean allowWithdrawal;

	@Getter
	@Setter
	public static class PostRecurringDepositProductsCharts {
		private String name;
		private String description;
		private String fromDate;
		private Boolean isPrimaryGroupingByAmount;
		private Set<ChartSlab> chartSlabs = new LinkedHashSet<>();
		private String locale;
		private String dateFormat;

		@Getter
		@Setter
		public static class ChartSlab {
			private Integer periodType;
			private String fromPeriod;
			private String toPeriod;
			private String amountRangeFrom;
			private String amountRangeTo;
			private String annualInterestRate;
			private String description;
			private Set<Object> incentives = new LinkedHashSet<>();
		}
	}
}
