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
public class StopDto implements Comparable<Object> {

	private String code;

	private String name;

	private String details;

	@Override
	public int compareTo(Object arg0) {

		return this.getName().compareTo(((StopDto) arg0).getName());
	}

}
