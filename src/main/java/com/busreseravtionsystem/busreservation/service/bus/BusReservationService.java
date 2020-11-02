package com.busreseravtionsystem.busreservation.service.bus;

import java.util.List;
import java.util.Set;

import com.busreseravtionsystem.busreservation.dto.bus.AgencyDto;
import com.busreseravtionsystem.busreservation.dto.bus.BusDto;
import com.busreseravtionsystem.busreservation.dto.bus.StopDto;
import com.busreseravtionsystem.busreservation.dto.bus.TicketDto;
import com.busreseravtionsystem.busreservation.dto.bus.TripDto;
import com.busreseravtionsystem.busreservation.dto.bus.TripScheduleDto;
import com.busreseravtionsystem.busreservation.dto.user.UserDto;
import com.busreseravtionsystem.busreservation.model.bus.TripSchedule;

public interface BusReservationService {
	/**
	 * 
	 * @return all Stop of bus
	 */
	Set<StopDto> getAllStopDtos();

	/**
	 * 
	 * @param stopCode
	 * @return code of stop
	 */
	StopDto getStopByCode(String stopCode);

	/**
	 * 
	 * @param userDto
	 * @return agency
	 */
	AgencyDto getAgency(UserDto userDto);

	/**
	 * 
	 * @param agencyDto
	 * @return agency
	 */
	AgencyDto addAgency(AgencyDto agencyDto);

	/**
	 * 
	 * @param agencyDto
	 * @param busDto
	 * @return
	 */
	AgencyDto updateAgency(AgencyDto agencyDto, BusDto busDto);

	/**
	 * 
	 * @param id
	 * @return
	 */
	TripDto getTripById(Long id);

	/**
	 * @param tripDto
	 * @return
	 */
	java.util.List<TripDto> addTrip(TripDto tripDto);
	
	/**
	 * @param agencyCode
	 * @return
	 */
	List<TripDto> getAgencyTrip(String agencyCode);
	
	
	/**
	 * @param sourceStop
	 * @param arrivalStop
	 * @return
	 */
	List<TripDto>getAvailableTripBetweenStops( String sourceStop, String arrivalStop);
	

	
	/**
	 * @param sourceStop
	 * @param arrivalStop
	 * @param tripDate
	 * @return
	 */
	List<TripScheduleDto> getAvailableTripSchedules ( String sourceStop, String arrivalStop, String tripDate);
	
	/**
	 * @param tripDto
	 * @param tripDate
	 * @param createSchedForTrip
	 * @return
	 */
	TripScheduleDto getTripScheduleDto (TripDto tripDto, String tripDate, boolean createSchedForTrip);
	
	/**
	 * @param tripScheduleDto
	 * @param passenger
	 * @return
	 */
	TicketDto bookTicket(TripScheduleDto tripScheduleDto, UserDto passenger);
	
	
	
}
