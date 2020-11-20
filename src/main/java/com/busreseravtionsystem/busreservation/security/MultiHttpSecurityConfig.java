
package com.busreseravtionsystem.busreservation.security;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.busreseravtionsystem.busreservation.security.api.ApiJWTAuthenticationFilter;
import com.busreseravtionsystem.busreservation.security.form.CustomAuthentionSuccesshandler;
import com.busreseravtionsystem.busreservation.security.form.CustomLogoutSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, proxyTargetClass = true)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MultiHttpSecurityConfig {

	@Configuration

	@Order(Ordered.HIGHEST_PRECEDENCE)
	public static class ApiWebSecurityConfigAdapter extends WebSecurityConfigurerAdapter {

		@Autowired
		private BCryptPasswordEncoder passwordEncoder;

		@Autowired
		private CustomuserDetailService userDetailsService;

		@Override
		@Bean
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}

		@Override
		protected void configure(AuthenticationManagerBuilder builderAuth) throws Exception {
			builderAuth.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder);
		}

		public void configure(HttpSecurity http) throws Exception {

			http.csrf().disable().antMatcher("/api/*").authorizeRequests().antMatchers("/api/v1/user/signup")
					.permitAll().anyRequest().authenticated().and().exceptionHandling()
					.authenticationEntryPoint((req, rsq, e) -> rsq.sendError(HttpServletResponse.SC_UNAUTHORIZED)).and()
					.addFilter(new ApiJWTAuthenticationFilter(authenticationManager()))
					.addFilter(new ApiJWTAuthenticationFilter(authenticationManager())).sessionManagement()
					// This diseables session creation on Spring Security
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		}

	}

	@Configuration
	@Order(2)
	public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

		private BCryptPasswordEncoder bCryptPasswordEncoder;
		private CustomAuthentionSuccesshandler customAuthentionSuccesshandler;
		private CustomuserDetailService customuserDetailsService;

		@Autowired
		public FormLoginWebSecurityConfigurerAdapter(BCryptPasswordEncoder bCryptPasswordEncoder,
				CustomAuthentionSuccesshandler customAuthentionSuccesshandler,
				CustomuserDetailService customuserDetailsService) {
			super();
			this.bCryptPasswordEncoder = bCryptPasswordEncoder;
			this.customAuthentionSuccesshandler = customAuthentionSuccesshandler;
			this.customuserDetailsService = customuserDetailsService;
		}

		@Override
		protected void configure(AuthenticationManagerBuilder builder) throws Exception {
			builder.userDetailsService(customuserDetailsService).passwordEncoder(bCryptPasswordEncoder);
		}

		@Override
		protected void configure(HttpSecurity httpSecurity) throws Exception {
			httpSecurity.cors().and().csrf().disable().authorizeRequests().antMatchers("/").permitAll()
					.antMatchers("/signup").permitAll().antMatchers("/dashbord/**").hasRole("ADMIN").anyRequest()
					.authenticated().and().formLogin().loginPage("/login").permitAll().failureUrl("/login?error=true")
					.usernameParameter("email").passwordParameter("password")
					.successHandler(customAuthentionSuccesshandler).and().logout().permitAll()
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.logoutSuccessHandler(new CustomLogoutSuccessHandler()).deleteCookies("JSESSIONID")
					.logoutSuccessUrl("/").and().exceptionHandling();

		}

		@Override
		public void configure(WebSecurity webSecurity) throws Exception {

			webSecurity.ignoring().antMatchers("/resources/**", "/static/**", "/js/**", "/images/**",
					"/resources/static/**", "/css/**", "/im/**", "/fonts/**", "/images/**", "/scss/**", "/vendor/**",
					"/favicon.ico", "/auth/**", "/favicon.png", "/v2/api-docs", "/configuration/ui",
					"/configuration/security", "/webjars/**", "/swagger-resources/**", "/actuator", "/swagger-ui/**",
					"/swagger-ui/index.html", "/swagger-ui/");
		}

	}

}
