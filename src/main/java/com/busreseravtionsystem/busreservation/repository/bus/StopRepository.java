package com.busreseravtionsystem.busreservation.repository.bus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.busreseravtionsystem.busreservation.model.bus.Stop;


@Repository
public interface StopRepository extends JpaRepository<Stop, Long> {

	/**
	 * 
	 * @param stopCode
	 * @return
	 */
	Stop findStopByCode(String stopCode);

	/**
	 * 
	 * @param stopName
	 * @return
	 */
	Stop findStopByName(String stopName);

}
