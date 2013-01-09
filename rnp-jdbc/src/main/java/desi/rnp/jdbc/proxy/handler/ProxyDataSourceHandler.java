package desi.rnp.jdbc.proxy.handler;

import javax.sql.DataSource;

import desi.rnp.jdbc.proxy.JdbcProxyFactory;

public class ProxyDataSourceHandler extends ProxyObjectInvocationHandlerSupport<DataSource>{

	public ProxyDataSourceHandler(JdbcProxyFactory proxyFactory, DataSource nativeObject) {
		super(proxyFactory, nativeObject);
	}
}
