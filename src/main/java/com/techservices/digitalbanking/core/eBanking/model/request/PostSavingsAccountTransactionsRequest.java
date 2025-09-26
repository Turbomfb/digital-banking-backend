/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.request;

import java.math.BigDecimal;
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

	private BigDecimal transactionAmount;
	private String transactionReference;
	private String narration;
}
