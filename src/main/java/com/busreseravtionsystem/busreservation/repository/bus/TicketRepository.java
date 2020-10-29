package com.busreseravtionsystem.busreservation.repository.bus;

import org.springframework.data.jpa.repository.JpaRepository;

import com.busreseravtionsystem.busreservation.model.bus.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Ticket  getTicketById(Long id);

}
