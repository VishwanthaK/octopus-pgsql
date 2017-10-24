package com.octopus.service.util;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.octopus.service.domain.SuccessResponse;

public class AppUtil {
	
	public static DateTime convertDateTimeLocalToUTC(String dateTimeInput, String timeZoneID) {
    	DateTimeZone dateTimeZone = DateTimeZone.forID(timeZoneID);
    	DateTime localDateTime =  AppConstants.DATETIME_FORMATTER
    			.withZone(dateTimeZone)
    			.parseDateTime(dateTimeInput);
    	DateTime UTCDateTime = localDateTime.toDateTime(DateTimeZone.UTC);
    	return UTCDateTime;
    }
	
	public static SuccessResponse frameSuccessResponse(Integer code, String message) {
    	return new SuccessResponse(code, message);
    }

}
