/* (C)2024 */
package com.techservices.digitalbanking.core.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Configuration
@Getter
public class SystemProperty {

	@Value("${spring.profiles.active}")
	private String activeProfile;

	@Value("${youverify.integration.url}")
	private String youverifyIntegrationUrl;

	@Value("${youverify.integration.api-key}")
	private String youverifyIntegrationApiKey;

	@Value("${payinvert.merchant.integration.url}")
	private String payinvertMerchantIntegrationUrl;

	@Value("${payinvert.merchant.email}")
	private String payinvertMerchantEmail;

	@Value("${payinvert.merchant.password}")
	private String payinvertMerchantPassword;

	@Value("${payinvert.merchant.channelId}")
	private String payinvertMerchantChannelId;

	@Value("${application.identity.verification.threshold}")
	private Long identityVerificationThreshold;

	@Value("${payinvert.loan.integration.url}")
	private String payinvertLoanIntegrationUrl;

	@Value("${payinvert.loan.integration.api-key}")
	private String payinvertLoanIntegrationApiKey;

	@Value("${sms.notification.service.url}")
	private String smsNotificationServiceUrl;

	@Value("${sms.notification.service.api-key}")
	private String smsNotificationServiceApiKey;

	@Value("${sms.notification.service.sender-id}")
	private String smsNotificationServiceSenderId;

	@Value("${application.client.url}")
	private String clientUrl;

	@Value("${application.cors.allowed-origins}")
	private List<String> corsAllowedOrigins;

	@Value("${application.client.payment.redirect.url}")
	private String clientPaymentRedirectUrl;

	@Value("${payinvert.public.key}")
	private String payinvertPublicKey;

	@Value("${payinvert.api.key}")
	private String payinvertApiKey;

	@Value("${payinvert.integration.url}")
	private String payinvertIntegrationUrl;

  @Value("${smtp.host}")
  private String smtpHost;

  @Value("${smtp.port}")
  private Integer smtpPort;

  @Value("${smtp.username}")
  private String smtpUsername;

  @Value("${smtp.password}")
  private String smtpPassword;

  @Value("${email.notification.sender.email}")
  private String smtpSenderEmail;
}
