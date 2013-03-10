package desi.mango.xml2json.model;

import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.EventFilter;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.XMLEvent;


public class Main {
	public static void main(String[] args) throws Exception {
		InputStream in = Main.class.getResourceAsStream("aFile.xml");
		StringWriter writer = new StringWriter();

		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLEventReader r = factory.createXMLEventReader(in);
		XMLEventReader fr = factory.createFilteredReader(r, new EventFilter() {
			public boolean accept(XMLEvent e) {
				return e.isStartElement();
			}
		});

		JAXBContext ctx = JAXBContext.newInstance(Flight.class);
		Unmarshaller um = ctx.createUnmarshaller();
		fr.nextEvent();
		while (fr.peek() != null) {
			XMLEvent event = fr.nextEvent();
			System.out.println("Event " + event);
			 um.unmarshal(fr);
		}

	}
}