package com.sapient.ipv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sapient.ipv.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
