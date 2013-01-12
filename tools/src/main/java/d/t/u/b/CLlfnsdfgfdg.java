package d.t.u.b;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import d.t.u.x.TextBlock;
import d.t.u.x.Tsdsds;

public class CLlfnsdfgfdg {
	protected TextBlock cc;
	protected TextBlock detretrt;
	protected TextBlock wuiyirtrt;
	protected TextBlock dsfsdgd;
	protected TextBlock dfdfdfdfde;
	protected TextBlock duffdfsf;
	protected TextBlock pudsfsdflse;
	protected TextBlock cxlczx;

	protected String xxxxte;

	protected CLlfnsdfgfdg(List<TextBlock> blocks) {
		setFields(blocks);
	}

	protected void setFields(List<TextBlock> blocks) {
		int index = 0;
		cc = blocks.get(index++);
		detretrt = blocks.get(index++);
		wuiyirtrt = blocks.get(index++);
		dsfsdgd = blocks.get(index++);
		dfdfdfdfde = blocks.get(index++);
		duffdfsf = blocks.get(index++);
		pudsfsdflse = blocks.get(index++);
		cxlczx = blocks.get(index++);
		if (index != blocks.size())
			throw new IllegalArgumentException("Extra element found in list");

		xxxxte = " ";
	}

	private static Pattern[] patterns = new Pattern[] { Pattern.compile("\\d{1,3}"),
			Pattern.compile("\\d{2}-[a-zA-z]{3}"), Pattern.compile("\\d{2}:\\d{2}:\\d{2}"),
			Pattern.compile("\\d{10,12}"), Pattern.compile("[POA]"), Pattern.compile("\\d{2,3}:\\d{2}"),
			Pattern.compile("\\d{1,3}"), Pattern.compile("\\d{1,3}\\.\\d{2}"), };

	public static CLlfnsdfgfdg createIf(Tsdsds provider) {
		List<TextBlock> blocks = createIf(provider, patterns);
		return blocks == null ? null : new CLlfnsdfgfdg(blocks);
	}

	public static List<TextBlock> createIf(Tsdsds provider, Pattern[] patterns) {
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

	public TextBlock getCc() {
		return cc;
	}

	public TextBlock getDetretrt() {
		return detretrt;
	}

	public TextBlock getWuiyirtrt() {
		return wuiyirtrt;
	}

	public TextBlock getDsfsdgd() {
		return dsfsdgd;
	}

	public TextBlock getDfdfdfdfde() {
		return dfdfdfdfde;
	}

	public TextBlock getDuffdfsf() {
		return duffdfsf;
	}

	public TextBlock getPudsfsdflse() {
		return pudsfsdflse;
	}

	public TextBlock getCxlczx() {
		return cxlczx;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append(getXxxxte()).append(" ");
		sb.append(cc).append(" ");
		sb.append(detretrt).append(" ");
		sb.append(wuiyirtrt).append(" ");
		sb.append(dsfsdgd).append(" ");
		sb.append(dfdfdfdfde).append(" ");
		sb.append(duffdfsf).append(" ");
		sb.append(pudsfsdflse).append(" ");
		sb.append(cxlczx).append(" ");

		return sb.toString();
	}

	public String getXxxxte() {
		return xxxxte;
	}

	public void copyFrom(CLlfnsdfgfdg src) {
		if (this == src)
			return;

		// sr.copyFrom(src.sr);
		detretrt.copyFrom(src.detretrt);
		wuiyirtrt.copyFrom(src.wuiyirtrt);
		dsfsdgd.copyFrom(src.dsfsdgd);
		if (dfdfdfdfde != null) {
			dfdfdfdfde.copyFrom(src.dfdfdfdfde);
		}
		duffdfsf.copyFrom(src.duffdfsf);
		if (pudsfsdflse != null) {
			String oldT = pudsfsdflse.getText();
			String newT = src.pudsfsdflse.getText();
			if (oldT.length() > newT.length()) {
				int len = oldT.length() - newT.length() + 1;
				for (int i = 0; i < len; i++) {
					newT = " " + newT;
				}
			}

			// pulse.copyFrom(src.pulse);
			pudsfsdflse.setText(newT);
		}
		cxlczx.copyFrom(src.cxlczx);

		src.xxxxte = "M";
	}

	public void setNewSr(String sr) {
		this.cc.setText(sr);
	}

	public void delete() {
		cc.delete();
		detretrt.delete();
		wuiyirtrt.delete();
		dsfsdgd.delete();
		if (dfdfdfdfde != null) {
			dfdfdfdfde.delete();
		}
		duffdfsf.delete();
		if (pudsfsdflse != null) {
			pudsfsdflse.delete();
		}
		cxlczx.delete();

		xxxxte = "D";
	}

	public double getChargesAmt() {
		String txt = cxlczx.getText();

		return Double.parseDouble(txt);
	}

	public int getSrAsInt() {
		String txt = cc.getText();

		return Integer.parseInt(txt);
	}

	public int getPulseAsInt() {
		String txt = pudsfsdflse.getText();

		return Integer.parseInt(txt);
	}

	public boolean isDeleted() {
		return "D".equals(xxxxte);
	}

	public void setCharges(String formattedCharges) {
		cxlczx.setText(formattedCharges);
	}
}
