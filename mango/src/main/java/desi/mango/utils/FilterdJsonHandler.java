package desi.mango.utils;

import java.io.IOException;
import java.io.Writer;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

public class FilterdJsonHandler extends DefaultHandler implements AutoCloseable {
	private JsonGenerator generator;

	public FilterdJsonHandler(Writer writer) throws IOException {
		generator = new JsonFactory().createGenerator(writer);
	}

	@Override
	public void close() throws Exception {
		generator.close();
	}

	@Override
	public void startDocument() throws SAXException {
	}

}
