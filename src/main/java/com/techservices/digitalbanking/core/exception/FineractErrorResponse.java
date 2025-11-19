/* (C)2024 */
package com.techservices.digitalbanking.core.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FineractErrorResponse implements Serializable {

  private String developerMessage;
  private String httpStatusCode;
  private String defaultUserMessage;
  private String userMessageGlobalisationCode;
  private List<ErrorDetail> errors = Collections.emptyList();

  @Setter
  @Getter
  @RequiredArgsConstructor
  public static class ErrorDetail {

    private String developerMessage;
    private String defaultUserMessage;
    private String userMessageGlobalisationCode;
    private String parameterName;
    private String value;
    private List<Object> args;
  }
}
