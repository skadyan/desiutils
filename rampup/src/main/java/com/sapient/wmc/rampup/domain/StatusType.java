package com.sapient.wmc.rampup.domain;

public enum StatusType {
	//@formatter:off
	ValidationPending("Validation Pending"),
	Validated("Validated"),
	Approved("Approved"),
	SubmittedToGeneva("Submitted to Geneva"),
	GenevaApproved("Geneva Approved"),
	GenevaRejected("GenevaRejected")
	//@formatter:on
	;

	private String description;

	private StatusType(String desc) {
		this.description = desc;
	}

	public String getDescription() {
		return description;
	}
}
