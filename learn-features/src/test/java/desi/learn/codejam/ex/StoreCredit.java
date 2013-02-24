package desi.learn.codejam.ex;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;

public class StoreCredit {
	public static class GivenProblem {

		private int creditAmount;
		private int[] values;

		public GivenProblem(int creditAmount, int[] values) {
			this.setCreditAmount(creditAmount);
			this.setValues(values);
		}

		public String solve(int seq) {
			StringBuilder sb = new StringBuilder("Case #").append(seq).append(":");

			return sb.toString();
		}

		public int[] getValues() {
			return values;
		}

		public void setValues(int[] values) {
			this.values = values;
		}

		public int getCreditAmount() {
			return creditAmount;
		}

		public void setCreditAmount(int creditAmount) {
			this.creditAmount = creditAmount;
		}

	}

	private Writer writer;
	private BufferedReader reader;

	public static void main(String[] args) throws IOException {
		StoreCredit credit = new StoreCredit();

		credit.setInput(new InputStreamReader(StoreCredit.class.getResourceAsStream("A-small-practice.in")));
		credit.setOutput(new FileWriter("D:/temp/A-Small-practice.out"));

		credit.solve();

	}

	public void solve() throws IOException {
		int numberOfTests = Integer.parseInt(reader.readLine());

		for (int i = 1; i < numberOfTests; i++) {
			GivenProblem problem = new GivenProblem(Integer.parseInt(reader.readLine()), toInts(reader.readLine()
					.split(" ")));
			String result = problem.solve(i);
			writer.write(result);
			writer.write(System.getProperty("line.separator"));
		}
	}

	private int[] toInts(String[] text) {
		int[] values = new int[text.length];
		for (int i = 0; i < values.length; i++) {
			values[i] = Integer.parseInt(text[i]);
		}
		return values;
	}

	public void setInput(Reader reader) {
		this.reader = new BufferedReader(reader);
	}

	public void setOutput(Writer writer) {
		this.writer = writer;
	}
}
