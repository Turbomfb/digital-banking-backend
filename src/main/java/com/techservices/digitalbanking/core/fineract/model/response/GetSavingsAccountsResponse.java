/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import java.util.Collections;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.Valid;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetSavingsAccountsResponse {

	private Integer totalFilteredRecords;

	@Valid
	private Set<@Valid GetSavingsAccountsAccountIdResponse> pageItems = Collections.emptySet();
}
