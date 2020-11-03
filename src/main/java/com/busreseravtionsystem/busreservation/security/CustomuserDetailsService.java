package com.busreseravtionsystem.busreservation.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.busreseravtionsystem.busreservation.dto.user.RoleDto;
import com.busreseravtionsystem.busreservation.dto.user.UserDto;
import com.busreseravtionsystem.busreservation.service.user.UserService;


@Service("customUserDetailsService")
public class CustomuserDetailsService implements UserDetailsService {

	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<UserDto> userDto = Optional.ofNullable(userService.findUserByEmail(email));

		if (userDto.isPresent()) {

			List<GrantedAuthority> authorities = getUserAuthority(userDto.get().getRolesDtos());
			return buildUserForAuthentication(userDto, authorities);
		}
		throw new UsernameNotFoundException("user with email" + email + "does not exist.");
	}

	private UserDetails buildUserForAuthentication(Optional<UserDto> userDto, List<GrantedAuthority> authorities) {
		
		return new User(userDto.get().getEmail(), userDto.get().getPassword(), authorities);
	}

	private List<GrantedAuthority> getUserAuthority(Set<RoleDto> rolesDtos) {
		
		Set<GrantedAuthority> authorities= new HashSet<GrantedAuthority> ();
		rolesDtos.forEach((role)->{
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		});
		return new ArrayList<GrantedAuthority>(authorities) ;
	}

}
