package desi.tools.utility.bill;

import org.junit.Assert;
import org.junit.Test;

public class TestSplit {

	@Test
	public void testSimpleSplit() throws Exception {
		String[] split = "20|454.454".split("\\|");

		Assert.assertEquals(2, split.length);
	}

}