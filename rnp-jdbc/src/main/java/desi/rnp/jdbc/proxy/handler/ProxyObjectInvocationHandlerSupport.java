package desi.rnp.jdbc.proxy.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import desi.rnp.jdbc.proxy.JdbcProxyFactory;

public abstract class ProxyObjectInvocationHandlerSupport<T> implements InvocationHandler {
	protected final JdbcProxyFactory proxyFactory;
	protected final T nativeObject;

	protected MethodTable methodTable;

	public ProxyObjectInvocationHandlerSupport(JdbcProxyFactory proxyFactory, T nativeObject) {
		this.proxyFactory = proxyFactory;
		this.nativeObject = nativeObject;
		this.methodTable = new MethodTable(getClass());
	}

	public T getNativeObject() {
		return nativeObject;
	}

	public JdbcProxyFactory getProxyFactory() {
		return proxyFactory;
	}

	@Override
	public final Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Method m = findInTable(method);
		Object result = null;
		try {
			if (m == null) {
				result = invokeNativeMethodDirectly(proxy, method, args);
			} else {
				result = m.invoke(this, args);
			}
		} catch (InvocationTargetException e) {
			throw e.getTargetException();
		}

		return result;
	}

	private Method findInTable(Method method) {
		return methodTable.findInTable(method);
	}

	private Object invokeNativeMethodDirectly(Object proxy, Method method, Object[] args) throws Throwable {
		return method.invoke(nativeObject, args);
	}
}
