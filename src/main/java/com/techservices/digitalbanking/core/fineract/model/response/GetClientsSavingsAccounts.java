/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetClientsSavingsAccounts {

	private Integer id;

	private Integer productId;

	private String productName;

	private String shortProductName;

	private String accountNo;

	private BigDecimal accountBalance;

	private GetSavingsCurrency currency;

	private EnumOptionData depositType;

	private GetSavingsStatus status;

	public boolean isSavingsAccount() {
		return this.depositType != null && this.depositType.getId() == 100 && this.status != null && this.status.getActive();
	}

	public boolean isFixedDeposit() {
		return this.depositType != null && this.depositType.getId() == 200 && this.status != null && this.status.getActive();
	}

	public boolean isRecurringDeposit() {
		return this.depositType != null && this.depositType.getId() == 300 && this.status != null && this.status.getActive();
	}
}
