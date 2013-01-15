package desi.rnp.jdbc.proxy.handler;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import desi.rnp.jdbc.proxy.JdbcProxyFactory;

public class ProxyDataSourceHandler extends ProxyObjectInvocationHandlerSupport<DataSource> {

	public ProxyDataSourceHandler(JdbcProxyFactory proxyFactory, DataSource nativeObject) {
		super(proxyFactory, nativeObject);
	}

	public Connection getConnection() throws SQLException {
		return proxyFactory.newProxyObject(nativeObject.getConnection());
	}

	public Connection getConnection(String username, String password) throws SQLException {
		return proxyFactory.newProxyObject(nativeObject.getConnection(username, password));
	}

}
