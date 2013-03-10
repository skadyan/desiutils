package com.sapient.wmc.rampup.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Trx {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private String enteredBy;
	private String approvedBy;
	private String keywords;

	@Enumerated(EnumType.STRING)
	private StatusType status;

	@Enumerated(EnumType.STRING)
	private BusinessArea businessArea;
	@Enumerated(EnumType.STRING)
	private ReasonType reasonType;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar created;
	
	public Long getId() {
		return id;
	}

	public String getEnteredBy() {
		return enteredBy;
	}

	public void setEnteredBy(String enteredBy) {
		this.enteredBy = enteredBy;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public StatusType getStatus() {
		return status;
	}

	public void setStatus(StatusType status) {
		this.status = status;
	}

	public BusinessArea getBusinessArea() {
		return businessArea;
	}

	public void setBusinessArea(BusinessArea businessArea) {
		this.businessArea = businessArea;
	}

	public ReasonType getReasonType() {
		return reasonType;
	}

	public void setReasonType(ReasonType reasonType) {
		this.reasonType = reasonType;
	}

	public Calendar getCreated() {
		return created;
	}

	public void setCreated(Calendar created) {
		this.created = created;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((approvedBy == null) ? 0 : approvedBy.hashCode());
		result = prime * result + ((businessArea == null) ? 0 : businessArea.hashCode());
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((enteredBy == null) ? 0 : enteredBy.hashCode());
		result = prime * result + ((keywords == null) ? 0 : keywords.hashCode());
		result = prime * result + ((reasonType == null) ? 0 : reasonType.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trx other = (Trx) obj;
		if (approvedBy == null) {
			if (other.approvedBy != null)
				return false;
		} else if (!approvedBy.equals(other.approvedBy))
			return false;
		if (businessArea != other.businessArea)
			return false;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (enteredBy == null) {
			if (other.enteredBy != null)
				return false;
		} else if (!enteredBy.equals(other.enteredBy))
			return false;
		if (keywords == null) {
			if (other.keywords != null)
				return false;
		} else if (!keywords.equals(other.keywords))
			return false;
		if (reasonType != other.reasonType)
			return false;
		if (status != other.status)
			return false;
		return true;
	}
	
	
	
}
