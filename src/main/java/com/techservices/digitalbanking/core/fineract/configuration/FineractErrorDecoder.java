/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.configuration;

import java.io.IOException;
import java.io.Reader;
import java.util.Objects;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.techservices.digitalbanking.core.exception.AbstractPlatformDomainRuleException;
import com.techservices.digitalbanking.core.exception.AbstractPlatformResourceNotFoundException;
import com.techservices.digitalbanking.core.exception.FineractErrorResponse;
import com.techservices.digitalbanking.core.exception.FineractErrorResponse.ErrorDetail;
import com.techservices.digitalbanking.core.exception.PlatformServiceException;
import com.techservices.digitalbanking.core.exception.UnAuthenticatedUserException;
import com.techservices.digitalbanking.core.exception.UnAuthorizedUserException;
import com.techservices.digitalbanking.core.util.JsonUtil;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FineractErrorDecoder implements ErrorDecoder {

	@Override
	public Exception decode(String s, Response response) {
		Reader reader = null;
		FineractErrorResponse fineractErrorResponse = new FineractErrorResponse();

		try {
			if (response.body() != null) { // Add null check for response.body()
				reader = response.body().asReader();
				String result = IOUtils.toString(reader);

				if (StringUtils.isNotEmpty(result)) {
					fineractErrorResponse = JsonUtil.convertJsonBodyToObject(result, FineractErrorResponse.class);
				}
			} else {
				log.warn("Response body is null for status: {}", response.status());
			}
		} catch (IOException e) {
			log.error(e.getLocalizedMessage(), e);
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				log.error(e.getLocalizedMessage(), e);
			}
		}

		String errorCode = Objects.toString(fineractErrorResponse.getUserMessageGlobalisationCode(), "error.msg.cba");
		String errorMessage = Objects.toString(fineractErrorResponse.getDefaultUserMessage(),
				"CBA error occurred, Please contact the admin");

		if (!fineractErrorResponse.getErrors().isEmpty()) {
			ErrorDetail errorDetail = fineractErrorResponse.getErrors().get(0);
			errorMessage = errorDetail.getDefaultUserMessage();
			errorCode = errorDetail.getUserMessageGlobalisationCode();
		}

		switch (response.status()) {
			case 400 -> throw new AbstractPlatformDomainRuleException(errorCode, errorMessage,
					fineractErrorResponse.getErrors());
			case 404 -> throw new AbstractPlatformResourceNotFoundException(errorCode, errorMessage,
					fineractErrorResponse.getErrors());
			case 403 -> throw new UnAuthorizedUserException(errorCode, errorMessage, fineractErrorResponse.getErrors());
			case 401 -> throw new UnAuthenticatedUserException(errorCode, errorMessage, fineractErrorResponse.getErrors());
			default -> throw new PlatformServiceException(errorCode, errorMessage, fineractErrorResponse.getErrors());
		}
	}
}
