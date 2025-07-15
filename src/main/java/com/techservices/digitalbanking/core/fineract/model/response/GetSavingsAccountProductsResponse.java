/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetSavingsAccountProductsResponse {

	private Long id;

	private String name;

	private String shortName;

	private String description;

	private GetSavingsCurrency currency;

	private EnumOptionData interestCalculationDaysInYearType;

	private EnumOptionData interestCalculationType;

	private EnumOptionData interestCompoundingPeriodType;

	private EnumOptionData interestPostingPeriodType;

	private Double nominalAnnualInterestRate;

	private Boolean preClosurePenalApplicable;

	private Boolean withdrawalFeeForTransfers;
	private Boolean allowOverdraft;
	private Double overdraftLimit;
	private Double minRequiredBalance;
	private Boolean enforceMinRequiredBalance;
	private Double maxAllowedLienLimit;
	private Boolean lienAllowed;
	private Double nominalAnnualInterestRateOverdraft;
	private Double minOverdraftForInterestCalculation;
	private Boolean withHoldTax;
	private Boolean isDormancyTrackingActive;

	private EnumOptionData accountingRule;
}
