package desi.rnp.jdbc.proxy.handler;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.CallableStatement;
import java.sql.ResultSetMetaData;

import org.junit.Before;

import desi.rnp.jdbc.proxy.JdbcProxyFactory;
import desi.rnp.jdbc.proxy.ProxyObject;

public class ProxyCallableStatementHandlerTest {
	private JdbcProxyFactory proxyFactory;
	private CallableStatement nativeObject;
	private CallableStatement proxyObject;

	@Before
	public void setUp() throws Exception {
		proxyFactory = new JdbcProxyFactory();
		nativeObject = mock(CallableStatement.class);
		proxyObject = proxyFactory.newProxyObject(nativeObject);
	}

	public void testGetMetaDataReturnProxyObject() throws Exception {
		when(nativeObject.getMetaData()).thenReturn(mock(ResultSetMetaData.class));
		assertThat(proxyObject.getMetaData(), not(instanceOf(ProxyObject.class)));
	}
}
