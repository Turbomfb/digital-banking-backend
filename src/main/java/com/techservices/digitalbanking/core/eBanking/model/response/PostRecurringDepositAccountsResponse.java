/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import com.techservices.digitalbanking.investment.domain.response.InvestmentApplicationResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRecurringDepositAccountsResponse extends InvestmentApplicationResponse {
	private Integer clientId;

	private Integer officeId;

	private Long resourceId;

	private Integer savingsId;
}
