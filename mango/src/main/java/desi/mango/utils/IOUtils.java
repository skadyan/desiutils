package desi.mango.utils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.net.URLConnection;

public class IOUtils {

	public static void closeQuietly(Closeable closeable) {
		try {
			if (closeable != null) {
				closeable.close();
			}
		} catch (IOException e) {
			// ssh
		}
	}

	public static String toString(URL resource) throws IOException {
		URLConnection connection = resource.openConnection();
		String encoding = connection.getContentEncoding();
		if (LangUtils.isNullOrBlank(encoding)) {
			encoding = "utf-8";
		}
		InputStream in = connection.getInputStream();

		return toString(in, encoding);
	}

	public static String toString(InputStream in, String encoding) throws IOException {
		StringWriter sw = new StringWriter(512);
		Reader reader = new InputStreamReader(in, encoding);

		copyAndClose(reader, sw);
		return sw.toString();
	}

	public static void copyAndClose(Reader reader, Writer writer) throws IOException {
		char[] cbuf = new char[1024];

		int read = -1;
		while ((read = reader.read(cbuf)) >= 0) {
			writer.write(cbuf, 0, read);
		}

		closeQuietly(reader);
		closeQuietly(writer);
	}
}
