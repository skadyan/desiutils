package desi.tools.utility.bill;

import java.util.List;

import desi.tools.utility.pdf.TextBlockProvider;

public class CUGCallProcessor extends CallProcessor {

	private boolean endPatternFound;

	protected void setBoundByPatterns() {
		provider.setCurrentByPattern("CUG Calls");
		endPatternFound = provider.setEndLimitByPattern("Total");
	}

	public boolean isEndPatternFound() {
		return endPatternFound;
	}

	@Override
	protected CallLog createCallIf(TextBlockProvider provider) {
		return CallLog.createIf(provider);
	}

	public void dumpCalls() {
		List<CallLog> callLogs = getCallLogs();
		CalllogHelper.sortBySerialNo(callLogs);
		log.info("Call Logs Size :" + callLogs.size());
		for (CallLog callLog : callLogs) {
			log.info("{}", callLog);

		}
	}
}
