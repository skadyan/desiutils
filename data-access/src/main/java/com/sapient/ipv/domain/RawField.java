package com.sapient.ipv.domain;

public interface RawField<T> {
	String name();

	Class<T> type();

	String toExternalString(T value);

	T fromExternalString(String value);
}
