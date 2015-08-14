package util;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Custom serializer for datetime
 */
public class CustomDateTimeSerializer extends JsonSerializer<LocalDateTime> {
	protected static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	@Override
	public void serialize(LocalDateTime dateTime, JsonGenerator generator, SerializerProvider serializerProvider)
			throws IOException {

		generator.writeString(dateTime.format(formatter));
	}
}
