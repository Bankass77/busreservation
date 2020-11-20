/**package com.busreseravtionsystem.busreservation.controller.v1.api;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.busreseravtionsystem.busreservation.controller.v1.request.BookTicketRequest;
import com.busreseravtionsystem.busreservation.controller.v1.request.GetripSchedulesRequest;
import com.busreseravtionsystem.busreservation.dto.bus.TicketDto;
import com.busreseravtionsystem.busreservation.dto.bus.TripDto;
import com.busreseravtionsystem.busreservation.dto.bus.TripScheduleDto;
import com.busreseravtionsystem.busreservation.dto.response.Response;
import com.busreseravtionsystem.busreservation.dto.user.UserDto;
import com.busreseravtionsystem.busreservation.service.bus.BusReservationService;
import com.busreseravtionsystem.busreservation.service.user.UserService;
import com.busreseravtionsystem.busreservation.util.DateUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping(value = "/api/v1/reservation")
@Api(value = "bsr-application", description = "Operations pertaining to agency management and ticket issue int the BSR application.")
public class BusReservationSystemController {

	@Autowired
	private BusReservationService busReservationService;

	@Autowired
	private UserService userService;

	@GetMapping("/stops")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	public Response<Object> getAllStop() {

		return Response.ok().setPayload(busReservationService.getAllStopDtos());

	}

	@GetMapping("tripsbystops")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	public Response<Object> getripByStops(@RequestBody @Valid GetripSchedulesRequest getTripSchedulesRequest) {

		List<TripScheduleDto> dtos = busReservationService.getAvailableTripSchedules(
				getTripSchedulesRequest.getSourceStop(), getTripSchedulesRequest.getArrivalStop(),
				getTripSchedulesRequest.getTripDate().toString());
		if (!dtos.isEmpty()) {

			return Response.ok().setPayload(dtos);
		}
		return Response.notFound()
				.setErrors(String.format(
						"No trips between source stop- '%s' and destination stop -'%s' are available at this time.",
						getTripSchedulesRequest.getSourceStop(), getTripSchedulesRequest.getArrivalStop()));

	}

	@GetMapping("/tripSchedules")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	public Response<Object> getTripSchedules(@RequestBody @Valid GetripSchedulesRequest getripSchedulesRequest) {

		List<TripScheduleDto> tripScheduleDtos = busReservationService.getAvailableTripSchedules(
				getripSchedulesRequest.getSourceStop(), getripSchedulesRequest.getArrivalStop(),
				DateUtils.formattedDate(getripSchedulesRequest.getTripDate()));

		if (!tripScheduleDtos.isEmpty()) {

			return Response.ok().setPayload(tripScheduleDtos);
		}
		return Response.notFound().setErrors(String.format(
				"No trips between source stop- '%s' and destination stop -'%s' are available at this date.\",\n"
						+ "						getTripSchedulesRequest.getSourceStop(), getTripSchedulesRequest.getArrivalStop())",
				getripSchedulesRequest.getSourceStop(), getripSchedulesRequest.getArrivalStop(),
				DateUtils.formattedDate(getripSchedulesRequest.getTripDate())));

	}

	@PostMapping("/bookticket")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	public Response<Object> bookTicket(@RequestBody @Valid BookTicketRequest bookTicketRequest) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String email = (String) authentication.getPrincipal();

		Optional<UserDto> userEmail = Optional.ofNullable(userService.findUserByEmail(email));

		if (userEmail.isPresent()) {

			Optional<TripDto> TripDtoOptional = Optional
					.ofNullable(busReservationService.getTripById(bookTicketRequest.getTripId()));

			if (TripDtoOptional.isPresent()) {

				Optional<TripScheduleDto> tripScheduleDtoOptional = Optional
						.ofNullable(busReservationService.getTripScheduleDto(TripDtoOptional.get(),
								DateUtils.formattedDate(bookTicketRequest.getTripDate()), true));
				if (tripScheduleDtoOptional.isPresent()) {

					Optional<TicketDto> ticketOptional = Optional.ofNullable(
							busReservationService.bookTicket(tripScheduleDtoOptional.get(), userEmail.get()));

					if (ticketOptional.isPresent()) {

						return Response.ok().setPayload(ticketOptional.get());
					}

				}

			}
		}

		return Response.badRequest().setErrors("Unable to process ticket booking.");

	}
}
**/
