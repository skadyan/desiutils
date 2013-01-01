package desi.coreservices;

import org.joda.time.DateTime;

public interface IsTicket {
	String getSource();

	String getSourceId();

	String getId();

	String getSubmitter();

	DateTime getSubmissionTime();
}
