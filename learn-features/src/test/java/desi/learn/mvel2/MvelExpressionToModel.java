package desi.learn.mvel2;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

import java.util.HashMap;

import org.junit.Test;
import org.mvel2.compiler.CompiledExpression;
import org.mvel2.compiler.ExpressionCompiler;
import org.mvel2.integration.impl.SimpleVariableResolverFactory;

public class MvelExpressionToModel {

	@Test
	public void simpleExpression() throws Exception {
		String expr = "Math.max(1,3)";
		ExpressionCompiler compiler = new ExpressionCompiler(expr);
		CompiledExpression expression = compiler.compile();

		Object value = expression.getValue(null, new SimpleVariableResolverFactory(new HashMap<String, Object>()));

		assertThat(value, instanceOf(Integer.class));
		assertThat((Integer) value, is(3));
	}
	
	@Test
	public void executeScript() throws Exception {
	}
}
