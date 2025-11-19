/* (C)2024 */
package com.techservices.digitalbanking.core.util;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoggingHelper {

	private ObjectMapper objectMapper;

	@PostConstruct
	public void init() {

		objectMapper = new ObjectMapper();
	}

	public void logRequest(Object object) {

		try {
			log.info(objectMapper.writeValueAsString(object));
		} catch (JsonProcessingException e) {
			log.error("Failed to log requests: ", e);
		}
	}
}
