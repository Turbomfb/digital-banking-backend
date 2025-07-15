/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetFixedDepositAccountsAccountChart {

	private Integer accountId;

	private Long accountNumber;

	@Valid
	private Set<@Valid GetFixedDepositAccountsChartSlabs> chartSlabs = new LinkedHashSet<>();

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate fromDate;

	private Integer id;

	@Valid
	private Set<@Valid EnumOptionData> periodTypes = new LinkedHashSet<>();
}
