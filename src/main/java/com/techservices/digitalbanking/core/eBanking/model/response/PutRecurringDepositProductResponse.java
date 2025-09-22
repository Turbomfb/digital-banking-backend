/* Developed by MKAN Engineering (C)2025 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PutRecurringDepositProductResponse {
	private Long resourceId;
	private Object changes;
}
