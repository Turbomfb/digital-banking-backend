/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@ConfigurationProperties("fineract.integration")
@Configuration
@Data
public class FineractProperty {

	private String baseUrl;
	private String baseUrlV2;
	private String username;
	private String password;
	private String tenantId;
	private String basePath;
	private String sortCode;
	private String bankName;
	private int connectTimeoutMs;
	private int readTimeoutMs;
	private int writeTimeoutMs;
	private Long paymentTypeId;
	private Long savingsAccountNominalAnnualInterestRate;
	private Long defaultSavingsProductId;
	private Long defaultRecurringDepositProductId;
	private Long defaultFixedDepositProductId;
}
