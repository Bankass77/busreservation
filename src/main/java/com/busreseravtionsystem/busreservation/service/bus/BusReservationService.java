package com.busreseravtionsystem.busreservation.service.bus;

import java.util.Set;

import com.busreseravtionsystem.busreservation.dto.bus.AgencyDto;
import com.busreseravtionsystem.busreservation.dto.bus.BusDto;
import com.busreseravtionsystem.busreservation.dto.bus.StopDto;
import com.busreseravtionsystem.busreservation.dto.bus.TripDto;
import com.busreseravtionsystem.busreservation.dto.user.UserDto;

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

	java.util.List<TripDto> addTrip(TripDto tripDto);
}
