package com.busreseravtionsystem.busreservation.dto.user;

import java.util.Set;

import com.busreseravtionsystem.busreservation.model.user.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Getter
@Setter
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class UserDto {

	private String firstName;

	private String lastName;

	private String email;

	private String password;

	private String mobileNumber;

	private boolean isAdmin;
	private Set<Role> roles;

}
