/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import lombok.Getter;
import lombok.Setter;

/** LoanProductConfigurableAttributes */
@Getter
@Setter
public class LoanProductConfigurableAttributes {

	private Boolean amortizationBoolean;

	private Boolean amortizationType;

	private Boolean arrearsToleranceBoolean;

	private Boolean graceOnArrearsAgeing;

	private Boolean graceOnArrearsAgingBoolean;

	private Boolean graceOnPrincipalAndInterestPayment;

	private Boolean graceOnPrincipalAndInterestPaymentBoolean;

	private Long id;

	private Boolean inArrearsTolerance;

	private Boolean interestCalcPeriodBoolean;

	private Boolean interestCalculationPeriodType;

	private Boolean interestMethodBoolean;

	private Boolean interestType;

	private LoanProduct loanProduct;

	private Boolean _new;

	private Boolean repaymentEvery;

	private Boolean repaymentEveryBoolean;

	private Boolean transactionProcessingStrategyBoolean;

	private Boolean transactionProcessingStrategyId;
}
