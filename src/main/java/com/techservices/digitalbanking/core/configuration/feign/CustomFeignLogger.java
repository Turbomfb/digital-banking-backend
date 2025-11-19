/* (C)2025 */
package com.techservices.digitalbanking.core.configuration.feign;

import feign.Logger;
import org.slf4j.LoggerFactory;

public class CustomFeignLogger extends Logger {

  private final org.slf4j.Logger logger;

  public CustomFeignLogger(Class<?> clazz) {

    this.logger = LoggerFactory.getLogger(clazz);
  }

  @Override
  protected void log(String configKey, String format, Object... args) {

    logger.info(String.format(methodTag(configKey) + format, args));
  }
}
