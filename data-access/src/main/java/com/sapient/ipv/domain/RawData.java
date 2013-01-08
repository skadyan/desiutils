package com.sapient.ipv.domain;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.Lob;

import org.xml.sax.InputSource;

@Embeddable
public class RawData implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;

//	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "raw_data")
	private IdentityHashMap<RawField<?>, Object> rawData = new IdentityHashMap<>();

	@SuppressWarnings("unchecked")
	public RawData fromXml(Reader reader) {
		rawData = (IdentityHashMap<RawField<?>, Object>) readRawDataXml(reader);
		return this;
	}

	public void toXml(Writer writer) {
		try {
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			try (XMLEncoder encoder = new XMLEncoder(bout, "utf-8", false, 1)) {
				encoder.writeObject(rawData);
			}
			String xml = bout.toString("utf-8");
			writer.write(xml);
		} catch (IOException e) {
			throw new IllegalStateException("Not able to convert in xml");
		}

	}

	private Object readRawDataXml(Reader reader) {
		try (XMLDecoder decoder = new XMLDecoder(new InputSource(reader))) {
			return decoder.readObject();
		}
	}

	public <T> T get(RawField<T> field) {
		return field.type().cast(rawData.get(field));
	}

	public <T> void put(RawField<T> field, T value) {
		rawData.put(field, value);
	}

	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			return new Error(e);
		}
	}

	public boolean isEmpty() {
		return rawData.isEmpty();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		boolean result = true;
		if (obj instanceof RawData) {
			IdentityHashMap<RawField<?>, Object> o1 = this.rawData;
			IdentityHashMap<RawField<?>, Object> o2 = ((RawData) obj).rawData;
			if (o1.size() == o2.size()) {
				for (Map.Entry<RawField<?>, Object> entry : o1.entrySet()) {
					Object value1 = entry.getValue();
					Object value2 = o2.get(entry.getKey());
					if (!Objects.equals(value1, value2)) {
						result = false;
						break;
					}
				}
			}
		}
		return result;
	}

	@Override
	public int hashCode() {
		return rawData.hashCode();
	}

	@Override
	public String toString() {
		return rawData.toString();
	}
}
