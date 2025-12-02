/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

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
