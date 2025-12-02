/* (C)2025 */
package com.techservices.digitalbanking.authentication.service;

import com.techservices.digitalbanking.authentication.domain.data.model.UserLoginActivity;
import com.techservices.digitalbanking.authentication.domain.request.AuthenticationRequest;
import com.techservices.digitalbanking.authentication.domain.request.PasswordMgtRequest;
import com.techservices.digitalbanking.authentication.domain.response.AuthenticationResponse;
import com.techservices.digitalbanking.common.domain.enums.UserType;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.customer.domian.dto.request.CustomerTransactionPinRequest;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthenticationService {
	AuthenticationResponse authenticate(AuthenticationRequest postAuthenticationRequest, UserType customerType,
			String userAgent, HttpServletRequest request);

	boolean isTokenValid(String token);

	GenericApiResponse createPassword(PasswordMgtRequest passwordMgtRequest);

	GenericApiResponse forgotPassword(PasswordMgtRequest passwordMgtRequest, String command, UserType customerType);

	BasePageResponse<UserLoginActivity> retrieveUserLoginActivities(Long customerId);

	GenericApiResponse changePassword(PasswordMgtRequest passwordMgtRequest);

	GenericApiResponse changeTransactionPin(CustomerTransactionPinRequest pinRequest);

	void logout(Long customerId);
}
