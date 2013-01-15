package desi.rnp.object;

import static desi.rnp.jdbc.proxy.util.TestUtils.getLastInteractionOn;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import desi.rnp.jdbc.proxy.JdbcProxyFactory;
import desi.rnp.proxy.bean.Interaction;

public class ObjectInteractionRecorderTest {
	private JdbcProxyFactory proxyFactory;
	private Connection nativeObject;
	private Connection proxyObject;

	@Before
	public void setUp() throws Exception {
		proxyFactory = new JdbcProxyFactory();
		nativeObject = mock(Connection.class);
		proxyObject = proxyFactory.newProxyObject(nativeObject);
	}

	@Test
	public void testConnectionInteraction() throws Exception {
		when(nativeObject.createStatement()).thenReturn(mock(Statement.class));
		proxyObject.createStatement();
		assertThat(getLastInteractionOn(proxyObject), notNullValue());
	}

	@Test
	public void testPreparedStatementInteraction() throws Exception {
		String sql = "select * from dual";
		// setupup
		when(nativeObject.prepareStatement(sql)).thenReturn(mock(PreparedStatement.class));
		when(nativeObject.createStatement()).thenReturn(mock(Statement.class));

		// interact
		proxyObject.prepareStatement(sql);

		Interaction interaction = getLastInteractionOn(proxyObject);

		assertThat(interaction.getArgs()[0], CoreMatchers.<Object> is(sql));
	}

	@Test
	public void testStatementInteraction() throws Exception {
		String sql = "create table t (c int)";
		Boolean returnedValue = Boolean.TRUE;

		Statement mockStatement = mock(Statement.class);
		when(mockStatement.execute(sql)).thenReturn(returnedValue);
		when(nativeObject.createStatement()).thenReturn(mockStatement);

		Statement statement = proxyObject.createStatement();
		statement.execute(sql);

		Interaction interaction = getLastInteractionOn(statement);

		assertThat(interaction.getArgs()[0], CoreMatchers.<Object> is(sql));
		assertThat(interaction.getReturnedValue(), CoreMatchers.<Object> is(returnedValue));
	}
}
