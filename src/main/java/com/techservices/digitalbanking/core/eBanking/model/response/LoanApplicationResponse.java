/* (C)2025 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoanApplicationResponse {
  private String loanId;
  private String customerId;
  private String message;
}
