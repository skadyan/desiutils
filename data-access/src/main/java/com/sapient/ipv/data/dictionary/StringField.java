package com.sapient.ipv.data.dictionary;

public enum StringField implements RawField<String> {
	//@formatter:off
	id,
	name, 
	description,
	
	
	//@formatter:on
	;

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
