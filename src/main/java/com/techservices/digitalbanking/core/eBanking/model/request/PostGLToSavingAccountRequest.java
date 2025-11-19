/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.request;

import jakarta.annotation.Generated;
import java.math.BigDecimal;
import lombok.*;

@Generated("jsonschema2pojo")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class PostGLToSavingAccountRequest {

  private Long officeId;
  private String currencyCode;
  private BigDecimal amount;
  private String referenceNumber;
  private Long paymentTypeId;
  private String transactionDate;
  private String locale;
  private String dateFormat;
  private GLAccount debit;
}
