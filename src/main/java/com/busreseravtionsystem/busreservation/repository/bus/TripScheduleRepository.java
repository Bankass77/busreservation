package com.busreseravtionsystem.busreservation.repository.bus;

import org.springframework.data.jpa.repository.JpaRepository;

import com.busreseravtionsystem.busreservation.model.bus.Trip;
import com.busreseravtionsystem.busreservation.model.bus.TripSchedule;

public interface TripScheduleRepository extends JpaRepository<TripSchedule, Long> {

	/**
	 * 
	 * @param tripDetail
	 * @param tripDate
	 * @return
	 */
	TripSchedule findTripScheduleByTripDetailAndTripDate(Trip tripDetail, String tripDate);

}
