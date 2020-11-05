package com.busreseravtionsystem.busreservation.controller.v1.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AgencyFormCommand {
	
	@NotBlank
	@Size(min = 5, max =100)
	private String agencyName;
	
	@NotBlank
	@Size(max = 100)
	private String agencyDetails;

}
