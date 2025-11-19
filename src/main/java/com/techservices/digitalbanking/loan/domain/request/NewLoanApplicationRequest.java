/* (C)2024 */
package com.techservices.digitalbanking.loan.domain.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewLoanApplicationRequest {
  private String productId;
  private BigDecimal amount;
  private Long duration;

  private String employerCategory;
  private String employerSector;
  private String employerEmail;
  private String employerName;
}
