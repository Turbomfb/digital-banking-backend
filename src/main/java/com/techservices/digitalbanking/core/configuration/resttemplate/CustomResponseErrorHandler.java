package com.techservices.digitalbanking.core.configuration.resttemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techservices.digitalbanking.core.exception.PlatformServiceException;
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

    public void handleError(ClientHttpResponse response) {
        String errorBody = null;
        try {
            errorBody = new String(response.getBody().readAllBytes());
            log.error("Error response body: {}", errorBody);

            if (isValidJson(errorBody)) {
                ErrorResponse errorResponse = objectMapper.readValue(errorBody, ErrorResponse.class);
                String message = errorResponse.getMessage() != null
                        ? errorResponse.getMessage()
                        : errorResponse.getStatusMessage() != null
                        ? errorResponse.getStatusMessage()
                        : "An error occurred while processing the request";
                throw new PlatformServiceException("error.from.external.service", message, errorResponse);
            } else {
                throw new PlatformServiceException("error.from.external.service", errorBody);
            }
        } catch (IOException e) {
            throw new PlatformServiceException("error.from.external.service", e.getMessage());
        }
    }


        private boolean isValidJson(String input) {
        try {
            objectMapper.readTree(input);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
