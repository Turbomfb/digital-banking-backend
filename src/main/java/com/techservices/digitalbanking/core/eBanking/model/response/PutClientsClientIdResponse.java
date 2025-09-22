/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.techservices.digitalbanking.core.eBanking.model.request.PutClientsClientIdRequest;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PutClientsClientIdResponse {

	private PutClientsClientIdRequest changes = new PutClientsClientIdRequest();

	private Long clientId;

	private Long officeId;

	private Long resourceId;
}
