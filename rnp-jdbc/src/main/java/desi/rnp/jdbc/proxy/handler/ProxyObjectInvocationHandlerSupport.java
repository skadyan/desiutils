package desi.rnp.jdbc.proxy.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import desi.rnp.jdbc.proxy.JdbcProxyFactory;
import desi.rnp.jdbc.proxy.recorder.InteractionRecoderSupport;
import desi.rnp.jdbc.proxy.recorder.spec.DoNotRecord;
import desi.rnp.proxy.bean.Interaction;

public abstract class ProxyObjectInvocationHandlerSupport<T> implements InvocationHandler {
	protected final JdbcProxyFactory proxyFactory;
	protected final Logger log = LoggerFactory.getLogger(getClass());
	protected final T nativeObject;

	protected MethodTable methodTable;
	protected ThreadLocal<Object> currentTargetProxy;
	private InteractionRecoderSupport interactionRecorder;
	private String objectId;

	public ProxyObjectInvocationHandlerSupport(JdbcProxyFactory proxyFactory, T nativeObject) {
		this.proxyFactory = proxyFactory;
		this.nativeObject = nativeObject;
		this.methodTable = new MethodTable(getClass());
		this.currentTargetProxy = new ThreadLocal<>();
		this.interactionRecorder = proxyFactory.getRecordSpecOf(getClass());
		this.objectId = proxyFactory.generateUUID(nativeObject.getClass().getSimpleName() + "-");
	}

	public T getNativeObject() {
		return nativeObject;
	}

	public JdbcProxyFactory getProxyFactory() {
		return proxyFactory;
	}

	@Override
	public final Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		beforeExecutingMethodOn(objectId, method, args);

		Method m = findInTable(method);
		Object result = null;
		long time = System.currentTimeMillis();
		try {
			currentTargetProxy.set(proxy);
			if (m == null) {
				result = invokeNativeMethodDirectly(proxy, method, args);
			} else {
				result = m.invoke(this, args);
			}

			time = System.currentTimeMillis() - time;
			afterExecutingMethodOn(objectId, method, args, result, time);
		} catch (InvocationTargetException e) {
			throw e.getTargetException();
		} finally {
			currentTargetProxy.set(null);
		}

		return result;
	}

	protected void afterExecutingMethodOn(String objectId, Method method, Object[] args, Object result, long duration) {
		interactionRecorder.recordIfNeeded(objectId, method, args, result, duration);
	}

	protected void beforeExecutingMethodOn(String objectId, Method method, Object[] args) {
	}

	private Method findInTable(Method method) {
		return methodTable.findInTable(method);
	}

	private Object invokeNativeMethodDirectly(Object proxy, Method method, Object[] args) throws Throwable {
		return method.invoke(nativeObject, args);
	}

	protected Object getTargetProxy() {
		return currentTargetProxy.get();
	}

	protected <H extends InvocationHandler> H getInvocationHandler(Object proxyObject, Class<H> expectedType) {
		return expectedType.cast(Proxy.getInvocationHandler(proxyObject));
	}

	public Interaction getLastInteraction() {
		return interactionRecorder.getLastInteractionOn(objectId, Thread.currentThread().getId());
	}

	@DoNotRecord
	public String getObjectId() {
		return objectId;
	}

	@DoNotRecord
	public ProxyObjectInvocationHandlerSupport<?> getInvocationHandler() {
		return this;
	}
}
