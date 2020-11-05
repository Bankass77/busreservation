package com.busreseravtionsystem.busreservation.controller.v1.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProfileFormCommand {

	@NotBlank
	@Size(min = 1, max = 40)
	private String firstName;
	
	@NotBlank
	private String lastname;
	
	@NotBlank
	@Size(max = 13, min = 10)
	private String mobileNumber;
	
}
