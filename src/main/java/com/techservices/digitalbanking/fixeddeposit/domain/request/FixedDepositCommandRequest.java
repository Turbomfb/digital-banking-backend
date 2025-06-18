/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.fixeddeposit.domain.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FixedDepositCommandRequest {
	private String note;
	private Long onAccountClosureId;
	private Long toSavingsAccountId;
	private String transferDescription;
}
