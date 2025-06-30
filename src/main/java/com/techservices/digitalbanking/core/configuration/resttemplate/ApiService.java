package com.techservices.digitalbanking.core.configuration.resttemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techservices.digitalbanking.core.exception.PlatformServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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

    public <T> T callExternalApi(String url, Class<T> responseType, HttpMethod method, Object requestPayload, HttpHeaders headers) throws JsonProcessingException {
        log.info("Calling external API with details: URL={}, Method={}, Payload={}", url, method, requestPayload);
        ResponseEntity<T> responseEntity;

        HttpEntity<Object> entity = headers != null ? new HttpEntity<>(requestPayload, headers) : new HttpEntity<>(requestPayload);

        if (HttpMethod.GET.equals(method)) {
            responseEntity = restTemplate.exchange(url, method, entity, responseType);
        } else if (HttpMethod.POST.equals(method)) {
            responseEntity = restTemplate.exchange(url, method, entity, responseType);
        } else if (HttpMethod.PUT.equals(method)) {
            restTemplate.exchange(url, method, entity, Void.class);
            return null;
        } else if (HttpMethod.DELETE.equals(method)) {
            restTemplate.exchange(url, method, entity, Void.class);
            return null;
        } else {
            log.error("Unsupported HTTP method: {}", method);
            throw new PlatformServiceException("HTTP method not supported: " + method);
        }
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            log.info("External API call successful: {}", responseEntity.getStatusCode());
        } else if (responseEntity.getStatusCode().is4xxClientError()) {
            log.error("External API call failed with client error: {}", responseEntity.getStatusCode());
            throw new PlatformServiceException("Client error during external API call: " + responseEntity.getBody());
        } else if (responseEntity.getStatusCode().is5xxServerError()) {
            log.error("External API call failed with server error: {}", responseEntity.getStatusCode());
            throw new PlatformServiceException("Server error during external API call: " + responseEntity.getBody());
        } else {
            log.error("External API call failed with status code: {}", responseEntity.getStatusCode());
            throw new PlatformServiceException("External API call failed: " + responseEntity.getBody());
        }
        log.info("Received response from external API: {}", new ObjectMapper().writeValueAsString(responseEntity));

        return responseEntity.getBody();
    }
}
