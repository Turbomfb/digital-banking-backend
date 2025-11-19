/* (C)2024 */
package com.techservices.digitalbanking.core.exception;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PlatformServiceException extends RuntimeException {

  private final String globalisationMessageCode;
  private final String defaultUserMessage;
  private final Object[] defaultUserMessageArgs;

  public PlatformServiceException(
      final String globalisationMessageCode,
      final String defaultUserMessage,
      final Object... defaultUserMessageArgs) {

    this.globalisationMessageCode = globalisationMessageCode;
    this.defaultUserMessage = defaultUserMessage;
    this.defaultUserMessageArgs = defaultUserMessageArgs;
  }

  public PlatformServiceException(
      final String defaultUserMessage, final Object... defaultUserMessageArgs) {

    this.globalisationMessageCode = "error.msg.platform.service.exception";
    this.defaultUserMessage = defaultUserMessage;
    this.defaultUserMessageArgs = defaultUserMessageArgs;
  }
}
