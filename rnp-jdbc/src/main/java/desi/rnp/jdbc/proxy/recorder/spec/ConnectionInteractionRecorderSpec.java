package desi.rnp.jdbc.proxy.recorder.spec;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.concurrent.Executor;

public interface ConnectionInteractionRecorderSpec extends Connection {
	@Override
	public void setAutoCommit(boolean autoCommit) throws SQLException;

	@Override
	public void clearWarnings() throws SQLException;

	@Override
	public void close() throws SQLException;

	@Override
	public void commit() throws SQLException;

	@Override
	public CallableStatement prepareCall(String sql) throws SQLException;

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException;

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
			int resultSetHoldability) throws SQLException;

	@Override
	public PreparedStatement prepareStatement(String sql) throws SQLException;

	@Override
	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException;

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
			throws SQLException;

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
			int resultSetHoldability) throws SQLException;

	@Override
	public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException;

	@Override
	public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException;

	@Override
	public void releaseSavepoint(Savepoint savepoint) throws SQLException;

	@Override
	public void rollback() throws SQLException;

	@Override
	public void rollback(Savepoint savepoint) throws SQLException;

	@Override
	public Savepoint setSavepoint() throws SQLException;

	@Override
	public Savepoint setSavepoint(String name) throws SQLException;

	@Override
	@DoNotRecord
	public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException;

	@Override
	public void setClientInfo(String name, String value) throws SQLClientInfoException;

	// all setter methods

}