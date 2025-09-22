/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import lombok.Getter;
import lombok.Setter;

/** PutLoansLoanIdChargesChargeIdResponse */
@Getter
@Setter
public class PutLoansLoanIdChargesChargeIdResponse {

	private Object changes;

	private Long clientId;

	private Long loanId;

	private Long officeId;

	private Integer resourceId;
}
