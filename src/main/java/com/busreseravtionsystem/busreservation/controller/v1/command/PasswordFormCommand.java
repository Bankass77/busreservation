package com.busreseravtionsystem.busreservation.controller.v1.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PasswordFormCommand {
	
	@NotBlank
	@Size(max = 12, min = 5)
	private String password;
	
	private String email;

}
