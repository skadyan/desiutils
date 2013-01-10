package desi.rnp.jdbc.proxy.handler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import desi.rnp.jdbc.proxy.JdbcProxyFactory;

public class ProxyPreparedStatementHandler extends ProxyStatementHandler {

	private final PreparedStatement nativeObject;

	public ProxyPreparedStatementHandler(JdbcProxyFactory proxyFactory, PreparedStatement nativeObject) {
		super(proxyFactory, nativeObject);

		this.nativeObject = nativeObject;
	}

	public ResultSet executeQuery() throws SQLException {
		ResultSet rs = proxyFactory.newProxyObject(nativeObject.executeQuery());
		return wireStatementOn(rs);
	}

	public ResultSet getGeneratedKeys() throws SQLException {
		// the native resultset may not have statement associated with it
		return proxyFactory.newProxyObject(nativeObject.getGeneratedKeys());
	}
}
