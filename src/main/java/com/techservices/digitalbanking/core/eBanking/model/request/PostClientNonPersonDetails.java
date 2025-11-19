/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostClientNonPersonDetails {

  private String remarks;
  private Long constitutionId;
  private String incorpNumber;
  private Long mainBusinessLineId;
  private String locale;
  private String dateFormat;
  private String incorpValidityTillDate;
}
