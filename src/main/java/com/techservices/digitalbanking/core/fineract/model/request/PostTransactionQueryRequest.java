/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PostTransactionQueryRequest {
	private String transactionDate;
	private Long transactionType;
	private BigDecimal amount;
	private String transactionReference;
	private String dateFormat;
	private String locale;
}
