package desi.standalone.jpa.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class UserTest extends StandaloneJapTestSupport {

	@Test
	public void demoStandAloneJPA() throws Exception {
		final User user = newSampleUser();
		
		beginTransaction();
		
		em.persist(user);
		
		commitTransaction();
		
		// for test case; make sure object come from DB
		em.clear();
		final User savedUser = em.find(User.class, user.getId());
		assertThat(savedUser, is(user));
	}

	private User newSampleUser() {
		User user = new User();
		user.setActive(true);
		user.setFirstName("System");
		user.setLastName("Sys");
		user.setId(10L);
		return user;
	}
}
