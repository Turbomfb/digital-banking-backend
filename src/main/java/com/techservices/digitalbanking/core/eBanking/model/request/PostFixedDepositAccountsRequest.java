/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PostFixedDepositAccountsRequest {
	private Long clientId;

	private String dateFormat;

	private BigDecimal depositAmount;

	private Long depositPeriod;

	private Long depositPeriodFrequencyId;

	private String locale;

	private String productId;

	private String accountNo;
	private String allocationName;

	private String submittedOnDate;

	private Long linkAccountId;
}
