/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetClientsLoanAccounts {

	private String accountNo;

	private String externalId;

	private Integer id;

	private Integer loanCycle;

	private EnumOptionData loanType;

	private Integer productId;

	private String productName;

	private GetClientsLoanAccountsStatus status;
}
