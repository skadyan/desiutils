package desi.rnp.jdbc.proxy.recorder;

import desi.rnp.jdbc.bean.Interaction;

public interface Recorder {
	void record(Interaction interaction);
}
