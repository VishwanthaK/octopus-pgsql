package com.octopus.service.util;

import java.io.IOException;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Custom serializer for {@link LocalDateTime}. Extracts date and convert it to string.
 */
public class LocalDateTimeToDateSerializer extends JsonSerializer<LocalDateTime> {

    private static final DateTimeFormatter formatter = ISODateTimeFormat.date();

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException, JsonProcessingException {
        gen.writeString(formatter.print(value));
    }

}
