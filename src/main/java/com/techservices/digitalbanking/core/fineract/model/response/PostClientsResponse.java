/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostClientsResponse {

	private Long clientId;
	private Long groupId;
	private Long officeId;
	private Long resourceId;
	private String savingsAccountId;
	private String savingsId;
	private String savingsAccountNo;
	private ClientTierData clientTierData;
}
