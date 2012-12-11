package desi.mango.utils;

import static desi.mango.utils.StringUtils.isNullOrBlank;
import static desi.mango.utils.StringUtils.parseCsv;
import static desi.mango.utils.StringUtils.unquote;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Collection;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StringUtilsTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	public StringUtilsTest() {
	}

	@Test
	public void checkStringIsNullOrBlank() throws Exception {
		assertThat(isNullOrBlank(null), is(true));
		assertThat(isNullOrBlank(""), is(true));
		assertThat(isNullOrBlank("\t\n\r"), is(true));
		assertThat(isNullOrBlank("Java"), is(false));
	}

	@Test
	public void splitNullStringOnSeparatorChars() throws Exception {
		String firstToken = null;
		Collection<String> tokens = parseCsv(firstToken);

		assertThat(tokens.isEmpty(), is(true));
	}

	@Test
	public void splitUnclosedQuotedStringThrowsAnException() throws Exception {
		expectedException.expect(IllegalArgumentException.class);

		String value = "1,\"Java,";
		parseCsv(value);
	}

	@Test
	public void unquoteAStringLiteral() throws Exception {
		assertThat(unquote(""), is(""));
		assertThat(unquote("\"Java\""), is("Java"));
		assertThat(unquote("'Java'"), is("Java"));
		assertThat(unquote("\"Java"), is("\"Java"));
		assertThat(unquote("Java\""), is("Java\""));
		assertThat(unquote("\"\""), is(""));
	}
}
