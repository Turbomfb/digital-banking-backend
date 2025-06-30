package com.techservices.digitalbanking.core.configuration.resttemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techservices.digitalbanking.core.exception.PlatformServiceException;
import com.techservices.digitalbanking.core.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Slf4j
public class CustomResponseErrorHandler implements ResponseErrorHandler {

    private final ObjectMapper objectMapper;

    public CustomResponseErrorHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().isError();
    }

    @Override
    public void handleError(ClientHttpResponse response) {
        String errorBody = null;
        ErrorResponse errorResponse;
        try {
            errorBody = new String(response.getBody().readAllBytes());
            log.error("Error response body: {}", errorBody);
            errorResponse = objectMapper.readValue(errorBody, ErrorResponse.class);
        } catch (Exception e) {
            log.error("Failed to parse error response: {}", e.getMessage());
            throw new PlatformServiceException("error.parsing.error.response", "Failed to parse error response", e);
        }
        String message = errorResponse.getMessage() != null ? errorResponse.getMessage() : errorResponse.getStatusMessage() != null ? errorResponse.getStatusMessage() : "An error occurred while processing the request";
        throw new PlatformServiceException("error.from.external.service", message, errorResponse);
    }
}
