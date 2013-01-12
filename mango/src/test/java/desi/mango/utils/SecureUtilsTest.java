package desi.mango.utils;

import static desi.mango.utils.SecureUtils.checkSum;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SecureUtilsTest {

	@Test
	public void calculateCheckSumOfAGivenReader() throws Exception {
		byte[] data = "JavaProgrammingLanguage".getBytes();
		String expectedChecksum = "D574F8D5CA9C5549F168D867950A3CA003997C07";

		assertThat(checkSum(data), is(expectedChecksum));
	}

	@Test
	public void encryptTheValueUsingPhase() throws Exception {
		String data = "ThisIsAConfidentialData";
		String encrypted = SecureUtils.encrypt(data, "secret");
		String original = SecureUtils.decrypt(encrypted, "secret");

		assertThat(original, is(data));
	}
}
