package com.sapient.ipv.repositories;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sapient.ipv.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = { "classpath:applicationContext.xml" })
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class UserRepositoryShould {
	@Autowired
	private UserRepository repository;

	@Test
	public void findAllUser() throws Exception {
		List<User> users = repository.findAll();
		assertThat(users.size(), is(2));
	}
	
	@Test
	public void findUserByName() throws Exception {
		User adminUser= repository.findByFirstName("Admin");
		
		assertThat(adminUser.getFirstName(), is("Admin"));
	}

}
