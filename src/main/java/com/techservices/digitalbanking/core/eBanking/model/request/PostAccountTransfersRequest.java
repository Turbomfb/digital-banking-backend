/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import lombok.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class PostAccountTransfersRequest {

  private String fromAccountId;

  private String toAccountId;

  private BigDecimal transferAmount;

  private String transactionReference;

  private String transferDescription;
}
