package com.sapient.ipv.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sapient.ipv.domain.PersistentTicket;

@Repository
public interface TicketRepository extends JpaRepository<PersistentTicket, String> {
	@Query("from Ticket  where typeName in ( :types ) ")
	List<? extends PersistentTicket> findTicketOfType(@Param("types") List<String> types);
}
