/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PutFixedDepositAccountsChanges {
	private Double depositAmount;

	private String locale;
}
