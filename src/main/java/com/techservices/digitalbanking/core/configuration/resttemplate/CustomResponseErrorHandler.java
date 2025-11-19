/* (C)2025 */
package com.techservices.digitalbanking.core.configuration.resttemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techservices.digitalbanking.core.exception.PlatformServiceException;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

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
      System.err.println("Error response body: " + errorBody);

      if (isValidJson(errorBody)) {
        ErrorResponse errorResponse = objectMapper.readValue(errorBody, ErrorResponse.class);
        String message =
            errorResponse.getMessage() != null
                ? errorResponse.getMessage()
                : errorResponse.getStatusMessage() != null
                    ? errorResponse.getStatusMessage()
                    : "We're unable to process your request at this time. Please try again later.";
        throw new PlatformServiceException("error.from.external.service", message, errorResponse);
      } else {
        throw new PlatformServiceException(
            "error.from.external.service",
            "We're unable to process your request at this time. Please try again later.",
            errorBody);
      }
    } catch (IOException e) {
      log.error("Error processing external payment transaction {}", e.toString());
      throw new PlatformServiceException(
          "error.from.external.service",
          "We're unable to process your request at this time. Please try again later.",
          e.getMessage());
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

  public static void main(String[] args) {

    System.out.println(new String("null"));
  }
}
