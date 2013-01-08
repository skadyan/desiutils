package desi.coreservices;

import org.joda.time.DateTime;

public interface IsTicket {
	public enum Status {
		Submitted, Started, Processed, Error, Cancelled
	}

	String getId();

	String getCorrelationId();

	String getSource();

	String getSourceId();

	String getSubmitter();

	DateTime getSubmissionTime();

	Status getStatus();
	void setStatus(Status status);
}
