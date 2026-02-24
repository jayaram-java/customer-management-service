package com.bank.customermanagement.customer_management_service.service;

public interface EmailService {

	public void sendSimpleMail(String toEmail, String subject, String body);
	
	public void sendTransactionDetails(Long accountId, String toEmail) throws Exception;

}
