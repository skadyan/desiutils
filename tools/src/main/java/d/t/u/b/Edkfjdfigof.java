package d.t.u.b;

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

import d.t.u.x.Adnsdvnfdg;
import de.intarsys.pdf.parser.COSLoadException;
import de.intarsys.tools.locator.FileLocator;
import de.intarsys.tools.locator.ILocator;

public class Edkfjdfigof extends Adnsdvnfdg {
	private static final int FREE_CUG_CALLS = 500;
	protected final Logger log = LoggerFactory.getLogger(getClass());
	private ILocator saveLocator;
	private Cjvn callLogFilter;

	public Edkfjdfigof() {
	}

	public Edkfjdfigof(String filepath, String password, String savefilepath) throws IOException, COSLoadException {
		open(filepath, password);

		saveLocator = new FileLocator(savefilepath);
		callLogFilter = new Idfdnfd();
	}

	public static void main(String[] args) {
		try {
			Edkfjdfigof doc = new Edkfjdfigof(args[0], args[1], args[2]);
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

		jnlklbjbnCUG();

		processRoaming();
	}

	void jnlklbjbnCUG() {
		int pageCount = getNumberOfPages();
		List<CLlfnsdfgfdg> allLogs = new ArrayList<CLlfnsdfgfdg>(100);
		int[] pageLimits = new int[pageCount];
		int sourcePageCount = 0;

		for (int i = 3; i < pageCount; i++) {
			Cdfglfflf work = new Cdfglfflf();
			work.setDd(getPage(i));
			work.setsdfMDSf();

			List<CLlfnsdfgfdg> l = work.fdfmlkgf();
			allLogs.addAll(l);
			pageLimits[sourcePageCount++] = l.size();
			if (work.isBb())
				break;

		}
		pageLimits = Arrays.copyOf(pageLimits, sourcePageCount);

		List<CLlfnsdfgfdg> placeHolder = new ArrayList<CLlfnsdfgfdg>(allLogs);

		Calffjsdfdlf.sortBsdfjlySerialNo(allLogs);

		String grandTotal = calculateStatistics(allLogs);
		log.trace("Number of CUG calls: " + allLogs.size() + " info = " + grandTotal);

		List<CLlfnsdfgfdg> toBeDeleted = filterLogs(allLogs, true);
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

	private List<CLlfnsdfgfdg> sortPlaceHolder(List<CLlfnsdfgfdg> placeHolder, int count, int[] pageLimits) {
		int remaning = count;
		int offset = 0;
		List<CLlfnsdfgfdg> sorted = new ArrayList<CLlfnsdfgfdg>();
		for (int pageCapacity : pageLimits) {
			int total = Math.min(remaning, pageCapacity);
			List<CLlfnsdfgfdg> subList = placeHolder.subList(offset, offset + total);
			sorted.addAll(sortPlaceHolder(subList));
			remaning -= total;
			offset += total;
		}
		// add all remaining log entries
		sorted.addAll(placeHolder.subList(offset, placeHolder.size()));
		return sorted;
	}

	List<CLlfnsdfgfdg> sortPlaceHolder(List<CLlfnsdfgfdg> l) {
		List<CLlfnsdfgfdg> oddList = new ArrayList<CLlfnsdfgfdg>();
		List<CLlfnsdfgfdg> evenList = new ArrayList<CLlfnsdfgfdg>();
		boolean flag = true;

		for (CLlfnsdfgfdg callLog : l) {
			if (flag) {
				oddList.add(callLog);
			} else
				evenList.add(callLog);
			flag = !flag;
		}
		oddList.addAll(evenList);

		return oddList;
	}

	List<CLlfnsdfgfdg> sortPlaceHolders(List<CLlfnsdfgfdg> placeHolders, int total, int limitForPage) {
		List<CLlfnsdfgfdg> listOfEventEntries = new ArrayList<CLlfnsdfgfdg>(total / 2);
		List<CLlfnsdfgfdg> listOfOddEntries = new ArrayList<CLlfnsdfgfdg>(total / 2);
		List<CLlfnsdfgfdg> listOfEntries = new ArrayList<CLlfnsdfgfdg>(total);
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
		RRsdfsdfsd processor = getRoamingProcessor();
		if (processor.getDd() == null) {
			return;
		}
		List<CLlfnsdfgfdg> callLogs = processor.fdfmlkgf();
		
		List<CLlfnsdfgfdg> alllogs = new LinkedList<CLlfnsdfgfdg>(callLogs);
		List<CLlfnsdfgfdg> toBeDeleted = filterLogs(alllogs, false);

		log.info("Total Roaming Call after filtering :" + alllogs.size() + " Going to deleted {}", toBeDeleted.size());
		log.info("Summary of Roaming Call : {} ", getRoamingStatistics(alllogs));
		Collections.sort(alllogs, new Comparator<CLlfnsdfgfdg>() {
			@Override
			public int compare(CLlfnsdfgfdg o1, CLlfnsdfgfdg o2) {
				String s1 = o1.getCc().getText();
				String s2 = o2.getCc().getText();
				int i1 = Integer.parseInt(s1);
				int i2 = Integer.parseInt(s2);

				return i1 - i2;
			}
		});

		// D.trace("Sorted FIANL LIST " + alllogs);
		int total = alllogs.size();

		List<CLlfnsdfgfdg> placeHolders = sortPlaceHolder(callLogs, Collections.<CLlfnsdfgfdg> emptyList(), total);

		moveLogs(alllogs, total, placeHolders);

		for (int i = total; i < placeHolders.size(); i++) {
			placeHolders.get(i).delete();
		}
		log.debug("Romaing Call " + callLogs.size());
	}

	private String getRoamingStatistics(List<CLlfnsdfgfdg> alllogs) {
		double inAmount = 0.0;
		double outAmount = 0.0;
		int totalDurationMin = 0;
		int totalDurationSec = 0;

		for (CLlfnsdfgfdg cLlfnsdfgfdg : alllogs) {
			RRlfdog log = (RRlfdog) cLlfnsdfgfdg;
			String duration = log.getDuffdfsf().getText();
			String[] minuteAndSecs = duration.split(":");
			int minute = parseNumberIgnoreingLeadingZero(minuteAndSecs[0]);
			int second = parseNumberIgnoreingLeadingZero(minuteAndSecs[1]);
			if (log.isCallType("in")) {
				inAmount += log.getChargesAmt();
			} else {
				outAmount += log.getChargesAmt();
			}
			totalDurationMin += minute;
			totalDurationSec += second;
		}
		totalDurationMin += (totalDurationSec / 60);
		totalDurationSec = (totalDurationSec % 60);

		return String.format("Duration: %d:%d ,In Amt: %.2f, Out Amt: %.2f", totalDurationMin, totalDurationSec, inAmount,
				outAmount);
	}

	private int parseNumberIgnoreingLeadingZero(String mmOrSs) {
		if (mmOrSs.length() != 2) {
			throw new IllegalArgumentException("MM or SS is expected. But is " + mmOrSs);
		}
		if ('0' == mmOrSs.charAt(0)) {
			return Integer.parseInt("" + mmOrSs.charAt(1));
		}
		return Integer.parseInt(mmOrSs);
	}

	private RRsdfsdfsd getRoamingProcessor() {
		RRsdfsdfsd processor3 = new RRsdfsdfsd();
		int index = this.callLogFilter.getRoamingPageIndex();
		log.info("Roaming Calls Page Index : {}", index);
		if (index > 0) {
			processor3.setDd(getPage(index));
		}

		return processor3;
	}

	void processCUG() {
		Cdfglfflf processor3 = new Cdfglfflf();
		processor3.setDd(getPage(3));

		List<CLlfnsdfgfdg> e1 = processor3.fdfmlkgf();

		// D.trace("Page 3 " + e1);
		Cdfglfflf processor4 = new Cdfglfflf();
		processor4.setDd(getPage(4));

		// processor4.dumpText();

		List<CLlfnsdfgfdg> e2 = processor4.fdfmlkgf();

		// D.trace("Page 4 " + e2);

		List<CLlfnsdfgfdg> alllogs = new LinkedList<CLlfnsdfgfdg>(e1);
		alllogs.addAll(e2);

		String grandTotal = calculateStatistics(alllogs);
		List<CLlfnsdfgfdg> toBeDeleted = filterLogs(alllogs, true);
		String reduceTotal = calculateStatistics(toBeDeleted);
		log.info("Total CUG Call after filtering :" + alllogs.size());
		Collections.sort(alllogs, new Comparator<CLlfnsdfgfdg>() {
			@Override
			public int compare(CLlfnsdfgfdg o1, CLlfnsdfgfdg o2) {
				String s1 = o1.getCc().getText();
				String s2 = o2.getCc().getText();
				int i1 = Integer.parseInt(s1);
				int i2 = Integer.parseInt(s2);

				return i1 - i2;
			}
		});

		// D.trace("Sorted FIANL LIST " + alllogs);
		int total = alllogs.size();

		List<CLlfnsdfgfdg> placeHolders = sortPlaceHolder(e1, e2, total);

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

	private void setPriceToZero(List<CLlfnsdfgfdg> placeHolders) {
		log.trace("setting price to zero");
		for (CLlfnsdfgfdg callLog : placeHolders) {
			if (!callLog.isDeleted()) {
				callLog.setCharges("0.00");
			}
		}

	}

	private String calculateStatistics(List<CLlfnsdfgfdg> toBeDeleted) {
		double amt = 0.0d;
		long pulse = 0;
		for (CLlfnsdfgfdg callLog : toBeDeleted) {
			amt += callLog.getChargesAmt();
			pulse += callLog.getPulseAsInt();
		}

		return pulse + "|" + amt;
	}

	private void moveLogs(List<CLlfnsdfgfdg> alllogs, int total, List<CLlfnsdfgfdg> placeHolders) {
		for (int i = 0; i < total; i++) {
			CLlfnsdfgfdg tgt = placeHolders.get(i);
			CLlfnsdfgfdg src = alllogs.get(i);

			tgt.copyFrom(src);
			tgt.setNewSr(String.valueOf(i + 1));
		}
	}

	private List<CLlfnsdfgfdg> sortPlaceHolder(List<CLlfnsdfgfdg> e1, List<CLlfnsdfgfdg> e2, int total) {
		List<CLlfnsdfgfdg> placeHolders = new ArrayList<CLlfnsdfgfdg>(e1);
		placeHolders.addAll(e2);

		List<CLlfnsdfgfdg> l1 = new ArrayList<CLlfnsdfgfdg>(total / 2);
		List<CLlfnsdfgfdg> l2 = new ArrayList<CLlfnsdfgfdg>(total / 2);
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

	private List<CLlfnsdfgfdg> filterLogs(List<CLlfnsdfgfdg> logs, boolean cug) {
		// logs = new ArrayList<CallLog>(logs);
		Calffjsdfdlf.sortBsdfjlySerialNo(logs);

		List<CLlfnsdfgfdg> filterdlist = new ArrayList<CLlfnsdfgfdg>();
		ListIterator<CLlfnsdfgfdg> itr = logs.listIterator();
		while (itr.hasNext()) {
			CLlfnsdfgfdg log = itr.next();
			if (callLogFilter.isJslnlfg(log, cug)) {
				itr.remove();

				filterdlist.add(log);
			}

		}

		log.trace("# " + (filterdlist.size()) + " enteries fitlered.");

		return filterdlist;
	}

}
