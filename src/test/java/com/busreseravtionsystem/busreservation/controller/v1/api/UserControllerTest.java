/**
  package com.busreseravtionsystem.busreservation.controller.v1.api;
  
  import java.util.ArrayList; import java.util.Arrays; import
  java.util.HashSet; import java.util.List;
  
  import org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.Test;
  import org.springframework.beans.factory.annotation.Autowired; import
  org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest; import
  org.springframework.boot.test.mock.mockito.MockBean; import
  org.springframework.test.context.ActiveProfiles; import
  org.springframework.test.web.servlet.MockMvc; import static
  org.mockito.ArgumentMatchers.any; import
  com.busreseravtionsystem.busreservation.dto.user.UserDto; import
  com.busreseravtionsystem.busreservation.model.user.Role; import
  com.busreseravtionsystem.busreservation.model.user.User; import
  com.busreseravtionsystem.busreservation.model.user.UserRole; import
  com.busreseravtionsystem.busreservation.repository.user.RoleRepository;
  import com.busreseravtionsystem.busreservation.service.user.UserService;
  import com.fasterxml.jackson.databind.ObjectMapper;
  
  import org.springframework.http.MediaType; 
  
  import static org.mockito.BDDMockito.given; import static
  org.hamcrest.CoreMatchers.is;
  
  import static
  org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; import
  static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
  
  @WebMvcTest(controllers = UserController.class)
  
  @ActiveProfiles("test") class UserControllerTest {
  
  @Autowired private MockMvc mockMvc;
  
  @MockBean private UserService userService;
  
  @Autowired private RoleRepository repository;
  
  @Autowired private ObjectMapper objectMapper;
  
  private List<User> users;
  
  @BeforeEach void setUp() throws Exception {
  
  users = new ArrayList<User>(); UserDto userDto = new UserDto();
  userDto.setAdmin(true); userDto.setEmail("admin2@example.com");
  userDto.setFirstName("admin2"); userDto.setLastName("admin2");
  userDto.setMobileNumber("0768965423"); userDto.setPassword("admin2"); Role
  userRole = new Role(); userRole = repository.findByRole(UserRole.ADMIN);
  
  users.add(new
  User().setEmail(userDto.getEmail()).setFirstName(userDto.getFirstName())
  .setLastName(userDto.getLastName()).setMobileNumber(userDto.getMobileNumber()
  ) .setPassword(userDto.getPassword()).setRoles(new
  HashSet<Role>(Arrays.asList(userRole)))); userService.updteProfile(userDto);
  userService.signup(userDto); }
  
  @Test void signupUserTest() throws Exception {
  
  given(userService.signup(any(UserDto.class))).willAnswer((invocation) ->
  invocation.getArgument(0));
  
  UserDto userDto = new UserDto(); userDto.setAdmin(true);
  userDto.setEmail("admin2@example.com"); userDto.setFirstName("admin2");
  userDto.setLastName("admin2"); userDto.setMobileNumber("0768965423");
  userDto.setPassword("admin2"); Role userRole = new Role(); userRole =
  repository.findByRole(UserRole.ADMIN); User user = new
  User().setEmail(userDto.getEmail()).setFirstName(userDto.getFirstName())
  .setLastName(userDto.getLastName()).setMobileNumber(userDto.getMobileNumber()
  ) .setPassword(userDto.getPassword()).setRoles(new
  HashSet<Role>(Arrays.asList(userRole))); this.mockMvc
  .perform(post("/api/v1/user/signup").contentType(MediaType.APPLICATION_JSON)
  .content(objectMapper.writeValueAsString(userDto)))
  .andExpect(status().isCreated()).andExpect(jsonPath("$.email",
  is(userDto.getEmail()))) .andExpect(jsonPath("$.password",
  is(userDto.getPassword()))) .andExpect(jsonPath("$.fisrtName",
  is(userDto.getFirstName())));
  
  }
  
  }
 **/ 
 