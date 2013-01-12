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
public class HexTest {

	private byte[] input;

	public HexTest(byte[] input) {
		this.input = input;
	}

	@Test
	public void verify() {
		String s = Hex.encode(input);
		byte[] b = Hex.decode(s);

		assertThat(b, is(input));
	}

	@Parameters
	public static Iterable<Object[]> inputs() {
		List<Object[]> list = new ArrayList<>();
		int numRuns = 1;
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
