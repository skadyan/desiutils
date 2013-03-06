package com.sapient.ipv.repositories;

import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnitUtil;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.sapient.ipv.domain.Account;
import com.sapient.ipv.domain.AccountProperty;
import com.sapient.ipv.domain.User;
import com.sapient.ipv.service.CoreDataService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = { "classpath:applicationContext.xml" })
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class AccountRepositoryShould {

	@Inject
	private UserRepository userRepository;

	@Inject
	private AccountRepository accountRepository;

	@Inject
	private CoreDataService coreDataService;

	@Inject
	private EntityManagerFactory entityManagerFactory;

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
					System.out.println("going to check account " + account);
					for (AccountProperty property : account.getProperties()) {
						assertThat(property.getName(), notNullValue());
					}
				}
				return null;
			}
		});
	}

	@Test
	@Transactional
	public void testObjectReference() throws Exception {
		EntityManager em = getEntityManager();
		assertThat(em, notNullValue());
		User user = em.getReference(User.class, Long.valueOf(1));
		assertThat(user, not(isInitialized()));
	}

	@Test
	@Transactional
	public void testFindObject() throws Exception {
		EntityManager em = getEntityManager();
		assertThat(em, notNullValue());
		User user = em.find(User.class, Long.valueOf(1));
		assertThat(user, isInitialized());
	}

	@Test
	@Transactional
	public void testFieldLevelProxyObject() throws Exception {
		EntityManager em = getEntityManager();
		assertThat(em, notNullValue());
		Account account = em.find(Account.class, Long.valueOf(1));
		assertThat(account, not(isInitialized("owner")));
	}

	private <T> Matcher<T> isInitialized(final String... fields) {
		return new BaseMatcher<T>() {
			private StringBuilder sb = new StringBuilder();

			@Override
			public void describeTo(Description desc) {
				desc.appendText(sb.toString());
			}

			@Override
			public boolean matches(Object proxy) {
				PersistenceUnitUtil persistenceUnitUtil = entityManagerFactory.getPersistenceUnitUtil();
				boolean loaded = true;
				if (fields.length != 0) {
					for (String field : fields) {
						if (!persistenceUnitUtil.isLoaded(proxy, field)) {
							sb.append(String.format(
									"\n'%s' field is not loaded", field));
							loaded = false;
						}
					}
				} else {
					if (!persistenceUnitUtil.isLoaded(proxy)) {
						sb.append("\nobject is not loaded");
						loaded = false;
					}
				}
				if (loaded) {
					sb.append("object is loaded");
				}
				return loaded;
			}
		};
	}

	public EntityManager getEntityManager() {
		EntityManagerHolder holder = (EntityManagerHolder) TransactionSynchronizationManager
				.getResource(entityManagerFactory);
		EntityManager em = holder.getEntityManager();
		return em;
	}

}
