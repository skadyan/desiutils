package com.sapient.ipv.domain;

import java.util.UUID;

import javax.persistence.Entity;

import org.joda.time.LocalDateTime;

@Entity
public class PersistentTicket extends Ticket {
	private String submitter;

	private LocalDateTime submissionTime;

	public PersistentTicket() {
		this(UUID.randomUUID().toString());
	}

	public PersistentTicket(String id) {
		super(id);
	}
	public String getSubmitter() {
		return submitter;
	}

	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}

	public LocalDateTime getSubmissionTime() {
		return submissionTime;
	}

	public void setSubmissionTime(LocalDateTime submissionTime) {
		this.submissionTime = submissionTime;
	}

}
