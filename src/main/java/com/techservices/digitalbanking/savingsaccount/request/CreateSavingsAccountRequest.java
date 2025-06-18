/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.savingsaccount.request;

public record CreateSavingsAccountRequest(Long clientId, String accountNumber, String accountName, String externalId,
		Long productId, boolean isActive) {
}
