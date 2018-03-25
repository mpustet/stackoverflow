package de.mle.stackoverflow;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ToSerializeSeserializer extends JsonSerializer<ToSerialize> {
	@Override
	public void serialize(ToSerialize toSerialize, JsonGenerator gen, SerializerProvider serializers)
			throws IOException {
		List<NestedObject> objs = toSerialize.getObjs();
		for (int i = 0; i < objs.size(); i++) {
			gen.writeObject("obj" + i);
			gen.writeObject(objs.get(i));
		}
	}
}
