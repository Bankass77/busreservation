package com.busreseravtionsystem.busreservation.controller.v1.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.busreseravtionsystem.busreservation.controller.v1.request.UserSignupRequest;
import com.busreseravtionsystem.busreservation.dto.response.Response;
import com.busreseravtionsystem.busreservation.dto.user.UserDto;
import com.busreseravtionsystem.busreservation.service.user.UserService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/v1/user")
@Api(value = "bsr-application", description = "Operations pertaining to user management in the BRS application")
public class UserController {

	@Qualifier("userService")
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	/**
	 * @param userSignupRequest
	 * @return
	 */
	@PostMapping("/signup")
	@PreAuthorize("hasRole('ADMIN')")
	private Response signup(@RequestBody @Valid UserSignupRequest userSignupRequest) {

		return Response.ok().setPayload(registerUser(userSignupRequest, false));

	}

	/**
	 * @param userSignupRequest
	 * @param isAdmin
	 * @return
	 */
	private UserDto registerUser(@Valid UserSignupRequest userSignupRequest, boolean isAdmin) {

		UserDto userDto = new UserDto().setEmail(userSignupRequest.getEmail())
				.setPassword(userSignupRequest.getPassword())
				.setFirstName(userSignupRequest.getFirstName()).setLastName(userSignupRequest.getLastname())
				.setMobileNumber(userSignupRequest.getMobileNumber()).setAdmin(isAdmin);
		return userService.signup(userDto);
	}
}
