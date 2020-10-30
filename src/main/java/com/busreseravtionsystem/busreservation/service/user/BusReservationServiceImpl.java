package com.busreseravtionsystem.busreservation.service.user;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.busreseravtionsystem.busreservation.dto.bus.AgencyDto;
import com.busreseravtionsystem.busreservation.dto.bus.BusDto;
import com.busreseravtionsystem.busreservation.dto.bus.StopDto;
import com.busreseravtionsystem.busreservation.dto.bus.TripDto;
import com.busreseravtionsystem.busreservation.dto.user.UserDto;
import com.busreseravtionsystem.busreservation.exception.BSRExecption;
import com.busreseravtionsystem.busreservation.exception.EntityType;
import com.busreseravtionsystem.busreservation.exception.ExceptionType;
import com.busreseravtionsystem.busreservation.model.bus.Agency;
import com.busreseravtionsystem.busreservation.model.bus.Stop;
import com.busreseravtionsystem.busreservation.model.user.User;
import com.busreseravtionsystem.busreservation.repository.bus.AgencyRepository;
import com.busreseravtionsystem.busreservation.repository.bus.BusRepository;
import com.busreseravtionsystem.busreservation.repository.bus.StopRepository;
import com.busreseravtionsystem.busreservation.repository.bus.TicketRepository;
import com.busreseravtionsystem.busreservation.repository.bus.TripRepository;
import com.busreseravtionsystem.busreservation.repository.bus.TripScheduleRepository;
import com.busreseravtionsystem.busreservation.repository.user.UserRepository;
import com.busreseravtionsystem.busreservation.service.bus.BusReservationService;

public class BusReservationServiceImpl implements BusReservationService {

	@Autowired
	private AgencyRepository agencyRepository;

	@Autowired
	private BusRepository BusRepository;

	@Autowired
	private StopRepository stopRepository;

	@Autowired
	private TicketRepository ticketrepository;

	@Autowired
	private TripRepository tripRepository;

	@Autowired
	private TripScheduleRepository tripSchedulerepositoiry;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Set<StopDto> getAllStopDtos() {

		return StreamSupport.stream(stopRepository.findAll().spliterator(), false)
				.map(stop -> modelMapper.map(stop, StopDto.class)).collect(Collectors.toCollection(TreeSet::new));
	}

	@Override
	public StopDto getStopByCode(String stopCode) {

		Optional<Stop> stop = Optional.ofNullable(stopRepository.findStopByCode(stopCode));

		if (stop.isPresent()) {

			return modelMapper.map(stop.get(), StopDto.class);

		}
		throw exception(EntityType.STOP, ExceptionType.ENTITY_NOT_FOUND, stopCode);
	}

	@Override
	public AgencyDto getAgency(UserDto userDto) {

		User user = getUser(userDto.getEmail());
		if (user != null) {

			Optional<Agency> agency = Optional.ofNullable(agencyRepository.findAgencyByUser(user));
 
			if (agency.isPresent()) {
				return modelMapper.map(agency.get(), AgencyDto.class);
				

			}

			//throw exceptionWithId(EntityType.AGENCY, ExceptionType.ENTITY_NOT_FOUND, 2, user.getEmail());
		}
 
		throw exception(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND, userDto.getEmail());
	}

	@Override
	public AgencyDto addAgency(AgencyDto agencyDto) {
		
		
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

	/**
	 * @param entityException
	 * @param ex
	 * @param args
	 * @return
	 */
	private RuntimeException exception(EntityType entityException, ExceptionType ex, String args) {

		return BSRExecption.throwException(entityException, ex, args);
	}

	/**
	 * @param agency
	 * @param entityNotFound
	 * @param i
	 * @param email
	 * @return
	 */
	private Exception exceptionWithId(EntityType agency, ExceptionType entityNotFound, Integer i, String email) {

		return BSRExecption.thRuntimeExceptionWithId(agency, entityNotFound, i, email);
	}

	/**
	 * @param userEmail
	 * @return
	 */
	private User getUser(String userEmail) {
		return userRepository.findByEmail(userEmail);

	}
	
	
	
	private Agency getAgencyCode(String code) {
		return agencyRepository.getAgencyByCode(code);
		
		
	}
	
}
