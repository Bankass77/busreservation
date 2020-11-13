/*
 * package com.busreseravtionsystem.busreservation.service.user;
 * 
 * import java.util.ArrayList; import java.util.Arrays; import
 * java.util.HashSet; import java.util.List; import java.util.Set; import
 * java.util.stream.Collectors;
 * 
 * import org.junit.jupiter.api.BeforeEach; import
 * org.junit.jupiter.api.DisplayName; import org.junit.jupiter.api.Test; import
 * org.junit.jupiter.api.extension.ExtendWith; import org.mockito.InjectMocks;
 * import org.mockito.Mock; import org.mockito.junit.jupiter.MockitoExtension;
 * import org.modelmapper.ModelMapper;
 * 
 * import com.busreseravtionsystem.busreservation.dto.user.RoleDto; import
 * com.busreseravtionsystem.busreservation.dto.user.UserDto; import
 * com.busreseravtionsystem.busreservation.model.user.Role; import
 * com.busreseravtionsystem.busreservation.model.user.User; import
 * com.busreseravtionsystem.busreservation.model.user.UserRole; import
 * com.busreseravtionsystem.busreservation.repository.user.RoleRepository;
 * import
 * com.busreseravtionsystem.busreservation.repository.user.UserRepository;
 * 
 * @ExtendWith(MockitoExtension.class) class UserServiceImplTest {
 * 
 * @Mock UserRepository userRepository;
 * 
 * @Mock RoleRepository roleRepository;
 * 
 * @InjectMocks UserServiceImpl userService;
 * 
 * private List<User> users;
 * 
 * @BeforeEach void setUp() throws Exception { users = new ArrayList<User>();
 * UserDto userDto = new UserDto(); userDto.setAdmin(true);
 * userDto.setEmail("admin2@example.com"); userDto.setFirstName("admin2");
 * userDto.setLastName("admin2"); userDto.setMobileNumber("0768965423");
 * userDto.setPassword("admin2"); Set<Role> roles = new HashSet<Role>();
 * 
 * Role role= new Role(); role=roleRepository.findByRole(UserRole.ADMIN);
 * roles.add(role); userDto.setRolesDtos(new HashSet<RoleDto> (new
 * HashSet<>()));
 * 
 * 
 * users.add(new
 * User().setEmail(userDto.getEmail()).setFirstName(userDto.getFirstName())
 * .setLastName(userDto.getLastName()).setMobileNumber(userDto.getMobileNumber()
 * ) .setPassword(userDto.getPassword()).setRoles(new
 * HashSet<Role>(Arrays.asList(role)))); //userService.updteProfile(userDto);
 * userService.signup(userDto); }
 * 
 * @Test
 * 
 * @DisplayName("test user signup") void testSignup() { UserDto userDto = new
 * UserDto(); userDto.setAdmin(true); userDto.setEmail("admin2@example.com");
 * userDto.setFirstName("admin2"); userDto.setLastName("admin2");
 * userDto.setMobileNumber("0768965423"); userDto.setPassword("admin2");
 * Set<RoleDto> rolesDtos= new HashSet<RoleDto>(); RoleDto roleDto= new
 * RoleDto(); roleDto.setName("ADMIN"); rolesDtos.add(roleDto);
 * userDto.setRolesDtos(rolesDtos);
 * 
 * Role userRole = new Role(); userRole =
 * roleRepository.findByRole(UserRole.ADMIN); User user = new
 * User().setEmail(userDto.getEmail()).setFirstName(userDto.getFirstName())
 * .setLastName(userDto.getLastName()).setMobileNumber(userDto.getMobileNumber()
 * ) .setPassword(userDto.getPassword()).setRoles(new
 * HashSet<Role>(Arrays.asList(userRole))); userRepository.save(user);
 * userService.signup(userDto);
 * 
 * }
 * 
 * @Test
 * 
 * @DisplayName("Find user email") void testFindUserByEmail() {
 * 
 * UserDto user= userService.findUserByEmail(users.get(0).getEmail());
 * 
 * }
 * 
 * 
 * @Test
 * 
 * @DisplayName("Test update profile") void testUpdteProfile() {
 * fail("Not yet implemented"); }
 * 
 * @Test
 * 
 * @DisplayName("Test change password") void testChangePassword() {
 * fail("Not yet implemented"); }
 * 
 * @Test
 * 
 * @DisplayName("Test get all users") void testGetAllUsers() {
 * fail("Not yet implemented"); }
 * 
 * }
 */