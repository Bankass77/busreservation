package com.busreseravtionsystem.busreservation.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class MultiHttpSecurityConfig {
	
	@Configuration
	@Order(1)
	public static class ApiWebSecurityConfigAdapter extends WebSecurityConfigurerAdapter {
		
		@Autowired
		private BCryptPasswordEncoder passwordEncoder;
		
		@Autowired
		private CustomuserDetailsService userDetailsService;
		
		
		@Override
		protected void configure(AuthenticationManagerBuilder builderAuth) throws Exception{
			builderAuth.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder);
		}
		 
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		
		BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
		
	}

}
