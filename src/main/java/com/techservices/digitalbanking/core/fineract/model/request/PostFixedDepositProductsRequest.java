/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.request;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.techservices.digitalbanking.core.fineract.model.response.PostFixedDepositProductsCharts;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PostFixedDepositProductsRequest {
	private Integer accountingRule;

	@Valid
	private Set<@Valid PostFixedDepositProductsCharts> charts = new LinkedHashSet<>();

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

	private Double nominalAnnualInterestRate;
}
