package com.sapient.ipv.domain;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.sapient.ipv.data.dictionary.StringField;


public class PersistentTicketTest {
	
	@Test
	public void testTicketRawAttrs() throws Exception {
		PersistentTicket ticket =new PersistentTicket();
		ticket.putRawField(StringField.id, "id");
		Object rawField = ticket.getRawField(StringField.id);
		assertNotNull(rawField);
	}
}
