package com.busreseravtionsystem.busreservation.controller.v1.ui;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.busreseravtionsystem.busreservation.controller.v1.command.AgencyFormCommand;
import com.busreseravtionsystem.busreservation.controller.v1.command.BusFormCommand;
import com.busreseravtionsystem.busreservation.controller.v1.command.PasswordFormCommand;
import com.busreseravtionsystem.busreservation.controller.v1.command.ProfileFormCommand;
import com.busreseravtionsystem.busreservation.controller.v1.command.TripFormCommand;
import com.busreseravtionsystem.busreservation.dto.bus.AgencyDto;
import com.busreseravtionsystem.busreservation.dto.bus.BusDto;
import com.busreseravtionsystem.busreservation.dto.bus.StopDto;
import com.busreseravtionsystem.busreservation.dto.bus.TripDto;
import com.busreseravtionsystem.busreservation.dto.user.UserDto;
import com.busreseravtionsystem.busreservation.service.bus.BusReservationService;
import com.busreseravtionsystem.busreservation.service.user.UserService;
import com.sun.net.httpserver.Authenticator.Result;

@Controller
public class DashbordController {

	@Autowired
	private UserService userService;

	@Autowired
	private BusReservationService busReservationService;

	@GetMapping("/dashboard")
	public ModelAndView dashboard() {

		ModelAndView modelAndView = new ModelAndView("dashboard");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDto userDto = userService.findUserByEmail(authentication.getName());
		modelAndView.addObject("currentUser", userDto);
		modelAndView.addObject("userName", userDto.getFullName());
		return modelAndView;
	}

	@GetMapping(value = "/agency")
	public ModelAndView agencyDetails() {

		ModelAndView modelAndView = new ModelAndView("agency");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDto userDto = userService.findUserByEmail(authentication.getName());
		AgencyDto agencyDto = busReservationService.getAgency(userDto);
		AgencyFormCommand agencyFormCommand = new AgencyFormCommand();
		agencyFormCommand.setAgencyDetails(agencyDto.getDetails()).setAgencyName(agencyDto.getName());

		modelAndView.addObject("agencyFormCommand", agencyFormCommand);
		modelAndView.addObject("agency", agencyDto);
		modelAndView.addObject("userName", userDto.getFullName());
		return modelAndView;

	}

	@PostMapping("/agency")
	public ModelAndView updateAgency(@Valid @ModelAttribute("agencyFormData") AgencyFormCommand agencyFormCommand,
			BindingResult result) {

		ModelAndView modelAndView = new ModelAndView("agency");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDto userDto = userService.findUserByEmail(authentication.getName());
		AgencyDto agencyDto = busReservationService.getAgency(userDto);
		modelAndView.addObject("agency", agencyDto);
		modelAndView.addObject("userName", userDto.getFullName());

		if (!result.hasErrors()) {
			if (agencyDto != null) {

				agencyDto.setName(agencyFormCommand.getAgencyName()).setDetails(agencyFormCommand.getAgencyDetails());
				busReservationService.updateAgency(agencyDto, null);
			}
		}
		return modelAndView;

	}

	@GetMapping(value = "/bus")
	public ModelAndView busDetails() {

		ModelAndView modelAndView = new ModelAndView("bus");

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDto userDto = userService.findUserByEmail(authentication.getName());
		AgencyDto agencyDto = busReservationService.getAgency(userDto);
		modelAndView.addObject("agency", agencyDto);
		modelAndView.addObject("busFormData", new BusFormCommand());
		modelAndView.addObject("userName", userDto.getFullName());

		return modelAndView;

	}

	@PostMapping("/bus")
	public ModelAndView addNewBus(@Valid @ModelAttribute("busFormData") BusFormCommand busFormCommand,
			BindingResult result) {

		ModelAndView modelAndView = new ModelAndView("bus");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		UserDto userDto = userService.findUserByEmail(authentication.getName());

		AgencyDto agencyDto = busReservationService.getAgency(userDto);

		modelAndView.addObject("agency", agencyDto);
		modelAndView.addObject("userName", userDto.getFullName());

		if (!result.hasErrors()) {
			try {

				BusDto busDto = new BusDto().setCode(busFormCommand.getCode()).setCapacity(busFormCommand.getCapacity())
						.setMake(busFormCommand.getMake());
				AgencyDto agencyDto2 = busReservationService.updateAgency(agencyDto, busDto);
				modelAndView.addObject("agency", agencyDto2);
				modelAndView.addObject("busFormData", new BusFormCommand());

			} catch (Exception e) {
				result.rejectValue("code", "error.busFormCommand", e.getMessage());
			}
		}
		return modelAndView;

	}

