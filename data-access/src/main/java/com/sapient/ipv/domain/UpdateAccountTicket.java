package com.sapient.ipv.domain;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class UpdateAccountTicket extends PersistentTicket {

	public UpdateAccountTicket() {
		this(UUID.randomUUID().toString());
	}

	public UpdateAccountTicket(String id) {
		super(id);
	}

	@ManyToOne
	private Account account;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
