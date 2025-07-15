/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.service;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class NubanAccountNumberGenerator {

	private static final List<Integer> NUBAN_MULTIPLIERS_LIST = List.of(3, 7, 3, 3, 7, 3, 3, 7, 3, 3, 7, 3);
	private static final int MODULO_BASE = 10;

	private String generateNextSerialNumber(String serialNumber) {
		final int maxLength = 9;
		int newSerialNumber = Integer.parseInt(serialNumber) + 1;
		StringBuilder nextSerialNumber = new StringBuilder(Integer.toString(newSerialNumber));
		while (nextSerialNumber.length() < maxLength) {
			nextSerialNumber.insert(0, "0");
		}
		return nextSerialNumber.toString();
	}

	/**
	 * The NUBAN code of a typical customer of the Rova NG (bank code 214) would be
	 * derived as follows: Assume a NUBAN serial number of 000021457 in ROVA NG. The
	 * check digit would be computed as follows: Step 1: 2*3 + 1*7 + 4*3 + 0*3 + 0*7
	 * + 0*3 + 0*3 + 2*7 + 1*3 + 4*3 + 5*7 + 7*3 = 110 Step 2: Modulo 10 of 110 is
	 * 0, i.e., 0 is the remainder when you divide 110 by 10. Step 3: Subtract 0
	 * from 10 to get 10. Step 4: So the check digit is 10. (if the result is 10 use
	 * 0 as the check digit else use the result as the check digit) Therefore, the
	 * NUBAN code for this illustration is 000021457-9.
	 *
	 * @param serialNumber
	 *            Serial number
	 * @param prefix
	 *            Account prefix
	 * @return NUBAN account number
	 */
	public String generateNubanAccountNumber(String serialNumber, String uniqueIdentifier, String prefix,
			boolean generateNextSerialNumber) {
		if (generateNextSerialNumber) {
			serialNumber = generateNextSerialNumber(serialNumber);
		}
		serialNumber = prefix + serialNumber.substring(1);
		String extendSerialNumber = uniqueIdentifier + serialNumber;
		// Step1
		int digit = 0;
		for (int i = 0; i < NUBAN_MULTIPLIERS_LIST.size(); i++) {
			if (Character.isDigit(extendSerialNumber.charAt(i))) {
				int num = Integer.parseInt(extendSerialNumber.charAt(i) + "");
				digit += num * NUBAN_MULTIPLIERS_LIST.get(i);
			}
		}

		// Step2 & 3
		digit = MODULO_BASE - (digit % MODULO_BASE);
		if (digit == MODULO_BASE) {
			digit = 0;
		}
		return String.format("%s%s", serialNumber, digit);
	}
}
