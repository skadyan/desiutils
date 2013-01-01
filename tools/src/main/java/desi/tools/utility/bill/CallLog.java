package desi.tools.utility.bill;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import desi.tools.utility.pdf.TextBlock;
import desi.tools.utility.pdf.TextBlockProvider;

public class CallLog {

	protected TextBlock sr;
	protected TextBlock date;
	protected TextBlock time;
	protected TextBlock number;
	protected TextBlock timeBand;
	protected TextBlock duration;
	protected TextBlock pulse;
	protected TextBlock charges;

	protected String state;

	protected CallLog(List<TextBlock> blocks) {
		setFields(blocks);
	}

	protected void setFields(List<TextBlock> blocks) {
		int index = 0;
		sr = blocks.get(index++);
		date = blocks.get(index++);
		time = blocks.get(index++);
		number = blocks.get(index++);
		timeBand = blocks.get(index++);
		duration = blocks.get(index++);
		pulse = blocks.get(index++);
		charges = blocks.get(index++);
		if (index != blocks.size())
			throw new IllegalArgumentException("Extra element found in list");

		state = " ";
	}

	private static Pattern[] patterns = new Pattern[] { Pattern.compile("\\d{1,3}"),
			Pattern.compile("\\d{2}-[a-zA-z]{3}"), Pattern.compile("\\d{2}:\\d{2}:\\d{2}"),
			Pattern.compile("\\d{10,12}"), Pattern.compile("[POA]"), Pattern.compile("\\d{2,3}:\\d{2}"),
			Pattern.compile("\\d{1,3}"), Pattern.compile("\\d{1,3}\\.\\d{2}"), };

	public static CallLog createIf(TextBlockProvider provider) {
		List<TextBlock> blocks = createIf(provider, patterns);
		return blocks == null ? null : new CallLog(blocks);
	}

	public static List<TextBlock> createIf(TextBlockProvider provider, Pattern[] patterns) {
		outer: while (provider.hasNext()) {
			int pos = provider.position();
			List<TextBlock> blocks = new ArrayList<TextBlock>();
			int i = 0;
			for (; i < patterns.length; i++) {
				Pattern p = patterns[i];
				if (!provider.hasNext())
					break;
				TextBlock block = provider.next();
				String text = block.getText();
				if (p.matcher(text).matches()) {
					blocks.add(block);
				} else {
					provider.position(pos + 1);
					continue outer;
				}
			}
			if (i == 8) {
				return blocks;
			}
		}

		return null;
	}

	public TextBlock getSr() {
		return sr;
	}

	public TextBlock getDate() {
		return date;
	}

	public TextBlock getTime() {
		return time;
	}

	public TextBlock getNumber() {
		return number;
	}

	public TextBlock getTimeBand() {
		return timeBand;
	}

	public TextBlock getDuration() {
		return duration;
	}

	public TextBlock getPulse() {
		return pulse;
	}

	public TextBlock getCharges() {
		return charges;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append(getState()).append(" ");
		sb.append(sr).append(" ");
		sb.append(date).append(" ");
		sb.append(time).append(" ");
		sb.append(number).append(" ");
		sb.append(timeBand).append(" ");
		sb.append(duration).append(" ");
		sb.append(pulse).append(" ");
		sb.append(charges).append(" ");

		return sb.toString();
	}

	public String getState() {
		return state;
	}

	public void copyFrom(CallLog src) {
		if (this == src)
			return;

		// sr.copyFrom(src.sr);
		date.copyFrom(src.date);
		time.copyFrom(src.time);
		number.copyFrom(src.number);
		timeBand.copyFrom(src.timeBand);
		duration.copyFrom(src.duration);

		String oldT = pulse.getText();
		String newT = src.pulse.getText();
		if (oldT.length() > newT.length()) {
			int len = oldT.length() - newT.length() + 1;
			for (int i = 0; i < len; i++) {
				newT = " " + newT;
			}
		}

		// pulse.copyFrom(src.pulse);
		pulse.setText(newT);

		charges.copyFrom(src.charges);

		src.state = "M";
	}

	public void setNewSr(String sr) {
		this.sr.setText(sr);
	}

	public void delete() {
		sr.delete();
		date.delete();
		time.delete();
		number.delete();
		timeBand.delete();
		duration.delete();
		pulse.delete();
		charges.delete();

		state = "D";
	}

	public double getChargesAmt() {
		String txt = charges.getText();

		return Double.parseDouble(txt);
	}

	public int getSrAsInt() {
		String txt = sr.getText();

		return Integer.parseInt(txt);
	}

	public int getPulseAsInt() {
		String txt = pulse.getText();

		return Integer.parseInt(txt);
	}

	public boolean isDeleted() {
		return "D".equals(state);
	}

	public void setCharges(String formattedCharges) {
		charges.setText(formattedCharges);
	}
}
