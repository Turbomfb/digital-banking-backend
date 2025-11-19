/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostSavingsAccountsAccountIdRequest {

  private String activatedOnDate;
  private String closedOnDate;
  private String note;
  private String approvedOnDate;
  private Long paymentTypeId;
  private Boolean withdrawBalance;
  private String dateFormat;
  private String locale;
  private String reasonForBlock;
  private Long closureReasonId; // remember to return to null
}
