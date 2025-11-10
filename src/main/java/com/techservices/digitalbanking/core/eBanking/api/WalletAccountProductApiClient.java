/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.api;

import org.springframework.cloud.openfeign.FeignClient;

import com.techservices.digitalbanking.core.eBanking.configuration.FineractClientConfiguration;

@FeignClient(name = "${savingsaccounts.products.name:savingsproducts}", url = "${ebanking.integration.baseUrl}", configuration = FineractClientConfiguration.class)
public interface WalletAccountProductApiClient extends WalletAccountProductApi {
}
