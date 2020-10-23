package com.busreseravtionsystem.busreservation.dto.mapper;

import com.busreseravtionsystem.busreservation.dto.bus.TicketDto;
import com.busreseravtionsystem.busreservation.model.bus.Ticket;

public class TicketMapper {

	
	public static TicketDto ticketDto (Ticket ticket) {
		
		return new TicketDto().setId(ticket.getId());	
		
	}
}
