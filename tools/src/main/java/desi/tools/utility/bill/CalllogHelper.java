package desi.tools.utility.bill;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalllogHelper {
	private static final Logger log = LoggerFactory.getLogger(CalllogHelper.class);

	public static void sortBySerialNo(List<CallLog> entries) {
		Collections.sort(entries, new Comparator<CallLog>() {
			@Override
			public int compare(CallLog o1, CallLog o2) {
				int val1 = Integer.parseInt(o1.getSr().getText());
				int val2 = Integer.parseInt(o2.getSr().getText());

				return val1 - val2;
			}
		});
	}

	public static void dumpCallLogs(List<CallLog> logs) {
		log.info("Total Calls: " + logs.size());
		logs = new ArrayList<CallLog>(logs);

		sortBySerialNo(logs);
		int i = 0;
		for (CallLog callLog : logs) {
			log.info(callLog + ":" + ++i);
		}
	}

}
