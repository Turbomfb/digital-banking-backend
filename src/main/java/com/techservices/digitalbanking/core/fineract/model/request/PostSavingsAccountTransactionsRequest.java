/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.request;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostSavingsAccountTransactionsRequest {

	private String dateFormat;

	private String lienAllowed;

	private String locale;

	private String reasonForBlock;

	private BigDecimal transactionAmount;

	private String transactionDate;

	private String transactionReference;

	private String transactionUniqueId;

	private Long paymentTypeId;

	private String beneficiaryAccountNumber;

	private String beneficiaryBankCode;

	private String toVirtualAccountId;

	private String note;

	private String accountNumber;

	private String receiptNumber;

	private Long glAccountId;

	@JsonProperty("isInternal")
	private Boolean isInternal;

	private Map<String, Object> additionalInformation;

	@Valid
	private List<@Valid PostClientsDatatable> datatables;
}
