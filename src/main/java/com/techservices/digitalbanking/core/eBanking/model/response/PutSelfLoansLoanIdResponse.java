/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import lombok.Getter;
import lombok.Setter;

/** PutSelfLoansLoanIdResponse */
@Getter
@Setter
public class PutSelfLoansLoanIdResponse {

	private PutSelfLoansChanges changes;

	private Integer clientId;

	private Integer loanId;

	private Integer officeId;

	private Integer resourceId;
}
