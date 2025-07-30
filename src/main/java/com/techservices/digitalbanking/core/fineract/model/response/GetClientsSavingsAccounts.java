/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import com.techservices.digitalbanking.core.domain.enums.AccountType;
import com.techservices.digitalbanking.core.fineract.service.AccountService;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetClientsSavingsAccounts {

	private Long id;

	private Long productId;

	private String productName;

	private String shortProductName;

	private String allocationName;

	private String accountNo;

	private BigDecimal accountBalance;
	private BigDecimal expectedInterest;
	private String maturityDate;

	private GetSavingsCurrency currency;

	private EnumOptionData depositType;

	private GetSavingsStatus status;

	@JsonIgnore
	public boolean isSavingsAccount() {
		return this.depositType != null && this.depositType.getId() == 100 && this.status != null && this.status.getActive();
	}

	@JsonIgnore
	public boolean isFixedDeposit() {
		return this.depositType != null && this.depositType.getId() == 200 && this.status != null && this.status.getActive();
	}

	@JsonIgnore
	public boolean isRecurringDeposit() {
		return this.depositType != null && this.depositType.getId() == 300 && this.status != null && this.status.getActive();
	}

	@JsonIgnore
	public boolean isAccountType(AccountType accountType) {
		return this.depositType != null && Objects.equals(this.depositType.getId(), accountType.getCode()) && this.status != null && (this.status.getActive() || this.status.getMatured());
	}

	@JsonIgnore
	public BigDecimal getTotalDeposits(AccountService accountService) {
		GetSavingsAccountsAccountIdResponse account = accountService.retrieveSavingsAccountById(this.id);
		return account.getSummary() != null && account.getSummary().getTotalDeposits() != null ? account.getSummary().getTotalDeposits() : BigDecimal.ZERO;
	}

	@JsonIgnore
	public BigDecimal getTotalWithdrawals(AccountService accountService) {
		GetSavingsAccountsAccountIdResponse account = accountService.retrieveSavingsAccountById(this.id);
		return account.getSummary() != null && account.getSummary().getTotalWithdrawals() != null ? account.getSummary().getTotalWithdrawals() : BigDecimal.ZERO;
	}

	@JsonIgnore
	public BigDecimal getTotalInterests(AccountService accountService) {
		GetSavingsAccountsAccountIdResponse account = accountService.retrieveSavingsAccountById(this.id);
		return account.getSummary() != null && account.getSummary().getTotalInterestEarned() != null ? account.getSummary().getTotalInterestEarned() : BigDecimal.ZERO;
	}
}
