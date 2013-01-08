package desi.learn.javascript;

import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeNotNull;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class InvokeJavaScript {
	private ScriptEngine scriptEngine;
	private static ScriptEngineManager scriptEngineManager;

	@BeforeClass
	public static void setUpGlobal() {
		scriptEngineManager = new ScriptEngineManager();
	}

	@Test
	public void readReturnedValueFromJavaScript() throws Exception {
		Object object = scriptEngine.eval("(function(){return 'helloJava';})();");

		assertEquals("helloJava", object);
	}

	@Before
	public void setUp() {
		scriptEngine = scriptEngineManager.getEngineByMimeType("text/javascript");

		assumeNotNull("Script engine is not available", scriptEngine);
	}
}
