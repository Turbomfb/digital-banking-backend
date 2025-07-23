/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.util;

import org.apache.commons.lang3.StringUtils;

import java.util.StringJoiner;

public class AppUtil {
	public static final String ROLES = "roles";

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
			"/api/v1/auth/create-password",
			"/api/v1/auth/forgot-password"
	};

	public static final String[] PUBLIC_GET_ENDPOINTS = {
			"/api/v1/customers/identity-verification",
			"api/v1/loan-products"
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

	public static boolean isDynamicVirtualAccountDynamic(String accountNumber) {
		return accountNumber.startsWith("9");
	}

	public static boolean isStaticVirtualAccountDynamic(String accountNumber) {
		return accountNumber.startsWith("1");
	}
}
