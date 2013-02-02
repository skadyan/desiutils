package com.sapient.ipv.data.dictionary;

public interface RawField<T> {
	String name();

	Class<T> type();

	String toExternalString(T value);

	T fromExternalString(String value);
}
