package desi.rnp.jdbc.proxy.integration;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import desi.rnp.jdbc.proxy.JdbcProxyFactory;

public class ProxyDataSource implements DataSource {
	private final DataSource proxyObject;

	public ProxyDataSource(DataSource nativeObject) {
		this(nativeObject, new JdbcProxyFactory());
	}

	public ProxyDataSource(DataSource nativeObject, JdbcProxyFactory proxyFactory) {
		this.proxyObject = proxyFactory.newProxyObject(nativeObject);
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return proxyObject.getLogWriter();
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		proxyObject.setLogWriter(out);
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		proxyObject.setLoginTimeout(seconds);
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return proxyObject.getLoginTimeout();
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return proxyObject.getParentLogger();
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return proxyObject.unwrap(iface);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return proxyObject.isWrapperFor(iface);
	}

	@Override
	public Connection getConnection() throws SQLException {
		return proxyObject.getConnection();
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		return proxyObject.getConnection(username, password);
	}
}
