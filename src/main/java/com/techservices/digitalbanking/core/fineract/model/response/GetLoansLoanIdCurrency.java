/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import lombok.Getter;
import lombok.Setter;

/** GetLoansLoanIdCurrency */
@Getter
@Setter
public class GetLoansLoanIdCurrency {

	private String code;

	private Integer decimalPlaces;

	private String displayLabel;

	private String displaySymbol;

	private String name;

	private String nameCode;
}
