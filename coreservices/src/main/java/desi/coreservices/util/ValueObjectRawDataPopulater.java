package desi.coreservices.util;

import static java.beans.Beans.isInstanceOf;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.sapient.ipv.data.dictionary.RawField;
import com.sapient.ipv.data.dictionary.StringField;

public class ValueObjectRawDataPopulater extends DeserializationProblemHandler {
	@Override
	@SuppressWarnings("unchecked")
	public boolean handleUnknownProperty(DeserializationContext ctxt, JsonParser jp, JsonDeserializer<?> deserializer,
			Object beanOrClass, String propertyName) throws IOException, JsonProcessingException {
		if (isInstanceOf(beanOrClass, SupportsRawData.class)) {
			RawField<?> rawField = discoverField(beanOrClass, propertyName);
			if (rawField != null) {
				Object value = jp.readValueAs(rawField.type());
				SupportsRawData.class.cast(beanOrClass).putRawField((RawField<Object>) rawField, value);
			}
			return true;
		} else {
			return super.handleUnknownProperty(ctxt, jp, deserializer, beanOrClass, propertyName);
		}
	}

	private RawField<?> discoverField(Object bean, String propertyName) {
		return StringField.description;
	}
}
