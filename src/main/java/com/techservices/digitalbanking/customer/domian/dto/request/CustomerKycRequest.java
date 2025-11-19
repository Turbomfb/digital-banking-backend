/* (C)2024 */
package com.techservices.digitalbanking.customer.domian.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerKycRequest {
  @JsonProperty("nin")
  private String nin;

  @JsonProperty("bvn")
  private String bvn;

  private String tin;
  private String rcNumber;

  private String uniqueId;
  private String otp;
  private String base64Image;
}
