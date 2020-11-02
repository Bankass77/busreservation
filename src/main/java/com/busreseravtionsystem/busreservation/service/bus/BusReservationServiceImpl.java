package com.busreseravtionsystem.busreservation.service.bus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.busreseravtionsystem.busreservation.dto.bus.AgencyDto;
import com.busreseravtionsystem.busreservation.dto.bus.BusDto;
import com.busreseravtionsystem.busreservation.dto.bus.StopDto;
import com.busreseravtionsystem.busreservation.dto.bus.TicketDto;
import com.busreseravtionsystem.busreservation.dto.bus.TripDto;
import com.busreseravtionsystem.busreservation.dto.bus.TripScheduleDto;
import com.busreseravtionsystem.busreservation.dto.mapper.TicketMapper;
import com.busreseravtionsystem.busreservation.dto.mapper.TripMapper;
import com.busreseravtionsystem.busreservation.dto.mapper.TripScheduleMapper;
import com.busreseravtionsystem.busreservation.dto.user.UserDto;
import com.busreseravtionsystem.busreservation.exception.BSRExecption;
import com.busreseravtionsystem.busreservation.exception.EntityType;
import com.busreseravtionsystem.busreservation.exception.ExceptionType;
import com.busreseravtionsystem.busreservation.model.bus.Agency;
import com.busreseravtionsystem.busreservation.model.bus.Bus;
import com.busreseravtionsystem.busreservation.model.bus.Stop;
import com.busreseravtionsystem.busreservation.model.bus.Ticket;
import com.busreseravtionsystem.busreservation.model.bus.Trip;
import com.busreseravtionsystem.busreservation.model.bus.TripSchedule;
import com.busreseravtionsystem.busreservation.model.user.User;
import com.busreseravtionsystem.busreservation.repository.bus.AgencyRepository;
import com.busreseravtionsystem.busreservation.repository.bus.BusRepository;
import com.busreseravtionsystem.busreservation.repository.bus.StopRepository;
import com.busreseravtionsystem.busreservation.repository.bus.TicketRepository;
import com.busreseravtionsystem.busreservation.repository.bus.TripRepository;
import com.busreseravtionsystem.busreservation.repository.bus.TripScheduleRepository;
import com.busreseravtionsystem.busreservation.repository.user.UserRepository;
import com.busreseravtionsystem.busreservation.util.RandomStringUtil;

@Transactional
public class BusReservationServiceImpl implements BusReservationService {

	@Autowired
	private AgencyRepository agencyRepository;

	@Autowired
	private BusRepository busRepository;

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

