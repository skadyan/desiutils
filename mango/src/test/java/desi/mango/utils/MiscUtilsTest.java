package desi.mango.utils;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;

import java.net.URL;

import org.junit.Test;

public class MiscUtilsTest {

	@Test
	public void testCodeSourceLocation() throws Exception {
		String location = MiscUtils.getCodeSourceLocation(MiscUtils.class);
		assertThat(new URL(location).toExternalForm(), is(location));
	}

	@Test
	public void generatUUID() throws Exception {
		String uuid = MiscUtils.generateUUID("X");
		assertThat(uuid, startsWith("X"));
	}

}
