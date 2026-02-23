package com.bank.customermanagement.customer_management_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.customermanagement.customer_management_service.dto.TransactionDTO;
import com.bank.customermanagement.customer_management_service.dto.TransactionRequest;
import com.bank.customermanagement.customer_management_service.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

	private final TransactionService transactionService;

	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@PostMapping
	public ResponseEntity<String> createTransaction(@RequestBody TransactionRequest request) {

        String response = null;
        try {
            response = transactionService.processTransaction(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(response);
	}

	@GetMapping("/account/{accountId}")
	public List<TransactionDTO> getTransactions(@PathVariable Long accountId) {
	    return transactionService.getTransactionDTOs(accountId);
	}
}
