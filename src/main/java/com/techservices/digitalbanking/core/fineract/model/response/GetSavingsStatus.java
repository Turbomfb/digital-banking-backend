/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetSavingsStatus {

	private Boolean active;
	private Boolean approved;
	private Boolean closed;
	private String code;
	private Integer id;
	private Boolean rejected;
	private Boolean submittedAndPendingApproval;
	private String value;
	private Boolean withdrawnByApplicant;
}
