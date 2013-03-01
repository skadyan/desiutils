package com.sapient.poc.spring;

import static org.hamcrest.CoreMatchers.sameInstance;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = { "classpath:testContext.xml" })
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class SpringBeanTest implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Test
	public void testBeanWiring() throws Exception {
		SampleBeanA beanA = applicationContext.getBean(SampleBeanA.class);
		SampleBeanB beanB = applicationContext.getBean(SampleBeanB.class);
		
		Assert.assertThat(beanB, sameInstance(beanA.getBean()));
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

}
