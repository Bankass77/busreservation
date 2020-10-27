package com.busreseravtionsystem.busreservation.dto.mapper;

import com.busreseravtionsystem.busreservation.dto.user.UserDto;
import com.busreseravtionsystem.busreservation.model.user.User;

public class UserMapper {

	
	public static UserDto userDto (User user) {
		return new UserDto().setAdmin(false)
				.setEmail(user.getEmail())
				.setFirstName(user.getFirstName())
				.setLastName(user.getLastName())
				.setMobileNumber(user.getMobileNumber())
				.setPassword(user.getPassword())
				.setRoles(user.getRoles());
		
	}
}
