package com.busreseravtionsystem.busreservation.repository.bus;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.busreseravtionsystem.busreservation.model.bus.Agency;
import com.busreseravtionsystem.busreservation.model.bus.Bus;
import com.busreseravtionsystem.busreservation.model.bus.Stop;
import com.busreseravtionsystem.busreservation.model.bus.Trip;

public interface TripRepository extends JpaRepository<Trip, Long> {

	/**
	 * 
	 * @param agency
	 * @return
	 */
	Trip findTripByAgency(Agency agency);

	/**
	 * 
	 * @param stopSource
	 * @param stopDest
	 * @param bus
	 * @return
	 */
	Trip findBySourceStopAndDestStopAndBus(Stop stopSource, Stop stopDest, Bus bus);

	/**
	 * 
	 * @param sourceStop
	 * @param destStop
	 * @return
	 */
	List<Trip> findAllBySourceStopAndDestStop(Stop sourceStop, Stop destStop);
}
