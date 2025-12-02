/* (C)2025 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlexInvestmentCreationResponse {
	private Long customerId;

	private Long officeId;

	private Long resourceId;

	private Long savingsId;

	private String accountNumber;
}
