package com.busreseravtionsystem.busreservation.controller.v1.request;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetripSchedulesRequest {

	@NotEmpty(message = "{constraints.NoEmpty.message}")
	private String sourceStop;

	@NotEmpty(message = "{constraints.NoEmpty.message}")
	private String arrivalStop;

	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
	private Date tripDate;

}
