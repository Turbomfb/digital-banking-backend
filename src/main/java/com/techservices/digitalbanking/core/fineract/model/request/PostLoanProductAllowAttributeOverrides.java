/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostLoanProductAllowAttributeOverrides {
	private Boolean amortizationType;
	private Boolean interestType;
	private Boolean transactionProcessingStrategyCode;
	private Boolean interestCalculationPeriodType;
	private Boolean inArrearsTolerance;
	private Boolean repaymentEvery;
	private Boolean graceOnPrincipalAndInterestPayment;
	private Boolean graceOnArrearsAgeing;
}
