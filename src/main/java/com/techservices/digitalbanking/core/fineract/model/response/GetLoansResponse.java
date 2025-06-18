/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

/** GetLoansResponse */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetLoansResponse {

	@Valid
	private Set<@Valid GetLoansLoanIdResponse> pageItems = new LinkedHashSet<>();

	private Integer totalFilteredRecords;
}
