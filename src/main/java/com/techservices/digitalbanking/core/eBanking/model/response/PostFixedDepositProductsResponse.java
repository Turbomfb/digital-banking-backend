/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostFixedDepositProductsResponse {
	private GetFixedDepositAccountsAccountChart accountChart;

	private Long accountNo;

	private Integer clientId;

	private String clientName;

	private GetFixedDepositAccountsAccountIdCurrency currency;

	private Double depositAmount;

	private Integer depositPeriod;

	private GetFixedDepositAccountsDepositPeriodFrequency depositPeriodFrequency;

	private String externalId;

	private Integer fieldOfficerId;

	private Integer id;

	private GetFixedDepositAccountsInterestCalculationDaysInYearType interestCalculationDaysInYearType;

	private EnumOptionData interestCalculationType;

	private EnumOptionData interestCompoundingPeriodType;

	private Boolean interestFreePeriodApplicable;

	private EnumOptionData interestPostingPeriodType;

	private Double maturityAmount;

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

	private Long resourceId;
}
