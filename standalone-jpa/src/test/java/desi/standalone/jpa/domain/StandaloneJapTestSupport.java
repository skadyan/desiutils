package desi.standalone.jpa.domain;

import java.util.concurrent.Callable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class StandaloneJapTestSupport {

	protected static EntityManagerFactory emf;

	@BeforeClass
	public static void setUpGlobal() throws Exception {
		emf = Persistence.createEntityManagerFactory("standalone-jpa");
	}

	@AfterClass
	public static void tearDownGlobal() throws Exception {
		if (emf != null)
			emf.close();
	}

	protected EntityManager em;

	@Before
	public void setUp() throws Exception {
		em = emf.createEntityManager();
	}

	@After
	public void tearDown() throws Exception {
		if (em != null)
			em.close();
	}

	public <V> V processUnderTransaction(Callable<V> task) throws Exception {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		boolean rollback = true;
		V result = null;
		try {
			result = task.call();
			rollback = false;
		} finally {
			if (rollback) {
				transaction.rollback();
			} else {
				transaction.commit();
			}
		}

		return result;
	}

	public void beginTransaction() {
		em.getTransaction().begin();
	}

	public void commitTransaction() {
		em.getTransaction().commit();
	}
}
