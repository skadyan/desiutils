package desi.rnp.jdbc.proxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import desi.rnp.jdbc.proxy.recorder.InteractionStore;
import desi.rnp.proxy.bean.Interaction;

public class InMemoryInteractionStore implements InteractionStore {
	private ConcurrentMap<Long, Map<String, List<Interaction>>> listOfInteractionOnObjectByThreads;

	public InMemoryInteractionStore() {
		listOfInteractionOnObjectByThreads = new ConcurrentHashMap<>();
	}

	@Override
	public void saveInteraction(Interaction interaction) {
		getOrCreateRelevantSlotFor(interaction.getThreadId(), interaction.getObjectId()).add(interaction);
	}

	private List<Interaction> getOrCreateRelevantSlotFor(Long threadId, String objectId) {
		Map<String, List<Interaction>> objectInteraction = listOfInteractionOnObjectByThreads.get(threadId);
		if (objectInteraction == null) {
			HashMap<String, List<Interaction>> newObjectInteraction = new HashMap<String, List<Interaction>>();
			objectInteraction = listOfInteractionOnObjectByThreads.putIfAbsent(threadId, newObjectInteraction);
			if (objectInteraction == null) {
				objectInteraction = newObjectInteraction;
			}
		}
		List<Interaction> list = objectInteraction.get(objectId);
		if (list == null) {
			objectInteraction.put(objectId, list = new ArrayList<>());
		}

		return list;
	}

	public Interaction getLastInteractionOn(String objectId, long threadId) {
		List<Interaction> interactions = getRelevantSlotFor(threadId, objectId);
		Interaction lastInteraction = null;
		if (interactions != null && interactions.size() > 0) {
			lastInteraction = interactions.get(interactions.size() - 1);
		}
		return lastInteraction;
	}

	private List<Interaction> getRelevantSlotFor(long threadId, String objectId) {
		Map<String, List<Interaction>> objectInteraction = listOfInteractionOnObjectByThreads.get(threadId);
		List<Interaction> list = null;
		if (objectInteraction != null) {
			list = objectInteraction.get(objectId);
		}

		return list;
	}

}
