package de.mle.stackoverflow.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

class CustomDeserializer extends StdDeserializer<Entity> {
    public CustomDeserializer(Class<Entity> t) {
        super(t);
    }

    @Override
    public Entity deserialize(JsonParser jp, DeserializationContext dc) throws IOException {
        String name = null;
        float price = 0;

        JsonToken currentToken = null;
        while ((currentToken = jp.nextValue()) != null) {
            switch (currentToken) {
                case VALUE_STRING:
                    switch (jp.getCurrentName()) {
                        case "name":
                            name = jp.getText() + "-3"; // change this text to whatever you want;
                            break;
                        case "price":
                            price = Float.parseFloat(jp.getText()) + .9f; // parse
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
        return new Entity(name, price);
    }
}