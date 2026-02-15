package com.bank.customermanagement.customer_management_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.customermanagement.customer_management_service.constants.ERole;
import com.bank.customermanagement.customer_management_service.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
