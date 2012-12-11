package desi.mango.utils;

import static desi.mango.utils.IOUtils.closeQuietly;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.Closeable;

import org.junit.Test;

public class IOUtilsTest {

	@Test
	public void testCloseQuietly() throws Exception {
		Closeable closeable = mock(Closeable.class);

		closeQuietly(closeable);
		
		verify(closeable).close();
	}
	
}
