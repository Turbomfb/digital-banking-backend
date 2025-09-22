/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.configuration;

import com.techservices.digitalbanking.core.configuration.feign.CustomFeignLogger;
import feign.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.auth.BasicAuthRequestInterceptor;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class FineractClientConfiguration {

	private final FineractProperty fineractProperty;

	@Bean
	@ConditionalOnProperty(name = "fineract.integration.username")
	public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
		return new BasicAuthRequestInterceptor(fineractProperty.getUsername(), fineractProperty.getPassword());
	}

	@Bean
	@ConditionalOnProperty(name = "fineract.integration.tenantid")
	public ApiKeyRequestInterceptor tenantIdRequestInterceptor() {
		return new ApiKeyRequestInterceptor("header", "fineract-platform-tenantid", fineractProperty.getTenantId());
	}

	@Bean
	public FineractErrorDecoder errorDecoder() {
		return new FineractErrorDecoder();
	}

	@Bean
	public Logger feignLogger() {
		return new CustomFeignLogger(FeignConfiguration.class);
	}
}
