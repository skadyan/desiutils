package com.sapient.ipv.domain;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "typeName")
public abstract class Ticket {
	private final String typeName = getClass().getSimpleName();
	
	@Id
	private String id;

	public Ticket(String id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
