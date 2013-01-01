package com.sapient.ipv.domain;

import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractAuditable;

@Entity
public class Region extends AbstractAuditable<User, Long> {
	private static final long serialVersionUID = 1L;

	private String name;

	private String timeZone;

	public void setId(Long id) {
		super.setId(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

}
