package desi.rnp.jdbc.proxy.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestUtils {
	private final Logger log = LoggerFactory.getLogger(getClass());
	private Properties properties;
	private String databseUrl;
	private String username;
	private String password;
	public static TestUtils instance;
	static {
		TestUtils testInstance = new TestUtils();
		testInstance.init();
		setInstance(testInstance);
	}

	public TestUtils() {
	}

	public static TestUtils getInstance() {
		return instance;
	}

	public static void setInstance(TestUtils instance) {
		TestUtils.instance = instance;
	}

	public Connection openConnection() throws SQLException {
		return DriverManager.getConnection(databseUrl, properties);
	}

	public Properties getProperties() {
		return properties;
	}

	private void init() {
		try {
			InputStream in = getClass().getResourceAsStream("/connection.properties");
			Properties props = new Properties();
			props.load(in);

			String driverClass = props.getProperty("jdbc.driverClass");
			log.info("Driver '{}' is going to load", driverClass);
			Class.forName(driverClass);
			databseUrl = props.getProperty("jdbc.url");
			username = props.getProperty("jdbc.username");
			password = props.getProperty("jdbc.password");

			log.info("Test instance initialized successfully for database '{}' using '{}'", databseUrl, username);

			// set back in JDBC name
			props.put("user", username);
			props.put("password", password);

			this.properties = props;
		} catch (IOException | ClassNotFoundException e) {
			log.error("Test instance initialization failed", e);
			throw new RuntimeException(e);
		}
	}

	public static <T> List<T> processResultset(ResultSet rs, RowMapper<T> rowMapper) throws SQLException {
		List<T> rows = new ArrayList<>();
		while (rs.next()) {
			T row = rowMapper.map(rs);
			rows.add(row);
		}
		rs.close();
		return rows;
	}
}
