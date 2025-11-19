/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDate;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostAdhocQuerySearchRequest {

  private String dateFormat;

  private Boolean includeOutStandingAmountPercentage;

  private Boolean includeOutstandingAmount;

  private String loanDateOption;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate loanFromDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate loanToDate;

  private String locale;

  private Long maxOutstandingAmount;

  private Long minOutstandingAmount;

  private Long outStandingAmountPercentage;

  private String outStandingAmountPercentageCondition;

  private String outstandingAmountCondition;
}
