package com.busreseravtionsystem.busreservation.dto.mapper;

import java.util.HashSet;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.busreseravtionsystem.busreservation.dto.user.RoleDto;
import com.busreseravtionsystem.busreservation.dto.user.UserDto;
import com.busreseravtionsystem.busreservation.model.user.User;

@Component
public class UserMapper {

	public static UserDto userDto(User user) {
		return new UserDto().setEmail(user.getEmail()).setFirstName(user.getFirstName())
				.setLastName(user.getLastName()).setMobileNumber(user.getMobileNumber())
				.setRolesDtos(new HashSet<RoleDto>(user.getRoles().stream()
						.map(role -> new ModelMapper().map(role, RoleDto.class)).collect(Collectors.toSet())));

	}
}
