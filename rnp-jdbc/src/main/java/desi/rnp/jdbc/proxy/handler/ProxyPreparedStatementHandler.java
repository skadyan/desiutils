package desi.rnp.jdbc.proxy.handler;

import java.sql.PreparedStatement;

import desi.rnp.jdbc.proxy.JdbcProxyFactory;

public class ProxyPreparedStatementHandler extends AbstractProxyStatementInvocationHandler<PreparedStatement>{

	public ProxyPreparedStatementHandler(JdbcProxyFactory proxyFactory, PreparedStatement nativeObject) {
		super(proxyFactory, nativeObject);
	}
}
