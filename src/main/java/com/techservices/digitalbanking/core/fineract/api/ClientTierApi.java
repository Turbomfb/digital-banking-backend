/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.api;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.techservices.digitalbanking.core.fineract.model.response.ClientTierData;

public interface ClientTierApi {
	@GetMapping(value = "/client-tiers")
	Collection<ClientTierData> getClientTiers();

	@GetMapping(value = "/client-tiers/{clientTierId}")
	ClientTierData getClientTier(@PathVariable Long clientTierId);
}
