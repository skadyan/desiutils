package desi.rnp.jdbc.bean;

import java.lang.reflect.Method;

public class Interaction {
	private final String objectId;
	private final Method method;
	private final Object[] args;
	private final Object result;
	private final long timestamp;
	private final long executationTime;

	public Interaction(String objectId, Method method, Object[] args, Object result, long timestamp, long duration) {
		this.objectId = objectId;
		this.method = method;
		this.args = args;
		this.result = result;
		this.timestamp = timestamp;
		this.executationTime = duration;
	}

	public String getObjectId() {
		return objectId;
	}

	public Method getMethod() {
		return method;
	}

	public Object[] getArgs() {
		return args;
	}

	public Object getResult() {
		return result;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public long getExecutationTime() {
		return executationTime;
	}

}
