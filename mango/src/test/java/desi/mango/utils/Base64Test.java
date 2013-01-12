package desi.mango.utils;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class Base64Test {

	private byte[] input;

	public Base64Test(byte[] input) {
		this.input = input;
	}

	@Test
	public void verify() {
		String s = Base64.encode(input);
		byte[] b = Base64.decode(s);

		assertThat(b, is(input));
	}

	@Parameters
	public static Iterable<Object[]> inputs() {
		List<Object[]> list = new ArrayList<>();
		int numRuns = 5;
		int numBytes = 10;
		java.util.Random rnd = new java.util.Random();
		for (int i = 0; i < numRuns; i++) {

			for (int j = 0; j < numBytes; j++) {
				byte[] arr = new byte[j];
				for (int k = 0; k < j; k++) {
					arr[k] = (byte) rnd.nextInt();
				}
				list.add(new Object[] { arr });
			}
		}
		return list;
	}

}
