package com.sapient.ipv.repositories;

import java.util.List;

import org.springframework.data.repository.RepositoryDefinition;

import com.sapient.ipv.domain.User;

@RepositoryDefinition(domainClass = User.class, idClass = Long.class)
public interface UserRepository  {
	List<User> findAll();

	User findById(long id);

	User findByFirstName(String firstName);
}
