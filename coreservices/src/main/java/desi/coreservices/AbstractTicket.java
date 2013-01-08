package desi.coreservices;

import org.joda.time.DateTime;

import com.sapient.ipv.domain.PersistentTicket;

public class AbstractTicket implements IsTicket {

	private PersistentTicket persistentTicket;

	protected AbstractTicket() {
		this(new PersistentTicket());
	}

	public AbstractTicket(PersistentTicket persistentTicket) {
		this.persistentTicket = persistentTicket;
	}

	@Override
	public String getId() {
		return persistentTicket.getId();
	}

	@Override
	public String getCorrelationId() {
		return persistentTicket.getCorrelationId();
	}

	@Override
	public String getSource() {
		return persistentTicket.getSource();
	}

	@Override
	public String getSourceId() {
		return persistentTicket.getSourceId();
	}

	@Override
	public String getSubmitter() {
		return persistentTicket.getSubmitter();
	}

	@Override
	public DateTime getSubmissionTime() {
		return persistentTicket.getSubmissionTime();
	}

	@Override
	public Status getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setStatus(Status status) {
		// TODO Auto-generated method stub

	}

}
