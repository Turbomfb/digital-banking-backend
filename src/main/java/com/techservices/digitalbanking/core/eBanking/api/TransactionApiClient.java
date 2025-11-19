/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.api;

import com.techservices.digitalbanking.core.eBanking.configuration.FineractClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
    name = "${accounttransfers.name:accounttransfers}",
    url = "${ebanking.integration.baseUrl}",
    configuration = FineractClientConfiguration.class)
public interface TransactionApiClient extends TransactionApi {}
