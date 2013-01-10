package desi.rnp.jdbc.proxy.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import desi.rnp.jdbc.proxy.JdbcProxyFactory;

public class ProxyResultSetHandler extends ProxyObjectInvocationHandlerSupport<ResultSet> {

	private Statement statement;

	public ProxyResultSetHandler(JdbcProxyFactory proxyFactory, ResultSet nativeObject) {
		super(proxyFactory, nativeObject);
	}

	public void setStatement(Statement statement) {
		this.statement = statement;

	}

	public Statement getStatement() throws SQLException{
		return statement;
	}

}
