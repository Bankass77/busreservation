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
public class TripScheduleDto {

	private long tripId;
	private String StripDate;
	private int availableSeats;
	private int fare;
	private int journeyTime;
	private String busCode;
	private String sourceStop;
	private String destinationStop;

}
