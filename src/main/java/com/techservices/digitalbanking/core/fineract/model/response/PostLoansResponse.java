/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

/** PostLoansResponse */
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class PostLoansResponse {

	private Integer clientId;

	private GetLoansLoanIdCurrency currency;

	private Integer loanId;

	private Integer loanTermInDays;

	private Integer officeId;

	@Valid
	private Set<@Valid PostLoansRepaymentSchedulePeriods> periods = new LinkedHashSet<>();

	private Integer resourceId;
	private String resourceExternalId;

	private Long totalFeeChargesCharged;

	private Double totalInterestCharged;

	private Long totalOutstanding;

	private Long totalPenaltyChargesCharged;

	private Long totalPrincipalDisbursed;

	private Long totalPrincipalExpected;

	private Long totalPrincipalPaid;

	private Long totalRepayment;

	private Double totalRepaymentExpected;

	private Long totalWaived;

	private Long totalWrittenOff;
}
