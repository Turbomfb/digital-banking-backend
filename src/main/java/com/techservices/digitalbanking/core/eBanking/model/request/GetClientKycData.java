/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.request;

import com.techservices.digitalbanking.core.domain.dto.KycTierDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetClientKycData {

	private KycTierDto kycTierDto;

	private ClientTransactionLimit clientTransactionLimit;
}
