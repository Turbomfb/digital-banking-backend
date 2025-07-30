/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import com.techservices.digitalbanking.core.domain.BaseAppResponse;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetFixedDepositAccountsAccountIdResponse extends BaseAppResponse {
	private Long accountNo;

	private Long clientId;

	private String clientName;
	private String allocationName;

	private GetSavingsCurrency currency;

	private BigDecimal depositAmount;

	private Long depositPeriod;

	private GetFixedDepositAccountsDepositPeriodFrequency depositPeriodFrequency;

	private Integer fieldOfficerId;

	private Integer id;

	private GetFixedDepositAccountsInterestCalculationDaysInYearType interestCalculationDaysInYearType;

	private EnumOptionData interestCalculationType;

	private EnumOptionData interestCompoundingPeriodType;

	private Boolean interestFreePeriodApplicable;

	private EnumOptionData interestPostingPeriodType;

	private BigDecimal maturityAmount;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate maturityDate;

	private Integer maxDepositTerm;

	private EnumOptionData maxDepositTermType;

	private Integer minDepositTerm;

	private EnumOptionData minDepositTermType;

	private Boolean preClosurePenalApplicable;

	private Integer savingsProductId;

	private String savingsProductName;

	private GetSavingsStatus status;

	private GetSavingsAccountsSummary summary;

	private GetSavingsTimeline timeline;

	List<GetFixedDepositTransactionResponse> transactions;

	public List<GetFixedDepositTransactionResponse> getTransactions() {
		this.transactions = transactions.stream()
						.sorted(Comparator.comparing(GetFixedDepositTransactionResponse::getId).reversed())
						.toList();
		return transactions;
	}
}
