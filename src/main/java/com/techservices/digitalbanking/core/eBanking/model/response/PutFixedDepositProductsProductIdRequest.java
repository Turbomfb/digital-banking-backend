/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PutFixedDepositProductsProductIdRequest {
	private String description;

	private String locale = "en";

	private Integer minDepositTerm;

	private Integer minDepositTermTypeId;

	private Integer accountingRule;

	@Valid
	private Set<@Valid PostFixedDepositProductsCharts> charts = new LinkedHashSet<>();

	private String currencyCode;

	private Integer digitsAfterDecimal;

	private Integer inMultiplesOf;

	private Integer interestCalculationDaysInYearType;

	private Integer interestCalculationType;

	private Integer interestCompoundingPeriodType;

	private Integer interestPostingPeriodType;

	private Integer maxDepositTerm;

	private Integer maxDepositTermTypeId;

	private String name;

	private Boolean preClosurePenalApplicable;

	private Double preClosurePenalInterest;

	private Integer preClosurePenalInterestOnTypeId;

	private String shortName;

	private BigDecimal depositAmount;

	private Double nominalAnnualInterestRate;
}
