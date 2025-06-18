/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.api;

import org.springframework.cloud.openfeign.FeignClient;

import com.techservices.digitalbanking.core.fineract.configuration.FineractClientConfiguration;

@FeignClient(name = "${clients.name:clients}", url = "${fineract.integration.base-url}", configuration = FineractClientConfiguration.class)
public interface ClientApiClient extends ClientApi {
}
