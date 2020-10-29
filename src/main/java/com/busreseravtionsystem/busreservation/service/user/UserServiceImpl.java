package com.busreseravtionsystem.busreservation.service.user;

import java.util.Arrays;
import java.util.HashSet;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.busreseravtionsystem.busreservation.dto.user.UserDto;
import com.busreseravtionsystem.busreservation.model.user.Role;
import com.busreseravtionsystem.busreservation.model.user.User;
import com.busreseravtionsystem.busreservation.model.user.UserRole;
import com.busreseravtionsystem.busreservation.repository.user.RoleRepository;
import com.busreseravtionsystem.busreservation.repository.user.UserRepository;
import com.busreseravtionsystem.busreservation.service.bus.BusReservationService;


@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private  RoleRepository roleRepository;
	
	/*
	 * @Autowired private ModelMapper modelMapper;
	 */
	
	@Autowired
	private BusReservationService busReservationService;
	
	

	@Override
	public UserDto signup(UserDto userDto) {
		
		
		Role userRole = new Role();
	  User userOptional= userRepository.findByEmail(userDto.getEmail());
		if (userOptional ==null) {
		
			if (userDto.isAdmin()) {
				
				userRole =roleRepository.findByRole(UserRole.ADMIN);
			}else {
				userRole= roleRepository.findByRole(UserRole.PASSENGER);
			}
			
		}
		
	
		User user = new User()
				.setEmail(userDto.getEmail())
				.setFirstName(userDto.getFirstName())
				.setLastName(userDto.getLastName())
				.setMobileNumber(userDto.getMobileNumber())
				.setPassword(userDto.getPassword())
				.setRoles(new HashSet<>(Arrays.asList(userRole)) );
				
		return userDto;
		
	}

	@Override
	public UserDto findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto updteProfile(UserDto userDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto changePassword(UserDto userDto, String newPassword) {
		// TODO Auto-generated method stub
		return null;
	}

}
