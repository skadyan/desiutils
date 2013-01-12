package desi.rnp.jdbc.proxy.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import desi.rnp.jdbc.proxy.JdbcProxyFactory;
import desi.rnp.jdbc.proxy.ProxyObject;
import desi.rnp.jdbc.proxy.recorder.InteractionRecoderSupport;

public abstract class ProxyObjectInvocationHandlerSupport<T> implements InvocationHandler {
	protected final JdbcProxyFactory proxyFactory;
	protected final Logger log = LoggerFactory.getLogger(getClass());
	protected final T nativeObject;

	protected MethodTable methodTable;
	protected ThreadLocal<Object> currentTargetProxy;
	private InteractionRecoderSupport interactionRecorder;

	public ProxyObjectInvocationHandlerSupport(JdbcProxyFactory proxyFactory, T nativeObject) {
		this.proxyFactory = proxyFactory;
		this.nativeObject = nativeObject;
		this.methodTable = new MethodTable(getClass());
		this.currentTargetProxy = new ThreadLocal<>();
		this.interactionRecorder = proxyFactory.getRecordSpecOf(getClass());

	}

	public T getNativeObject() {
		return nativeObject;
	}

	public JdbcProxyFactory getProxyFactory() {
		return proxyFactory;
	}

	@Override
	public final Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		beforeExecutingMethodOn(proxy, method, args);
		Method m = findInTable(method);
		Object result = null;
		try {
			currentTargetProxy.set(proxy);
			if (m == null) {
				result = invokeNativeMethodDirectly(proxy, method, args);
			} else {
				result = m.invoke(this, args);
			}
			afterExecutingMethodOn(proxy, method, args, result);
		} catch (InvocationTargetException e) {
			throw e.getTargetException();
		} finally {

			currentTargetProxy.set(null);
		}

		return result;
	}

	protected void afterExecutingMethodOn(Object proxy, Method method, Object[] args, Object result) {
		interactionRecorder.recordIfNeeded((ProxyObject) proxy, method, args, result);
	}

	protected void beforeExecutingMethodOn(Object proxy, Method method, Object[] args) {
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
}
