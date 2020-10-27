package com.busreseravtionsystem.busreservation.dto.mapper;

import com.busreseravtionsystem.busreservation.dto.bus.TripScheduleDto;
import com.busreseravtionsystem.busreservation.model.bus.TripSchedule;

public class TripScheduleMapper {
	
	public static TripScheduleDto tripScheduleDto (TripSchedule tripSchedule) {
		return new TripScheduleDto()
				.setAvailableSeats(tripSchedule.getAvailableSeats())
				.setBusCode(tripSchedule.getTripDetail().getBus().getCode())
				.setDestinationStop(tripSchedule.getTripDetail().getDestStop().getName())
				.setFare(tripSchedule.getTripDetail().getFare())
				.setTripId(tripSchedule.getTripDetail().getId())
				.setJourneyTime(tripSchedule.getTripDetail().getJourneyTime())
				.setSourceStop(tripSchedule.getTripDetail().getSourceStop().getName())
				.setStripDate(tripSchedule.getTripDate())
				.setTripId(tripSchedule.getTripDetail().getId());
		
		
	}

}
