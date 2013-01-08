package com.sapient.ipv.domain;

public enum StringField implements RawField<String> {
	id, name, description;

	@Override
	public Class<String> type() {
		return String.class;
	}

	@Override
	public String toExternalString(String value) {
		return value;
	}

	@Override
	public String fromExternalString(String value) {
		return value;
	}

}
