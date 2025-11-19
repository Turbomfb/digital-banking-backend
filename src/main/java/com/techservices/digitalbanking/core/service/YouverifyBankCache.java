/* (C)2025 */
package com.techservices.digitalbanking.core.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techservices.digitalbanking.core.configuration.SystemProperty;
import com.techservices.digitalbanking.core.configuration.resttemplate.ApiService;
import com.techservices.digitalbanking.core.domain.dto.response.YouverifyBankDataResponse;
import com.techservices.digitalbanking.core.exception.PlatformServiceException;
import com.techservices.digitalbanking.core.exception.ValidationException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class YouverifyBankCache {

	private final ApiService apiService;
	private final SystemProperty systemProperty;

	@Cacheable("youverifyBankList")
	public YouverifyBankDataResponse getBankList() {

		String url = systemProperty.getYouverifyIntegrationUrl() + "/v2/api/identity/ng/bank-account-number/bank-list";

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("token", systemProperty.getYouverifyIntegrationApiKey());
			return apiService.callExternalApi(url, YouverifyBankDataResponse.class, HttpMethod.GET, null, headers);
		} catch (PlatformServiceException | JsonProcessingException e) {
			log.error("Error retrieving bank list", e);
			throw new ValidationException("external.bank.data.service.error", "Error retrieving Bank data",
					e.getMessage());
		}
	}
}
