/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostFixedDepositProductsCharts {
	@Valid
	private Set<@Valid PostFixedDepositProductsChartSlabs> chartSlabs = new LinkedHashSet<>();

	private String dateFormat;

	private String fromDate;

	private String locale;
	private Long id;
	private String name;
	private String description;
}
