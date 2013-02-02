package desi.coreservices.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.sapient.ipv.data.dictionary.RawField;
import com.sapient.ipv.data.dictionary.StringField;

import desi.mango.utils.IOUtils;

public class JsonValueObjectMapperTest {
	private JsonValueObjectMapper objectMapper;
	@Rule
	public TestName testName = new TestName();

	public static class TestValueObjectSample implements SupportsRawData {
		private String name;
		private long modifiedAt;

		private Map<RawField<?>, Object> rawData;

		public TestValueObjectSample() {
			rawData = new HashMap<RawField<?>, Object>();
		}

		public long getModifiedAt() {
			return modifiedAt;
		}

		public void setModifiedAt(long modifiedAt) {
			this.modifiedAt = modifiedAt;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@JsonAnyGetter
		public Map<RawField<?>, Object> getRawData() {
			return rawData;
		}

		@Override
		public String toString() {
			return "TestValueObjectSample [name=" + name + ", modifiedAt=" + modifiedAt + ", rawData=" + rawData + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (modifiedAt ^ (modifiedAt >>> 32));
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + ((rawData == null) ? 0 : rawData.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			TestValueObjectSample other = (TestValueObjectSample) obj;
			if (modifiedAt != other.modifiedAt)
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (rawData == null) {
				if (other.rawData != null)
					return false;
			} else if (!rawData.equals(other.rawData))
				return false;
			return true;
		}

		@Override
		public <T> void putRawField(RawField<T> field, T value) {
			this.rawData.put(field, value);
		}

		public <T> T getRawField(RawField<T> field) {
			return field.type().cast(this.rawData.get(field));
		}
	}

	@Before
	public void setUp() throws Exception {
		objectMapper = new JsonValueObjectMapper();
	}

	@Test
	public void testConvertSimpleValueObjectToJsonDocument() throws Exception {
		String expectedJson = loadJsonResourceByMethodName();
		String name = "Java Object Name";
		long modifiedAt = 1234567890L;
		TestValueObjectSample sample = newValueObjectInstance(name, modifiedAt);
		sample.putRawField(StringField.description, "Object description");
		StringWriter jsonDocumentWriter = new StringWriter(100);
		objectMapper.writeObject(jsonDocumentWriter, sample);
		String json = jsonDocumentWriter.toString();

		assertThat(json, isSameJsonDocument(expectedJson));
	}

	@Test
	public void testConvertJsonDocumentToSimpleValueObject() throws Exception {
		StringReader jsonDocumentReader = new StringReader(loadJsonResourceByMethodName());

		TestValueObjectSample actual = objectMapper.readObject(jsonDocumentReader, TestValueObjectSample.class);

		String name = "Java Object Name";
		long modifiedAt = 1234567890L;
		TestValueObjectSample sample = newValueObjectInstance(name, modifiedAt);
		sample.putRawField(StringField.description, "Object description");

		assertThat(actual, is(sample));

	}

	private String loadJsonResourceByMethodName() throws IOException {
		String resourceName = testName.getMethodName() + ".json";
		return IOUtils.toString(getClass().getResource(resourceName));
	}

	private Matcher<String> isSameJsonDocument(final String expectedJson) {
		return new BaseMatcher<String>() {
			private String message;

			@Override
			public boolean matches(Object item) {
				String actual = (String) item;
				try {
					JsonNode actualJsonMap = jsonAsMap(actual);
					JsonNode expectedJsonMap = jsonAsMap(expectedJson);

					return actualJsonMap.equals(expectedJsonMap);
				} catch (IOException e) {
					message = "Not able to compare Json due to exception :" + e;
					return false;
				}
			}

			private JsonNode jsonAsMap(String actual) throws IOException, JsonProcessingException {
				JsonParser parser = objectMapper.createJsonParserFor(actual);
				return parser.<JsonNode> readValueAsTree();
			}

			@Override
			public void describeTo(Description description) {
				if (message != null) {
					description.appendText(message);
				} else {
					description.appendText(expectedJson);
				}
			}
		};
	}

	private TestValueObjectSample newValueObjectInstance(String name, long modifiedAt) {
		TestValueObjectSample sample = new TestValueObjectSample();
		sample.setName(name);
		sample.setModifiedAt(modifiedAt);
		return sample;
	}
}
