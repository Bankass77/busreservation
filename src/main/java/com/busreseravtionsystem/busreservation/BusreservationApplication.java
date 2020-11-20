package com.busreseravtionsystem.busreservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaAuditing
@Slf4j
public class BusreservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusreservationApplication.class, args);
		log.info("Bus reservation System started",BusreservationApplication.class);
	
	/**

@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Bean
	CommandLineRunner init(RoleRepository roleRepository, UserRepository userRepository, StopRepository stopRepository,
			AgencyRepository agencyRepository, BusRepository busRepository, TripRepository tripRepository,
			TripScheduleRepository tripScheduleRepository, UserService userService) {
		return args -> { // Create Admin and Passenger Role
			
			
			Role adminRole = roleRepository.findByRole(UserRole.ADMIN);
			if (adminRole == null) {
				adminRole = new Role();
				adminRole.setRole(UserRole.ADMIN);
				roleRepository.save(adminRole);
			}

			// roles.add(adminRole);
			Role userRole = roleRepository.findByRole(UserRole.PASSENGER);
			if (userRole == null) {
				userRole = new Role();
				userRole.setRole(UserRole.PASSENGER);
				roleRepository.save(userRole);
			}

			// Create a first Admin user
			User admin = userRepository.findByEmail("admin@gmail.com");
			if (admin == null) {
				admin = new User().setEmail("admin@gmail.com").setPassword(bCryptPasswordEncoder.encode("admin1")) // "123456"
						.setFirstName("John").setLastName("Doe").setMobileNumber("9425094250")
						.setRoles(new HashSet<Role>(Arrays.asList(adminRole))); // .setRoles(Arrays.asList(adminRole));
				//userRepository.save(admin);
				Set<RoleDto> roleDtos= new HashSet<>();
				roleDtos.add(new RoleDto().setName(admin.getRoles().toString()));
				UserDto userDto = new UserDto().setAdmin(true)
						.setEmail(admin.getEmail())
						.setFirstName(admin.getFirstName())
						.setLastName(admin.getLastName())
						.setMobileNumber(admin.getMobileNumber())
						.setPassword(admin.getPassword())
						.setRolesDtos(roleDtos);
				userService.signup(userDto );
			}


			/*
			 * // Crete a second admin user
			 * 
			 * User admin2 = userRepository.findByEmail("admin2@example.com");
			 * 
			 * if (admin2 == null) {
			 * 
			 * admin2 = new
			 * User().setEmail("admin2@example.com").setFirstName("Boubacar").setLastName(
			 * "Guindo")
			 * .setMobileNumber("0789563445").setPassword(bCryptPasswordEncoder.encode(
			 * "admin2")) .setRoles(new HashSet<Role>(Arrays.asList(adminRole)));
			 * 
			 * userService.signup(UserMapper.userDto(admin2)); }
			 */

			
	


	}

}
