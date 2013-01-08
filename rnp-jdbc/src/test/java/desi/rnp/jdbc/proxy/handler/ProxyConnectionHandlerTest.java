package desi.rnp.jdbc.proxy.handler;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.sql.Connection;
import java.sql.DatabaseMetaData;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import desi.rnp.jdbc.proxy.JdbcProxyFactory;

public class ProxyConnectionHandlerTest {
	private JdbcProxyFactory jdbcProxyFactory;
	
	@Before
	public void setUp() throws Exception {
		jdbcProxyFactory = new JdbcProxyFactory();
	}
	@Test
	public void invokeGetMetaData() throws Exception {
		DatabaseMetaData nativeMetaData = mock(DatabaseMetaData.class);
		Connection nativeObject = mock(Connection.class);
		Mockito.when(nativeObject.getMetaData()).thenReturn(nativeMetaData);
		Connection proxyObject = jdbcProxyFactory.newProxyObject(nativeObject);
		DatabaseMetaData metaData = proxyObject.getMetaData();
		assertThat(metaData, CoreMatchers.sameInstance(nativeMetaData));
	}
}
