package desi.rnp.jdbc.proxy.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import desi.rnp.jdbc.proxy.recorder.InteractionStore;
import desi.rnp.proxy.bean.Interaction;

public class Slf4jLoggerInteractionStore implements InteractionStore {
	private final Logger log = LoggerFactory.getLogger(getClass());
	public static Slf4jLoggerInteractionStore INSTANCE = new Slf4jLoggerInteractionStore();

	@Override
	public void saveInteraction(Interaction interaction) {
		log.info("Interaction {}", interaction);
	}

	@Override
	public Interaction getLastInteractionOn(String objectId, long threadId) {
		throw new UnsupportedOperationException("method not supported");
	}

}
