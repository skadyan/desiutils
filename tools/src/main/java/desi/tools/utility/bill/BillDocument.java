package desi.tools.utility.bill;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import de.intarsys.pdf.parser.COSLoadException;
import de.intarsys.tools.locator.FileLocator;
import de.intarsys.tools.locator.ILocator;
import desi.tools.utility.pdf.AbstractPDFDocumentSupport;

public class BillDocument extends AbstractPDFDocumentSupport {
	private static final int FREE_CUG_CALLS = 500;
	protected final Logger log = LoggerFactory.getLogger(getClass());
	private ILocator saveLocator;
	private CallLogFilter callLogFilter;

	public BillDocument() {
	}

	public BillDocument(String filepath, String password, String savefilepath) throws IOException, COSLoadException {
		open(filepath, password);

		saveLocator = new FileLocator(savefilepath);
		callLogFilter = new InteractiveCallFilter();
	}

	public static void main(String[] args) {
		try {
			BillDocument doc = new BillDocument(args[0], args[1], args[2]);
			doc.process();
			doc.saveAndClose();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void saveAndClose() throws IOException {
		saveTo(saveLocator, null);

		close();
	}

	public void process() {
		log.info(" Processing Doc :" + document.getLocator() + " Page # " + getNumberOfPages());

		processEnhancedCUG();

		// processRoaming();
	}

	void processEnhancedCUG() {
		int pageCount = getNumberOfPages();
		List<CallLog> allLogs = new ArrayList<CallLog>(100);
		int[] pageLimits = new int[pageCount];
		int sourcePageCount = 0;

		for (int i = 3; i < pageCount; i++) {
			CUGCallProcessor work = new CUGCallProcessor();
			work.setPage(getPage(i));
			work.setBoundByPatterns();

			List<CallLog> l = work.getCallLogs();
			allLogs.addAll(l);
			pageLimits[sourcePageCount++] = l.size();
			if (work.isEndPatternFound())
				break;

		}
		pageLimits = Arrays.copyOf(pageLimits, sourcePageCount);

		List<CallLog> placeHolder = new ArrayList<CallLog>(allLogs);

		CalllogHelper.sortBySerialNo(allLogs);

		String grandTotal = calculateStatistics(allLogs);
		log.trace("Number of CUG calls: " + allLogs.size() + " info = " + grandTotal);

		List<CallLog> toBeDeleted = filterLogs(allLogs);
		String newStatistics = calculateStatistics(allLogs);

		log.debug(toBeDeleted.size() + " entrie(s) removed" + ") Final Number of entries: " + allLogs.size()
				+ " info = " + newStatistics + " pulse/amt");

		int count = allLogs.size();

		placeHolder = sortPlaceHolder(placeHolder, count, pageLimits);
		// D.trace("Sorted Logs" + allLogs);

		moveLogs(allLogs, count, placeHolder);

		for (int i = count; i < placeHolder.size(); i++) {
			placeHolder.get(i).delete();
		}

		// int grandCalltotal = Integer.parseInt(grandTotal.split("\\|")[0]);
		int finalNumberOfCalls = Integer.parseInt(newStatistics.split("\\|")[0]);
		if (finalNumberOfCalls < FREE_CUG_CALLS) {
			setPriceToZero(placeHolder);
		}

		log.info("CUG Processing Complete.");
	}

	private List<CallLog> sortPlaceHolder(List<CallLog> placeHolder, int count, int[] pageLimits) {
		int remaning = count;
		int offset = 0;
		List<CallLog> sorted = new ArrayList<CallLog>();
		for (int pageCapacity : pageLimits) {
			int total = Math.min(remaning, pageCapacity);
			List<CallLog> subList = placeHolder.subList(offset, offset + total);
			sorted.addAll(sortPlaceHolder(subList));
			remaning -= total;
			offset += total;
		}
		// add all remaining log entries
		sorted.addAll(placeHolder.subList(offset, placeHolder.size()));
		return sorted;
	}

	List<CallLog> sortPlaceHolder(List<CallLog> l) {
		List<CallLog> oddList = new ArrayList<CallLog>();
		List<CallLog> evenList = new ArrayList<CallLog>();
		boolean flag = true;

		for (CallLog callLog : l) {
			if (flag) {
				oddList.add(callLog);
			} else
				evenList.add(callLog);
			flag = !flag;
		}
		oddList.addAll(evenList);

		return oddList;
	}

	List<CallLog> sortPlaceHolders(List<CallLog> placeHolders, int total, int limitForPage) {
		List<CallLog> listOfEventEntries = new ArrayList<CallLog>(total / 2);
		List<CallLog> listOfOddEntries = new ArrayList<CallLog>(total / 2);
		List<CallLog> listOfEntries = new ArrayList<CallLog>(total);
		int limit = Math.min(total, limitForPage);
		for (int i = 0; i < limit; i++) {
			if (i % 2 == 0)
				listOfEventEntries.add(placeHolders.get(i));
			else
				listOfOddEntries.add(placeHolders.get(i));
		}

		for (int i = limitForPage + 1; i < total; i++) {
			if (i % 2 == 0)
				listOfEventEntries.add(placeHolders.get(i));
			else
				listOfOddEntries.add(placeHolders.get(i));
		}

		listOfEntries.addAll(listOfEventEntries);
		listOfEntries.addAll(listOfOddEntries);

		// add remaining for removal
		for (int i = total; i < placeHolders.size(); i++) {
			listOfEntries.add(placeHolders.get(i));
		}

		return listOfEntries;
	}

	protected void processRoaming() {
		RomaingCallProcessor processor3 = new RomaingCallProcessor();
		processor3.setPage(getPage(6));

		List<CallLog> callLogs = processor3.getCallLogs();
		for (CallLog log : callLogs) {
			if (callLogFilter.isDeletable(log)) {
				int sr = log.getSrAsInt();
				if (sr != 14) {
					log.getNumber().setText("919802457571");
				}
			}
		}

		log.debug("Romaing Call " + callLogs);
	}

	void processCUG() {
		CUGCallProcessor processor3 = new CUGCallProcessor();
		processor3.setPage(getPage(3));

		List<CallLog> e1 = processor3.getCallLogs();

		// D.trace("Page 3 " + e1);
		CUGCallProcessor processor4 = new CUGCallProcessor();
		processor4.setPage(getPage(4));

		// processor4.dumpText();

		List<CallLog> e2 = processor4.getCallLogs();

		// D.trace("Page 4 " + e2);

		List<CallLog> alllogs = new LinkedList<CallLog>(e1);
		alllogs.addAll(e2);

		String grandTotal = calculateStatistics(alllogs);

		List<CallLog> toBeDeleted = filterLogs(alllogs);

		String reduceTotal = calculateStatistics(toBeDeleted);

		log.info("Total CUG Call after filtering :" + alllogs.size());

		Collections.sort(alllogs, new Comparator<CallLog>() {
			@Override
			public int compare(CallLog o1, CallLog o2) {
				String s1 = o1.getSr().getText();
				String s2 = o2.getSr().getText();
				int i1 = Integer.parseInt(s1);
				int i2 = Integer.parseInt(s2);

				return i1 - i2;
			}
		});

		// D.trace("Sorted FIANL LIST " + alllogs);
		int total = alllogs.size();

		List<CallLog> placeHolders = sortPlaceHolder(e1, e2, total);

		moveLogs(alllogs, total, placeHolders);

		for (int i = total; i < placeHolders.size(); i++) {
			placeHolders.get(i).delete();
		}

		int grandCalltotal = Integer.parseInt(grandTotal.split("\\|")[0]);
		int reducedCalltotal = Integer.parseInt(reduceTotal.split("\\|")[0]);
		log.info("Total Pulse|Amt : " + grandTotal);
		log.info("Reduced Pulse|Amt : " + reducedCalltotal);
		if ((grandCalltotal - reducedCalltotal) < 500) {
			setPriceToZero(placeHolders);
		}
	}

	private void setPriceToZero(List<CallLog> placeHolders) {
		log.trace("setting price to zero");
		for (CallLog callLog : placeHolders) {
			if (!callLog.isDeleted()) {
				callLog.setCharges("0.00");
			}
		}

	}

	private String calculateStatistics(List<CallLog> toBeDeleted) {
		double amt = 0.0d;
		long pulse = 0;
		for (CallLog callLog : toBeDeleted) {
			amt += callLog.getChargesAmt();
			pulse += callLog.getPulseAsInt();
		}

		return pulse + "|" + amt;
	}

	private void moveLogs(List<CallLog> alllogs, int total, List<CallLog> placeHolders) {
		for (int i = 0; i < total; i++) {
			CallLog tgt = placeHolders.get(i);
			CallLog src = alllogs.get(i);

			tgt.copyFrom(src);
			tgt.setNewSr(String.valueOf(i + 1));
		}
	}

	private List<CallLog> sortPlaceHolder(List<CallLog> e1, List<CallLog> e2, int total) {
		List<CallLog> placeHolders = new ArrayList<CallLog>(e1);
		placeHolders.addAll(e2);

		List<CallLog> l1 = new ArrayList<CallLog>(total / 2);
		List<CallLog> l2 = new ArrayList<CallLog>(total / 2);
		for (int i = 0; i < total; i++) {
			if (i % 2 == 0)
				l1.add(placeHolders.get(i));
			else
				l2.add(placeHolders.get(i));
		}

		l1.addAll(l2);

		// add remaining for removal
		for (int i = total; i < placeHolders.size(); i++) {
			l1.add(placeHolders.get(i));
		}

		return l1;
	}

	private List<CallLog> filterLogs(List<CallLog> logs) {
		// logs = new ArrayList<CallLog>(logs);
		CalllogHelper.sortBySerialNo(logs);

		List<CallLog> filterdlist = new ArrayList<CallLog>();
		ListIterator<CallLog> itr = logs.listIterator();
		while (itr.hasNext()) {
			CallLog log = itr.next();
			if (callLogFilter.isDeletable(log)) {
				itr.remove();

				filterdlist.add(log);
			}

		}

		log.trace("# " + (filterdlist.size()) + " enteries fitlered.");

		return filterdlist;
	}

}
