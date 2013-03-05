package com.sapient.poc.domain;

import java.util.Set;

public class User implements Entity<User, Long> {

	private Long id;

	private Set<Role> roles;

	public User() {
	}

	@Override
	public Long getId() {
		return id;
	}

}
