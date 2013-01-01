package desi.tools.utility.bill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Properties;

import desi.tools.utility.pdf.IO;

public class InteractiveCallFilter implements CallLogFilter {
	private Properties prop;
	private String[] candidates;

	private Range<Time> range;
	private int pulseLimit;

	private String[] whiteListSr;

	public InteractiveCallFilter(Properties p) {
		setProperties(p);
	}

	private void setProperties(Properties p) {
		prop = p;

		init();
	}

	private void init() {
		initCandiate();

		initTimeRange();

		String raw = prop.getProperty("candidate.critiera.pulseLimit", "3");
		pulseLimit = Integer.parseInt(raw);

		initWhiteListSr();

	}

	private void initWhiteListSr() {
		String raw = prop.getProperty("candiate.white.list.sr", "");
		String[] parts = raw.split(",");
		Arrays.sort(parts);
		whiteListSr = parts;
	}

	private void initCandiate() {
		byte[] bytes = new byte[] { 57, 57, 49, 49, 57, 52, 53, 48, 53, 53, 44, 57, 55, 49, 56, 51, 54, 53, 48, 53, 53,
				44, 57, 57, 49, 49, 54, 49, 52, 53, 48, 48 };
		String defaultCandidates = new String(bytes);
		String raw = prop.getProperty("candidate.numbers", defaultCandidates);

		candidates = raw.split(",");
	}

	private void initTimeRange() {
		String raw;
		raw = prop.getProperty("candidate.critiera.callTimeRange", "17:00,22:00");
		String[] parts = raw.split(",");

		Time lower = Time.parse(parts[0]);
		Time upper = Time.parse(parts[1]);

		range = new Range<Time>(lower, upper);
	}

	public InteractiveCallFilter() {
		String resource = System.getProperty("config.file", "interactive.properties");

		Properties p = IO.loadProperties(getClass().getResourceAsStream(resource));
		setProperties(p);
	}

	@Override
	public boolean isDeletable(CallLog e) {
		boolean isDeletable = false;
		for (int i = 0; i < candidates.length; i++) {
			if (e.getNumber().getText().endsWith(candidates[i])) {
				isDeletable = true;
				break;
			}
		}
		if (isDeletable)
			isDeletable = !checkForWhiteList(e);

		if (isDeletable) {
			boolean needInteration = isInterationNeeded(e);
			if (needInteration) {
				isDeletable = ask(e);
			}

		}
		return isDeletable;
	}

	private boolean isInterationNeeded(CallLog e) {
		String t = e.getTime().getText();
		Time tt = Time.parse(t);
		// first check in specified time-range
		if (range.in(tt)) {
			int pulse = e.getPulseAsInt();
			// check the pulse limit
			if (pulse <= pulseLimit) {
				return true;
			}
		}
		return false;
	}

	private boolean checkForWhiteList(CallLog e) {
		String sr = e.getSr().getText();

		return Arrays.binarySearch(whiteListSr, sr) >= 0;
	}

	private boolean ask(CallLog log) {
		try {
			System.out.println("Delete " + log + " (y|[n]):");
			boolean answer = !reader.readLine().trim().equalsIgnoreCase("n");

			return answer;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	static {
		reader = new BufferedReader(new InputStreamReader(System.in));
	}
	static BufferedReader reader;
}
