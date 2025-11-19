/* (C)2025 */
package com.techservices.digitalbanking.core.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class ImageComparisonRequest {
  private String image1;
  private String image2;

  @JsonProperty("isSubjectConsent")
  private Boolean isSubjectConsent;
}
