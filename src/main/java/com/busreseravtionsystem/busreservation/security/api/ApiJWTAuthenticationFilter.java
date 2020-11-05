package com.busreseravtionsystem.busreservation.security.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.busreseravtionsystem.busreservation.model.user.User;
import com.busreseravtionsystem.busreservation.security.SecurityConstants;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class ApiJWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	public ApiJWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
		this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/auth", "POST"));
	}

	
	/**
	 * 
	 * Return an Authentication object that contains the autorities
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {

		try {
			User user = new ObjectMapper().readValue(req.getInputStream(), User.class);
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(), new ArrayList<>()));
		} catch (Exception e) {
			throw new RuntimeException();
		}

	}

	protected void succesfullAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain filter,
			Authentication auth) throws Exception {

		if (auth.getPrincipal() != null) {
			org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) auth
					.getPrincipal();
			String login = user.getUsername();
			if (login != null && login.length() > 0) {
				Claims claims = Jwts.claims().setSubject(login);
				List<String> roles = new ArrayList<>();
				user.getAuthorities().stream().forEach(authority -> roles.add(authority.getAuthority()));
				claims.put("roles", roles);

				// create token for user
				String token = Jwts.builder().setClaims(claims)
						.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIMES))
						.signWith(SignatureAlgorithm.HS256, SecurityConstants.SECRET.getBytes()).compact();
				res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
				String body = ((User) auth.getPrincipal()).getFullName() + " " + token;
				res.getWriter().write(body);
				res.getWriter().flush();
			}
		}
	}
}
