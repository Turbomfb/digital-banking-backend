/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import lombok.Getter;
import lombok.Setter;

/** GetGroupsGroupIdAccountsLoanAccounts */
@Getter
@Setter
public class GetGroupsGroupIdAccountsLoanAccounts {

	private Long accountNo;

	private Integer id;

	private GetGroupsGroupIdAccountsLoanType loanType;

	private Integer productId;

	private String productName;

	private Object status;
}
