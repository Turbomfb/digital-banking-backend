/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

/** PostLoansLoanIdRequest */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostLoansLoanIdRequest {

	private String actualDisbursementDate;

	private String approvedLoanAmount;

	private String approvedOnDate;

	private String rejectedOnDate;

	private String assignmentDate;

	private String dateFormat;

	@Valid
	private List<@Valid PostLoansLoanIdDisbursementData> disbursementData;

	private String expectedDisbursementDate;

	private String externalId;

	private Integer fromLoanOfficerId;

	private String locale;

	private String note;

	private Integer paymentTypeId;

	private Integer toLoanOfficerId;

	private String transactionAmount;
}
