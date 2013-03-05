package com.sapient.poc.domain;

import static java.util.Objects.requireNonNull;

public class Permission implements ValueObject<Permission> {
	private Long id;

	private final String name;
	
	public Permission(String name) {
		requireNonNull(name, "name is required");
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name + "#" + id;
	}
}
