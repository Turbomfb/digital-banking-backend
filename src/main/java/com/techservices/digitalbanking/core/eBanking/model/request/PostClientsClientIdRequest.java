/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostClientsClientIdRequest {

	private String activationDate;
	private String closureDate;
	private Long closureReasonId;

	private String dateFormat;

	private String locale;
}
