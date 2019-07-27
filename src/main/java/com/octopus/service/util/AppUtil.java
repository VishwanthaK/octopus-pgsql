package com.octopus.service.util;

import java.io.IOException;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.octopus.service.domain.ApiResponse;

public class AppUtil {

	private static ObjectMapper MAPPER = new ObjectMapper();

	public static DateTime convertDateTimeLocalToUTC(String dateTimeInput, String timeZoneID) {
    	DateTimeZone dateTimeZone = DateTimeZone.forID(timeZoneID);
    	DateTime localDateTime =  AppConstants.DATETIME_FORMATTER
    			.withZone(dateTimeZone)
    			.parseDateTime(dateTimeInput);
    	DateTime UTCDateTime = localDateTime.toDateTime(DateTimeZone.UTC);
    	return UTCDateTime;
    }
	
	public static ApiResponse frameSuccessResponse(Integer code, String message) {
    	return new ApiResponse(code, message);
    }

	public static JsonNode jsonStringToJsonNode(final String jsonString) {
		JsonNode jsonNode = null;

		try {
			jsonNode = MAPPER.readTree(jsonString);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return jsonNode;
	}

	public static String getJsonValueByKey(final JsonNode json, final String key) {
		return json.findValue(key).asText();
	}
}
