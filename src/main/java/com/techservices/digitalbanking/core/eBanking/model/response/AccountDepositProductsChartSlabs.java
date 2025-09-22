/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDepositProductsChartSlabs {

	private Long id;
	private BigDecimal annualInterestRate;

	private String description;

	private Integer fromPeriod;

	private EnumOptionData periodType;

	private Integer toPeriod;

	private String amountRangeFrom;
	private GetRecurringDepositProductProductIdResponse.Currency currency;
	private String amountRangeTo;
	private Set<Object> incentives = new LinkedHashSet<>();
}
