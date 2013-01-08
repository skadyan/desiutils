package desi.rnp.jdbc.proxy;

import static java.lang.reflect.Proxy.newProxyInstance;
import static java.util.Objects.requireNonNull;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.sql.DataSource;

import desi.rnp.jdbc.proxy.handler.ProxyConnectionHandler;
import desi.rnp.jdbc.proxy.handler.ProxyDataSourceHandler;
import desi.rnp.jdbc.proxy.handler.ProxyPreparedStatementHandler;
import desi.rnp.jdbc.proxy.handler.ProxyStatementHandler;

public class JdbcProxyFactory {

	public Connection newProxyObject(Connection nativeObject) {
		requireNonNull(nativeObject, "Native Object is required");
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Object proxy = newProxyInstance(classLoader, new Class<?>[] { ProxyObject.class, Connection.class },
				new ProxyConnectionHandler());
		return (Connection) proxy;
	}

	public Statement newProxyObject(Statement nativeObject) {
		requireNonNull(nativeObject, "Native Object is required");
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Object proxy = newProxyInstance(classLoader, new Class<?>[] { ProxyObject.class, Statement.class },
				new ProxyStatementHandler());
		return (Statement) proxy;
	}

	public PreparedStatement newProxyObject(PreparedStatement nativeObject) {
		requireNonNull(nativeObject, "Native Object is required");
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Object proxy = newProxyInstance(classLoader, new Class<?>[] { ProxyObject.class, PreparedStatement.class },
				new ProxyPreparedStatementHandler());
		return (PreparedStatement) proxy;
	}

	public CallableStatement newProxyObject(CallableStatement nativeObject) {
		requireNonNull(nativeObject, "Native Object is required");
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Object proxy = newProxyInstance(classLoader, new Class<?>[] { ProxyObject.class, CallableStatement.class },
				new ProxyPreparedStatementHandler());
		return (CallableStatement) proxy;
	}

	public DataSource newProxyObject(DataSource nativeObject) {
		requireNonNull(nativeObject, "Native Object is required");
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Object proxy = newProxyInstance(classLoader, new Class<?>[] { ProxyObject.class, DataSource.class },
				new ProxyDataSourceHandler());
		return (DataSource) proxy;
	}

}
