/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.configuration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import feign.Logger;

@Configuration
@EnableFeignClients(basePackages = "com.techservices.digitalbanking")
@Import(FeignClientsConfiguration.class)
public class FeignConfiguration {

	/** Set the Feign specific log level to log client REST requests. */
	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}
}
