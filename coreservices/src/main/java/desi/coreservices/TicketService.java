package desi.coreservices;

import desi.coreservices.IsTicket.Status;

public class TicketService {
	public void submit(IsTicket ticket) {
		markTicketAsSubmited(ticket);
		
		
		
		
	}

	private void markTicketAsSubmited(IsTicket ticket) {
		ticket.setStatus(Status.Submitted);
	}
	
	
	
}
