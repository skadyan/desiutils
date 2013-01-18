package desi.standalone.jpa.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserTest {

	private static EntityManagerFactory emf;

	@BeforeClass
	public static void setUpGlobal() throws Exception {
		emf = Persistence.createEntityManagerFactory("standalone-jpa");
	}

	@AfterClass
	public static void tearDownGlobal() throws Exception {
		if (emf != null)
			emf.close();
	}

	private EntityManager em;

	@Before
	public void setUp() throws Exception {
		em = emf.createEntityManager();
	}

	@After
	public void tearDown() throws Exception {
		if (em != null)
			em.close();
	}

	@Test
	public void demoStandAloneJPA() throws Exception {
		User user = newSampleUser();
		// begin tx
		em.getTransaction().begin();

		em.persist(user);

		// commit tx
		em.getTransaction().commit();

		// for test case; make sure object come from DB
		em.clear();
		User savedUser = em.find(User.class, user.getId());

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
