/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

/** GetSelfLoansLoanIdTransactionsTransactionIdResponse */
@Getter
@Setter
public class GetSelfLoansLoanIdTransactionsTransactionIdResponse {

	private Double amount;

	private GetLoanCurrency currency;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate date;

	private Integer id;

	private Double interestPortion;

	private Boolean manuallyReversed;

	private GetSelfLoansLoanIdTransactionsType type;
}
