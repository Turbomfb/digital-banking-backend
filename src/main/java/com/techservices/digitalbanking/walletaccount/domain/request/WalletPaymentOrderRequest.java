package com.techservices.digitalbanking.walletaccount.domain.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class WalletPaymentOrderRequest {
    private BigDecimal amount;
    private String currency;
}
