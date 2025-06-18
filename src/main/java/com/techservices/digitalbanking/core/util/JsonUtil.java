/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techservices.digitalbanking.core.exception.PlatformServiceException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtil {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	static {
		OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS);
	}

	private JsonUtil() {
	}

	public static boolean isJSONValid(String jsonString) {
		try {
			JsonNode jsonNode = OBJECT_MAPPER.readTree(jsonString);
			return jsonNode.isObject() || jsonNode.isArray();
		} catch (Exception e) {
			return false;
		}
	}

	public static <T> T convertJsonBodyToObject(String jsonBody, Class<T> clazz) throws RuntimeException {
		if (StringUtils.isEmpty(jsonBody)) {
			throw new RuntimeException("Received empty response body");
		}
		try {
			return OBJECT_MAPPER.readValue(jsonBody, clazz);
		} catch (IOException e) {
			log.error("Error while de-serializing json body", e);
			throw new PlatformServiceException("Could not read response body", e);
		}
	}

	public static <T> List<T> convertJsonArrayToList(String jsonArray, Class<T> clazz) throws RuntimeException {
		if (StringUtils.isEmpty(jsonArray)) {
			throw new PlatformServiceException("Received empty response body");
		}

		try {
			JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
			return OBJECT_MAPPER.readValue(jsonArray, javaType);
		} catch (IOException e) {
			log.error("Error while de-serializing json body", e);
			throw new PlatformServiceException("Could not read response body", e);
		}
	}

	public static String objToJsonStringMapper(final Object obj) {
		try {
			return new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL).findAndRegisterModules()
					.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new PlatformServiceException(e.getMessage(), e);
		}
	}
}
