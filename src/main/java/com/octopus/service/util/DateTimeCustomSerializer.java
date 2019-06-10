package com.octopus.service.util;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.octopus.service.security.RequestHeaderData;

public class DateTimeCustomSerializer extends JsonSerializer<DateTime> {

    @Autowired
    RequestHeaderData requestHeaderData;

    @Override
    public void serialize(DateTime value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException, JsonProcessingException {

        DateTimeFormatter formatter = ISODateTimeFormat.dateTime();

        if (requestHeaderData == null || StringUtils.isBlank(requestHeaderData.getDateTimeFormat())) {
            gen.writeString(formatter.print(value));
        } else if (requestHeaderData.getDateTimeFormat().trim().contentEquals("epoch_millis")) {
            gen.writeString(String.valueOf(value.getMillis()));
        } else {
            formatter = DateTimeFormat.forPattern(requestHeaderData.getDateTimeFormat());
            gen.writeString(formatter.print(value));
        }
    }

}
