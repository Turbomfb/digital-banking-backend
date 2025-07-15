/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.util;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FormatUtil {

	public static boolean amountIsValid(String amount) {
		boolean greaterThanTwoDecimals = StringUtils.substringAfter(amount, ".").length() > 2;
		if (greaterThanTwoDecimals) {
			log.error("Amount {} has more than 2 decimals ", amount);
		}
		try {
			Double.parseDouble(amount);
		} catch (NumberFormatException e) {
			log.error("Invalid string supplied as a number :{}", amount);
			return false;
		}
		return !greaterThanTwoDecimals;
	}

	public static boolean amountValidAndIsGTZero(String amount) {
		return amountIsValid(amount) && !(Double.parseDouble(amount) < 1.0);
	}

	public static boolean maxAmountExceeded(String amount) {
		return amountIsValid(amount) && Double.parseDouble(amount) > 100000000;
	}
}
