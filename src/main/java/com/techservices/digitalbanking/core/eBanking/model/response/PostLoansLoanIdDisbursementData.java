/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/** List of PostLoansLoanIdDisbursementData */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostLoansLoanIdDisbursementData {

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate expectedDisbursementDate;

  private BigDecimal principal;
}
