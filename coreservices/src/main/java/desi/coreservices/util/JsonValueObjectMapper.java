package desi.coreservices.util;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonValueObjectMapper {

	private ObjectMapper objectMapper;

	public JsonValueObjectMapper() {
		objectMapper = initObjectMapper();
	}

	private ObjectMapper initObjectMapper() {
		ObjectMapper  mapper = new ObjectMapper();
		mapper.addHandler(new ValueObjectRawDataPopulater());
		return mapper;
	}

	public void writeObject(Writer writer, Object vo) throws IOException {
		objectMapper.writeValue(writer, vo);
	}

	public JsonParser createJsonParserFor(String content) throws IOException {
		return objectMapper.getFactory().createParser(content);
	}

	public <T> T readObject(Reader reader, Class<T> valueType) throws IOException {
		return objectMapper.readValue(reader, valueType);
	}

}
