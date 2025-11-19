/* (C)2024 */
package com.techservices.digitalbanking.walletaccount.domain.request;

import com.techservices.digitalbanking.core.eBanking.model.response.TransactionMetadata;
import java.math.BigDecimal;

public record CreateSavingsAccountTransactionRequest(
    BigDecimal transactionAmount,
    String transactionReference,
    String narration,
    String beneficiaryAccountNumber,
    String reasonForBlock,
    TransactionMetadata additionalInformation) {}
