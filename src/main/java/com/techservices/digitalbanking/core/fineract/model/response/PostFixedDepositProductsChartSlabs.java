/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_EMPTY)
public class PostFixedDepositProductsChartSlabs {

	private Double annualInterestRate;

	private String description;

	private Integer fromPeriod;

	private Integer periodType;

	private Integer toPeriod;

	private Long id;
	private String amountRangeFrom;
	private String amountRangeTo;
	private Set<Object> incentives = new LinkedHashSet<>();
}
