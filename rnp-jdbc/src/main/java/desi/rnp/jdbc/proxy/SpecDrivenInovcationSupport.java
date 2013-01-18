package desi.rnp.jdbc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import desi.rnp.jdbc.proxy.recorder.Recorder;

public class SpecDrivenInovcationSupport implements InvocationHandler {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	private final Class<?> specification;
	private final JdbcProxyFactory proxyFactory;
	private final Object nativeObject;
	private final String objectId;

	public SpecDrivenInovcationSupport(Class<?> specification, JdbcProxyFactory proxyFactory, Object nativeObject) {
		this.specification = specification;
		this.proxyFactory = proxyFactory;
		this.nativeObject = nativeObject;
		this.objectId = proxyFactory.generateUUID(nativeObject.getClass().getSimpleName());
	}

	public Class<?> getSpecification() {
		return specification;
	}

	public Object getNativeObject() {
		return nativeObject;
	}

	public JdbcProxyFactory getProxyFactory() {
		return proxyFactory;
	}

	@Override
	public final Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		try {
			return method.invoke(nativeObject, args);
		} catch (InvocationTargetException e) {
			return e.getCause();
		}
	}

	protected Recorder lookupInteractionRecoder(Method method) {
		return null;
	}

	public String getObjectId() {
		return objectId;
	}

}
