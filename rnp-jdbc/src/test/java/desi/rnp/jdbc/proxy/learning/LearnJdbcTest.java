package desi.rnp.jdbc.proxy.learning;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import desi.rnp.jdbc.proxy.util.RowMapper;
import desi.rnp.jdbc.proxy.util.TestUtils;

public class LearnJdbcTest {

	private Connection connection;

	@Before
	public void setUp() throws Exception {
		connection = TestUtils.getInstance().openConnection();
	}

	@After
	public void tearDown() throws Exception {
		connection.close();
	}

	@Test
	public void testJdbcDriverSupportsJDBC40() throws Exception {
		DatabaseMetaData meta = connection.getMetaData();
		int jdbcMajorVersion = meta.getJDBCMajorVersion();
		int jdbcMinorVersion = meta.getJDBCMinorVersion();

		assertThat(jdbcMajorVersion, is(4));
		assertThat(jdbcMinorVersion, is(0));
	}

	@Test
	public void testDatabaseVendorSupportedTables() throws Exception {
		DatabaseMetaData meta = connection.getMetaData();
		ResultSet rs = meta.getTableTypes();

		List<String> tableTypes = TestUtils.processResultset(rs, new RowMapper<String>() {
			@Override
			public String map(ResultSet rs) throws SQLException {
				return rs.getString(1);
			}
		});

		assertThat(tableTypes, hasItems("TABLE", "VIEW"));
	}

	@Test
	public void testGetConnectionThroughDatabaseMetaData() throws Exception {
		DatabaseMetaData metaData = connection.getMetaData();
		Statement statement = metaData.getTableTypes().getStatement();
		Connection connection = statement.getConnection();
		assertNotNull("Connection not supported though informative resultset", connection);
		assertThat(connection, sameInstance(this.connection));
	}
}
