/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.request;

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

	private String fromAccountId;

	private Long fromAccountType;

	private String fromClientId;

	private Long fromOfficeId;

	private String locale;

	private String toAccountId;

	private Long toAccountType;

	private String toClientId;

	private Long toOfficeId;

	private BigDecimal transferAmount;

	private String transferDate;
	private String transactionReference;

	private String transferDescription;

	// private String transactionUniqueId = UUID.randomUUID().toString();
}
