package desi.rnp.jdbc.proxy.handler;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MethodTable {

	private Map<String, Method> map;

	public MethodTable(Class<?> clazz) {
		initTableFor(clazz);
	}

	private void initTableFor(Class<?> clazz) {
		map = new HashMap<>();
		do {
			addDeclaredMethodOf(clazz);
			clazz = clazz.getSuperclass();
		} while (clazz != ProxyObjectInvocationHandlerSupport.class);
	}

	private void addDeclaredMethodOf(Class<?> clazz) {
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			map.put(generateMethodKey(method), method);
		}
	}

	private String generateMethodKey(Method method) {
		StringBuilder sb = new StringBuilder();
		sb.append(method.getReturnType().getName()).append(' ');
		sb.append(method.getName()).append('(').append(' ');

		Class<?>[] parameterTypes = method.getParameterTypes();
		for (Class<?> type : parameterTypes) {
			sb.append(type.getName());
			sb.append(',');
		}
		sb.setCharAt(sb.length() - 1, ')');

		return sb.toString();
	}

	public Method findInTable(Method method) {
		return map.get(generateMethodKey(method));
	}
}
