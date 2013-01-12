package desi.rnp.jdbc.proxy.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import desi.rnp.jdbc.proxy.JdbcProxyFactory;
import desi.rnp.jdbc.proxy.recorder.spec.InteractionRecordSpec;
import desi.rnp.jdbc.proxy.recorder.spec.StatementInteractionSpec;

@InteractionRecordSpec(StatementInteractionSpec.class)
public class ProxyStatementHandler extends AbstractProxyStatementHandler<Statement> {
	public ProxyStatementHandler(JdbcProxyFactory proxyFactory, Statement nativeObject) {
		super(proxyFactory, nativeObject);
	}

	public ResultSet executeQuery(String sql) throws SQLException {
		ResultSet rs = proxyFactory.newProxyObject(nativeObject.executeQuery(sql));

		return wireStatementOn(rs);
	}

	protected ResultSet wireStatementOn(ResultSet rs) {
		ProxyResultSetHandler statementHandler = getInvocationHandler(rs, ProxyResultSetHandler.class);
		statementHandler.setStatement((Statement) getTargetProxy());
		return rs;
	}

}
