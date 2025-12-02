/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostSavingsAccountTransactionsRequest {

	private BigDecimal transactionAmount;
	private String transactionReference;
	private String narration;
}
