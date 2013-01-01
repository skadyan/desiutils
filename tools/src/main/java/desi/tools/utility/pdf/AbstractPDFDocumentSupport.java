package desi.tools.utility.pdf;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.intarsys.pdf.parser.COSLoadException;
import de.intarsys.pdf.pd.PDDocument;
import de.intarsys.pdf.pd.PDPage;
import de.intarsys.pdf.pd.PDPageTree;
import de.intarsys.tools.locator.FileLocator;
import de.intarsys.tools.locator.ILocator;

public class AbstractPDFDocumentSupport {

	protected PDDocument document;

	protected PDPageTree pageTree;

	protected Map<String, Object> attrs = new HashMap<String, Object>();

	protected final Logger log = LoggerFactory.getLogger(getClass());

	protected AbstractPDFDocumentSupport() {
	}

	public void open(String filepath, String password) throws IOException, COSLoadException {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("password", password);

		document = PDDocument.createFromLocator(new FileLocator(filepath), options);
		pageTree = document.getPageTree();
	}

	public PageWrapper getPage(int pageIndex) {
		String key = "__wra_page_" + pageIndex;
		PageWrapper wrapper = (PageWrapper) attrs.get(key);
		if (wrapper == null) {
			PDPage page = pageTree.getPageAt(pageIndex);
			if (page != null) {
				wrapper = PageWrapper.wrap(page);
				attrs.put(key, wrapper);
			}
		}

		return wrapper;
	}

	public void flush() throws IOException {
		for (String key : attrs.keySet()) {
			if (key.startsWith("__wra_page_")) {
				PageWrapper wrapper = (PageWrapper) attrs.get(key);

				wrapper.flushChanges();
			}
		}
	}

	public void saveTo(ILocator locator, Map<?, ?> options) throws IOException {
		flush();
		document.setAttribute("password", null);
		log.info("Document '{}' going to save --> '{}'", document.getName(), locator);
		document.save(locator, options);
	}

	public void close() throws IOException {
		document.close();
		pageTree = null;
		attrs.clear();
		log.info("Document '{}'closed successfully.", document.getName());
	}

	public int getNumberOfPages() {
		return pageTree.getCount();
	}
}
