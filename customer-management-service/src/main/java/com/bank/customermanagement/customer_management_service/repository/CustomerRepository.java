package com.bank.customermanagement.customer_management_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.customermanagement.customer_management_service.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	boolean existsByCustomerCode(String customerCode);

}
