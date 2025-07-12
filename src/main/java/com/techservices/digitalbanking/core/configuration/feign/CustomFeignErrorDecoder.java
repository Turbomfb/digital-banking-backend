/*
 * Copyright (c) 2025 Techservice Engineering Team.
 * All rights reserved.
 *
 * This software is proprietary and confidential. It may not be reproduced,
 * distributed, or transmitted in any form or by any means, including photocopying,
 * recording, or other electronic or mechanical methods, without the prior written
 * permission of Techservice Engineering Team.
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 *
 * For any questions regarding this license, please contact:
 * Techservice Engineering Team
 * Email: engineering@techservice.com
 */ 
package com.techservices.digitalbanking.core.configuration.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techservices.digitalbanking.core.exception.AbstractPlatformDomainRuleException;
import com.techservices.digitalbanking.core.exception.UnAuthenticatedUserException;
import com.techservices.digitalbanking.core.exception.UnAuthorizedUserException;
import com.techservices.digitalbanking.core.exception.handler.ExceptionResponse;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

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
				case HTTP_BAD_REQUEST -> new AbstractPlatformDomainRuleException(
						"error.msg.cba",
						"Bad request occurred, response body is null",
						List.of("No additional details provided")
				);
				case HTTP_NOT_FOUND -> new AbstractPlatformDomainRuleException(
						"error.msg.cba",
						"Resource not found, response body is null",
						List.of("No additional details provided")
				);
				case HTTP_FORBIDDEN -> new UnAuthorizedUserException(
						"error.msg.cba",
						"Access forbidden, response body is null",
						List.of("No additional details provided")
				);
				case HTTP_UNAUTHORIZED -> new UnAuthenticatedUserException(
						"error.msg.cba",
						"Unauthorized access, response body is null",
						List.of("No additional details provided")
				);
				default -> new AbstractPlatformDomainRuleException(
						"error.msg.cba",
						"Service error occurred, response body is null",
						List.of("No additional details provided")
				);
			};
		}

		try (InputStream bodyIs = response.body().asInputStream()) {
			ObjectMapper mapper = new ObjectMapper();
			ExceptionResponse errorResponse = mapper.readValue(bodyIs, ExceptionResponse.class);

			String errorCode = Objects.toString(errorResponse.getGlobalisationMessageCode(), "error.msg.cba");
			String errorMessage = Objects.toString(errorResponse.getMessage(),
					"We're unable to process your request at this time. Please try again later.");
			List<String> errorDetails = errorResponse.getDetails();

			System.err.println("Status: " + response.status());
			return switch (response.status()) {
				case HTTP_BAD_REQUEST -> new AbstractPlatformDomainRuleException(errorCode, errorMessage, errorDetails);
				case HTTP_NOT_FOUND ->
						new AbstractPlatformDomainRuleException(errorCode, errorMessage, errorDetails);
				case HTTP_FORBIDDEN -> new UnAuthorizedUserException(errorCode, errorMessage, errorDetails);
				case HTTP_UNAUTHORIZED -> new UnAuthenticatedUserException(errorCode, errorMessage, errorDetails);
				default -> new AbstractPlatformDomainRuleException(errorCode, errorMessage, errorDetails);
			};
		} catch (IOException e) {
			log.error("Failed to process error response");
			log.error(e.getMessage(), e);
			return new RuntimeException("We're unable to process your request at this time. Please try again later.", e);
		}
	}

}
