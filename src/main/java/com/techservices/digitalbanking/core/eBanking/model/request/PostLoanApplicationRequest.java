/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.request;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.Valid;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostLoanApplicationRequest {
	private String dateFormat;
	private String locale;
	private Long clientId;
	private Long productId;
	private BigDecimal principal;
	private Long loanTermFrequency;
	private Long loanTermFrequencyType;
	private String loanType;
	private Long numberOfRepayments;
	private Long repaymentEvery;
	private Long repaymentFrequencyType;
	private Double interestRatePerPeriod;
	private Long amortizationType;
	private Long interestType;
	private Long interestCalculationPeriodType;
	private String transactionProcessingStrategyCode;
	private String expectedDisbursementDate;
	private String submittedOnDate;
	private Long linkAccountId;
	private BigDecimal fixedEmiAmount;
	private BigDecimal maxOutstandingLoanBalance;
	private Long daysInYearType;
	private String externalId;
	private String repaymentsStartingFromDate;
	private Boolean createStandingInstructionAtDisbursement;
	private Boolean enableInstallmentLevelDelinquency;
	private List<Object> charges;
	private List<Object> collateral;

	@Valid
	private List<@Valid PostLoanApplicationDisbursementData> disbursementData;

	private List<PostLoanApplicationDatatable> datatables;
}
