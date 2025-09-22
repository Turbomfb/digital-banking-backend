/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import lombok.Getter;
import lombok.Setter;

/** PostLoansLoanIdChanges */
@Getter
@Setter
public class PostLoansLoanIdChanges {

	private String approvedOnDate;

	private String dateFormat;

	private String locale;

	private String note;

	private PostLoansLoanIdStatus status;
}
