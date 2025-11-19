/* (C)2025 */
package com.techservices.digitalbanking.core.redis.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class RedisProperty {

  @Value("${redis.ttl.minutes}")
  private String ttlMinutes;
}
