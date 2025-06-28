/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.api;

import com.techservices.digitalbanking.core.fineract.configuration.FineractClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "${system.name:system}", url = "https://cba.dev.turbomfb.com/fineract-provider", configuration = FineractClientConfiguration.class)
public interface SystemApiClient extends SystemApi {
}
