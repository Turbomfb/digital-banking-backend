/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FixedDepositTransactionTransfer {

	private int id;
	private boolean reversed;
	private GetFixedDepositAccountsAccountIdCurrency currency;
	private double transferAmount;
	private LocalDate transferDate;
	private String transferDescription;
}
