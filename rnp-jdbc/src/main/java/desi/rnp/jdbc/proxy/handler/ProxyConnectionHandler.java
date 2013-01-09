package desi.rnp.jdbc.proxy.handler;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import desi.rnp.jdbc.proxy.JdbcProxyFactory;

public class ProxyConnectionHandler extends ProxyObjectInvocationHandlerSupport<Connection> {

	public ProxyConnectionHandler(JdbcProxyFactory proxyFactory, Connection nativeObject) {
		super(proxyFactory, nativeObject);
	}

	public DatabaseMetaData getMetaData() throws SQLException {
		DatabaseMetaData nativeMetaData = nativeObject.getMetaData();
		return proxyFactory.newProxyObject(nativeMetaData);
	}

}
