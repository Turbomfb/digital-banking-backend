/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.exception;

import lombok.Getter;

@Getter
public class AbstractPlatformResourceNotFoundException extends RuntimeException {

	private final String globalisationMessageCode;
	private final String defaultUserMessage;
	private final Object[] defaultUserMessageArgs;
	private final String message;

	public AbstractPlatformResourceNotFoundException(String globalisationMessageCode, String defaultUserMessage,
			Object... defaultUserMessageArgs) {
		this.globalisationMessageCode = globalisationMessageCode;
		this.defaultUserMessage = defaultUserMessage;
		this.defaultUserMessageArgs = defaultUserMessageArgs;
		this.message = defaultUserMessage;
	}
}
