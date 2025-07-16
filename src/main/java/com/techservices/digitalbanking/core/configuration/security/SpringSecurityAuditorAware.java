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
package com.techservices.digitalbanking.core.configuration.security;

import com.techservices.digitalbanking.core.domain.data.model.AppUser;
import com.techservices.digitalbanking.core.exception.UnAuthenticatedUserException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

	@Override
	@NonNull
	public Optional<String> getCurrentAuditor() {
		return getAppUser()
				.filter(auth -> auth instanceof AppUser)
				.map(auth -> ((AppUser) auth).getEmailAddress())
				.or(() -> {
					log.warn("Authentication is not an instance of CustomUserToken");
					return Optional.of("SYSTEM");
				});
	}

	public AppUser getAuthenticatedUser() {
		return getAppUser()
				.filter(Authentication::isAuthenticated)
				.filter(auth -> auth.getPrincipal() instanceof AppUser)
				.filter(auth -> auth.getPrincipal() != null && ((AppUser)auth.getPrincipal()).getUserId() != null)
				.map(auth -> {
					AppUser userInfo = (AppUser) auth.getPrincipal();
					log.debug("Retrieved userInfo: {}", userInfo);
					return userInfo;
				})
				.orElseThrow(() -> new UnAuthenticatedUserException("user.is.not.authenticated","User is not authenticated"));
	}

	private Optional<Authentication> getAppUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			log.debug("No authentication found in SecurityContext");
			return Optional.empty();
		}
		return Optional.of(authentication);
	}
}
