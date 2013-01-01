package desi.learn.test;

import java.io.IOException;
import java.net.URL;

import org.junit.Test;


public class ParameterizedTestExample {
	@Test
	public void testVerifyCsv() throws IOException{
		URL url = new URL("ftp://x.com");
		
		url.openConnection();
	}
	
}
