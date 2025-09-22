/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.api;

import org.springframework.cloud.openfeign.FeignClient;

import com.techservices.digitalbanking.core.eBanking.configuration.FineractClientConfiguration;

@FeignClient(name = "${codes.name:codes}", url = "${fineract.integration.base-url}", configuration = FineractClientConfiguration.class)
public interface CodeApiClient extends CodeApi {
}
