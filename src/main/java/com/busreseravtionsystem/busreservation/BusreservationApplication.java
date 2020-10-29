package com.busreseravtionsystem.busreservation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.busreseravtionsystem.busreservation.model.bus.Agency;
import com.busreseravtionsystem.busreservation.model.bus.Bus;
import com.busreseravtionsystem.busreservation.model.bus.Stop;
import com.busreseravtionsystem.busreservation.model.bus.Trip;
import com.busreseravtionsystem.busreservation.model.bus.TripSchedule;
import com.busreseravtionsystem.busreservation.model.user.Role;
import com.busreseravtionsystem.busreservation.model.user.User;
import com.busreseravtionsystem.busreservation.model.user.UserRole;
import com.busreseravtionsystem.busreservation.repository.bus.AgencyRepository;
import com.busreseravtionsystem.busreservation.repository.bus.BusRepository;
import com.busreseravtionsystem.busreservation.repository.bus.StopRepository;
import com.busreseravtionsystem.busreservation.repository.bus.TripRepository;
import com.busreseravtionsystem.busreservation.repository.bus.TripScheduleRepository;
import com.busreseravtionsystem.busreservation.repository.user.RoleRepository;
import com.busreseravtionsystem.busreservation.repository.user.UserRepository;
import com.busreseravtionsystem.busreservation.util.DateUtils;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaAuditing
public class BusreservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusreservationApplication.class, args);
	}

	 @Bean
	    CommandLineRunner init(RoleRepository roleRepository, UserRepository userRepository,
	                           StopRepository stopRepository, AgencyRepository agencyRepository,
	                           BusRepository busRepository, TripRepository tripRepository,
	                           TripScheduleRepository tripScheduleRepository) {
	        return args -> {
	            //Create Admin and Passenger Roles
	            Role adminRole = roleRepository.findByRole(UserRole.ADMIN);
	            if (adminRole == null) {
	                adminRole = new Role();
	                adminRole.setRole(UserRole.ADMIN);
	                roleRepository.save(adminRole);
	            }

	            //roles.add(adminRole);
	            Role userRole = roleRepository.findByRole(UserRole.PASSENGER);
	            if (userRole == null) {
	                userRole = new Role();
	                userRole.setRole(UserRole.PASSENGER);
	                roleRepository.save(userRole);
	            }
	         

	            //Create an Admin user
	            User admin = userRepository.findByEmail("admin@gmail.com");
	            if (admin == null) {
	                admin = new User()
	                        .setEmail("admin@gmail.com")
	                        .setPassword("admin1") // "123456"
	                        .setFirstName("John")
	                        .setLastName("Doe")
	                        .setMobileNumber("9425094250")
	                        .setRoles(new HashSet<Role>(Arrays.asList(adminRole)));
	                        //.setRoles(Arrays.asList(adminRole));
	                userRepository.save(admin);
	            }

	            //Create a passenger user
	            User passenger = userRepository.findByEmail("passenger@gmail.com");
	            if (passenger == null) {
	                passenger = new User()
	                        .setEmail("passenger@gmail.com")
	                        .setPassword("passenger1") // "123456"
	                        .setFirstName("Mira")
	                        .setLastName("Jane")
	                        .setMobileNumber("8000110008")
	                        .setRoles(new HashSet<Role>(Arrays.asList(userRole)));
	                //Arrays.asList(userRole)
	                userRepository.save(passenger);
	            }

	            //Create four stops
	            Stop stopA = stopRepository.findStopByCode("STPA");
	            if (stopA == null) {
	                stopA = new Stop()
	                        .setName("Stop A")
	                        .setDetail("Near hills")
	                        .setCode("STPA");
	                stopRepository.save(stopA);
	            }

	            Stop stopB = stopRepository.findStopByCode("STPB");
	            if (stopB == null) {
	                stopB = new Stop()
	                        .setName("Stop B")
	                        .setDetail("Near river")
	                        .setCode("STPB");
	                stopRepository.save(stopB);
	            }

	            Stop stopC = stopRepository.findStopByCode("STPC");
	            if (stopC == null) {
	                stopC = new Stop()
	                        .setName("Stop C")
	                        .setDetail("Near desert")
	                        .setCode("STPC");
	                stopRepository.save(stopC);
	            }

	            Stop stopD = stopRepository.findStopByCode("STPD");
	            if (stopD == null) {
	                stopD = new Stop()
	                        .setName("Stop D")
	                        .setDetail("Near lake")
	                        .setCode("STPD");
	                stopRepository.save(stopD);
	            }

	            //Create an Agency
	            Agency agencyA = agencyRepository.findAgencyByName("AGENCY-A");
	            if (agencyA == null) {
	                agencyA = new Agency()
	                        .setName("Green Mile Agency")
	                        .setCode("AGENCY-A")
	                        .setDetails("Reaching desitnations with ease")
	                        .setUser(admin);
	                agencyRepository.save(agencyA);
	            }

	            //Create a bus
	            Bus busA = busRepository.findBusByCode("AGENCY-A-1");
	            if (busA == null) {
	                busA = new Bus()
	                        .setCode("AGENCY-A-1")
	                        .setAgency(agencyA)
	                        .setCapacity(60);
	                busRepository.save(busA);
	            }

	            //Add busA to set of buses owned by Agency 'AGENCYA'
	            if (agencyA.getBuses() == null) {
	                Set<Bus> buses = new HashSet<>();
	                agencyA.setBuses(buses);
	                agencyA.getBuses().add(busA);
	                agencyRepository.save(agencyA);
	            }

	            //Create a Trip
	            Trip trips = tripRepository.findBySourceStopAndDestStopAndBus(stopA, stopB, busA);
	            if (trips == null) {
	             trips= new Trip()
	                        .setSourceStop(stopA)
	                        .setDestStop(stopB)
	                        .setBus(busA)
	                        .setAgency(agencyA)
	                        .setFare(100)
	                        .setJourneyTime(60);
	                tripRepository.save(trips);
	              
	            }

	            //Create a trip schedule
	            TripSchedule tripSchedule = tripScheduleRepository.findTripScheduleByTripDetailAndTripDate(trips, DateUtils.todayStr());
	            if (tripSchedule == null) {
	                tripSchedule = new TripSchedule()
	                        .setTripDetail(trips)
	                        .setTripDate(DateUtils.todayStr())
	                        .setAvailableSeats(trips.getBus().getCapacity());
	                tripScheduleRepository.save(tripSchedule);
	            }
	        };
	    }
}
