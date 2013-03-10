package com.sapient.wmc.rampup.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Calendar;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sapient.wmc.rampup.domain.BusinessArea;
import com.sapient.wmc.rampup.domain.Trx;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@ContextConfiguration("classpath:/context/applicationContext-test.xml")
public class TrxSetRepositoryTest {

	@Inject
	private TrxSetRepository repository;

	@Test
	public void testSaveAndReadBackATransactionSet() throws Exception {
		Trx trxSet = new Trx();
		trxSet.setEnteredBy("joe");
		trxSet.setApprovedBy("bob");
		trxSet.setBusinessArea(BusinessArea.BankLoans);
		trxSet.setCreated(Calendar.getInstance());

		repository.save(trxSet);

		assertThat(trxSet.getId(), notNullValue());

		Trx entity = repository.findOne(trxSet.getId());
		assertThat(entity, is(trxSet));
	}
}
