/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetSavingsAccountsSummary {

	private BigDecimal accountBalance;
	private BigDecimal availableBalance;
	private GetSavingsCurrency currency;
	private BigDecimal totalDeposits;
	private BigDecimal totalWithdrawals;
	private BigDecimal totalInterestEarned;
}
