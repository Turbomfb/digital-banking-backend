/* (C)2025 */
package com.techservices.digitalbanking.core.domain.enums;

public enum IdentityVerificationDataType {
	NIN, BVN, RC_NUMBER, TIN;

	public boolean isIndividual() {

		return this == NIN || this == BVN;
	}

	public boolean isCorporate() {

		return this == RC_NUMBER || this == TIN;
	}
}
