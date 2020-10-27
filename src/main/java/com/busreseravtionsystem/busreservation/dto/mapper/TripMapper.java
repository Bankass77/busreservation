package com.busreseravtionsystem.busreservation.dto.mapper;

import com.busreseravtionsystem.busreservation.dto.bus.TripDto;
import com.busreseravtionsystem.busreservation.model.bus.Trip;

public class TripMapper {
	
	public static TripDto toTripMapper(Trip trip) {
		
		
		
		return new  TripDto()
				.setId(trip.getId())
				.setAgencyCode(trip.getBus().getAgency().getCode())
				.setBusCode(trip.getBus().getCode())
				.setDestinationStopCode(trip.getDestStop().getCode())
				.setDestinationStopName(trip.getSourceStop().getName())
				.setFare(trip.getFare())
				.setJourneyTime(trip.getJourneyTime())
				.setSourceStopCode(trip.getSourceStop().getCode());
		
		
	}

}
