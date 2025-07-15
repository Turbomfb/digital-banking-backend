/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetDataTablesResponse {

	@JsonProperty("account_number")
	private String accountNumber;

	@JsonProperty("account_name")
	private String accountName;

	@JsonProperty("bank_code")
	private String bankCode;

	@JsonProperty("bank_name")
	private String bankName;
}
