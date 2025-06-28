/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class SystemProperty {

	@Value("${spring.profiles.active}")
	private String activeProfile;

	@Value("${payinvert.integration.url}")
	private String payinvertIntegrationUrl;

	@Value("${application.identity.verification.threshold}")
	private Long identityVerificationThreshold;
}
