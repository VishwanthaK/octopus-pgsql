package com.octopus.service.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.web.multipart.MultipartFile;

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

	public static ApiResponse frameSuccessResponse(Integer code, String message, Object data) {
		return new ApiResponse(code, message, data);
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

	public static void uploadFile(
			final String uploadFolder,
			final String fileName,
			final MultipartFile file) {
		try {
			final byte[] bytes = file.getBytes();
			final Path path = Paths.get(uploadFolder + "/" + fileName);
			Files.write(path, bytes);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
