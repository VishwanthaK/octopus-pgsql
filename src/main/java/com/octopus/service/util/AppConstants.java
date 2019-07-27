package com.octopus.service.util;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public interface AppConstants {
	
	public static int INACTIVE_RECORD_STATUS = 0;
	public static int ACTIVE_RECORD_STATUS = 1;
	public static String DATE_TIME_INPUT_FORMAT = "dd-MM-yyyy HH:mm:ss";
	public static DateTimeFormatter DATETIME_FORMATTER = DateTimeFormat.forPattern(DATE_TIME_INPUT_FORMAT);

	interface USER_SETTING_KEYS {
		String ENABLE_CUSTOM_LOCALITY = "enableCustomLocality";
		String PREFERRED_LOCALITY_GROUP = "preferredLocalityGroup";
		String CUSTOM_LOCALITIES = "customLocalities";
	}

	interface APP_SETTING_KEYS {
		String LOCALITY_GROUP = "localityGroup";
	}
	
}
