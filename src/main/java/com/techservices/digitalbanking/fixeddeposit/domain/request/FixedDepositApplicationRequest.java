/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.fixeddeposit.domain.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FixedDepositApplicationRequest {

	private Long clientId;

	private BigDecimal depositAmount;

	private Long depositPeriod;

	private Long depositPeriodFrequencyId;

	private Long productId;

	private String accountNo;

	private Long linkAccountId;
}
