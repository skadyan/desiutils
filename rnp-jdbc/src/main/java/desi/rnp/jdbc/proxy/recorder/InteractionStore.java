package desi.rnp.jdbc.proxy.recorder;

import desi.rnp.proxy.bean.Interaction;

public interface InteractionStore {
	void saveInteraction(Interaction interaction);
	
	Interaction getLastInteractionOn(String objectId, long threadId) ;
}
