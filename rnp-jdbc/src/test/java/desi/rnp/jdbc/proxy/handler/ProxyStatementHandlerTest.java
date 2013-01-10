package desi.rnp.jdbc.proxy.handler;

import static desi.rnp.jdbc.proxy.util.ProxyObjectMatchers.isAProxyObjectHavingProxyStatement;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;

import desi.rnp.jdbc.proxy.JdbcProxyFactory;

public class ProxyStatementHandlerTest {
	private JdbcProxyFactory proxyFactory;
	private Statement nativeObject;
	private Statement proxyObject;

	@Before
	public void setUp() throws Exception {
		proxyFactory = new JdbcProxyFactory();
		nativeObject = mock(Statement.class);
		proxyObject = proxyFactory.newProxyObject(nativeObject);
	}

	@Test
	public void testExecuteQueryReturnProxyObject() throws Exception {
		when(nativeObject.executeQuery(anyString())).thenReturn(mock(ResultSet.class));
		ResultSet result = proxyObject.executeQuery("select now() from dual");
		assertThat(result, isAProxyObjectHavingProxyStatement());
	}
	
	
}
