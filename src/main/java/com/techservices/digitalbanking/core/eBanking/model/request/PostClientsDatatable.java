/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.request;

import java.util.Collections;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.Valid;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostClientsDatatable {

	@Valid
	private Map<String, Object> data = Collections.emptyMap();

	private String registeredTableName;
}
