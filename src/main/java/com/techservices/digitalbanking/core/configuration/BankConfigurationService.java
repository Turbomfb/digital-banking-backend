/* (C)2025 */
package com.techservices.digitalbanking.core.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Service
@Getter
public class BankConfigurationService {

	@Value("${app.bank.code}")
	private String bankCode;

	@Value("${app.bank.name}")
	private String bankName;

	@Value("${app.bank.logo.url}")
	private String bankLogoUrl;

	@Value("${app.bank.address}")
	private String bankAddress;

	@Value("${app.bank.supoort.phone}")
	private String bankSupportPhone;

	@Value("${app.bank.supoort.email}")
	private String bankSupportEmail;

	@Value("${app.bank.website}")
	private String bankWebsite;

	@Value("${app.bank.license.number}")
	private String bankLicenseNumber;

	@Value("${app.bank.statement.footer}")
	private String statementFooter;

	@Data
	@Builder
	public static class BankConfiguration {
		private String bankName;
		private String bankLogoUrl;
		private String bankAddress;
		private String bankPhone;
		private String bankEmail;
		private String bankWebsite;
		private String bankLicenseNumber;
		private String statementFooter;
	}
}
