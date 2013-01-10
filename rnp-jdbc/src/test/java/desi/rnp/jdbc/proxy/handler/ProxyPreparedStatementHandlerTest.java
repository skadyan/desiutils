package desi.rnp.jdbc.proxy.handler;

import static desi.rnp.jdbc.proxy.util.ProxyObjectMatchers.isAProxyObjectHavingProxyStatement;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Before;
import org.junit.Test;

import desi.rnp.jdbc.proxy.JdbcProxyFactory;
import desi.rnp.jdbc.proxy.ProxyObject;

public class ProxyPreparedStatementHandlerTest {
	private JdbcProxyFactory proxyFactory;
	private PreparedStatement nativeObject;
	private PreparedStatement proxyObject;

	@Before
	public void setUp() throws Exception {
		proxyFactory = new JdbcProxyFactory();
		nativeObject = mock(PreparedStatement.class);
		proxyObject = proxyFactory.newProxyObject(nativeObject);
	}

	@Test
	public void testExecuteQueryReturnProxyObject() throws Exception {
		when(nativeObject.executeQuery()).thenReturn(mock(ResultSet.class));
		ResultSet result = proxyObject.executeQuery();
		assertThat(result, isAProxyObjectHavingProxyStatement());
	}

	@Test
	public void testGeneratedKeysReturnProxyObject() throws Exception {
		when(nativeObject.getGeneratedKeys()).thenReturn(mock(ResultSet.class));
		ResultSet result = proxyObject.getGeneratedKeys();

		assertThat(result, instanceOf(ProxyObject.class));
	}
}
