package com.busreseravtionsystem.busreservation.repository.bus;

import org.springframework.data.jpa.repository.JpaRepository;

import com.busreseravtionsystem.busreservation.model.bus.Agency;
import com.busreseravtionsystem.busreservation.model.user.User;

public interface AgencyRepository extends JpaRepository<Agency, Long> {

	/**
	 * 
	 * @param agencyCode
	 * @return agencyCode
	 */
	Agency getAgencyByCode(String agencyCode);

	/**
	 * 
	 * @param name
	 * @return name
	 */
	Agency findAgencyByName(String name);

	/**
	 * 
	 * @param owner
	 * @return owner
	 */
	Agency findAgencyByUser(User owner);

}
