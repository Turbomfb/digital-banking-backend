/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostFixedDepositAccountsAccountIdResponse {
	private Integer clientId;

	private Integer officeId;

	private Integer resourceId;

	private Integer savingsId;

	private Object changes;
}