	@GetMapping(value = "/trip")
	public ModelAndView tripDetails() {

		ModelAndView modelAndView = new ModelAndView("trip");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDto userDto = userService.findUserByEmail(authentication.getName());
		AgencyDto agencyDto = busReservationService.getAgency(userDto);
		Set<StopDto> stopDtos = busReservationService.getAllStopDtos();
		List<TripDto> dtos = busReservationService.getAgencyTrip(agencyDto.getCode());
		modelAndView.addObject("userName", userDto.getFullName());
		modelAndView.addObject("agency", agencyDto);
		modelAndView.addObject("stops", stopDtos);
		modelAndView.addObject("trips", dtos);
		modelAndView.addObject("tripFormData", new TripFormCommand());

		return modelAndView;

	}

	@PostMapping(value = "/trip")
	public ModelAndView addNewTrip(@Valid @ModelAttribute("tripFormData") TripFormCommand tripFormCommand,
			BindingResult result) {

		ModelAndView modelAndView = new ModelAndView("trip");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		UserDto userDto = userService.findUserByEmail(authentication.getName());
		AgencyDto agencyDto = busReservationService.getAgency(userDto);

		Set<StopDto> stopDtos = busReservationService.getAllStopDtos();
		List<TripDto> tripDtos = busReservationService.getAgencyTrip(agencyDto.getCode());
		modelAndView.addObject("stops", stopDtos);
		modelAndView.addObject("agency", agencyDto);
		modelAndView.addObject("userName", userDto.getFullName());
		modelAndView.addObject("trips", tripDtos);
		if (!result.hasErrors()) {
			try {
				TripDto tripDto = new TripDto().setAgencyCode(agencyDto.getCode())
						.setBusCode(tripFormCommand.getBusCode())
						.setDestinationStopCode(tripFormCommand.getSourceStop())
						.setSourceStopCode(tripFormCommand.getArrivalStop()).setFare(tripFormCommand.getTripFare())
						.setJourneyTime(tripFormCommand.getTripDuration()).setAgencyCode(agencyDto.getCode());
				busReservationService.addTrip(tripDto);

				tripDtos = busReservationService.getAgencyTrip(agencyDto.getCode());
				modelAndView.addObject("trips", tripDtos);
				modelAndView.addObject("tripFormData", new TripFormCommand());
			} catch (Exception e) {
				result.rejectValue("sourceStop", "error.tripFormData", e.getMessage());
			}

		}

		return modelAndView;
	}

	@GetMapping(value = "/profile")
	public ModelAndView getUserProfile() {

		ModelAndView modelAndView = new ModelAndView("profile");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDto userDto = userService.findUserByEmail(authentication.getName());
		ProfileFormCommand profileFormCommand = new ProfileFormCommand().setFirstName(userDto.getFirstName())
				.setLastname(userDto.getLastName()).setMobileNumber(userDto.getMobileNumber());
		PasswordFormCommand passwordFormCommand = new PasswordFormCommand().setEmail(userDto.getEmail())
				.setPassword(userDto.getPassword());
		modelAndView.addObject("passwordForm", passwordFormCommand);
		modelAndView.addObject("profileForm", profileFormCommand);
		modelAndView.addObject("userName", userDto.getFullName());
		return modelAndView;
	}

	@PostMapping(value = "/profile")
	public ModelAndView addNewProfile(
			@Valid @ModelAttribute("profileFormCommand") ProfileFormCommand profileFormCommand, BindingResult result) {

		ModelAndView modelAndView = new ModelAndView("profile");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDto userDto = userService.findUserByEmail(authentication.getName());
		PasswordFormCommand passwordFormCommand = new PasswordFormCommand().setEmail(userDto.getEmail())
				.setPassword(userDto.getPassword());
		modelAndView.addObject("paswordForm", passwordFormCommand);
		modelAndView.addObject("userName", userDto.getFullName());

		if (!result.hasErrors()) {
			userDto.setFirstName(profileFormCommand.getFirstName()).setLastName(profileFormCommand.getLastname())
					.setMobileNumber(profileFormCommand.getMobileNumber());
			modelAndView.addObject("profileForm", profileFormCommand);
			modelAndView.addObject("userName", userDto.getFullName());
		}

		return modelAndView;

	}

	@PostMapping(value = "/password")
	public ModelAndView changeuserPassword(
			@Valid @ModelAttribute("passwordFrm") PasswordFormCommand passwordFormCommand, BindingResult result) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		UserDto userDto = userService.findUserByEmail(authentication.getName());
		if (!result.hasErrors()) {

			ModelAndView modelAndView = new ModelAndView("profile");
			ProfileFormCommand profileFormCommand = new ProfileFormCommand().setFirstName(userDto.getFirstName())
					.setLastname(userDto.getLastName()).setMobileNumber(userDto.getMobileNumber());
			modelAndView.addObject("profileForm", profileFormCommand);
			modelAndView.addObject("userName", userDto.getFullName());
			return modelAndView;
		} else {

			userService.changePassword(userDto, passwordFormCommand.getPassword());
			return new ModelAndView("login");
		}
	}
}
