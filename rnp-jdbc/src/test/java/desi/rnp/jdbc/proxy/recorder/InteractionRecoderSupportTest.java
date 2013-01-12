package desi.rnp.jdbc.proxy.recorder;

import static org.mockito.Mockito.mock;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

import desi.rnp.jdbc.proxy.JdbcProxyFactory;
import desi.rnp.jdbc.proxy.ProxyObject;
import desi.rnp.jdbc.proxy.recorder.spec.DoNotRecord;

public class InteractionRecoderSupportTest {
	public static interface MyInteractionSpec {
		void createFoo();

		@DoNotRecord
		void doSomeFunnyStuff();
	}

	private JdbcProxyFactory proxyFactory;

	@Before
	public void setUp() throws Exception {
		proxyFactory = new JdbcProxyFactory();
	}

	@Test
	public void simpleRecord() throws Exception {
		InteractionRecoderSupport recoderSupport = proxyFactory.getRecordSpecOf(MyInteractionSpec.class);
		ProxyObject proxyObject = mock(ProxyObject.class);
		Method m = MyInteractionSpec.class.getMethod("createFoo");
		Object[] args = new Object[0];
		Object result = null;

		recoderSupport.recordIfNeeded(proxyObject, m, args, result);
		
	}

}
