/* (C)2025 */
package com.techservices.digitalbanking.core.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class IpLocationService {

	private final RestTemplate restTemplate = new RestTemplate();

	public String getLocation(String ip) {

		try {
			String url = UriComponentsBuilder.fromHttpUrl("http://ip-api.com/json/" + ip).toUriString();

			Map<String, Object> response = restTemplate.getForObject(url, Map.class);

			if (response != null && "success".equals(response.get("status"))) {
				return response.get("city") + ", " + response.get("country");
			}
		} catch (Exception e) {
			// Log or suppress as needed
		}
		return "Unknown";
	}
}
