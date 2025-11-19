/* (C)2024 */
package com.techservices.digitalbanking.walletaccount.domain.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSavingsAccountRequest {
	private String clientId;
	private String accountNumber;
	private String accountName;
	private String externalId;
	private Long productId;
	private boolean isActive;
}
