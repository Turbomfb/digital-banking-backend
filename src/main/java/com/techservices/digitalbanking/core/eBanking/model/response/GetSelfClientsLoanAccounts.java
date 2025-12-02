/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import lombok.Getter;
import lombok.Setter;

/** GetSelfClientsLoanAccounts */
@Getter
@Setter
public class GetSelfClientsLoanAccounts {

	private Long accountNo;

	private Integer externalId;

	private Integer id;

	private Integer loanCycle;

	private GetSelfClientsLoanAccountsType loanType;

	private Integer productId;

	private String productName;

	private GetSelfClientsLoanAccountsStatus status;
}
