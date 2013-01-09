package desi.rnp.jdbc.proxy.handler;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import desi.rnp.jdbc.proxy.JdbcProxyFactory;

public class ProxyConnectionHandler extends ProxyObjectInvocationHandlerSupport<Connection> {

	public ProxyConnectionHandler(JdbcProxyFactory proxyFactory, Connection nativeObject) {
		super(proxyFactory, nativeObject);
	}

	public DatabaseMetaData getMetaData() throws SQLException {
		DatabaseMetaData nativeMetaData = nativeObject.getMetaData();
		return proxyFactory.newProxyObject(nativeMetaData);
	}

	public Statement createStatement() throws SQLException {
		Statement stmt = proxyFactory.newProxyObject(nativeObject.createStatement());

		return wireProxyConnectionOn(stmt);
	}

	private Statement wireProxyConnectionOn(Statement stmt) {
		ProxyStatementHandler statementHandler = getInvocationHandler(stmt, ProxyStatementHandler.class);
		statementHandler.setConnection((Connection) getTargetProxy());

		return stmt;
	}

	public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
		Statement stmt = proxyFactory.newProxyObject(nativeObject.createStatement(resultSetType, resultSetConcurrency));

		return wireProxyConnectionOn(stmt);
	}

}
