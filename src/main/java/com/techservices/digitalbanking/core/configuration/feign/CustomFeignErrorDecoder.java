/* (C)2025 */
package com.techservices.digitalbanking.core.configuration.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techservices.digitalbanking.core.exception.AbstractPlatformDomainRuleException;
import com.techservices.digitalbanking.core.exception.UnAuthenticatedUserException;
import com.techservices.digitalbanking.core.exception.UnAuthorizedUserException;
import com.techservices.digitalbanking.core.exception.handler.ExceptionResponse;
import feign.Response;
import feign.codec.ErrorDecoder;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomFeignErrorDecoder implements ErrorDecoder {

  private static final int HTTP_BAD_REQUEST = 400;
  private static final int HTTP_UNAUTHORIZED = 401;
  private static final int HTTP_FORBIDDEN = 403;
  private static final int HTTP_NOT_FOUND = 404;

  @Override
  public Exception decode(String methodKey, Response response) {

    if (response.body() == null) {
      return switch (response.status()) {
        case HTTP_BAD_REQUEST ->
            new AbstractPlatformDomainRuleException(
                "error.msg.cba",
                "Bad request occurred, response body is null",
                List.of("No additional details provided"));
        case HTTP_NOT_FOUND ->
            new AbstractPlatformDomainRuleException(
                "error.msg.cba",
                "Resource not found, response body is null",
                List.of("No additional details provided"));
        case HTTP_FORBIDDEN ->
            new UnAuthorizedUserException(
                "error.msg.cba",
                "Access forbidden, response body is null",
                List.of("No additional details provided"));
        case HTTP_UNAUTHORIZED ->
            new UnAuthenticatedUserException(
                "error.msg.cba",
                "Unauthorized access, response body is null",
                List.of("No additional details provided"));
        default ->
            new AbstractPlatformDomainRuleException(
                "error.msg.cba",
                "Service error occurred, response body is null",
                List.of("No additional details provided"));
      };
    }

    try (InputStream bodyIs = response.body().asInputStream()) {
      ObjectMapper mapper = new ObjectMapper();
      ExceptionResponse errorResponse = mapper.readValue(bodyIs, ExceptionResponse.class);

      String errorCode =
          Objects.toString(errorResponse.getGlobalisationMessageCode(), "error.msg.cba");
      String errorMessage =
          Objects.toString(
              errorResponse.getMessage(),
              "We're unable to process your request at this time. Please try again later.");
      List<String> errorDetails = errorResponse.getDetails();

      System.err.println("Status: " + response.status());
      return switch (response.status()) {
        case HTTP_BAD_REQUEST ->
            new AbstractPlatformDomainRuleException(errorCode, errorMessage, errorDetails);
        case HTTP_NOT_FOUND ->
            new AbstractPlatformDomainRuleException(errorCode, errorMessage, errorDetails);
        case HTTP_FORBIDDEN -> new UnAuthorizedUserException(errorCode, errorMessage, errorDetails);
        case HTTP_UNAUTHORIZED ->
            new UnAuthenticatedUserException(errorCode, errorMessage, errorDetails);
        default -> new AbstractPlatformDomainRuleException(errorCode, errorMessage, errorDetails);
      };
    } catch (IOException e) {
      log.error("Failed to process error response");
      log.error(e.getMessage(), e);
      return new RuntimeException(
          "We're unable to process your request at this time. Please try again later.", e);
    }
  }
}
