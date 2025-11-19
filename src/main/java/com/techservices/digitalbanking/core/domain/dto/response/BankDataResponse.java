/* (C)2025 */
package com.techservices.digitalbanking.core.domain.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.techservices.digitalbanking.core.domain.data.model.BankData;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BankDataResponse {
	private boolean success;
	private int statusCode;
	private String message;
	private List<BankData> data;
}
