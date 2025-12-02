/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FixedDepositTransactionType {

	private int id;
	private String code;
	private String value;
	private boolean deposit;
	private boolean dividendPayout;
	private boolean withdrawal;
	private boolean interestPosting;
	private boolean feeDeduction;
	private boolean initiateTransfer;
	private boolean approveTransfer;
	private boolean withdrawTransfer;
	private boolean rejectTransfer;
	private boolean overdraftInterest;
	private boolean writtenoff;
	private boolean overdraftFee;
	private boolean withholdTax;
	private boolean escheat;
	private boolean amountHold;
	private boolean amountRelease;
	private boolean accrual;
}
