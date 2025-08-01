/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.exception.handler;

import java.util.*;

import com.techservices.digitalbanking.core.configuration.resttemplate.ApiService;
import feign.RetryableException;
import org.springframework.http.*;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.techservices.digitalbanking.core.exception.AbstractPlatformDomainRuleException;
import com.techservices.digitalbanking.core.exception.AbstractPlatformResourceNotFoundException;
import com.techservices.digitalbanking.core.exception.PlatformServiceException;
import com.techservices.digitalbanking.core.exception.UnAuthenticatedUserException;
import com.techservices.digitalbanking.core.exception.UnAuthorizedUserException;
import com.techservices.digitalbanking.core.exception.ValidationException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@RestController
@Slf4j
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private final ApiService apiService;

	public CustomizedResponseEntityExceptionHandler(ApiService apiService) {
		this.apiService = apiService;
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
		log.error("Exception occurred while making a call to {} with reason: \n {}", request.getDescription(false),
				ex.getMessage(), ex);
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
				"Oops, Something went wrong. It's not you , It's us. Please contact  the Admin if issue persists  ",
				request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(AbstractPlatformResourceNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleTenantNotFoundException(
			AbstractPlatformResourceNotFoundException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getDefaultUserMessage(),
				request.getDescription(false), ex.getGlobalisationMessageCode());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(AbstractPlatformDomainRuleException.class)
	public final ResponseEntity<ExceptionResponse> handleValidationException(AbstractPlatformDomainRuleException ex,
			WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getDefaultUserMessage(),
				request.getDescription(false), ex.getGlobalisationMessageCode());
		Map<String, Object> errorResponse = new HashMap<>();
		errorResponse.put("message", ex.getGlobalisationMessageCode());
		errorResponse.put("default", ex.getDefaultUserMessage());
		errorResponse.put("data", ex.getDefaultUserMessageArgs());
		try {
			apiService.callExternalApi("https://webhook.site/430adecb-d4d6-4b5d-a576-373b4ff44e9b", String.class, HttpMethod.POST, errorResponse, null);
		} catch (Exception e) {
			log.error("Failed to send error response to webhook: {}", e.getMessage(), e);
		}
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UnAuthorizedUserException.class)
	public final ResponseEntity<ExceptionResponse> handleInvalidUserException(UnAuthorizedUserException ex,
			WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getDefaultUserMessage(),
				request.getDescription(false), ex.getGlobalisationMessageCode());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(UnAuthenticatedUserException.class)
	public final ResponseEntity<ExceptionResponse> handleInvalidUserException(UnAuthenticatedUserException ex,
			WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getDefaultUserMessage(),
				request.getDescription(false), ex.getGlobalisationMessageCode());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(PlatformServiceException.class)
	public final ResponseEntity<ExceptionResponse> handleInternalServiceException(PlatformServiceException ex,
			WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getDefaultUserMessage(),
				request.getDescription(false), ex.getGlobalisationMessageCode());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ValidationException.class)
	public final ResponseEntity<ExceptionResponse> handleInternalServiceException(ValidationException ex,
			WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getDefaultUserMessage(),
				request.getDescription(false), ex.getGlobalisationMessageCode(), ex.getDefaultUserMessageArgs());
		Map<String, Object> errorResponse = new HashMap<>();
		errorResponse.put("message", ex.getGlobalisationMessageCode());
		errorResponse.put("default", ex.getDefaultUserMessage());
		errorResponse.put("data", ex.getDefaultUserMessageArgs());
		try {
			apiService.callExternalApi("https://webhook.site/430adecb-d4d6-4b5d-a576-373b4ff44e9b", String.class, HttpMethod.POST, errorResponse, null);
		} catch (Exception e) {
			log.error("Failed to send error response to webhook: {}", e.getMessage(), e);
		}
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<String> details = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		ExceptionResponse error = new ExceptionResponse(new Date(), "Validation Failed", details);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RetryableException.class)
	protected ResponseEntity<Object> handleRetryableException(RetryableException ex,
																  HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false), ex.getLocalizedMessage(), null);
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
