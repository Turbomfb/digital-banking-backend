/* (C)2025 */
package com.techservices.digitalbanking.walletaccount.domain.request;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WalletPaymentOrderRequest {
	private BigDecimal amount;
	private String currency;
}
