package com.sapient.ipv.repositories;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sapient.ipv.domain.PersistentTicket;
import com.sapient.ipv.domain.StringField;
import com.sapient.ipv.domain.UpdateAccountTicket;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = { "classpath:applicationContext.xml" })
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class TicketRepositoryShould {

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Test
	public void testSaveATicketAndLoadBack() throws Exception {
		PersistentTicket ticket;
		PersistentTicket savedTicket;
		ticket = new PersistentTicket("xxx");
		ticket.setSubmissionTime(DateTime.now());
		ticket.setSubmitter("UnitTestCase");
		ticket.setSource("SystemTesting");
		ticket.setSourceId("SystemTesting-1");
		ticket.putRawField(StringField.description, "Some Description");
		ticket.setBytes(new byte[] { 1 });
		ticketRepository.save(ticket);

		savedTicket = ticketRepository.findOne(ticket.getId());

		assertThat(savedTicket, is(ticket));

		String description = savedTicket.getRawField(StringField.description);
		byte[] bytes = savedTicket.getBytes();

		assertThat(description, is(ticket.getRawField(StringField.description)));
		assertNotNull(bytes);

	}

	@Test
	public void testSaveASubTypeOfTicket() throws Exception {
		UpdateAccountTicket ticket = new UpdateAccountTicket();
		ticket.setSubmissionTime(DateTime.now());
		ticket.setSubmitter("SystemUnitTest");
		ticket.setAccount(accountRepository.findOne(Long.valueOf(1)));
		ticket.putRawField(StringField.description, "Some Description");
		ticket = ticketRepository.save(ticket);

		assertNotNull(ticket);
	}

	@Test
	public void testFindAllTicket() throws Exception {
		List<PersistentTicket> tickets = ticketRepository.findAll();
		for (PersistentTicket ticket : tickets) {
			System.out.println(" " + ticket);
		}
	}

	@Test
	public void testSolution() throws Exception {
		List<? extends PersistentTicket> tickets = ticketRepository.findTicketOfType(Arrays.asList("PersistentTicket"));
		for (PersistentTicket ticket : tickets) {
			System.out.println(" " + ticket);
		}

	}

}
