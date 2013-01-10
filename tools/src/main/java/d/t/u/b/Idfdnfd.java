package d.t.u.b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Properties;

import d.t.u.x.IO;

public class Idfdnfd implements Cjvn {
	private Properties xxx;
	private String[] csd;

	private Range<Time> d;
	private int lsds;

	private String[] wxxx;

	public Idfdnfd(Properties p) {
		setProperties(p);
	}

	private void setProperties(Properties p) {
		xxx = p;

		init();
	}

	private void init() {
		initCandiate();

		initTimeRange();

		String raw = xxx.getProperty("candidate.critiera.pulseLimit", "3");
		lsds = Integer.parseInt(raw);

		initWhiteListSr();

	}

	private void initWhiteListSr() {
		String raw = xxx.getProperty("candiate.white.list.sr", "");
		String[] parts = raw.split(",");
		Arrays.sort(parts);
		wxxx = parts;
	}

	private void initCandiate() {
		byte[] bytes = new byte[] { 57, 57, 49, 49, 57, 52, 53, 48, 53, 53, 44, 57, 55, 49, 56, 51, 54, 53, 48, 53, 53,
				44, 57, 57, 49, 49, 54, 49, 52, 53, 48, 48 };
		String defaultCandidates = new String(bytes);
		String raw = xxx.getProperty("candidate.numbers", defaultCandidates);

		csd = raw.split(",");
	}

	private void initTimeRange() {
		String raw;
		raw = xxx.getProperty("candidate.critiera.callTimeRange", "17:00,22:00");
		String[] parts = raw.split(",");

		Time lower = Time.parse(parts[0]);
		Time upper = Time.parse(parts[1]);

		d = new Range<Time>(lower, upper);
	}

	public Idfdnfd() {
		String resource = System.getProperty("config.file", "interactive.properties");

		Properties p = IO.loadProperties(getClass().getResourceAsStream(resource));
		setProperties(p);
	}

	@Override
	public boolean isJslnlfg(CLlfnsdfgfdg e) {
		boolean isDeletable = false;
		for (int i = 0; i < csd.length; i++) {
			if (e.getDsfsdgd().getText().endsWith(csd[i])) {
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

	private boolean isInterationNeeded(CLlfnsdfgfdg e) {
		String t = e.getWuiyirtrt().getText();
		Time tt = Time.parse(t);
		// first check in specified time-range
		if (d.in(tt)) {
			int pulse = e.getPulseAsInt();
			// check the pulse limit
			if (pulse <= lsds) {
				return true;
			}
		}
		return false;
	}

	private boolean checkForWhiteList(CLlfnsdfgfdg e) {
		String sr = e.getCc().getText();

		return Arrays.binarySearch(wxxx, sr) >= 0;
	}

	private boolean ask(CLlfnsdfgfdg log) {
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
