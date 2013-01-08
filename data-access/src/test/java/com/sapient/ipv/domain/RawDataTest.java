package com.sapient.ipv.domain;

import static com.sapient.ipv.domain.StringField.id;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.StringReader;
import java.io.StringWriter;

import org.junit.Test;

public class RawDataTest {

	@Test
	public void simplePutAndRetrive() throws Exception {
		String value = "SimpleRawDataContent";
		RawData rawData = new RawData();
		rawData.put(id, value);
		assertThat(rawData.get(id), is(value));
	}

	@Test
	public void convertToXmlAndBackToRawData() throws Exception {
		String value = "SimpleRawDataContent";
		RawData rawData = new RawData();
		rawData.put(id, value);
		StringWriter sw = new StringWriter();
		rawData.toXml(sw);
		RawData copy = new RawData().fromXml(new StringReader(sw.toString()));
		assertThat(copy, is(rawData));
	}
}
