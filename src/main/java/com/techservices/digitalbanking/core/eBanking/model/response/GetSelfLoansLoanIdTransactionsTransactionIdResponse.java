/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/** GetSelfLoansLoanIdTransactionsTransactionIdResponse */
@Getter
@Setter
public class GetSelfLoansLoanIdTransactionsTransactionIdResponse {

  private Double amount;

  private GetLoanCurrency currency;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate date;

  private Integer id;

  private Double interestPortion;

  private Boolean manuallyReversed;

  private GetSelfLoansLoanIdTransactionsType type;
}
