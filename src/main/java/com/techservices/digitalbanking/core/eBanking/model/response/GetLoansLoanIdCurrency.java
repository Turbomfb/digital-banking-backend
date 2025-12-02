/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

/** GetLoansLoanIdCurrency */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetLoansLoanIdCurrency {

	private String code;

	private Integer decimalPlaces;

	private String displayLabel;

	private String displaySymbol;

	private String name;

	private String nameCode;
}
