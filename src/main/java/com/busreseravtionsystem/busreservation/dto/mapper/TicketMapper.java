
package com.busreseravtionsystem.busreservation.dto.mapper;

import com.busreseravtionsystem.busreservation.dto.bus.TicketDto;
import com.busreseravtionsystem.busreservation.model.bus.Ticket;

public class TicketMapper {
	public static TicketDto toTicketDto(Ticket ticket) {
		return new TicketDto().setId(ticket.getId())
				.setBusCode(ticket.getTripSchedule().getTripDetail().getBus().getCode())
				.setSeatNumber(ticket.getSeatNumber())
				.setSourceStop(ticket.getTripSchedule().getTripDetail().getSourceStop().getName())
				.setDestinationStop(ticket.getTripSchedule().getTripDetail().getDestStop().getName())
				.setCancellable(false).setJourneyDate(ticket.getJourneyDate())
				.setPassengerMobileNumber(ticket.getPassenger().getMobileNumber())
				.setPassengerName(ticket.getPassenger().getFullName());

	}
}
