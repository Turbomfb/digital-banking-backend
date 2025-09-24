/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.util;

import com.techservices.digitalbanking.core.exception.ValidationException;
import org.apache.commons.lang3.StringUtils;

import java.util.StringJoiner;

public class AppUtil {
	public static final String ROLES = "roles";
	public static final String EXTERNAL = "External";
	public static final String INTERNAL = "Internal";
	public static final String DEFAULT_CURRENCY = "NGN";

	public static final String[] PUBLIC_ENDPOINTS = {
			"/api-docs/**",
			"/swagger-ui/**",
			"/swagger-ui.html",
			"/actuator/health",
			"/actuator/info",
			"/api/v1/cba/health"
	};

	public static final String[] PUBLIC_POST_ENDPOINTS = {
			"/api/v1/customers",
			"/api/v1/loan-products/loan-schedule-calculation",
			"/api/v1/customers/{customerId}/transaction-pin",
			"/api/v1/auth",
			"/api/v1/wallet-accounts/me/transactions/webhooks",
			"/api/v1/auth/create-password",
			"/api/v1/auth/forgot-password"
	};

	public static final String DIRECTORS_DATATABLE_NAME = "Directors";

	public static final String[] PUBLIC_GET_ENDPOINTS = {
			"/api/v1/customers/identity-verification",
			"/api/v1/customers/business-identity-verification",
			"api/v1/loan-products",
			"api/v1/system/codes/**",
			"api/v1/privacy-policy",
			"api/v1/docs",
			"api/v3/api-docs/customers",
			"api/v3/api-docs",
			"api/v3/api-docs/swagger-config",
			"api/v1/swagger-ui/favicon-16x16.png",
			"api/v1/swagger-ui/favicon-32x32.png",
			"api/v1/swagger-ui/swagger-initializer.js",
			"api/v1/swagger-ui/index.css",
			"api/v1/swagger-ui/swagger-ui-bundle.js",
			"api/v1/swagger-ui/swagger-ui-standalone-preset.js",
			"api/v1/swagger-ui/swagger-ui.css",
			"api/v1/swagger-ui/index.html",
			"api/v1/customers/me/addresses/states",
			"api/v1/customers/me/addresses/states/**"
	};

	public static String concatenate(String... strings) {
		StringJoiner joiner = new StringJoiner("");
		for (String str : strings) {
			if (str != null) {
				joiner.add(str);
			}
		}
		return joiner.toString().replaceAll("\\s+$", "");
	}

	public static String maskPhoneNumber(String phoneNumber) {
		return StringUtils.isNotBlank(phoneNumber) ? phoneNumber.replaceAll("(\\d{3})\\d+(\\d{2})", "$1*****$2") : null;
	}

	public static String maskEmailAddress(String emailAddress) {
		return StringUtils.isNotBlank(emailAddress) ? emailAddress.replaceAll("(?<=.{3}).(?=.*@)", "*") : null;
	}

	public static String normalizePhoneNumber(String phoneNumber) {
		String digits = phoneNumber.replaceAll("\\D", "");

		if (digits.startsWith("234") && digits.length() == 13) {
			return "0" + digits.substring(3);
		} else if (digits.startsWith("0") && digits.length() == 11) {
			return digits;
		} else if (digits.startsWith("234") && digits.length() == 10) {
			return "0" + digits.substring(3);
		} else if (digits.length() == 13 && phoneNumber.startsWith("+234")) {
			return "0" + digits.substring(3);
		}

		throw new ValidationException("Invalid.phone.format", "Phone number format is invalid");
	}
	public static boolean isDynamicVirtualAccountDynamic(String accountNumber) {
		return accountNumber.startsWith("9");
	}

	public static boolean isStaticVirtualAccountDynamic(String accountNumber) {
		return accountNumber.startsWith("1");
	}
}
