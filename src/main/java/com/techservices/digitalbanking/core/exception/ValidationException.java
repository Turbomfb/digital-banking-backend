/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.exception;

import com.techservices.digitalbanking.core.domain.enums.IdentityVerificationDataType;
import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException {

	private final String globalisationMessageCode;
	private final String defaultUserMessage;
	private final Object[] defaultUserMessageArgs;


	public ValidationException(final String globalisationMessageCode, final String defaultUserMessage,
									 final Object... defaultUserMessageArgs) {
		this.globalisationMessageCode = globalisationMessageCode;
		this.defaultUserMessage = defaultUserMessage;
		this.defaultUserMessageArgs = defaultUserMessageArgs;
	}

	public ValidationException(final IdentityVerificationDataType dataType) {
		this.globalisationMessageCode = dataType.name() + ".verification.failed";
		this.defaultUserMessage = "We're unable to complete your tier upgrade because the information provided does not match our records. " +
				"Please confirm that your " + dataType.name() + " details are correct or contact your bank customer support for assistance.";
		this.defaultUserMessageArgs = null;
	}

	public ValidationException() {
		this.globalisationMessageCode = "error.msg.user.unauthorized";
		this.defaultUserMessage = "User is not allowed to access resource ";
		this.defaultUserMessageArgs = null;
	}

	public ValidationException(final String message) {
		this.globalisationMessageCode = "error.msg.validation.exception";
		this.defaultUserMessage = message;
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
