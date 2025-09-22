/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetLoanProductBorrowerCycleVariationData {

	private Long id;
	private Integer borrowerCycleNumber;
	private EnumOptionData paramType;
	private EnumOptionData valueConditionType;
	private BigDecimal minValue;
	private BigDecimal maxValue;
	private BigDecimal defaultValue;
}
