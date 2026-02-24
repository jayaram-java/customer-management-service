package com.bank.customermanagement.customer_management_service.config;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bank.customermanagement.customer_management_service.service.TransactionService;

@Component
public class TransactionScheduler {

	@Autowired
	private TransactionService transactionService;

	@Scheduled(fixedRate = 60000) // Runs every 60 seconds
	public void runEveryMinute() {
		System.out.println("Scheduler running: " + LocalDateTime.now());
	}
}
