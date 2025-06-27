/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import com.techservices.digitalbanking.core.domain.enums.AccountType;
import com.techservices.digitalbanking.core.fineract.service.AccountService;
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

	public BigDecimal getTotalAccountBalanceFor(AccountType accountType) {
		return this.getSavingsAccounts()
				.stream()
				.filter(account -> account != null && account.isAccountType(accountType))
				.map(account -> Optional.ofNullable(account.getAccountBalance()).orElse(BigDecimal.ZERO))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public BigDecimal getTotalAccountDepositsFor(AccountType accountType, AccountService accountService) {
		return this.getSavingsAccounts()
				.stream()
				.filter(account -> account != null && account.isAccountType(accountType))
				.map(account -> Optional.ofNullable(account.getTotalDeposits(accountService)).orElse(BigDecimal.ZERO))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public BigDecimal getTotalAccountWithdrawalsFor(AccountType accountType, AccountService accountService) {
		return this.getSavingsAccounts()
				.stream()
				.filter(account -> account != null && account.isAccountType(accountType))
				.map(account -> Optional.ofNullable(account.getTotalWithdrawals(accountService)).orElse(BigDecimal.ZERO))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public BigDecimal getTotalAccountInterestsFor(AccountType accountType, AccountService accountService) {
		return this.getSavingsAccounts()
				.stream()
				.filter(account -> account != null && account.isAccountType(accountType))
				.map(account -> Optional.ofNullable(account.getTotalInterests(accountService)).orElse(BigDecimal.ZERO))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public Long getTotalActivePlanFor(AccountType accountType, AccountService accountService) {
		return this.getSavingsAccounts()
				.stream()
				.filter(account -> account != null && account.isAccountType(accountType))
				.count();
	}
}
