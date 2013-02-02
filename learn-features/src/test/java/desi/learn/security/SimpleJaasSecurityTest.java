package desi.learn.security;

import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;

import javax.management.remote.JMXPrincipal;
import javax.security.auth.Subject;

import org.junit.Test;

public class SimpleJaasSecurityTest {
	@Test
	public void checkSubject() throws Exception {
		Subject subject = new Subject();
		subject.getPrincipals().add(new JMXPrincipal("Bob"));
		subject.setReadOnly();

		Subject.doAs(subject, new PrivilegedAction<Void>() {
			@Override
			public Void run() {
				doThings();
				return null;
			}

		});
		
		doThings();
		

	}

	public void doThings() {
		AccessControlContext acc = AccessController.getContext();
		Subject subject = Subject.getSubject(acc);

		System.out.println("Subject found:" + subject);

	}
}
