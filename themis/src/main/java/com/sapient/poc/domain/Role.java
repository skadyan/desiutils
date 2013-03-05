package com.sapient.poc.domain;

import java.util.Set;

public class Role implements Entity<Role, Long> {
	private final String name;

	private Long id;

	private Set<Permission> permissions;

	public Role(String name, Set<Permission> permissions) {
		this.name = name;
		this.permissions = permissions;
	}

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}

	public boolean hasPermission(Permission permission) {
		return permissions.contains(permission);
	}

	public void addPermission(Permission permission) {
		if (hasPermission(permission)) {
			String message = String.format("already have permission: %s", permission);
			throw new IllegalArgumentException(message);
		}
		this.permissions.add(permission);
	}

	@Override
	public String toString() {
		return name + "#" + id;
	}
}
