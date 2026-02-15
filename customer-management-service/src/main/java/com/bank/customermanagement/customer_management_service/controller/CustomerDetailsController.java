package com.bank.customermanagement.customer_management_service.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.customermanagement.customer_management_service.dto.CustomerRequestDto;
import com.bank.customermanagement.customer_management_service.service.CustomerDetailsService;

@RestController // @Controller + @ResponseBody //REST web services
@RequestMapping("/api/bank")
public class CustomerDetailsController {

	private static final Logger logger = LogManager.getLogger(CustomerDetailsController.class);

	@Autowired
	CustomerDetailsService customerDetailsService;

	@GetMapping("/info")
	public String bankinfo() {

		String response = customerDetailsService.bankdetails();

		return response;

	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/createcustomer")
	public ResponseEntity<String> createCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
		
		 logger.info("POST /api/customers called");

		Long customerId = customerDetailsService.createCustomer(customerRequestDto);

		return ResponseEntity.status(HttpStatus.CREATED).body("Customer created successfully with ID: " + customerId);
	}

}
