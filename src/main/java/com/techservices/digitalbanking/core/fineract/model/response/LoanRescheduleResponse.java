/* Developed by MKAN Engineering (C)2025 */
package com.techservices.digitalbanking.core.fineract.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanRescheduleResponse {

	private Long loanId;
	private Long resourceId;
}
