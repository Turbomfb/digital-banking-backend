/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetRecurringDepositTransactionResponse {

	private int id;
	private RecurringDepositTransactionType transactionType;
	private String entryType;
	private Long accountId;
	private String accountNo;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate date;

	private GetRecurringDepositProductProductIdResponse.Currency currency;
	private double amount;
	private double runningBalance;
	private boolean reversed;
	private RecurringDepositTransactionTransfer transfer;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate submittedOnDate;

	private boolean interestedPostedAsOn;
	private String submittedByUsername;
	private boolean isManualTransaction;
	private boolean isReversal;
	private int originalTransactionId;
	private boolean lienTransaction;
	private Long releaseTransactionId;
	private List<Object> chargesPaidByData;
}
