/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import com.techservices.digitalbanking.investment.domain.response.InvestmentUpdateResponse;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostRecurringDepositTransactionCommandResponse extends InvestmentUpdateResponse {
	private Long officeId;
	private Long clientId;
	private Long savingsId;
	private Long resourceId;
	private Object changes;
}
