package desi.tools.utility.bill;

import java.util.List;
import java.util.regex.Pattern;

import desi.tools.utility.pdf.TextBlock;
import desi.tools.utility.pdf.TextBlockProvider;

public class RomaingCallLog extends CallLog {

	private TextBlock network;
	private TextBlock type;

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

	protected RomaingCallLog(List<TextBlock> blocks) {
		super(blocks);
	}

	@Override
	protected void setFields(List<TextBlock> blocks) {
		int index = 0;
		sr = blocks.get(index++);
		date = blocks.get(index++);
		network = blocks.get(index++);
		time = blocks.get(index++);
		number = blocks.get(index++);
		type = blocks.get(index++);
		duration = blocks.get(index++);
		charges = blocks.get(index++);
		if (index != blocks.size())
			throw new IllegalArgumentException("Extra element found in list");

		state = " ";
	}

	public static RomaingCallLog createIf(TextBlockProvider provider) {
		List<TextBlock> blocks = createIf(provider, patterns);
		return blocks == null ? null : new RomaingCallLog(blocks);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append(getState()).append(" ");
		sb.append(sr).append(" ");
		sb.append(date).append(" ");
		sb.append(network).append(" ");
		sb.append(time).append(" ");
		sb.append(number).append(" ");
		sb.append(type).append(" ");
		sb.append(duration).append(" ");
		sb.append(charges).append(" ");

		return sb.toString();

	}
}
