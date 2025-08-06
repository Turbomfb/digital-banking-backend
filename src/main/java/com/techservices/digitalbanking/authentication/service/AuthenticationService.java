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
package com.techservices.digitalbanking.authentication.service;

import com.techservices.digitalbanking.authentication.domain.data.model.UserLoginActivity;
import com.techservices.digitalbanking.authentication.domain.request.AuthenticationRequest;
import com.techservices.digitalbanking.authentication.domain.request.PasswordMgtRequest;
import com.techservices.digitalbanking.authentication.domain.response.AuthenticationResponse;
import com.techservices.digitalbanking.common.domain.enums.UserType;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest postAuthenticationRequest, UserType customerType, String userAgent, HttpServletRequest request);

    GenericApiResponse createPassword(PasswordMgtRequest passwordMgtRequest);

    GenericApiResponse forgotPassword(PasswordMgtRequest passwordMgtRequest, String command, UserType customerType);

    BasePageResponse<UserLoginActivity> retrieveUserLoginActivities(Long customerId);

    GenericApiResponse changePassword(PasswordMgtRequest passwordMgtRequest);
}
