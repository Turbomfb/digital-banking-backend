package com.techservices.digitalbanking.walletaccount.domain.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

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
