package com.bank.customermanagement.customer_management_service.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bank.customermanagement.customer_management_service.dto.TransactionDTO;
import com.bank.customermanagement.customer_management_service.dto.TransactionRequest;
import com.bank.customermanagement.customer_management_service.model.Account;
import com.bank.customermanagement.customer_management_service.model.Transaction;
import com.bank.customermanagement.customer_management_service.repository.AccountRepository;
import com.bank.customermanagement.customer_management_service.repository.TransactionRepository;
import com.bank.customermanagement.customer_management_service.service.TransactionService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
	

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository,
                              AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }
    
    @Override
    public String processTransaction(TransactionRequest request) {

        Account account = accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found")); // account id // SELECT * FROM  `account` AS i WHERE i.account_id = 1

        Double currentBalance = account.getBalance();

        if ("DEBIT".equalsIgnoreCase(request.getTransactionType())) {

            if (currentBalance < request.getAmount()) {
                throw new RuntimeException("Insufficient balance");
            }

            account.setBalance(currentBalance - request.getAmount());

        } else if ("CREDIT".equalsIgnoreCase(request.getTransactionType())) {

            account.setBalance(currentBalance + request.getAmount());

        } else {
            throw new RuntimeException("Invalid transaction type");
        }

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setTransactionType(request.getTransactionType());
        transaction.setAmount(request.getAmount());
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setReferenceNumber("TXN" + System.currentTimeMillis());

        transactionRepository.save(transaction);

        return "Transaction successful. Updated Balance: " + account.getBalance();
    }

    @Override
    public List<Transaction> getTransactionsByAccount(Long accountId) {
    	
    	List<Transaction> ob = null;
    	try {
    	 ob =  transactionRepository.findByAccountAccountId(accountId);
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
        return ob;
    }

    public List<TransactionDTO> getTransactionDTOs(Long accountId) {
        List<Transaction> transactions = transactionRepository
                .findByAccountAccountId(accountId);

        return transactions.stream().map(t -> {
            TransactionDTO dto = new TransactionDTO();
            dto.setTransactionId(t.getTransactionId());
            dto.setAmount(t.getAmount());
            dto.setTransactionType(t.getTransactionType());
            dto.setAccountNumber(t.getAccount().getAccountNumber());
            return dto;
        }).toList();
    }

}
