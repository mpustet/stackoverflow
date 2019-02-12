package de.mle.stackoverflow.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class WorkPackageEstimateTypeDeserializer extends StdDeserializer<WorkPackageEstimateType> {

	public WorkPackageEstimateTypeDeserializer() {
		super(WorkPackageEstimateType.class);
	}

	public WorkPackageEstimateTypeDeserializer(Class<WorkPackageEstimateType> t) {
		super(t);
	}

	@Override
	public WorkPackageEstimateType deserialize(JsonParser parser, DeserializationContext deserializationContext)
			throws IOException, JsonProcessingException {
		WorkPackageEstimateType value = null;
		JsonToken currentToken = null;
		while ((currentToken = parser.nextValue()) != null) {
			if (parser.getCurrentName().equals("name")) {
				value = WorkPackageEstimateType.valueOf(parser.getText());
				break;
			}
		}
		return value;
	}
}