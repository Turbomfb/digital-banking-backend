/* (C)2025 */
package com.techservices.digitalbanking.core.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptRequest {
  private String transactionId;

  @NotNull(message = "Amount is required")
  @Positive(message = "Amount must be positive")
  private BigDecimal amount;

  @NotBlank(message = "Recipient bank name is required")
  private String recipientBankName;

  @NotBlank(message = "Recipient account number is required")
  private String recipientAccountNumber;

  @NotBlank(message = "Recipient account name is required")
  private String recipientAccountName;

  private String currency = "USD";
  private String receiptFormat = "PDF";
}
