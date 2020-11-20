package com.busreseravtionsystem.busreservation.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.busreseravtionsystem.busreservation.model.user.Role;
import com.busreseravtionsystem.busreservation.model.user.UserRole;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByRole(UserRole userRole);

}
