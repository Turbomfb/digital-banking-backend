/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.api;

import org.springframework.cloud.openfeign.FeignClient;

import com.techservices.digitalbanking.core.eBanking.configuration.FineractClientConfiguration;

@FeignClient(name = "${journal-entries.name:journal-entries}", url = "${fineract.integration.base-url}", configuration = FineractClientConfiguration.class)
public interface JournalEntryApiClient extends JournalEntryApi {
}
