package desi.rnp.jdbc.proxy;

import desi.rnp.jdbc.proxy.recorder.spec.DoNotRecord;

public interface ProxyObject {
	@DoNotRecord
	Object getNativeObject();
}
