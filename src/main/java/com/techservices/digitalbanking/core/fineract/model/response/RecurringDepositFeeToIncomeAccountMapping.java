/* Developed by MKAN Engineering (C)2025 */
package com.techservices.digitalbanking.core.fineract.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.techservices.digitalbanking.core.fineract.model.request.Charge;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecurringDepositFeeToIncomeAccountMapping {
	private Charge charge;
	private RecurringDepositAccountingMappingsAccount incomeAccount;
}
