package com.busreseravtionsystem.busreservation.controller.v1.command;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class AdminSignupFormCommand {
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	@Size (min=5)
  private String password;
	
	@NotBlank
	private String firstName;
	
	@NotBlank
	private String lastName;
	
	@NotBlank
	@Size(max = 200)
	private String agencyDetails;
	
	@NotBlank
	private String agencyName;
	
	@NotBlank
	@Size(min = 8, max = 10)
	private String mobileNumber;	
	

}
