/* Developed by MKAN Engineering (C)2025 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanTransactionResponse {

	private Long id;
	private Long loanId;
	private String externalLoanId;
	private Integer officeId;
	private String officeName;
	private TransactionType type;
	private List<Integer> date;
	private Currency currency;
	private BigDecimal amount;
	private BigDecimal netDisbursalAmount;
	private BigDecimal principalPortion;
	private BigDecimal interestPortion;
	private BigDecimal feeChargesPortion;
	private BigDecimal penaltyChargesPortion;
	private BigDecimal overpaymentPortion;
	private BigDecimal unrecognizedIncomePortion;
	private BigDecimal outstandingLoanBalance;
	private List<Integer> submittedOnDate;
	private Boolean manuallyReversed;
	private List<Object> loanChargePaidByList;
	private Integer numberOfRepayments;
	private List<Object> transactionRelations;

	@Getter
	@Setter
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class TransactionType {
		private Integer id;
		private String code;
		private String value;
		private Boolean disbursement;
		private Boolean repaymentAtDisbursement;
		private Boolean repayment;
		private Boolean merchantIssuedRefund;
		private Boolean payoutRefund;
		private Boolean goodwillCredit;
		private Boolean interestPaymentWaiver;
		private Boolean chargeRefund;
		private Boolean contra;
		private Boolean waiveInterest;
		private Boolean waiveCharges;
		private Boolean accrual;
		private Boolean writeOff;
		private Boolean recoveryRepayment;
		private Boolean initiateTransfer;
		private Boolean approveTransfer;
		private Boolean withdrawTransfer;
		private Boolean rejectTransfer;
		private Boolean chargePayment;
		private Boolean refund;
		private Boolean refundForActiveLoans;
		private Boolean creditBalanceRefund;
		private Boolean chargeAdjustment;
		private Boolean chargeback;
		private Boolean chargeoff;
		private Boolean downPayment;
		private Boolean reAge;
		private Boolean reAmortize;
		private Boolean accrualActivity;
		private Boolean interestRefund;
		private Boolean accrualAdjustment;
	}

	@Getter
	@Setter
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Currency {
		private String code;
		private String name;
		private Integer decimalPlaces;
		private Integer inMultiplesOf;
		private String nameCode;
		private String displayLabel;
	}
}
