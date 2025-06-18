/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.api;

import org.springframework.cloud.openfeign.FeignClient;

import com.techservices.digitalbanking.core.fineract.configuration.FineractClientConfiguration;

@FeignClient(name = "${recurring-deposit-products.name:recurringdepositaccounts}", url = "${fineract.integration.base-url}", configuration = FineractClientConfiguration.class)
public interface RecurringDepositAccountsApiClient extends RecurringDepositAccountsApi {
}
