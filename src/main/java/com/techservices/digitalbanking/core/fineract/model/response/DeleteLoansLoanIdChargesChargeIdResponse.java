/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import lombok.Getter;
import lombok.Setter;

/** DeleteLoansLoanIdChargesChargeIdResponse */
@Getter
@Setter
public class DeleteLoansLoanIdChargesChargeIdResponse {

	private Long clientId;

	private Long loanId;

	private Long officeId;

	private Integer resourceId;
}
