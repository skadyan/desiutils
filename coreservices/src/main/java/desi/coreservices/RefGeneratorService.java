package desi.coreservices;

import desi.mango.utils.MiscUtils;

public class RefGeneratorService implements RefService {

	@Override
	public String newTicketRef() {
		return MiscUtils.generateUUID("T");
	}

}
