/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import com.techservices.digitalbanking.investment.domain.response.InvestmentApplicationResponse;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostSavingsAccountsResponse extends InvestmentApplicationResponse {

	private Long clientId;

	private Long officeId;

	private Long resourceId;

	private Long savingsId;

	private String savingsAccountNo;
}
