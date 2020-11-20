package com.busreseravtionsystem.busreservation.controller.v1.ui;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.busreseravtionsystem.busreservation.controller.v1.command.AdminSignupFormCommand;
import com.busreseravtionsystem.busreservation.dto.bus.AgencyDto;
import com.busreseravtionsystem.busreservation.dto.user.UserDto;
import com.busreseravtionsystem.busreservation.service.bus.BusReservationService;
import com.busreseravtionsystem.busreservation.service.user.UserService;

@Controller
public class AdminController {

	@Autowired
	BusReservationService busReservationService;

	@Autowired
	@Qualifier("userService")
	UserService userService;

	@GetMapping(value = { "/", "/login" })
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
		ModelAndView modelAndView= new ModelAndView("login");

		if (error !=null) {
			modelAndView.addObject("error", "Invalid Username and password!");
		}
		
		if (logout !=null) {
			modelAndView.addObject("message", "You're been logout out successfully.");
		}
		return modelAndView;
	}

	@GetMapping(value = "/signup")
	public ModelAndView signup() {

		ModelAndView modelAndView = new ModelAndView("signup");
		modelAndView.addObject("adminSignupFormData", new AdminSignupFormCommand());

		return modelAndView;
	}

	@PostMapping(value = "/signup")
	public ModelAndView createNewAdminUser(
			@Valid @ModelAttribute("adminSignupFormData") AdminSignupFormCommand adminSignupFormCommand,
			BindingResult result) {

		ModelAndView modelAndView = new ModelAndView("signup");

		if (result.hasErrors()) {

			return modelAndView;

		} else {
			try {
				UserDto newUser = registerAdmin(adminSignupFormCommand);
			} catch (Exception e) {
				result.rejectValue("email", "error.adminSignupFormCommand", e.getMessage());
				return modelAndView;
			}
		}

		return new ModelAndView("login");

	}

	private UserDto registerAdmin(@Valid AdminSignupFormCommand adminSignupFormCommand) {

		UserDto userDto = new UserDto()
				.setEmail(adminSignupFormCommand.getEmail())
				.setFirstName(adminSignupFormCommand.getFirstName())
				.setLastName(adminSignupFormCommand.getLastName())
				.setPassword(adminSignupFormCommand.getPassword())
				.setMobileNumber(adminSignupFormCommand.getMobileNumber())
				.setAdmin(true);
		UserDto admiDto = userService.signup(userDto);
		AgencyDto agencyDto = new AgencyDto().setDetails(adminSignupFormCommand.getAgencyDetails())
				.setName(adminSignupFormCommand.getAgencyName()).setUserOwner(admiDto);
		busReservationService.addAgency(agencyDto);
		return admiDto;
	}
	
	@GetMapping(value = {"/logout"})
	private String logout() {
		SecurityContextHolder.getContext().setAuthentication(null);
		return "redirect:login";
		
	}
	
	@GetMapping(value = "/home")
	public String home() {
		
		return "redirect:dashboard";
	}
}
