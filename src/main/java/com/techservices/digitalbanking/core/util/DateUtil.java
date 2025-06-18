/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public final class DateUtil {

	public static final String DEFAULT_DATE_FORMAT = "dd MMMM yyyy";
	public static final String DEFAULT_LOCALE = "en";
	public static final String DEFAULT_DATETIME_FORMAT = DEFAULT_DATE_FORMAT + " HH:mm:ss";
	public static final DateTimeFormatter DEFAULT_DATETIME_FORMATER = new DateTimeFormatterBuilder()
			.appendPattern(DEFAULT_DATETIME_FORMAT).toFormatter();
	public static final DateTimeFormatter DEFAULT_DATE_FORMATER = new DateTimeFormatterBuilder()
			.appendPattern(DEFAULT_DATE_FORMAT).toFormatter();

	private DateUtil() {
	}

	public static ZoneId getSystemZoneId() {
		return ZoneId.systemDefault();
	}

	public static String getCurrentDate() {
		return LocalDate.now().format(DEFAULT_DATE_FORMATER);
	}

	public static String getDefaultDateFormat() {
		return DEFAULT_DATE_FORMAT;
	}
}
