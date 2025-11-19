/* (C)2024 */
package com.techservices.digitalbanking.investment.domain.request;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RecurringDepositTransactionCommandRequest {
  private String note;
  private BigDecimal transactionAmount;
  private Long paymentTypeId;
  private String accountNumber;
  private String checkNumber;
  private String routingCode;
  private String receiptNumber;
  private String bankNumber;
  private Long linkAccountId;
}
