/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetClientsResponse {
	private Long totalFilteredRecords;
	private List<GetClientsClientIdResponse> pageItems;
}
