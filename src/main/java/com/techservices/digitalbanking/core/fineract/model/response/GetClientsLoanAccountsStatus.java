/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetClientsLoanAccountsStatus {

	private Boolean active;

	private Boolean closed;

	private Boolean closedObligationsMet;

	private Boolean closedRescheduled;

	private Boolean closedWrittenOff;

	private String code;

	private String description;

	private Integer id;

	private Boolean overpaid;

	private Boolean pendingApproval;

	private Boolean waitingForDisbursal;
}
