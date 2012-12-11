package desi.learn.test;

import static org.hamcrest.CoreMatchers.is;

import java.io.Reader;
import java.io.StringReader;

import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Assert;
import org.junit.Test;

public class Dom4jTest {

	@Test
	public void simpleDocumentParsing() throws Exception {
		Reader reader = new StringReader("<xml><a>Java</a></xml>");
		Document document = new SAXReader().read(reader);
		
		Node node = (Node) document.selectNodes("xml/a").get(0);
		
		Assert.assertThat(node.getText(), is("Java"));
	}
}
