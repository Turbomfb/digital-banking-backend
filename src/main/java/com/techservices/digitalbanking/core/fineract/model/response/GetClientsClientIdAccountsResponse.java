/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.Valid;
import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetClientsClientIdAccountsResponse {

	@Valid
	private Set<@Valid GetClientsLoanAccounts> loanAccounts = new LinkedHashSet<>();

	@Valid
	private Set<@Valid GetClientsSavingsAccounts> savingsAccounts = new LinkedHashSet<>();
}
