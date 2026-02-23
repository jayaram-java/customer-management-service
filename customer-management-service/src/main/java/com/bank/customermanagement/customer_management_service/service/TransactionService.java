package com.bank.customermanagement.customer_management_service.service;

import java.util.List;

import com.bank.customermanagement.customer_management_service.dto.TransactionDTO;
import com.bank.customermanagement.customer_management_service.dto.TransactionRequest;
import com.bank.customermanagement.customer_management_service.model.Transaction;

public interface TransactionService {

	public String processTransaction(TransactionRequest request);

	public List<Transaction> getTransactionsByAccount(Long accountId);
	
	public List<TransactionDTO> getTransactionDTOs(Long accountId);
}
