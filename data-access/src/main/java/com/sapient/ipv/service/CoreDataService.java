package com.sapient.ipv.service;

import java.util.concurrent.Callable;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CoreDataService {

	
	public <V> V doInTransaction(Callable<V> callable) throws Exception {
		return callable.call();
	}
}
