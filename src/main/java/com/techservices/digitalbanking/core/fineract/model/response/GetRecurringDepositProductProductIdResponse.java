/* Developed by MKAN Engineering (C)2025 */
package com.techservices.digitalbanking.core.fineract.model.response;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetRecurringDepositProductProductIdResponse {

	private Long id;
	private String name;
	private String shortName;
	private String description;
	private Currency currency;
	private Long recurringDepositFrequency;
	private EnumOptionData recurringDepositFrequencyType;
	private Boolean preClosurePenalApplicable;
	private Double preClosurePenalInterest;
	private EnumOptionData preClosurePenalInterestOnType;
	private Integer minDepositTerm;
	private EnumOptionData minDepositTermType;
	private Long inMultiplesOfDepositTerm;
	private EnumOptionData inMultiplesOfDepositTermType;
	private Long maxDepositTerm;
	private EnumOptionData maxDepositTermType;
	private EnumOptionData interestCompoundingPeriodType;
	private EnumOptionData interestPostingPeriodType;
	private EnumOptionData interestCalculationType;
	private EnumOptionData interestCalculationDaysInYearType;
	private GetRecurringDepositAccountingMappings accountingMappings;
	private List<RecurringDepositFeeToIncomeAccountMapping> feeToIncomeAccountMappings;
	private List<RecurringDepositPenaltyToIncomeAccountMapping> penaltyToIncomeAccountMappings;
	private BigDecimal minDepositAmount;
	private BigDecimal depositAmount;
	private BigDecimal maxDepositAmount;
	private DepositProductsCharts activeChart;
	private Double nominalAnnualInterestRate;

	@Getter
	@Setter
	public static class Currency {
		private String code;
		private String name;
		private Integer decimalPlaces;
		private Integer inMultiplesOf;
		private String displaySymbol;
		private String nameCode;
		private String displayLabel;
	}
}
