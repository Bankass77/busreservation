package com.busreseravtionsystem.busreservation.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.busreseravtionsystem.busreservation.dto.user.RoleDto;
import com.busreseravtionsystem.busreservation.dto.user.UserDto;
import com.busreseravtionsystem.busreservation.service.user.UserService;

@Service("customUserDetailsService")
public class CustomuserDetailService implements UserDetailsService {

	private final UserService userService;

	@Autowired
	public CustomuserDetailService(UserService userService) {
		super();
		this.userService = userService;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserDto userDto = userService.findUserByEmail(email);

		if (userDto !=null) {

			List<GrantedAuthority> authorities = getUserAuthority(userDto.getRolesDtos());

			return buildUserForAuthentication(userDto, authorities);
		}else {
		throw new UsernameNotFoundException("user with email" + email + "does not exist.");
	}
	}
	
	private List<GrantedAuthority> getUserAuthority(Set<RoleDto> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        userRoles.forEach((role) -> {
            roles.add(new SimpleGrantedAuthority(role.getName()));
            
        });
        return new ArrayList<GrantedAuthority>(roles);
        
    }

    private UserDetails buildUserForAuthentication(UserDto user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

	/*
	 * public void autologin(String email, String password) {
	 * 
	 * if(email==null) {
	 * 
	 * throw new IllegalArgumentException("le username ne peut être null"); }
	 * 
	 * if( password ==null) {
	 * 
	 * throw new IllegalArgumentException("password ne peut être null  "); } UserDto
	 * userDetails = userService.findUserByEmail(email);
	 * UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new
	 * UsernamePasswordAuthenticationToken( userDetails, password,
	 * AuthorityUtils.createAuthorityList(UserRole.ADMIN.getValue()));
	 * 
	 * authenticationManager.authenticate(usernamePasswordAuthenticationToken);
	 * 
	 * if (usernamePasswordAuthenticationToken.isAuthenticated()) {
	 * SecurityContextHolder.getContext().setAuthentication(
	 * usernamePasswordAuthenticationToken); } }
	 */

}
