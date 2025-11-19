/* (C)2024 */
package com.techservices.digitalbanking.loan.domain.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanApplicationRequest {
	private String sessionId;
	private String msisdn;
	private String referralCode;
	private Long offerId;
	private String salaryAccountNo;
	private String acceptOffer;
}
