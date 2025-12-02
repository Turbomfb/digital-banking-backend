/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.api;

import org.springframework.cloud.openfeign.FeignClient;

import com.techservices.digitalbanking.core.eBanking.configuration.FineractClientConfiguration;

@FeignClient(name = "${accounttransfers.name:accounttransfers}", url = "${ebanking.integration.baseUrl}", configuration = FineractClientConfiguration.class)
public interface TransactionApiClient extends TransactionApi {
}
