/* (C)2025 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionMetadata {

	private String debitAccountNumber;
	private String debitAccountName;
	private BigDecimal amount;
	private String creditAccountNumber;
	private String creditAccountName;
	private String transactionReference;
	private String debitBvn;
}
