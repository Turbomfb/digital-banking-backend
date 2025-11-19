/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/** List of PostLoansDisbursementData */
@Getter
@Setter
public class PostLoansDisbursementData {

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate expectedDisbursementDate;

  private Double principal;
}
