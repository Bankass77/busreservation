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
public class TripDto {
	
	private String id;
	
	private int fare;
	private int journeyTime;
	
	private String sourceStopCode;
	
	private String destinationStopCode;
	
	private String destinationStopName;
	private String busCode;
	private String agencyCode;
	public String getId() {
		return id;
	}
	

}
