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
public class TripScheduleDto {
	
	private String id;
	
	private String tripId;
	private String StripDate;
	private int availableSeats;
	private int fare;
	private int journeyTime;
	private String busCode;
	private String sourceStop;
	private String destinationStop;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTripId() {
		return tripId;
	}
	public void setTripId(String tripId) {
		this.tripId = tripId;
	}
	public String getStripDate() {
		return StripDate;
	}
	public void setStripDate(String stripDate) {
		StripDate = stripDate;
	}
	public int getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
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
	public String getBusCode() {
		return busCode;
	}
	public void setBusCode(String busCode) {
		this.busCode = busCode;
	}
	public String getSourceStop() {
		return sourceStop;
	}
	public void setSourceStop(String sourceStop) {
		this.sourceStop = sourceStop;
	}
	public String getDestinationStop() {
		return destinationStop;
	}
	public void setDestinationStop(String destinationStop) {
		this.destinationStop = destinationStop;
	}
	

}
