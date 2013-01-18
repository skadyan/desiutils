package desi.mango.utils;

import static desi.mango.utils.LangUtils.notEmpty;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class LangUtilsTest {
	@Test
	public void testArrayIsEmpty() throws Exception {
		assertThat(notEmpty(new Object[1]), is(true));
		assertThat(notEmpty(new Object[0]), is(false));
		assertThat(notEmpty((Object[]) null), is(false));
	}
}
