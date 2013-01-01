package desi.tools.utility.bill;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import desi.tools.utility.pdf.PageWrapper;
import desi.tools.utility.pdf.TextBlockProvider;

public class CallProcessor {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	protected PageWrapper page;

	protected TextBlockProvider provider;

	public void setPage(PageWrapper page) {
		this.page = page;
		this.provider = new TextBlockProvider(page);
	}

	public List<CallLog> getCallLogs() {
		List<CallLog> list = new ArrayList<CallLog>(40);

		provider.reset();
		setBoundByPatterns();

		CallLog callLog = null;
		do {
			callLog = createCallIf(provider);
			if (callLog == null)
				break;
			list.add(callLog);
		} while (true);

		log.debug(" # " + list.size() + " Call log found on page {}", (page.getPage().getNodeIndex()+1));

		return list;
	}

	protected CallLog createCallIf(TextBlockProvider provider2) {
		return null;
	}

	public void dumpText() {
		while (provider.hasNext()) {
			int pos = provider.position();
			String text = provider.next().getText();
			log.info(pos + "  " + text);
		}
	}

	public PageWrapper getPage() {
		return page;
	}

	protected void setBoundByPatterns() {
	}

}
