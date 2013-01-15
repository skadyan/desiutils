package desi.rnp.jdbc.proxy.recorder;

import static java.util.Objects.requireNonNull;

import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import desi.rnp.jdbc.proxy.recorder.spec.DoNotRecord;
import desi.rnp.proxy.bean.Interaction;

public class InteractionRecoderSupport {
	private final Logger log = LoggerFactory.getLogger(getClass());
	private InteractionStore store;
	private AtomicLong lastTime = new AtomicLong();

	public InteractionRecoderSupport(Class<?> specType, InteractionStore store) {
		requireNonNull(specType, "spec type is null");
		prepareBySpec(specType);

		this.store = store;
	}

	private void prepareBySpec(Class<?> specType) {
		for (Method interactionSpec : specType.getDeclaredMethods()) {
			if (!isDoNotRecordSpecifiedOn(interactionSpec)) {
				// TODO ADD Here
			}
		}
	}

	private boolean isDoNotRecordSpecifiedOn(Method interactionSpec) {
		return interactionSpec.isAnnotationPresent(DoNotRecord.class);
	}

	public void recordIfNeeded(String objectId, Method method, Object[] args, Object result, long duration) {
		if (isDoNotRecordSpecifiedOn(method)) {
			return;
		}
		long timestamp = System.nanoTime();
		long threadId = Thread.currentThread().getId();

		Interaction interaction = new Interaction(objectId, threadId, timestamp);
		interaction.setMethod(method);
		interaction.setArguments(args);
		interaction.setReturnedValue(result);
		interaction.setNativeExecutionTime(duration);

		saveInteraction(interaction);
	}

	private void saveInteraction(Interaction interaction) {
		long newTime = interaction.getTimestamp();
		long time = lastTime.getAndSet(newTime);
		if (newTime < time) {
			log.error("Internaction of thread {} is not saved in correct order. ", interaction.getThreadId());
		}

		store.saveInteraction(interaction);
	}

	public Interaction getLastInteractionOn(String objectId, long threadId) {
		return store.getLastInteractionOn(objectId, threadId);
	}

}
