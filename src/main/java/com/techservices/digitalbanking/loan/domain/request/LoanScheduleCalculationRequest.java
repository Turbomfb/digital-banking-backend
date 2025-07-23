/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.loan.domain.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanScheduleCalculationRequest {
	private Long productId;
	private BigDecimal principal;
	private Long loanTermFrequency;
}
