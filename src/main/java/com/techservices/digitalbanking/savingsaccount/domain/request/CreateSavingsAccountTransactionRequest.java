/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.savingsaccount.domain.request;

import java.math.BigDecimal;

import com.techservices.digitalbanking.core.fineract.model.response.TransactionMetadata;

public record CreateSavingsAccountTransactionRequest(BigDecimal transactionAmount, String transactionReference,
		String narration, String beneficiaryAccountNumber, String reasonForBlock,
		TransactionMetadata additionalInformation) {
}
