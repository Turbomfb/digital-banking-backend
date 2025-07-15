/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.exception.handler;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExceptionResponse {

	private Date timestamp;
	private String message;
	private List<String> details;
	private String globalisationMessageCode;
	private String defaultUserMessage;
	private Object[] defaultUserMessageArgs;

	public ExceptionResponse(Date timestamp, String message, List<String> details) {
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public ExceptionResponse(Date timestamp, String message, String details) {
		this.timestamp = timestamp;
		this.message = message;
		this.details = Collections.singletonList(details);
	}

	public ExceptionResponse(Date timestamp, String message, String details, String globalisationMessageCode) {
		this.timestamp = timestamp;
		this.message = message;
		this.details = Collections.singletonList(details);
		this.globalisationMessageCode = globalisationMessageCode;
	}

	public ExceptionResponse(Date timestamp, String message, String details, String globalisationMessageCode, Object[] defaultUserMessageArgs) {
		this.timestamp = timestamp;
		this.message = message;
		this.details = Collections.singletonList(details);
		this.globalisationMessageCode = globalisationMessageCode;
		this.defaultUserMessageArgs = defaultUserMessageArgs;
	}

	public static ExceptionResponse getCodeAndNarration(RuntimeException e, WebRequest request) {
		return new ExceptionResponse(new Date(), e.getLocalizedMessage(), request.getDescription(false));
	}
}
