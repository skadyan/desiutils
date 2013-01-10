package d.t.u.b;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Calffjsdfdlf {
	private static final Logger log = LoggerFactory.getLogger(Calffjsdfdlf.class);

	public static void sortBsdfjlySerialNo(List<CLlfnsdfgfdg> entries) {
		Collections.sort(entries, new Comparator<CLlfnsdfgfdg>() {
			@Override
			public int compare(CLlfnsdfgfdg o1, CLlfnsdfgfdg o2) {
				int val1 = Integer.parseInt(o1.getCc().getText());
				int val2 = Integer.parseInt(o2.getCc().getText());

				return val1 - val2;
			}
		});
	}

	public static void dumpCallLogs(List<CLlfnsdfgfdg> logs) {
		log.info("Total Calls: " + logs.size());
		logs = new ArrayList<CLlfnsdfgfdg>(logs);

		sortBsdfjlySerialNo(logs);
		int i = 0;
		for (CLlfnsdfgfdg callLog : logs) {
			log.info(callLog + ":" + ++i);
		}
	}

}
