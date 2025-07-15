/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetFixedDepositProductsProductIdResponse {
	private Object accountingMappings;

	private Object activeChart;

	private GetSavingsCurrency currency;

	private String description;

	private BigDecimal nominalAnnualInterestRate;

	@Valid
	private Set<@Valid Object> feeToIncomeAccountMappings = new LinkedHashSet<>();

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

	@Valid
	private Set<@Valid Object> penaltyToIncomeAccountMappings = new LinkedHashSet<>();

	private Boolean preClosurePenalApplicable;

	private Double preClosurePenalInterest;

	private EnumOptionData preClosurePenalInterestOnType;

	private String shortName;
}
