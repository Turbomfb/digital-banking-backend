/* (C)2025 */
package com.techservices.digitalbanking.core.eBanking.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanRescheduleRequest {

	private Long loanId;
	private Integer graceOnPrincipal;
	private Integer graceOnInterest;
	private Integer extraTerms;
	private String rescheduleFromDate;
	private String dateFormat;
	private String locale;
	private Boolean recalculateInterest;
	private String submittedOnDate;
	private Integer newInterestRate;
	private Integer rescheduleReasonId;
}
