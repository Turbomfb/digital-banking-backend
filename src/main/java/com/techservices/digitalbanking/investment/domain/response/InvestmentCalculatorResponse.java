/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.investment.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class InvestmentCalculatorResponse {
	private BigDecimal interestExpected;
	private BigDecimal totalPayout;

	private String maturityDate;

}
