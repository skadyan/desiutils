package com.sapient.ipv.repositories;

import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.concurrent.Callable;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sapient.ipv.domain.Account;
import com.sapient.ipv.domain.AccountProperty;
import com.sapient.ipv.service.CoreDataService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = { "classpath:applicationContext.xml" })
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class AccountRepositoryShould {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CoreDataService coreDataService;

	@Test
	@Ignore
	public void saveAnAccount() throws Exception {
		Account account = new Account();
		account.setName("SAPIENT-UBS");
		account.setActive(true);
		account.setOwner(userRepository.findById(1L));

		account = accountRepository.save(account);

		assertThat(account.getId(), notNullValue());
	}

	@Test
	@Ignore
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
		assertThat(property.getId(), notNullValue());
	}

	@Test
	public void loadAccountWithProperties() throws Exception {
		saveAnAccountWithAccountProperty();
		coreDataService.doInTransaction(new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				
				List<Account> accounts = accountRepository.findAll();
				System.out.println("accounts are loaded " + accounts);
				for (Account account : accounts) {
					System.out.println("going to check account "+ account);
					for (AccountProperty property : account.getProperties()) {
						assertThat(property.getName(), notNullValue());
					}
				}
				return null;
			}
		});

	}

}
