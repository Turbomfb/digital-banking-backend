/* (C)2025 */
package com.techservices.digitalbanking.core.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessDataRequest {

	private String registrationNumber;
	private String countryCode;
	private String tin;

	@JsonProperty("isConsent")
	private boolean isConsent = true;
}
