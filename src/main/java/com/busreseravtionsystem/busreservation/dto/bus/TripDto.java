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
	public void setId(String id) {
		this.id = id;
	}
	public int getFare() {
		return fare;
	}
	public void setFare(int fare) {
		this.fare = fare;
	}
	public int getJourneyTime() {
		return journeyTime;
	}
	public void setJourneyTime(int journeyTime) {
		this.journeyTime = journeyTime;
	}
	public String getSourceStopCode() {
		return sourceStopCode;
	}
	public void setSourceStopCode(String sourceStopCode) {
		this.sourceStopCode = sourceStopCode;
	}
	public String getDestinationStopCode() {
		return destinationStopCode;
	}
	public void setDestinationStopCode(String destinationStopCode) {
		this.destinationStopCode = destinationStopCode;
	}
	public String getDestinationStopName() {
		return destinationStopName;
	}
	public void setDestinationStopName(String destinationStopName) {
		this.destinationStopName = destinationStopName;
	}
	public String getBusCode() {
		return busCode;
	}
	public void setBusCode(String busCode) {
		this.busCode = busCode;
	}
	public String getAgencyCode() {
		return agencyCode;
	}
	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}

}
