package com.smartretailpriceengine.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class Utility {

	private Utility() {

	}

	public static String convertMillisToTimeStamp(long timeInMillis) {
		Instant instant = Instant.ofEpochMilli(timeInMillis);
		ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z", Locale.ENGLISH);
		return zonedDateTime.format(formatter);
	}

	public static void main(String[] args) {
		// Utility test
		System.out.println(convertMillisToTimeStamp(System.currentTimeMillis()));
	}
}
