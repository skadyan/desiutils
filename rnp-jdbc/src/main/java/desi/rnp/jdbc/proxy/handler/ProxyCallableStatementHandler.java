package desi.rnp.jdbc.proxy.handler;

import java.sql.CallableStatement;

import desi.rnp.jdbc.proxy.JdbcProxyFactory;

public class ProxyCallableStatementHandler extends ProxyPreparedStatementHandler {

	public ProxyCallableStatementHandler(JdbcProxyFactory proxyFactory, CallableStatement nativeObject) {
		super(proxyFactory, nativeObject);
	}

}
