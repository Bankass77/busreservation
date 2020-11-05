package com.busreseravtionsystem.busreservation.controller.v1.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TripFormCommand {
	
	@NotBlank
	private String sourceStop;
	
	@NotBlank
	private String arrivalStop;
	
	@NotBlank
	private String busCode;
	
	@Positive
	private int tripDuration;
	
	@Positive
	private int tripFare;

}
