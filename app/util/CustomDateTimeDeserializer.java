package util;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * Custom deserializer for datetime
 */
public class CustomDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

	@Override
	public LocalDateTime deserialize(JsonParser arg0, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		return LocalDateTime.parse(arg0.getValueAsString(), CustomDateTimeSerializer.formatter);
	}
}
