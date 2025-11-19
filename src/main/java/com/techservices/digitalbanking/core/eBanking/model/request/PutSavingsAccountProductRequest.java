/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class PutSavingsAccountProductRequest {
	private String name;
	private String shortName;
	private String description;
	private String currencyCode;
	private int digitsAfterDecimal;
	private int inMultiplesOf;
	private String locale;
	private String accountingRule;
}
