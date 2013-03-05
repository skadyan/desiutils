package com.sapient.ipv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.ipv.domain.Account;

@Transactional(propagation = Propagation.REQUIRED)
public interface AccountRepository extends JpaRepository<Account, Long> {

}
