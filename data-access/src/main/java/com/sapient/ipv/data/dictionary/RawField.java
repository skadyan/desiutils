package com.sapient.ipv.data.dictionary;

import java.beans.PropertyEditor;

public interface RawField<T> {
	String name();

	Class<T> type();

	PropertyEditor propertyEditor();
}
