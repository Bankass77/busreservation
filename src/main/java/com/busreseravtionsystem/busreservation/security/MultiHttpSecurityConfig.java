package com.busreseravtionsystem.busreservation.security;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
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
import com.busreseravtionsystem.busreservation.security.api.ApiJWTAuthorizationFilter;
import com.busreseravtionsystem.busreservation.security.form.CustomAuthentionSuccesshandler;
import com.busreseravtionsystem.busreservation.security.form.CustomLogoutSuccessHandler;

@Configuration
public class MultiHttpSecurityConfig {

	private static final String AUTHORITIES_QUERY = "SELECT u.email, r.role FROM bankass.users u "
			+ "INNER JOIN bankass.users_role ur ON u.users_id = ur.users_role_id "
			+ "INNER JOIN bankass.role r ON ur.users_role_id = r.role_id" + "WHERE u.email=?";
	private static final String USERS_QUERY = "SELECT email FROM bankass.users WHERE email=?";

	@Configuration
	@Order(1)
	@EnableWebSecurity(debug = true)
	@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, proxyTargetClass = true)
	public static class ApiWebSecurityConfigAdapter extends WebSecurityConfigurerAdapter {

		@Autowired
		private BCryptPasswordEncoder passwordEncoder;

		@Autowired
		private CustomuserDetailsService userDetailsService;

		//private final DataSource dataSource;
		/*
		 * public ApiWebSecurityConfigAdapter(final DataSource dataSource) {
		 * this.dataSource = dataSource; }
		 */

		@Override
		protected void configure(AuthenticationManagerBuilder builderAuth) throws Exception {
			builderAuth/*
						 * .jdbcAuthentication().authoritiesByUsernameQuery(AUTHORITIES_QUERY)
						 * .usersByUsernameQuery(USERS_QUERY).dataSource(dataSource).and()
						 */
					.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder);
		}

		public void configure(HttpSecurity http) throws Exception {

			http.csrf().disable().antMatcher("/api/*").authorizeRequests().antMatchers("/api/v1/user/signup")
					.permitAll().anyRequest().authenticated().and().exceptionHandling()
					.authenticationEntryPoint((req, rsq, e) -> rsq.sendError(HttpServletResponse.SC_UNAUTHORIZED)).and()
					.addFilter(new ApiJWTAuthenticationFilter(authenticationManager()))
					.addFilter(new ApiJWTAuthorizationFilter(authenticationManager())).sessionManagement()
					// This diseables session creation on Spring Security
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		}

	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;

	}

	/*
	 * @Bean public CorsConfigurationSource corsConfigurationSource() {
	 * 
	 * final UrlBasedCorsConfigurationSource source = new
	 * UrlBasedCorsConfigurationSource();
	 * 
	 * CorsConfiguration corsConfiguration = new
	 * CorsConfiguration().applyPermitDefaultValues();
	 * source.registerCorsConfiguration("/**", corsConfiguration); return source; }
	 */

	@Order(2)
	@Configuration
	public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

		private BCryptPasswordEncoder bCryptPasswordEncoder;
		private CustomAuthentionSuccesshandler customAuthentionSuccesshandler;
		private CustomuserDetailsService customuserDetailsService;

		@Autowired
		public FormLoginWebSecurityConfigurerAdapter(BCryptPasswordEncoder bCryptPasswordEncoder,
				CustomAuthentionSuccesshandler customAuthentionSuccesshandler,
				CustomuserDetailsService customuserDetailsService) {
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
					.antMatchers("/signup").permitAll().antMatchers("/dashbord/**").hasAuthority("ADMIN").anyRequest()
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
