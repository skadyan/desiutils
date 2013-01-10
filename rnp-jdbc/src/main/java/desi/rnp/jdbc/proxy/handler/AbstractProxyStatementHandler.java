package desi.rnp.jdbc.proxy.handler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import desi.rnp.jdbc.proxy.JdbcProxyFactory;

public class AbstractProxyStatementHandler<T extends Statement> extends ProxyObjectInvocationHandlerSupport<T> {
	private Connection connection;

	public AbstractProxyStatementHandler(JdbcProxyFactory proxyFactory, T nativeObject) {
		super(proxyFactory, nativeObject);
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Connection getConnection() throws SQLException {
		return connection;
	}

}
