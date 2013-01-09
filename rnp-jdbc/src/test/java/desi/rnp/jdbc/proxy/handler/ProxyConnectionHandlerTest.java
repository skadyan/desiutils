package desi.rnp.jdbc.proxy.handler;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import desi.rnp.jdbc.proxy.JdbcProxyFactory;
import desi.rnp.jdbc.proxy.ProxyObject;

public class ProxyConnectionHandlerTest {
	private JdbcProxyFactory jdbcProxyFactory;

	@Before
	public void setUp() throws Exception {
		jdbcProxyFactory = new JdbcProxyFactory();
	}

	@Test
	public void testDatabaseMetaDataIsAProxyObjectReturnFromProxyConnecction() throws Exception {
		DatabaseMetaData nativeMetaData = mock(DatabaseMetaData.class);

		Connection nativeObject = mock(Connection.class);
		when(nativeObject.getMetaData()).thenReturn(nativeMetaData);

		Connection proxyObject = jdbcProxyFactory.newProxyObject(nativeObject);
		DatabaseMetaData metaData = proxyObject.getMetaData();

		assertThat(metaData, instanceOf(ProxyObject.class));
	}

	@Test(expected = SQLException.class)
	public void testNativeSQLExcpetionIsThrownByProxyObject() throws Exception {
		Connection nativeObject = mock(Connection.class);
		doThrow(new SQLException()).when(nativeObject).getTransactionIsolation();

		Connection proxyObject = jdbcProxyFactory.newProxyObject(nativeObject);
		// trigger the exception
		proxyObject.getTransactionIsolation();
	}

}
