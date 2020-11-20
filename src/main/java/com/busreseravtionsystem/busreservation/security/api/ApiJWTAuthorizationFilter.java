
package com.busreseravtionsystem.busreservation.security.api;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.busreseravtionsystem.busreservation.security.SecurityConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class ApiJWTAuthorizationFilter extends BasicAuthenticationFilter {

	public ApiJWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);

	}

	/**
	 * 
	 * doFilterInternal method intercepts the request then checks the Authorization
	 * header. If the header is not present or doesn't start with"Bearer", it
	 * proceeeds to the filter chain.
	 */

	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {

		String header = req.getHeader(SecurityConstants.HEADER_STRING);
		if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			filterChain.doFilter(req, res);
			return;
		}
		UsernamePasswordAuthenticationToken authenticationToken = getAuthencation(req); // The new Token is then saved
																						// to SeccurityContext.
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		filterChain.doFilter(req, res);
	}

	/**
	 * If the header is present, the getAuthentication mothod is invoked. It
	 * verifies the JWT, and if the token is valid, it returns an access token which
	 * Spring wille use internally. Reads the JWTs from the Authorization header,
	 * and the uses JWT to validate the token
	 * 
	 * @param req
	 * @return
	 */
	private UsernamePasswordAuthenticationToken getAuthencation(HttpServletRequest req) {

		String token = req.getHeader(SecurityConstants.HEADER_STRING);
		if (token != null) {
			Claims claims = Jwts.parser().setSigningKey(SecurityConstants.SECRET)
					.parseClaimsJws(token.replaceAll(SecurityConstants.TOKEN_PREFIX, "")).getBody();

			// On extrait le user
			String user = claims.getSubject();
			// on extrait le role
			@SuppressWarnings("unchecked")
			ArrayList<String> roles = (ArrayList<String>) claims.get("roles");

			// Ensuite on converti le Role GrantedAuthority object pour l'injecter
			ArrayList<GrantedAuthority> authorities = new ArrayList<>();

			if (roles != null) {
				for (String newRole : roles) {

					GrantedAuthority authority = new SimpleGrantedAuthority(newRole);
					authorities.add(authority);
				}
			}
			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, authorities);
			}
		}
		return null;
	}
}
