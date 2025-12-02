/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientTierData {

	private Long id;
	private Long tierRankId;
	private CodeData tierRank;
	private String tierName;
	private BigDecimal savingsTransactionWithdrawLimit;
	private BigDecimal savingsDailyWithdrawLimit;
	private boolean isActive;
}
