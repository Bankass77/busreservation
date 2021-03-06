package com.busreseravtionsystem.busreservation.repository.bus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.busreseravtionsystem.busreservation.model.bus.Agency;
import com.busreseravtionsystem.busreservation.model.bus.Bus;


@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {

	/**
	 * 
	 * @param busCode
	 * @return
	 */
	Bus findBusByCode(String busCode);

	/**
	 * 
	 * @param busCode
	 * @param agency
	 * @return
	 */
	Bus findBusByCodeAndAgency(String busCode, Agency agency);

}
