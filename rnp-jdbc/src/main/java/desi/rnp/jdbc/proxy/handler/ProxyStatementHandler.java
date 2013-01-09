package desi.rnp.jdbc.proxy.handler;

import java.sql.Statement;

import desi.rnp.jdbc.proxy.JdbcProxyFactory;

public class ProxyStatementHandler extends AbstractProxyStatementInvocationHandler<Statement>{

	public ProxyStatementHandler(JdbcProxyFactory proxyFactory, Statement nativeObject) {
		super(proxyFactory, nativeObject);
	}
}
