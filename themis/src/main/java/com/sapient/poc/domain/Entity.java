package com.sapient.poc.domain;

import java.io.Serializable;

public interface Entity<T, ID extends Serializable> {
	ID getId();
}
