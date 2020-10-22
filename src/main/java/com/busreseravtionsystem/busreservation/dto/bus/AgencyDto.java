package com.busreseravtionsystem.busreservation.dto.bus;

import java.util.Set;

import com.busreseravtionsystem.busreservation.dto.user.UserDto;
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
public class AgencyDto {
	
	
	private String code;
	
	private UserDto userOwner;
	
	private Set<BusDto> busDtos;
	
	private String name;
	
	private String details;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public UserDto getUserOwner() {
		return userOwner;
	}

	public void setUserOwner(UserDto userOwner) {
		this.userOwner = userOwner;
	}

	public Set<BusDto> getBusDtos() {
		return busDtos;
	}

	public void setBusDtos(Set<BusDto> busDtos) {
		this.busDtos = busDtos;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}


}
