package d.t.u.b;

import java.util.List;
import java.util.regex.Pattern;

import d.t.u.x.TextBlock;
import d.t.u.x.Tsdsds;

public class RRlfdog extends CLlfnsdfgfdg {

	private TextBlock xx;
	private TextBlock txx;

	private static Pattern[] patterns = new Pattern[] {
			// sr
			Pattern.compile("\\d{1,3}"),
			// date
			Pattern.compile("\\d{2}-[a-zA-z]{3}-\\d{2}"),
			// /network
			Pattern.compile("[a-zA-z]+"),
			// time
			Pattern.compile("\\d{2}:\\d{2}:\\d{2}"),
			// number
			Pattern.compile("\\d{10,12}"),
			// type
			Pattern.compile("[a-zA-Z]{1,6}"),
			// duration
			Pattern.compile("\\d{2,3}:\\d{2}"),
			// total amt
			Pattern.compile("\\d{1,3}\\.\\d{2}"), };

	protected RRlfdog(List<TextBlock> blocks) {
		super(blocks);
	}

	@Override
	protected void setFields(List<TextBlock> blocks) {
		int index = 0;
		cc = blocks.get(index++);
		detretrt = blocks.get(index++);
		xx = blocks.get(index++);
		wuiyirtrt = blocks.get(index++);
		dsfsdgd = blocks.get(index++);
		txx = blocks.get(index++);
		duffdfsf = blocks.get(index++);
		cxlczx = blocks.get(index++);
		if (index != blocks.size())
			throw new IllegalArgumentException("Extra element found in list");

		xxxxte = " ";
	}

	public static RRlfdog createIf(Tsdsds provider) {
		List<TextBlock> blocks = createIf(provider, patterns);
		return blocks == null ? null : new RRlfdog(blocks);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append(getXxxxte()).append(" ");
		sb.append(cc).append(" ");
		sb.append(detretrt).append(" ");
		sb.append(xx).append(" ");
		sb.append(wuiyirtrt).append(" ");
		sb.append(dsfsdgd).append(" ");
		sb.append(txx).append(" ");
		sb.append(duffdfsf).append(" ");
		sb.append(cxlczx).append(" ");

		return sb.toString();

	}
}
