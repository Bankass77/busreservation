package com.busreseravtionsystem.busreservation.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.busreseravtionsystem.busreservation.model.user.User;



public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

}
