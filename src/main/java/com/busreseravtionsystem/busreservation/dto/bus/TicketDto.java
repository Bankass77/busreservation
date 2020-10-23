package com.busreseravtionsystem.busreservation.dto.bus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Getter
@Setter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketDto {

	private String id;

	private String busCode;

	private int seatNumber;

	private boolean cancellable;

	private String journeyDate;

	private String destinationStop;

	private String sourceStop;

	private String passengerName;
	private String passengerMobileNumber;
	
}
