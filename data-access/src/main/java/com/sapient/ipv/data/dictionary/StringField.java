package com.sapient.ipv.data.dictionary;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;

public enum StringField implements RawField<String> {
	// @formatter:off
	id, name, description,

	// @formatter:on
	;

	@Override
	public Class<String> type() {
		return String.class;
	}

	@Override
	public PropertyEditor propertyEditor() {
		return PropertyEditorManager.findEditor(String.class);
	}
}
