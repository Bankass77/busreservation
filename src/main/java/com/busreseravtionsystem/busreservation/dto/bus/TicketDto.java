package com.busreseravtionsystem.busreservation.dto.bus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketDto {

	private long id;

	private String busCode;

	private int seatNumber;

	private boolean cancellable;

	private String journeyDate;

	private String destinationStop;

	private String sourceStop;

	private String passengerName;
	private String passengerMobileNumber;

}
