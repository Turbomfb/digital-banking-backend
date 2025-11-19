/* (C)2025 */
package com.techservices.digitalbanking.core.configuration.resttemplate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponse {
  private String status;
  private String statusCode;
  private String message;
  private String statusMessage;
}
