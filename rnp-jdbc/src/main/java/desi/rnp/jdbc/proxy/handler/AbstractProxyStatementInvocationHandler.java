package desi.rnp.jdbc.proxy.handler;

import java.sql.Statement;

import desi.rnp.jdbc.proxy.JdbcProxyFactory;

public class AbstractProxyStatementInvocationHandler<T extends Statement> extends ProxyObjectInvocationHandlerSupport<T>{

	public AbstractProxyStatementInvocationHandler(JdbcProxyFactory proxyFactory, T nativeObject) {
		super(proxyFactory, nativeObject);
	}

}
