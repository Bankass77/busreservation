/**package com.busreseravtionsystem.busreservation.controller.v1.api;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;

import com.busreseravtionsystem.busreservation.dto.user.UserDto;
import com.busreseravtionsystem.busreservation.model.user.Role;
import com.busreseravtionsystem.busreservation.model.user.User;
import com.busreseravtionsystem.busreservation.model.user.UserRole;
import com.busreseravtionsystem.busreservation.repository.user.RoleRepository;
import com.busreseravtionsystem.busreservation.security.MultiHttpSecurityConfig;
import com.busreseravtionsystem.busreservation.service.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterChainProxy;

import static org.mockito.BDDMockito.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UserController.class)
@ActiveProfiles("dev")
@Import(MultiHttpSecurityConfig.class)
class UserControllerTest {
	
	 @Autowired
	    public WebApplicationContext context;

	    @Autowired
	    private FilterChainProxy springSecurityFilter;
	    
	    @BeforeEach
	    public void setup() {
	    	Authentication authentication = Mockito.mock(Authentication.class);
	    	// Mockito.whens() for your authorization object
	    	SecurityContext securityContext = Mockito.mock(SecurityContext.class);
	    	Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
	    	SecurityContextHolder.setContext(securityContext);
	        assertNotNull(context);
	        assertNotNull(springSecurityFilter);
	        // Process mock annotations
	        MockitoAnnotations.initMocks(this);
	        // Setup Spring test in webapp-mode (same config as spring-boot)
	        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
	                .addFilters(springSecurityFilter)
	                .build();
	        context.getServletContext().setAttribute(
	            WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, context);
	    }

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@Autowired
	private RoleRepository repository;

	@Autowired
	private ObjectMapper objectMapper;


	@Test
	void signupUserTest() throws Exception {

		given(userService.signup(any(UserDto.class))).willAnswer((invocation) -> invocation.getArgument(0));

		UserDto userDto = new UserDto();
		userDto.setAdmin(true);
		userDto.setEmail("admin2@example.com");
		userDto.setFirstName("admin2");
		userDto.setLastName("admin2");
		userDto.setMobileNumber("0768965423");
		userDto.setPassword("admin2");
		Role userRole = new Role();
		userRole.setRole(UserRole.ADMIN) ;
		User user = new User().setEmail(userDto.getEmail()).setFirstName(userDto.getFirstName())
				.setLastName(userDto.getLastName()).setMobileNumber(userDto.getMobileNumber())
				.setPassword(userDto.getPassword()).setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		this.mockMvc
				.perform(post("/api/v1/user/signup").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(userDto)))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.email", is(userDto.getEmail())))
				.andExpect(jsonPath("$.password", is(userDto.getPassword())))
				.andExpect(jsonPath("$.fisrtName", is(userDto.getFirstName())))
				.andExpect(jsonPath("$.lastName", is(userDto.getLastName())))
				.andExpect(jsonPath("$.mobilNumber", is(userDto.getMobileNumber())));
		

	}
	
	
}**/

