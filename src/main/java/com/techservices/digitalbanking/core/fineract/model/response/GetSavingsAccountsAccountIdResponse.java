/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetSavingsAccountsAccountIdResponse {

	private String accountNo;
	private String accountName;
	private String alternateAccountNumber;
	private String accountBvn;
	private Long clientId;
	private String clientName;
	private String clientBvn;
	private GetSavingsCurrency currency;
	private Long fieldOfficerId;
	private Long id;
	private EnumOptionData interestCalculationDaysInYearType;
	private EnumOptionData interestCalculationType;
	private EnumOptionData interestCompoundingPeriodType;
	private EnumOptionData interestPostingPeriodType;
	private Double nominalAnnualInterestRate;
	private Long savingsProductId;
	private String savingsProductName;
	private GetSavingsStatus status;
	private GetSavingsSubStatus subStatus;
	private GetSavingsAccountsSummary summary;
	private GetSavingsTimeline timeline;
	private List<SavingsAccountTransactionData> transactions = Collections.emptyList();

	public String getPreferredName() {
		return StringUtils.isNotBlank(accountName) ? accountName : clientName;
	}

	public BigDecimal getAccountBalance() {
		return this.summary != null ? this.summary.getAvailableBalance() : BigDecimal.ZERO;
	}
}
