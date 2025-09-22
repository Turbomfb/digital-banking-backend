/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetLoanInterestRecalculationData {
	private Long id;
	private Long productId;
	private EnumOptionData interestRecalculationCompoundingType;
	private EnumOptionData recalculationCompoundingFrequencyType;
	private EnumOptionData rescheduleStrategyType;
	private EnumOptionData recalculationRestFrequencyType;
	private EnumOptionData preClosureInterestCalculationStrategy;
	private Boolean isArrearsBasedOnOriginalSchedule;
}
