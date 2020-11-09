package com.busreseravtionsystem.busreservation.config;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@RestController
@RequestMapping("/api")
@Api(value = "bsr-application", description = "Operation pertaining to user login and logout in the BSR application")
public class FakeController {

	@ApiOperation("Login")
	@PostMapping("/auth")
	public void fakeLogin(@RequestBody @Valid LoginRequest loginRequest) {

		
		throw  new  IllegalStateException("This method shouldn't be called. It's implemented by spring security filters.");
	}

	
	@ApiOperation(value = "Logout")
	@PostMapping(value = "/logout")
	private void logout() {
		
		throw new IllegalStateException("This method shouldn't called. It's implemented by spring security filters.");
	}
	@Setter
	@Getter
	@Accessors(chain = true)
	@JsonIgnoreProperties(ignoreUnknown = true)
	private static class LoginRequest {

		@NotEmpty
		@Email
		private String email;

		@NotEmpty
		@Size(min = 5)
		private String password;

	}
}
