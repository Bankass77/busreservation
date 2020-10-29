package com.busreseravtionsystem.busreservation.service.bus;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.busreseravtionsystem.busreservation.dto.bus.AgencyDto;
import com.busreseravtionsystem.busreservation.dto.bus.BusDto;
import com.busreseravtionsystem.busreservation.dto.bus.StopDto;
import com.busreseravtionsystem.busreservation.dto.bus.TripDto;
import com.busreseravtionsystem.busreservation.dto.user.UserDto;


@Service("busService")
public class BusServiceImpl implements BusReservationService {

	
	
	
	@Override
	public Set<StopDto> getAllStopDtos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StopDto getStopByCode(String stopCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AgencyDto getAgency(UserDto userDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AgencyDto addAgency(AgencyDto agencyDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AgencyDto updateAgency(AgencyDto agencyDto, BusDto busDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TripDto getTripById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TripDto> addTrip(TripDto tripDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
