package desi.learn.beans;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.beans.Beans;
import java.beans.Expression;
import java.lang.management.BufferPoolMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class JavaBeanLearningTest {

	@Test
	public void instantiateBeanObject() throws Exception {
		ClassLoader cls = Thread.currentThread().getContextClassLoader();

		Object object = Beans.instantiate(cls, SimpleBean.class.getName());

		assertNotNull(object);
	}

	@Test
	public void useExpressionToInvokeDynamicMethodOnAnyTarget() throws Exception {
		final String name = "BeanName";
		SimpleBean target = new SimpleBean();
		target.setName(name);

		Expression getName = new Expression(target, "getName", new Object[0]);

		String value = (String) getName.getValue();

		assertThat(value, CoreMatchers.sameInstance(name));
	}

	@Test
	public void testName() throws Exception {
		  List<BufferPoolMXBean> pools = ManagementFactory.getPlatformMXBeans(BufferPoolMXBean.class);
		  for (BufferPoolMXBean bufferPoolMXBean : pools) {
			System.out.println(" " + bufferPoolMXBean);
		}

	}	

}
