/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.investment.domain.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class InvestmentUpdateRequest {
	private BigDecimal amount;

}
