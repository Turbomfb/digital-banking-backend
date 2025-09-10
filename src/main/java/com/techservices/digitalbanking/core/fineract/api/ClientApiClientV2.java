/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.api;

import com.techservices.digitalbanking.core.fineract.configuration.FineractClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "${clients.name:clients}", url = "${fineract.integration.baseUrlV2}", configuration = FineractClientConfiguration.class)
public interface ClientApiClientV2 extends ClientApiV2 {
}
