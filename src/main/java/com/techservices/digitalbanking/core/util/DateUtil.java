/* (C)2024 */
package com.techservices.digitalbanking.core.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public final class DateUtil {

  public static final String DEFAULT_DATE_FORMAT = "dd MMMM yyyy";
  public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
  public static final String DEFAULT_LOCALE = "en";
  public static final String DEFAULT_DATETIME_FORMAT =
      DEFAULT_DATE_FORMAT + " " + DEFAULT_TIME_FORMAT;
  public static final DateTimeFormatter DEFAULT_DATETIME_FORMATER =
      new DateTimeFormatterBuilder().appendPattern(DEFAULT_DATETIME_FORMAT).toFormatter();
  public static final DateTimeFormatter DEFAULT_DATE_FORMATER =
      new DateTimeFormatterBuilder().appendPattern(DEFAULT_DATE_FORMAT).toFormatter();

  private DateUtil() {}

  public static ZoneId getSystemZoneId() {

    return ZoneId.systemDefault();
  }

  public static String getCurrentDate() {

    return LocalDate.now().format(DEFAULT_DATE_FORMATER);
  }

  public static String getCurrentTime() {

    return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT));
  }

  public static String getDefaultDateFormat() {

    return DEFAULT_DATE_FORMAT;
  }

  public static String getOrdinal(int day) {

    if (day >= 11 && day <= 13) {
      return "th";
    }
    return switch (day % 10) {
      case 1 -> "st";
      case 2 -> "nd";
      case 3 -> "rd";
      default -> "th";
    };
  }

  public static String getFormattedCurrentDateTime() {

    LocalDateTime now = LocalDateTime.now();
    int day = now.getDayOfMonth();
    String suffix = getOrdinal(day);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM, yyyy 'at' h:mm a");
    return String.format("%d%s of %s", day, suffix, now.format(formatter));
  }
}
