/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.request;

import java.math.BigDecimal;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.techservices.digitalbanking.core.eBanking.model.response.PostRecurringDepositProductsCharts;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PutRecurringDepositProductRequest {

	private Integer accountingRule;

	@Valid
	private Set<@Valid PostRecurringDepositProductsCharts> charts;

	private String currencyCode;

	private String allocationName;
	private String description;

	private BigDecimal mandatoryRecommendedDepositAmount;
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

	private Integer recurringDepositFrequency;

	private Integer recurringDepositFrequencyTypeId;

	private Double nominalAnnualInterestRate;
}
