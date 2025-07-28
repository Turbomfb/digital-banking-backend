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
    @JsonProperty("Amount")
    private BigDecimal amount;
    @JsonProperty("Transactiondate")
    private String transactionDate;
    @JsonProperty("Channel")
    private String channel;
    @JsonProperty("Reference")
    private String reference;
    @JsonProperty("Processor")
    private String processor;
    @JsonProperty("Status")
    private String status;
}
