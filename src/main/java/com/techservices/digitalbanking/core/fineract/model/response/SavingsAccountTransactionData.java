/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SavingsAccountTransactionData {

	private Long id;
	private TransactionTypeData transactionType;
	private Long accountId;
	private String accountNo;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate date;

	private String createdDateTime;

	private GetSavingsCurrency currency;
	private PaymentDetailData paymentDetailData;
	private BigDecimal amount;
	private BigDecimal outstandingChargeAmount;
	private BigDecimal runningBalance;
	private boolean reversed;
	private Object transfer;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate submittedOnDate;

	private boolean interestedPostedAsOn;
	private String submittedByUsername;
	private String note;
	private boolean isManualTransaction;
	private Boolean isReversal;
	private Long originalTransactionId;
	private Boolean lienTransaction;
	private Long releaseTransactionId;
	private String reasonForBlock;
	private String narration;
	private String refNo;
	private String depositIban;
	private TransactionMetadata additionalInformation;
}
