package com.busreseravtionsystem.busreservation.service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.busreseravtionsystem.busreservation.dto.user.UserDto;

@Service("userService")
public interface UserService {

	/**
	 * 
	 * @param userDto
	 * @return new User register
	 */
	UserDto signup(UserDto userDto);

	/**
	 * 
	 * 
	 * @param email
	 * @return user by Email
	 */
	UserDto findUserByEmail(String email);

	/**
	 * 
	 * update user profile
	 * 
	 * @param userDto
	 * @return
	 */
	UserDto updteProfile(UserDto userDto);

	/**
	 * Change user password
	 * 
	 * @param userDto
	 * @param newPassword
	 * @return
	 */
	UserDto changePassword(UserDto userDto, String newPassword);

	List<UserDto> getAllUsers();
}
