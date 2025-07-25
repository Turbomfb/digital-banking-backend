/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.request;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class KycTier {

	private String tierName;

	private BigDecimal maximumBalance;

	private BigDecimal singleDepositLimit;

	private BigDecimal dailyWithdrawalLimit;

	private BigDecimal singleWithdrawalLimit;
}
