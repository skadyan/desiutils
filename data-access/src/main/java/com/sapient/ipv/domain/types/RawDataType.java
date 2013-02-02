package com.sapient.ipv.domain.types;

import static java.sql.Types.LONGVARBINARY;

import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.EnhancedUserType;

import com.sapient.ipv.domain.RawData;

public class RawDataType implements EnhancedUserType {

	@Override
	public int[] sqlTypes() {
		return new int[] { LONGVARBINARY };
	}

	@Override
	public Class<?> returnedClass() {
		return RawDataType.class;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		return Objects.equals(x, y);
	}

	@Override
	public int hashCode(Object x) throws HibernateException {
		return Objects.hash(x);
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner)
			throws HibernateException, SQLException {
		RawData rawData = new RawData();
		String clob = rs.getString(names[0]);
		if (!rs.wasNull()) {
			rawData.fromXml(new StringReader(clob));
		}

		return rawData;
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session)
			throws HibernateException, SQLException {
		if (isRawDataEmpty(value)) {
			st.setNull(index, Types.LONGVARBINARY);
		} else {
			st.setString(index, convertToXml(value));
		}
	}

	private boolean isRawDataEmpty(Object value) {
		return value == null || ((RawData) value).isEmpty();
	}

	private String convertToXml(Object value) {
		StringWriter writer = new StringWriter();
		((RawData) value).toXml(writer);
		return writer.toString();
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		if (value == null)
			return null;
		return ((RawData) value).clone();
	}

	@Override
	public boolean isMutable() {
		return true;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	@Override
	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return cached;
	}

	@Override
	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return deepCopy(original);
	}

	@Override
	public String objectToSQLString(Object value) {
		return toXMLString(value);
	}

	@Override
	public String toXMLString(Object value) {
		return convertToXml(value);
	}

	@Override
	public Object fromXMLString(String xmlValue) {
		return new RawData().fromXml(new StringReader(xmlValue));
	}

}
