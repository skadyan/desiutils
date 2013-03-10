package com.sapient.wmc.rampup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sapient.wmc.rampup.domain.Trx;

public interface TrxSetRepository extends JpaRepository<Trx, Long>, JpaSpecificationExecutor<Trx> {
	// no-op
}
