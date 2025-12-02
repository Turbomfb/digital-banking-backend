/* (C)2025 */
package com.techservices.digitalbanking.core.configuration.resttemplate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RestTemplateConfig {

	@Bean
	public RestTemplate restTemplate(ObjectMapper objectMapper) {

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new CustomResponseErrorHandler(objectMapper));
		return restTemplate;
	}
}
