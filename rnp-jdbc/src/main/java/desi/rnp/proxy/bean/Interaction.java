package desi.rnp.proxy.bean;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Interaction implements Serializable, Comparable<Interaction> {
	private static final long serialVersionUID = 6322919058541655239L;
	private final String objectId;
	private final long timestamp;
	private Method method;
	private Object[] args;
	private Object returnedValue;
	private long threadId;
	private long nativeExecutionTime;

	public Interaction(String objectId, long threadId, long nanoTime) {
		this.objectId = objectId;
		this.timestamp = nanoTime;
		this.threadId = threadId;
	}

	public void setMethod(Method m) {
		this.method = m;
	}

	public String getObjectId() {
		return objectId;
	}

	public Method getMethod() {
		return method;
	}

	public void setArguments(Object[] args) {
		this.args = args;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setReturnedValue(Object result) {
		this.returnedValue = result;
	}

	public Object getReturnedValue() {
		return returnedValue;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public long getThreadId() {
		return threadId;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(" at (").append(timestamp).append(')');
		sb.append(" by [").append(threadId).append(']');
		sb.append(" on (").append(objectId).append(')');
		sb.append(' ').append(method.getName()).append(' ');
		if (args != null && args.length > 0) {
			sb.append(" with ").append(Arrays.toString(args));
		}

		if (nativeExecutionTime > 0) {
			sb.append(" in ").append(nativeExecutionTime).append(" ms.");
		}

		return sb.toString();
	}

	public void setNativeExecutionTime(long nativeExecutionTime) {
		this.nativeExecutionTime = nativeExecutionTime;
	}

	public long getNativeExecutionTime() {
		return nativeExecutionTime;
	}

	@Override
	public int compareTo(Interaction o) {
		return (int) (timestamp - o.timestamp);
	}
}
