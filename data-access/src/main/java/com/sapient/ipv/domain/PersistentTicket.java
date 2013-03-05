package com.sapient.ipv.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.joda.time.DateTime;

import com.sapient.ipv.data.dictionary.RawField;

import desi.mango.utils.MiscUtils;

@Entity(name = "Ticket")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "persistentType")
public class PersistentTicket {
	@Column(nullable = false, updatable = false)
	private String persistentType = getClass().getSimpleName();
	@Id
	private String id;
	@Column(nullable = false)
	private String correlationId;
	@Column(nullable = false)
	private String typeName;
	@Column(nullable = false)
	private String submitter;
	@Column(nullable = false)
	private String source;
	@Column(nullable = false)
	private String sourceId;
	@Column(nullable = false)
	private DateTime submissionTime;

	private RawData rawData;

	@Basic(fetch = FetchType.EAGER)
	@Column(name = "data_bytes")
	private byte[] bytes;

	public PersistentTicket() {
		this(MiscUtils.generateUUID());

	}

	public PersistentTicket(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getPersistentType() {
		return persistentType;
	}

	public String getTypeName() {
		return typeName;
	}

	public String getSubmitter() {
		return submitter;
	}

	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}

	public DateTime getSubmissionTime() {
		return submissionTime;
	}

	public void setSubmissionTime(DateTime submissionTime) {
		this.submissionTime = submissionTime;
	}

	public String getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public <T> void putRawField(RawField<T> field, T value) {
		RawData data = getRawData();
		if (data == null) {
			data = new RawData();
			this.rawData = data;
		}
		data.put(field, value);
	}

	public RawData getRawData() {
		return rawData;
	}

	public <T> T getRawField(RawField<T> field) {
		return getRawData() != null ? rawData.get(field) : null;
	}

	@Override
	public boolean equals(Object obj) {
		return id.equals(((PersistentTicket) obj).id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
}
