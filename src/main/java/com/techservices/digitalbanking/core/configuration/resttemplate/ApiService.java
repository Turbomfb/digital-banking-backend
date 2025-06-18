package com.techservices.digitalbanking.core.configuration.resttemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techservices.digitalbanking.core.exception.PlatformServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class ApiService {

    private final RestTemplate restTemplate;

    public ApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> T callExternalApi(String url, Class<T> responseType, HttpMethod method, Object requestPayload) throws JsonProcessingException {
        log.info("Calling external API with details: URL={}, Method={}, Payload={}", url, method, requestPayload);
        ResponseEntity<T> responseEntity;

        if (HttpMethod.GET.equals(method)) {
            responseEntity = restTemplate.getForEntity(url, responseType);
        } else if (HttpMethod.POST.equals(method)) {
            responseEntity = restTemplate.postForEntity(url, requestPayload, responseType);
        } else if (HttpMethod.PUT.equals(method)) {
            restTemplate.put(url, requestPayload);
            return null;
        } else if (HttpMethod.DELETE.equals(method)) {
            restTemplate.delete(url);
            return null;
        } else {
            log.error("Unsupported HTTP method: {}", method);
            throw new PlatformServiceException("HTTP method not supported: " + method);
        }
        log.info("Received response from external API: {}", new ObjectMapper().writeValueAsString(responseEntity));

        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            log.error("API call failed with status code: {}", responseEntity.getStatusCode());
            throw new PlatformServiceException("API call failed with status code: " + responseEntity.getStatusCode());
        }

        return responseEntity.getBody();
    }
}
