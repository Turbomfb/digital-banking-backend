/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecurringDepositProductsCharts {
	@Valid
	private Set<@Valid RecurringDepositProductsChartSlabs> chartSlabs = new LinkedHashSet<>();

	private String dateFormat;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate fromDate;

	private String locale;
	private Long id;
	private String name;
	private String description;
	private Integer savingsProductId;
	private String savingsProductName;
	private List<EnumOptionData> periodType;
}
