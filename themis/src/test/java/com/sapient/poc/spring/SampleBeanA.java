package com.sapient.poc.spring;

import org.springframework.beans.factory.annotation.Autowired;

public class SampleBeanA {
	private SampleBeanB bean;
	
	@Autowired
	public SampleBeanA(SampleBeanB bean) {
		this.bean = bean;
	}

	public SampleBeanB getBean() {
		return bean;
	}
}
