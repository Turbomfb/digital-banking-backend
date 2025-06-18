/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

/** GetSelfLoansTemplateResponse */
@Getter
@Setter
public class GetSelfLoansTemplateResponse {

	private Integer clientId;

	private String clientName;

	private Integer clientOfficeId;

	@Valid
	private Set<@Valid GetSelfLoansProductOptions> productOptions = new LinkedHashSet<>();

	private GetSelfLoansTimeline timeline;
}
