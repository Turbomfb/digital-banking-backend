/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PostFixedDepositAccountsAccountIdRequest {
  private String locale;
  private String dateFormat;
  private String approvedOnDate;
  private String rejectedOnDate;
  private String activatedOnDate;
  private String note;
  private Long onAccountClosureId;
  private Long toSavingsAccountId;
  private String transferDescription;
  private String closedOnDate;
}
