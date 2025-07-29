/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class PostAccountTransfersRequest {

	private String dateFormat;

	private Long fromAccountId;

	private Long fromAccountType;

	private Long fromClientId;

	private Long fromOfficeId;

	private String locale;

	private Long toAccountId;

	private Long toAccountType;

	private Long toClientId;

	private Long toOfficeId;

	private BigDecimal transferAmount;

	private String transferDate;
	private String transactionReference;

	private String transferDescription;

	// private String transactionUniqueId = UUID.randomUUID().toString();
}
