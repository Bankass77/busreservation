package com.busreseravtionsystem.busreservation.service.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import com.busreseravtionsystem.busreservation.dto.mapper.UserMapper;
import com.busreseravtionsystem.busreservation.dto.user.UserDto;
import com.busreseravtionsystem.busreservation.exception.BSRExecption;
import com.busreseravtionsystem.busreservation.exception.EntityType;
import com.busreseravtionsystem.busreservation.exception.ExceptionType;
import com.busreseravtionsystem.busreservation.model.user.Role;
import com.busreseravtionsystem.busreservation.model.user.User;
import com.busreseravtionsystem.busreservation.model.user.UserRole;
import com.busreseravtionsystem.busreservation.repository.user.RoleRepository;
import com.busreseravtionsystem.busreservation.repository.user.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional
	public UserDto signup(UserDto userDto) {

		Role userRole = new Role();

		User userOptional = userRepository.findByEmail(userDto.getEmail());

		if (userOptional == null) {

			if (userDto.isAdmin()) {

				userRole = roleRepository.findByRole(UserRole.ADMIN);
			} else {
				userRole = roleRepository.findByRole(UserRole.PASSENGER);
			}
			User user = new User().setEmail(userDto.getEmail()).setFirstName(userDto.getFirstName())
					.setLastName(userDto.getLastName()).setMobileNumber(userDto.getMobileNumber())
					.setPassword(userDto.getPassword()).setRoles(new HashSet<>(Arrays.asList(userRole)));

			return UserMapper.userDto(userRepository.save(user));
		}
		throw exception(EntityType.USER, ExceptionType.DUPLICATE_ENTITY, userDto.getEmail());
	}

	@Override
	@Transactional
	public UserDto findUserByEmail(String email) {

		Optional<User> usOptional = Optional.ofNullable(userRepository.findByEmail(email));

		if (usOptional.isPresent()) {

			return modelMapper.map(usOptional.get(), UserDto.class);
		}
		throw exception(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND, email);
	}

	@Override
	@Transactional
	public UserDto updteProfile(UserDto userDto) {

		Optional<User> uOptional = Optional.ofNullable(userRepository.findByEmail(userDto.getEmail()));

		if (uOptional.isPresent()) {

			User user = uOptional.get().setEmail(userDto.getEmail()).setFirstName(userDto.getFirstName())
					.setLastName(userDto.getLastName()).setMobileNumber(userDto.getMobileNumber());

			return UserMapper.userDto(userRepository.save(user));
		}

		throw exception(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND, userDto.getEmail());
	}

	@Override
	@Transactional
	public UserDto changePassword(UserDto userDto, String newPassword) {

		Optional<User> userChangepassword = Optional.ofNullable(userRepository.findByEmail(userDto.getEmail()));

		if (userChangepassword.isPresent()) {

			User password = userChangepassword.get().setPassword(passwordEncoder.encode(newPassword));
			return UserMapper.userDto(userRepository.save(password));

		}

		throw exception(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND, userDto.getEmail());
	}

	/**
	 * @param entityType
	 * @param exceptionType
	 * @param arg
	 * @return
	 */
	private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... arg) {

		return BSRExecption.throwException(entityType, exceptionType, arg);

	}

	@Override
	@Transactional
	public List<UserDto> getAllUsers() {

		return StreamSupport.stream(userRepository.findAll().spliterator(), false).distinct()
				.map(users -> (modelMapper.map(users, UserDto.class))).collect(Collectors.toCollection(ArrayList::new));
	}

}
