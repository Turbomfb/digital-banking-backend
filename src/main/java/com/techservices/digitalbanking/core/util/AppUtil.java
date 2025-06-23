/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.util;

import java.util.StringJoiner;

public class AppUtil {
	public static final String ROLES = "roles";

	public static final String[] PUBLIC_ENDPOINTS = {
			"/api-docs/**",
			"/swagger-ui/**",
			"/swagger-ui.html",
			"/actuator/health",
			"/actuator/info",
			"/api/v1/auth",
			"/api/v1/auth/create-password"
	};

	public static final String[] PUBLIC_POST_ENDPOINTS = {
			"/api/v1/customers"
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

	public static boolean isDynamicVirtualAccountDynamic(String accountNumber) {
		return accountNumber.startsWith("9");
	}

	public static boolean isStaticVirtualAccountDynamic(String accountNumber) {
		return accountNumber.startsWith("1");
	}
}
