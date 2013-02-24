package desi.standalone.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Role {

	@Id
	private Long id;

	private String name;

	public Role() {
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
