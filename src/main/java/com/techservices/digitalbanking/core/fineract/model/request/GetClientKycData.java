/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetClientKycData {

	private KycTier kycTier;

	private ClientTransactionLimit clientTransactionLimit;
}
