package com.bank.customermanagement.customer_management_service.service;

import com.bank.customermanagement.customer_management_service.dto.CustomerRequestDto;

public interface CustomerDetailsService {

	String bankdetails();

	Long createCustomer(CustomerRequestDto customerRequestDto);

}
