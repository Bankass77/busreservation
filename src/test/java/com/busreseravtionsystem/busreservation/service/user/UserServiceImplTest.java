/*
 * package com.busreseravtionsystem.busreservation.service.user;
 * 
 * import static org.junit.Assert.assertNotNull; import static
 * org.junit.jupiter.api.Assertions.assertAll; import static
 * org.junit.jupiter.api.Assertions.assertEquals;
 * 
 * import java.util.Arrays; import java.util.HashSet; import java.util.Set;
 * 
 * import org.junit.jupiter.api.DisplayName; import org.junit.jupiter.api.Test;
 * import org.junit.jupiter.api.extension.ExtendWith; import
 * org.mockito.InjectMocks; import org.mockito.Mock; import
 * org.mockito.junit.jupiter.MockitoExtension; import
 * org.springframework.boot.test.context.SpringBootTest;
 * 
 * import com.busreseravtionsystem.busreservation.dto.user.RoleDto; import
 * com.busreseravtionsystem.busreservation.dto.user.UserDto; import
 * com.busreseravtionsystem.busreservation.model.user.Role; import
 * com.busreseravtionsystem.busreservation.model.user.User; import
 * com.busreseravtionsystem.busreservation.model.user.UserRole; import
 * com.busreseravtionsystem.busreservation.repository.user.RoleRepository;
 * 
 * @ExtendWith(MockitoExtension.class)
 * 
 * @SpringBootTest class UserServiceImplTest {
 * 
 * @Mock RoleRepository roleRepository;
 * 
 * @InjectMocks UserServiceImpl userService;
 * 
 * @Test
 * 
 * @DisplayName("test user signup") void testSignup() { UserDto userDto = new
 * UserDto();
 * 
 * Set<RoleDto> roleDtos = new HashSet<RoleDto>(); roleDtos.add(new
 * RoleDto().setName(UserRole.ADMIN.getValue()));
 * userDto.setAdmin(true).setEmail("admin6@example.com").setFirstName("admin6").
 * setLastName("admin6")
 * .setMobileNumber("0768965423").setPassword("admin6").setRolesDtos(roleDtos);
 * 
 * Role userRole = new Role(); userRole = new Role().setRole(UserRole.ADMIN);
 * User user = new
 * User().setEmail(userDto.getEmail()).setFirstName(userDto.getFirstName())
 * .setLastName(userDto.getLastName()).setMobileNumber(userDto.getMobileNumber()
 * ) .setPassword(userDto.getPassword()).setRoles(new
 * HashSet<Role>(Arrays.asList(userRole))); userService.signup(userDto);
 * 
 * System.out.println("User test signup :" + " " + user);
 * assertAll("test of sihnup", ()->assertNotNull(userService.signup(userDto)));
 * assertAll("Test of signup", () -> assertNotNull(user));
 * assertAll("Test of signup", () -> assertEquals(user, userDto));
 * 
 * }
 * 
 * }
 */