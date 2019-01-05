package com.octopus.service.util;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public final class AppConstants {
	
	private AppConstants() {
		//Hiding this class
	}
	
	public static int INACTIVE_RECORD_STATUS = 0;
	public static int ACTIVE_RECORD_STATUS = 1;
	public static String DATE_TIME_INPUT_FORMAT = "dd-MM-yyyy HH:mm:ss";
	public static DateTimeFormatter DATETIME_FORMATTER = DateTimeFormat.forPattern(DATE_TIME_INPUT_FORMAT);
	
}
