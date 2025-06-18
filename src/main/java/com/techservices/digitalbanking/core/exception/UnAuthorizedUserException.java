/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.exception;

public class UnAuthorizedUserException extends RuntimeException {

	private final String globalisationMessageCode;
	private final String defaultUserMessage;
	private final Object[] defaultUserMessageArgs;

	public UnAuthorizedUserException(final String globalisationMessageCode, final String defaultUserMessage,
			final Object... defaultUserMessageArgs) {
		this.globalisationMessageCode = globalisationMessageCode;
		this.defaultUserMessage = defaultUserMessage;
		this.defaultUserMessageArgs = defaultUserMessageArgs;
	}

	public UnAuthorizedUserException() {
		this.globalisationMessageCode = "error.msg.user.unauthorized";
		this.defaultUserMessage = "User is not allowed to access resource ";
		this.defaultUserMessageArgs = null;
	}

	public UnAuthorizedUserException(final String message) {
		this.globalisationMessageCode = "error.msg.user.unauthorized";
		this.defaultUserMessage = "message";
		this.defaultUserMessageArgs = null;
	}

	public String getGlobalisationMessageCode() {
		return this.globalisationMessageCode;
	}

	public String getDefaultUserMessage() {
		return this.defaultUserMessage;
	}

	public Object[] getDefaultUserMessageArgs() {
		return this.defaultUserMessageArgs;
	}
}
