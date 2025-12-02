/* (C)2024 */
package com.techservices.digitalbanking.investment.domain.request;

import lombok.*;

@Setter
@Getter
public class RecurringDepositCommandRequest {
	private String note;
	private Long onAccountClosureId;
	private Long toSavingsAccountId;
	private String transferDescription;
}
