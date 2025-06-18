/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.request;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.Valid;
import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostSavingsAccountsRequest {

	private Long clientId;

	private String dateFormat;

	private String locale;

	private String accountName;

	private String externalId;

	private String accountNo;

	private Long productId;

	private Boolean active;

	private String submittedOnDate;

	private String approvedOnDate;

	private String activatedOnDate;

	@Valid
	private List<@Valid PostClientsDatatable> datatables = Collections.emptyList();
}
