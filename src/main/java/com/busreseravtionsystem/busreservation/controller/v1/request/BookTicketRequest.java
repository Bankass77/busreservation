package com.busreseravtionsystem.busreservation.controller.v1.request;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookTicketRequest {
	@NotEmpty(message = "{constraints.NotEmpty.message}")
	private Long tripId;

	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date tripDate;

}
