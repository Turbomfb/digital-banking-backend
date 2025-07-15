/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostRecurringDepositTransactionCommandRequest {
	private String note;
	private BigDecimal transactionAmount;
	private Long paymentTypeId;
	private String accountNumber;
	private String checkNumber;
	private String routingCode;
	private String receiptNumber;
	private String bankNumber;
	private String locale;
	private String dateFormat;
	private String transactionDate;
	private Long linkAccountId;
}
