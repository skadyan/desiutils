package desi.rnp.jdbc.proxy.recorder;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class CapturePreparedStatementParameter implements PreparedStatement{
	@Override
	public void addBatch() throws SQLException {
	}
}
