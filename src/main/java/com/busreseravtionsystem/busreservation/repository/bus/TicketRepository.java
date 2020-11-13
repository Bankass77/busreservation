package com.busreseravtionsystem.busreservation.repository.bus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.busreseravtionsystem.busreservation.model.bus.Ticket;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

	/**
	 * 
	 * @param id
	 * @return
	 */
	Ticket getTicketById(Long id);

}
