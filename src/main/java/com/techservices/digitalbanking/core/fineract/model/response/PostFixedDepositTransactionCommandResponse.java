/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostFixedDepositTransactionCommandResponse {
	private Long officeId;
	private Long clientId;
	private Long savingsId;
	private Long resourceId;
	private Object changes;
}
