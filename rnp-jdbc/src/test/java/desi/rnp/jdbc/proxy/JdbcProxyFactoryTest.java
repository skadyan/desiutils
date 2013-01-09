package desi.rnp.jdbc.proxy;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;

public class JdbcProxyFactoryTest {
	private JdbcProxyFactory proxyFactory;

	@Before
	public void setUp() throws Exception {
		proxyFactory = new JdbcProxyFactory();
	}

	@Test
	public void createProxyConnectionObject() throws Exception {
		Connection nativeObject = mock(Connection.class);
		Connection proxyObject = proxyFactory.newProxyObject(nativeObject);
		assertThat(proxyObject, instanceOf(ProxyObject.class));
	}

	@Test
	public void createProxyStatementObject() throws Exception {
		Statement nativeObject = mock(Statement.class);
		Statement proxyObject = proxyFactory.newProxyObject(nativeObject);
		assertThat(proxyObject, instanceOf(ProxyObject.class));
	}

	@Test
	public void createProxyPreparedStatementObject() throws Exception {
		PreparedStatement nativeObject = mock(PreparedStatement.class);
		PreparedStatement proxyObject = proxyFactory.newProxyObject(nativeObject);
		assertThat(proxyObject, instanceOf(ProxyObject.class));
	}

	@Test
	public void createProxyCallableStatementObject() throws Exception {
		CallableStatement nativeObject = mock(CallableStatement.class);
		CallableStatement proxyObject = proxyFactory.newProxyObject(nativeObject);
		assertThat(proxyObject, instanceOf(ProxyObject.class));
	}

	@Test
	public void createProxyDataSourceObject() throws Exception {
		DataSource nativeObject = mock(DataSource.class);
		DataSource proxyObject = proxyFactory.newProxyObject(nativeObject);
		assertThat(proxyObject, instanceOf(ProxyObject.class));
	}

	@Test
	public void createProxyDatabaseMetaDataObject() throws Exception {
		DatabaseMetaData nativeObject = mock(DatabaseMetaData.class);
		DatabaseMetaData proxyObject = proxyFactory.newProxyObject(nativeObject);
		assertThat(proxyObject, instanceOf(ProxyObject.class));
	}

}
