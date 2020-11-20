package com.busreseravtionsystem.busreservation.dto.user;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import com.busreseravtionsystem.busreservation.dto.mapper.UserMapper;
import com.busreseravtionsystem.busreservation.model.user.Role;
import com.busreseravtionsystem.busreservation.model.user.User;
import com.busreseravtionsystem.busreservation.model.user.UserRole;

class UserDtoTest {
	
	ModelMapper modelMapper = new ModelMapper();
	Role userRole= new Role().setRole( UserRole.ADMIN);
	User  user= new User().setEmail("admin@gmail.com")
			.setFirstName("admin")
			.setLastName("admin")
			.setMobileNumber("0678953412")
			.setPassword("admin")
			.setRoles(new HashSet<Role> (Arrays.asList(userRole)));
	
	
	UserDto userDto= modelMapper.map(user, UserDto.class);

	@Test
	void test() {
		assertEquals(user.getEmail(), userDto.getEmail());
		assertEquals(user.getFirstName(), userDto.getFirstName());
		assertEquals(user.getLastName(), userDto.getLastName());
		assertEquals(user.getMobileNumber(), userDto.getMobileNumber());
		assertEquals(user.getPassword(), userDto.getPassword());
		assertEquals(user.getMobileNumber(), userDto.getMobileNumber());
		assertNotNull(UserMapper.userDto(user));
		//assertEquals(user.getRoles(), userDto.getRolesDtos());
	}

}
