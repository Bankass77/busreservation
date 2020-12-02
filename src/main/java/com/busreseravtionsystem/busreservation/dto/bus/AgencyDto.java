package com.busreseravtionsystem.busreservation.dto.bus;

import java.util.Set;

import com.busreseravtionsystem.busreservation.dto.user.UserDto;
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
public class AgencyDto {

	private String code;

	private UserDto userOwner;

	private Set<BusDto> bus;

	private String name;

	private String details;

}
