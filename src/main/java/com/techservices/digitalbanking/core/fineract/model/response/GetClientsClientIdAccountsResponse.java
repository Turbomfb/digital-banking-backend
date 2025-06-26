/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Optional;
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

	public BigDecimal getSavingsAccountsBalance() {
		return this.getSavingsAccounts()
				.stream()
				.filter(GetClientsSavingsAccounts::isSavingsAccount)
				.map(account -> Optional.ofNullable(account.getAccountBalance()).orElse(BigDecimal.ZERO))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public BigDecimal getFixedDepositAccountsBalance() {
		return this.getSavingsAccounts()
				.stream()
				.filter(GetClientsSavingsAccounts::isFixedDeposit)
				.map(account -> Optional.ofNullable(account.getAccountBalance()).orElse(BigDecimal.ZERO))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public BigDecimal getRecurringDepositAccountsBalance() {
		return this.getSavingsAccounts()
				.stream()
				.filter(GetClientsSavingsAccounts::isRecurringDeposit)
				.map(account -> Optional.ofNullable(account.getAccountBalance()).orElse(BigDecimal.ZERO))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
}