			// throw exceptionWithId(EntityType.AGENCY, ExceptionType.ENTITY_NOT_FOUND, 2,
			// user.getEmail());
		}

		throw exception(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND, userDto.getEmail());
	}

	@Override
	public AgencyDto addAgency(AgencyDto agencyDto) {

		User agencyAdminUser = getUser(agencyDto.getUserOwner().getEmail());

		if (agencyAdminUser != null) {
			Optional<Agency> newAgency = Optional.ofNullable(agencyRepository.findAgencyByName(agencyDto.getName()));
			if (!newAgency.isPresent()) {

				Agency aNewAgency = new Agency().setName(agencyDto.getName())
						.setCode(RandomStringUtil.getAlphaNumericString(16, agencyDto.getName()))
						.setDetails(agencyDto.getDetails()).setUser(agencyAdminUser);

				agencyRepository.save(aNewAgency);
				return modelMapper.map(aNewAgency, AgencyDto.class);
			}

			throw exception(EntityType.AGENCY, ExceptionType.DUPLICATE_ENTITY, agencyDto.getName());

		}

		throw exception(EntityType.AGENCY, ExceptionType.ENTITY_NOT_FOUND, agencyDto.getUserOwner().getEmail());
	}

	@Override
	public AgencyDto updateAgency(AgencyDto agencyDto, BusDto busDto) {

		Agency agencyToUpdate = getAgencyCode(agencyDto.getCode());

		if (agencyToUpdate != null) {

			if (busDto != null) {

				Optional<Bus> bOptional = Optional
						.ofNullable(busRepository.findBusByCodeAndAgency(busDto.getCode(), agencyToUpdate));

				if (!bOptional.isPresent()) {

					Bus bus = new Bus().setAgency(agencyToUpdate).setCapacity(busDto.getCapacity())
							.setCode(busDto.getCode()).setMake(busDto.getMake());

					busRepository.save(bus);
					if (agencyToUpdate.getBuses() == null) {

						agencyToUpdate.setBuses(new HashSet<Bus>());
					}

					agencyToUpdate.getBuses().add(bus);

					return modelMapper.map(agencyRepository.save(agencyToUpdate), AgencyDto.class);
				}

			} else {

				// update agency info

				agencyToUpdate.setName(agencyDto.getName()).setDetails(agencyDto.getDetails());

				return modelMapper.map(agencyRepository.save(agencyToUpdate), AgencyDto.class);
			}

		}
		return agencyDto;

	}

	@Override
	public TripDto getTripById(Long id) {

		Optional<Trip> trip = tripRepository.findById(id);

		if (trip.isPresent()) {
			return TripMapper.toTripMapper(trip.get());
		}

		throw exception(EntityType.TRIP, ExceptionType.ENTITY_NOT_FOUND, id.toString());
	}

	@Override
	public List<TripDto> addTrip(TripDto tripDto) {

		List<TripDto> trips = new ArrayList<TripDto>();
		Stop stopDepart = getStop(tripDto.getSourceStopCode());
		if (stopDepart != null) {
			Stop stopArrival = getStop(tripDto.getDestinationStopCode());
			if (stopArrival != null) {

				if (!stopDepart.getCode().equalsIgnoreCase(stopArrival.getCode())) {
					Agency agency = getAgencyCode(tripDto.getAgencyCode());
					if (agency != null) {

						Bus busOfAgency = getBusOfAgency(tripDto.getBusCode());
						if (busOfAgency != null) {

							Trip trip = new Trip().setAgency(agency).setBus(busOfAgency).setDestStop(stopArrival)
									.setSourceStop(stopDepart).setFare(tripDto.getFare())
									.setJourneyTime(tripDto.getJourneyTime());
							trips.add(TripMapper.toTripMapper(tripRepository.save(trip)));

							return trips;
						}
						throw exception(EntityType.BUS, ExceptionType.ENTITY_NOT_FOUND, tripDto.getBusCode());
					}
					throw exception(EntityType.AGENCY, ExceptionType.ENTITY_NOT_FOUND, tripDto.getAgencyCode());
				}
				throw exception(EntityType.STOP, ExceptionType.ENTITY_NOT_FOUND, tripDto.getSourceStopCode());
			}
			throw exception(EntityType.AGENCY, ExceptionType.ENTITY_NOT_FOUND, tripDto.getDestinationStopCode());
		}
		return trips;

	}

	private Bus getBusOfAgency(String busCode) {

		return busRepository.findBusByCode(busCode);
	}

	@Override
	public List<TripDto> getAgencyTrip(String agencyCode) {
		Agency agency = getAgencyCode(agencyCode);

		if (agency != null) {
			List<Trip> listOfTrips = tripRepository.findTripByAgency(agency);

			if (!listOfTrips.isEmpty()) {
				return listOfTrips.stream().distinct().map(trip -> TripMapper.toTripMapper(trip))
						.collect(Collectors.toList());
			}
			return Collections.emptyList();
		}
		throw exception(EntityType.AGENCY, ExceptionType.ENTITY_NOT_FOUND, agencyCode);
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

	/**
	 * @param codeStop
	 * @return
	 */
	private Stop getStop(String codeStop) {
		return stopRepository.findStopByCode(codeStop);

	}

	@Override
	public List<TripDto> getAvailableTripBetweenStops(String sourceStopCode, String arrivalStopCode) {

		Optional<Stop> sourceStop = Optional.ofNullable(stopRepository.findStopByCode(sourceStopCode));

		if (sourceStop.isPresent()) {

			Optional<Stop> arrivalStop = Optional.ofNullable(stopRepository.findStopByCode(arrivalStopCode));

			if (arrivalStop.isPresent()) {

				List<Trip> availableTrips = tripRepository.findAllBySourceStopAndDestStop(sourceStop.get(),
						arrivalStop.get());

				if (!availableTrips.isEmpty()) {

					return availableTrips.stream().map(trip -> TripMapper.toTripMapper(trip))
							.collect(Collectors.toCollection(ArrayList::new));
				}

				return Collections.emptyList();
			}
			throw exception(EntityType.STOP, ExceptionType.ENTITY_NOT_FOUND, arrivalStopCode);
		}

		throw exception(EntityType.STOP, ExceptionType.ENTITY_NOT_FOUND, sourceStopCode);
	}

	@Override
	public TripScheduleDto getTripScheduleDto(TripDto tripDto, String tripDate, boolean createSchedForTrip) {

		Optional<Trip> trip = tripRepository.findById(tripDto.getId());
		if (trip.isPresent()) {

			Optional<TripSchedule> tripSchedule = Optional
					.ofNullable(tripSchedulerepositoiry.findTripScheduleByTripDetailAndTripDate(trip.get(), tripDate));
			if (tripSchedule.isPresent()) {
				return TripScheduleMapper.tripScheduleDto(tripSchedule.get());

			} else if (createSchedForTrip) {

				TripSchedule tripSchedule2 = new TripSchedule().setTripDetail(trip.get()).setTripDate(tripDate)
						.setAvailableSeats(trip.get().getBus().getCapacity());
				return TripScheduleMapper.tripScheduleDto(tripSchedulerepositoiry.save(tripSchedule2));
			}
		}
		throw exception(EntityType.TRIP, ExceptionType.ENTITY_NOT_FOUND, String.valueOf(tripDto.getId()));
	}

	@Override
	public List<TripScheduleDto> getAvailableTripSchedules(String sourceStop, String arrivalStop, String tripDate) {

		List<Trip> availableTrips = findTripsBetweenStops(sourceStop, arrivalStop);

		if (!availableTrips.isEmpty()) {
			return availableTrips.stream()
					.map(trip -> getTripScheduleDto(TripMapper.toTripMapper(trip), tripDate, true))
					.filter(tripScheduleDtao -> tripScheduleDtao != null).collect(Collectors.toList());

		}
		return Collections.emptyList();
	}

	@Override
	public TicketDto bookTicket(TripScheduleDto tripScheduleDto, UserDto passenger) {

		User user = getUser(passenger.getEmail());

		if (user != null) {

			Optional<TripSchedule> trOptional = tripSchedulerepositoiry.findById(tripScheduleDto.getTripId());

			if (trOptional.isPresent()) {

				Ticket ticket = new Ticket().setJourneyDate(trOptional.get().getTripDate()).setPassenger(user)
						.setTripSchedule(trOptional.get())
						.setSeatNumber(trOptional.get().getTripDetail().getBus().getCapacity()
								- trOptional.get().getAvailableSeats());
				ticketrepository.save(ticket);
				trOptional.get().setAvailableSeats(trOptional.get().getAvailableSeats() - 1); // On duminie de -1 le
																								// nombre de sièges
				tripSchedulerepositoiry.save(trOptional.get()); // On met à jour le planning
				return TicketMapper.toTicketDto(ticket);
			}
		}

		throw exception(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND, user.getEmail());

	}

	private List<Trip> findTripsBetweenStops(String sourceStop, String arrivalStop) {

		Optional<Stop> sourceStop2 = Optional.ofNullable(stopRepository.findStopByCode(sourceStop));

		if (sourceStop2.isPresent()) {
			Optional<Stop> arrivalStop2 = Optional.ofNullable(stopRepository.findStopByCode(arrivalStop));

			if (arrivalStop2.isEmpty()) {
				List<Trip> availablesTrips = tripRepository.findAllBySourceStopAndDestStop(sourceStop2.get(),
						arrivalStop2.get());

				if (!availablesTrips.isEmpty()) {

					return Collections.emptyList();
				}

				throw exception(EntityType.STOP, ExceptionType.ENTITY_NOT_FOUND, sourceStop);
			}

		}
		throw exception(EntityType.STOP, ExceptionType.ENTITY_EXCEPTION, arrivalStop);
	}

}
