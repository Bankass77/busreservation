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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBusCode() {
		return busCode;
	}
	public void setBusCode(String busCode) {
		this.busCode = busCode;
	}
	public int getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}
	public boolean isCancellable() {
		return cancellable;
	}
	public void setCancellable(boolean cancellable) {
		this.cancellable = cancellable;
	}
	public String getJourneyDate() {
		return journeyDate;
	}
	public void setJourneyDate(String journeyDate) {
		this.journeyDate = journeyDate;
	}
	public String getDestinationStop() {
		return destinationStop;
	}
	public void setDestinationStop(String destinationStop) {
		this.destinationStop = destinationStop;
	}
	public String getSourceStop() {
		return sourceStop;
	}
	public void setSourceStop(String sourceStop) {
		this.sourceStop = sourceStop;
	}
	public String getPassengerName() {
		return passengerName;
	}
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	public String getPassengerMobileNumber() {
		return passengerMobileNumber;
	}
	public void setPassengerMobileNumber(String passengerMobileNumber) {
		this.passengerMobileNumber = passengerMobileNumber;
	}


}
