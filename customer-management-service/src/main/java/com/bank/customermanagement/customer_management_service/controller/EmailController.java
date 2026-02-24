package com.bank.customermanagement.customer_management_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.customermanagement.customer_management_service.service.EmailService;

@RestController
@RequestMapping("/email")
public class EmailController {

	@Autowired
	private EmailService emailService;

	@GetMapping("/send")
	public String sendMail() {
		emailService.sendSimpleMail("vidhya98.sri@gmail.com", "Test Email", "Hello Vidya, Mail sent successfully!");
		return "Email sent successfully!";
	}

	@GetMapping("/{accountId}")
	public String sendTransactionEmail(@PathVariable Long accountId, @RequestParam String email) throws Exception {

		emailService.sendTransactionDetails(accountId, email);
		return "Transaction details email sent successfully!";
	}

}
