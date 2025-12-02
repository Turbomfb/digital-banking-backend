/* (C)2025 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanTransactionResponse {

	private String id;
	private String loanId;
	private String transactionType;
	private LocalDate date;
	private BigDecimal amount;
	private BigDecimal outstandingLoanBalance;
}
