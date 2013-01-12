package desi.rnp.jdbc.proxy.recorder;

import static java.util.Objects.requireNonNull;

import java.lang.reflect.Method;

import desi.rnp.jdbc.proxy.ProxyObject;
import desi.rnp.jdbc.proxy.recorder.spec.DoNotRecord;

public class InteractionRecoderSupport {

	public InteractionRecoderSupport(Class<?> specType) {
		requireNonNull(specType, "spec type is null");
		prepareBySpec(specType);
	}

	private void prepareBySpec(Class<?> specType) {
		for (Method interactionSpec : specType.getDeclaredMethods()) {
			if (!isDoNotRecordSpecifiedOn(interactionSpec)) {
				//TODO ADD Here
			}
		}
	}

	private boolean isDoNotRecordSpecifiedOn(Method interactionSpec) {
		return interactionSpec.isAnnotationPresent(DoNotRecord.class);
	}

	public void recordIfNeeded(ProxyObject proxyObject, Method method, Object[] args, Object result) {

	}

}
