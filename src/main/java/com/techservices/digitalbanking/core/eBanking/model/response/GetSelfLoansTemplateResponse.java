/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import jakarta.validation.Valid;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

/** GetSelfLoansTemplateResponse */
@Getter
@Setter
public class GetSelfLoansTemplateResponse {

  private Integer clientId;

  private String clientName;

  private Integer clientOfficeId;

  @Valid private Set<@Valid GetSelfLoansProductOptions> productOptions = new LinkedHashSet<>();

  private GetSelfLoansTimeline timeline;
}
