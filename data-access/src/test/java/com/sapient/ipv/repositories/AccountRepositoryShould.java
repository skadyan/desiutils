package com.sapient.ipv.repositories;

import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sapient.ipv.domain.Account;
import com.sapient.ipv.domain.AccountProperty;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = { "classpath:applicationContext.xml" })
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class AccountRepositoryShould {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Test
	public void saveAnAccount() throws Exception {
		Account account = new Account();
		account.setName("SAPIENT-UBS");
		account.setActive(true);
		account.setOwner(userRepository.findById(1L));

		account = accountRepository.save(account);

		assertThat(account.getId(), notNullValue());
	}

	@Test
	public void saveAnAccountWithAccountProperty() throws Exception {
		Account account = new Account();
		account.setName("SAPIENT-Weligton");
		account.setActive(true);
		account.setOwner(userRepository.findById(1L));
		AccountProperty property = new AccountProperty();
		property.setName("onHold");
		property.setValue("true");

		account.setProperties(singletonList(property));

		account = accountRepository.save(account);
		assertThat(account.getId(), notNullValue());
	}
}
