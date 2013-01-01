package com.sapient.ipv.repositories;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.joda.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sapient.ipv.domain.PersistentTicket;
import com.sapient.ipv.domain.Ticket;
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
	public void testSaveATicket() throws Exception {
		PersistentTicket ticket = new PersistentTicket();
		ticket.setSubmissionTime(LocalDateTime.now());
		ticket.setSubmitter("SystemUnitTest");

		ticket = ticketRepository.save(ticket);

		assertNotNull(ticket);
	}

	@Test
	public void testSaveASubTypeOfTicket() throws Exception {
		UpdateAccountTicket ticket = new UpdateAccountTicket();
		ticket.setSubmissionTime(LocalDateTime.now());
		ticket.setSubmitter("SystemUnitTest");
		ticket.setAccount(accountRepository.findOne(Long.valueOf(1)));

		ticket = ticketRepository.save(ticket);

		assertNotNull(ticket);
	}

	@Test
	public void testFindAllTicket() throws Exception {
		List<Ticket> tickets = ticketRepository.findAll();
		for (Ticket ticket : tickets) {
			System.out.println(" " + ticket);
		}
	}

	@Test
	public void testSolution() throws Exception {
		List<? extends Ticket> tickets = ticketRepository.findTicketOfType(
				Arrays.asList("PersistentTicket"));
		for (Ticket ticket : tickets) {
			System.out.println(" " + ticket);
		}

	}

}
