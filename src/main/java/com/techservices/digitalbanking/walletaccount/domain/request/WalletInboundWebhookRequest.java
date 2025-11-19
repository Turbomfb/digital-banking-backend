/* (C)2025 */
package com.techservices.digitalbanking.walletaccount.domain.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WalletInboundWebhookRequest {

	private BigDecimal amount;

	private String transactionDate;

	private String channel;

	private String reference;

	private String processor;

	private String status;
}
