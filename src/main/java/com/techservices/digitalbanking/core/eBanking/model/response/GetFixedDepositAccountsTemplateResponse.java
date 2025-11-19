/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetFixedDepositAccountsTemplateResponse {
	private Integer clientId;

	private String clientName;

	@Valid
	private Set<@Valid GetFixedDepositAccountsProductOptions> productOptions = new LinkedHashSet<>();
}
