/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import lombok.Getter;
import lombok.Setter;

/** DeleteLoansLoanIdResponse */
@Getter
@Setter
public class DeleteLoansLoanIdResponse {

	private Integer clientId;

	private Integer loanId;

	private Integer officeId;

	private Integer resourceId;
}
