package com.bank.customermanagement.customer_management_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.customermanagement.customer_management_service.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username); // SELECT * FROM users AS i WHERE i.username = 'raja'

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
