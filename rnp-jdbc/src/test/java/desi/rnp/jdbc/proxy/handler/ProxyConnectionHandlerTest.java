package desi.rnp.jdbc.proxy.handler;

import static java.sql.ResultSet.CONCUR_READ_ONLY;
import static java.sql.ResultSet.TYPE_FORWARD_ONLY;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;

import desi.rnp.jdbc.proxy.JdbcProxyFactory;
import desi.rnp.jdbc.proxy.ProxyObject;

public class ProxyConnectionHandlerTest {
	private JdbcProxyFactory proxyFactory;

	@Before
	public void setUp() throws Exception {
		proxyFactory = new JdbcProxyFactory();
	}

	@Test
	public void testDatabaseMetaDataIsAProxyObjectReturnFromProxyConnecction() throws Exception {
		DatabaseMetaData nativeMetaData = mock(DatabaseMetaData.class);

		Connection nativeObject = mock(Connection.class);
		when(nativeObject.getMetaData()).thenReturn(nativeMetaData);

		Connection proxyObject = proxyFactory.newProxyObject(nativeObject);
		DatabaseMetaData metaData = proxyObject.getMetaData();

		assertThat(metaData, instanceOf(ProxyObject.class));
	}

	@Test
	public void testCreateStatementReturnProxyObject() throws Exception {
		Connection nativeObject = mock(Connection.class);

		when(nativeObject.createStatement()).thenReturn(mock(Statement.class));

		Connection proxyObject = proxyFactory.newProxyObject(nativeObject);
		Statement object = proxyObject.createStatement();

		assertThat(object, instanceOf(ProxyObject.class));
	}

	@Test
	public void testCreateStatementWithResultSetTypeAndConcurrencyReturnProxyObject() throws Exception {
		Connection nativeObject = mock(Connection.class);
		when(nativeObject.createStatement(anyInt(), anyInt())).thenReturn(mock(Statement.class));

		Connection proxyObject = proxyFactory.newProxyObject(nativeObject);
		Statement object = proxyObject.createStatement(TYPE_FORWARD_ONLY, CONCUR_READ_ONLY);
		assertThat(object, instanceOf(ProxyObject.class));
	}

}
