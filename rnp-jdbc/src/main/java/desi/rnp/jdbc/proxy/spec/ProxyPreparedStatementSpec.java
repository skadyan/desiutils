package desi.rnp.jdbc.proxy.spec;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import desi.rnp.jdbc.proxy.ProxyObject;

public interface ProxyPreparedStatementSpec extends PreparedStatement, ProxyObject {

	@Override
	public void addBatch() throws SQLException;

	@Override
	@Recorder("xxxx")
	public boolean execute() throws SQLException;

}
