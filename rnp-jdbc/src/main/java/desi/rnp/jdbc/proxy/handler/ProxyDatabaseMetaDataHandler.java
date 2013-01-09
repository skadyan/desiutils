package desi.rnp.jdbc.proxy.handler;

import java.sql.DatabaseMetaData;

import desi.rnp.jdbc.proxy.JdbcProxyFactory;

public class ProxyDatabaseMetaDataHandler extends ProxyObjectInvocationHandlerSupport<DatabaseMetaData> {
	public ProxyDatabaseMetaDataHandler(JdbcProxyFactory proxyFactory, DatabaseMetaData nativeObject) {
		super(proxyFactory, nativeObject);
	}

}
