package com.bank.customermanagement.customer_management_service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.bank.customermanagement.customer_management_service.model.Transaction;
import com.bank.customermanagement.customer_management_service.service.EmailService;
import com.bank.customermanagement.customer_management_service.service.TransactionService;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private TransactionService transactionService;

	@Value("${spring.mail.username}")
	private String fromEmail;

	@Override
	public void sendSimpleMail(String toEmail, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(fromEmail);
		message.setTo(toEmail);
		message.setSubject(subject);
		message.setText(body);

		mailSender.send(message);
	}

	public void sendTransactionDetails(Long accountId, String toEmail) throws Exception {

		List<Transaction> transactions = transactionService.getTransactionsByAccount(accountId);

		String htmlContent = buildHtmlTable(transactions);

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setFrom(fromEmail);
		helper.setTo(toEmail);
		helper.setSubject("Transaction Details - Account ID: " + accountId);
		helper.setText(htmlContent, true);

		mailSender.send(message);
	}

	private String buildHtmlTable(List<Transaction> transactions) {

		StringBuilder sb = new StringBuilder();

		sb.append("<h2>Transaction Details</h2>");
		sb.append("<table border='1' cellpadding='5' cellspacing='0'>");
		sb.append("<tr>");
		sb.append("<th>Transaction ID</th>");
		sb.append("<th>Type</th>");
		sb.append("<th>Amount</th>");
		sb.append("<th>Date</th>");
		sb.append("<th>Reference</th>");
		sb.append("</tr>");

		for (Transaction t : transactions) {
			sb.append("<tr>");
			sb.append("<td>").append(t.getTransactionId()).append("</td>");
			sb.append("<td>").append(t.getTransactionType()).append("</td>");
			sb.append("<td>").append(t.getAmount()).append("</td>");
			sb.append("<td>").append(t.getTransactionDate()).append("</td>");
			sb.append("<td>").append(t.getReferenceNumber()).append("</td>");
			sb.append("</tr>");
		}

		sb.append("</table>");

		return sb.toString();
	}

}
