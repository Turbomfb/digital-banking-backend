/* (C)2024 */
package com.techservices.digitalbanking.loan.domain.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanRepaymentRequest {
	private String externalId;
	private String note;
	private Integer paymentTypeId;
	private BigDecimal transactionAmount;
	private String transactionDate;
	private String accountNumber;
	private String checkNumber;
	private String routingCode;
	private String receiptNumber;
	private String bankNumber;
	private String transactionPin;
}
