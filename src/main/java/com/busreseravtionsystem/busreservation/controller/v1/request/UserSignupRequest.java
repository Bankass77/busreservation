package com.busreseravtionsystem.busreservation.controller.v1.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class UserSignupRequest {

	@NotEmpty(message = "{constraints.NoEmpty.message}")
	@NotBlank
	@NonNull
	private String email;

	@NotEmpty(message = "{constraints.NoEmpty.message}}")
	@NonNull
	@NotBlank
	private String firstName;

	@NonNull
	@NotBlank
	@NotEmpty(message = "{constraints.NoEmpty.message}}")
	String lastname;

	private String mobileNumber;

}
