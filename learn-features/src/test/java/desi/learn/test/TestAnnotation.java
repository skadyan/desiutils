package desi.learn.test;


import org.junit.Assert;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestAnnotation {
	@BeforeClass
	public static void assumeName() throws Exception {
		Assume.assumeTrue("XXXXX",false);
	}

	@Test
	public void testName() throws Exception {
		Assert.assertTrue(true);
	}
}
