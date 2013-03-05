package com.sapient.poc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sapient.poc.domain.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {

}
