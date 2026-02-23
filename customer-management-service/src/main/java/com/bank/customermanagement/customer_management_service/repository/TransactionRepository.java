package com.bank.customermanagement.customer_management_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bank.customermanagement.customer_management_service.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccountAccountId(Long accountId);
    
    @Query("SELECT t FROM Transaction t JOIN FETCH t.account WHERE t.account.accountId = :accountId")
    List<Transaction> findTransactionsWithAccount(@Param("accountId") Long accountId);

}
