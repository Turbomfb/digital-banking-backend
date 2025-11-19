/* (C)2024 */
package com.techservices.digitalbanking.core.util;

import java.util.UUID;

public class IdGenerator {

	public static String generateTransactionId(String prefix) {

		return String.format("%s-%s", prefix, UUID.randomUUID());
	}
}
