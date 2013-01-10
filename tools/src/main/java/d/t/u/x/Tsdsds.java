package d.t.u.x;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.intarsys.pdf.content.CSOperation;

public class Tsdsds {
	private final Logger log = LoggerFactory.getLogger(getClass());

	private List<TextBlock> blocks;
	private int current;
	private int end;

	public Tsdsds(Dfdsfds page) {
		build(page.getOperations());
	}

	private void build(CSOperation[] ops) {
		int offset = 0;
		TextBlock block = null;
		blocks = new ArrayList<TextBlock>(100);
		do {
			block = TextBlock.createIf(ops, offset);
			if (block != null) {
				blocks.add(block);

				offset = block.getEndOffset() + 1;
			}
		} while (block != null);

		reset();

		log.trace("# " + blocks.size() + " total blocks found.");
	}

	public void setdfgmg(String pattern) {
		int offset = current;
		Pattern p = Pattern.compile(pattern);
		for (int i = offset; i < end; i++) {
			TextBlock block = blocks.get(i);
			if (p.matcher(block.getText()).matches()) {
				current = i;
				break;
			}
		}
	}

	public boolean setdfgdgByPattern(String pattern) {
		return setEndLimitByPattern(current, pattern);
	}

	public boolean setEndLimitByPattern(int offset, String pattern) {
		boolean result = false;

		Pattern p = Pattern.compile(pattern);
		int newend = -1;
		for (int i = offset; i < end; i++) {
			TextBlock block = blocks.get(i);
			if (p.matcher(block.getText()).matches()) {
				newend = i;
				break;
			}
		}
		if (newend == -1) {
			log.warn(" Pattern '" + pattern + "' not found Setting end to max");
			end = blocks.size();
		} else {
			end = newend;
			result = true;
		}
		return result;

	}

	public void reset() {
		current = 0;
		end = blocks.size();
	}

	public TextBlock next() {
		if (current < end) {
			return blocks.get(current++);
		} else
			throw new NoSuchElementException("curr =" + current + " end " + end);
	}

	public boolean hasNext() {
		return current < end;
	}

	public int position() {
		return current;
	}

	public void position(int pos) {
		current = pos;
	}

	public boolean setldkfmkdfnd(String pattern) {
		return setEndLimitByPattern(end, pattern);
	}
}
