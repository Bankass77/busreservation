/*
 * package com.busreseravtionsystem.busreservation.controller.v1.ui;
 * 
 * import static
 * org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; import
 * static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
 * 
 * import org.apache.catalina.security.SecurityConfig; import
 * org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.Test; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest; import
 * org.springframework.context.annotation.Import; import
 * org.springframework.http.MediaType; import
 * org.springframework.security.test.context.support.WithMockUser; import
 * org.springframework.security.web.FilterChainProxy; import
 * org.springframework.test.context.ActiveProfiles; import
 * org.springframework.test.web.servlet.MockMvc; import
 * org.springframework.test.web.servlet.setup.MockMvcBuilders;
 * 
 * import com.busreseravtionsystem.busreservation.ServletInitializer; import
 * com.busreseravtionsystem.busreservation.controller.v1.api.UserController;
 * import
 * com.busreseravtionsystem.busreservation.security.MultiHttpSecurityConfig;
 * 
 * @WebMvcTest(controllers = UserController.class)
 * 
 * @ActiveProfiles("DEV")
 * 
 * @Import(MultiHttpSecurityConfig.class) class AdminControllerTest {
 * 
 * @Autowired private MockMvc mockMvc;
 * 
 * @Autowired private FilterChainProxy filterChainProxy;
 * 
 * @BeforeEach public void setup() { mockMvc =
 * MockMvcBuilders.standaloneSetup(ServletInitializer.class,
 * SecurityConfig.class) .addFilters(filterChainProxy).build(); }
 * 
 * @Test // @WithMockUser(username = "admin@€xample.com", password = "admin1",
 * roles = // "ADMIN") public void logintTest() throws Exception {
 * mockMvc.perform(post("/login").param("email",
 * "admin@example.com").param("password",
 * "$2y$10$egDXy2IvBX.LkGT2WOqQQuFnV745aQZdIktcqTfJC.XV9o/woZwTe"))
 * .andExpect(status().isOk()); }
 * 
 * @Test public void testInvalidLogin() throws Exception {
 * mockMvc.perform(post("/login").param("email", "admin@example.com"))
 * .andExpect(model().attributeHasFieldErrors("email", "password"))
 * .andExpect(view().name("redirect:/login"));
 * 
 * }
 * 
 * @Test public void testSuccessfulLogin() throws Exception {
 * mockMvc.perform(post("/login").param("email",
 * "admin3@example.com").param("password",
 * "$2a$10$JPtuXohAOktdzNH4/hISYOQXZjPLuge4Y.q0oea2go.xCMYLLYgqu")).andExpect(
 * status().isOk()) .andExpect(view().name("login"));
 * 
 * }
 * 
 * @Test public void testDoesNotExistLogin() throws Exception {
 * mockMvc.perform(post("/login").param("email",
 * "admin2@admin.com").param("password", "some random pass"))
 * .andExpect(status().isOk()).andExpect(model().attribute("STATUS",
 * "DOES NOT EXIST")) .andExpect(view().name("redirect:/login")); }
 * 
 * }
 */